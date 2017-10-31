package com.kingdee.eas.hrp.sms.service.impl.sendcargo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.dao.customize.SendcargoDaoMapper;
import com.kingdee.eas.hrp.sms.dao.generate.SendcargoMapper;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.model.Sendcargo;
import com.kingdee.eas.hrp.sms.service.api.IWebService;
import com.kingdee.eas.hrp.sms.service.api.sendcargo.ISendcargoService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;
import com.kingdee.eas.hrp.sms.util.Environ;

@Service
public class SendcargoService extends BaseService implements ISendcargoService {

    @Resource
    IWebService IWebService;

    @Override
    public List<Map<String, Object>> getCode(String items) {

        SendcargoDaoMapper sendcargoDaoMapper = sqlSession.getMapper(SendcargoDaoMapper.class);
        List<String> list = new ArrayList<String>();
        String[] split = items.split("\\,");
        for (int i = 0; i < split.length; i++) {
            String id = split[i];
            list.add(id);
        }

        List<Map<String, Object>> map = sendcargoDaoMapper.selectInvoiceById(list);
        if (map == null || map.size() == 0) {
            throw new BusinessLogicRunTimeException("发货单无个体码数据");
        }
        return map;

    }

    @Override
    @Transactional
    public void sendCargoHrp(String items) {

        SendcargoDaoMapper sendcargoDaoMapper = sqlSession.getMapper(SendcargoDaoMapper.class);

        JSONArray lists = new JSONArray();

        String[] split = items.split("\\,");

        for (int i = 0; i < split.length; i++) {

            List<Map<String, Object>> item = sendcargoDaoMapper.selectSendCargoANDEntry(split[i]);
            JSONObject entry = new JSONObject();

            JSONObject json = new JSONObject();
            ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

            if (null != item) {

                for (int j = 0; j < item.size(); j++) {

                    JSONObject entrys = new JSONObject();

                    Map<String, Object> sendCargo = item.get(j);
                    if (sendCargo.get("type") != null && !sendCargo.get("type").equals("")) {
                        if (Integer.parseInt(sendCargo.get("type").toString()) == 1) {
                            throw new BusinessLogicRunTimeException("已发送到医院的发货单不能再次发送");
                        }
                    }

                    if (j == 0) {
                        // 表头
                        json.put("id", sendCargo.get("id"));
                        json.put("supplier", sendCargo.get("supplier"));
                        json.put("number", sendCargo.get("number"));
                    }
                    entrys.put("entryId", sendCargo.get("entryId"));
                    entrys.put("seq", sendCargo.get("seq"));
                    entrys.put("purOrder", sendCargo.get("orderId"));
                    entrys.put("purOrderEntry", sendCargo.get("orderEntryId"));
                    entrys.put("material", sendCargo.get("material"));
                    entrys.put("lot", sendCargo.get("lot"));
                    entrys.put("plotNum", sendCargo.get("dyBatchNum"));
                    entrys.put("innerCode", sendCargo.get("code"));
                    entrys.put("dyPrice", sendCargo.get("price"));
                    entrys.put("unit", sendCargo.get("unit"));
                    entrys.put("qty", sendCargo.get("actualQty"));
                    entrys.put("dyProDate", sendCargo.get("dyProDate"));
                    entrys.put("dyProduct", sendCargo.get("dyManufacturer"));
                    entrys.put("dyRegistNum", sendCargo.get("registrationNo"));
                    entrys.put("dyEffDate", sendCargo.get("effectiveDate"));
                    entrys.put("dyamount", sendCargo.get("amount"));
                    entrys.put("department_name", sendCargo.get("department_name"));
                    entrys.put("department", sendCargo.get("department"));

                    entrys.put("remark", "来自供应商平台发货单[" + sendCargo.get("number") + "]行号[" + sendCargo.get("seq"));
                    list.add(entrys);
                }

                entry.put("1", list);
                json.put("entry", entry);
                lists.add(json);
            }
        }

        String response = IWebService.webService(lists.toString(), "sms2hrpSendCargo");
        JSONObject rps = JSONObject.parseObject(response);
        if (rps == null || rps.equals("")) {
            throw new BusinessLogicRunTimeException("网络错误!请稍后再试");
        }

        if (rps.get("code").equals("200")) {
            PlatformTransactionManager txManager = Environ.getBean(PlatformTransactionManager.class);

            TransactionTemplate template = new TransactionTemplate(txManager);

            template.execute(new TransactionCallback<Object>() {

                @Override
                public Object doInTransaction(TransactionStatus status) {
                    SendcargoMapper sendcargoMapper = sqlSession.getMapper(SendcargoMapper.class);
                    Sendcargo sendcargo = new Sendcargo();
                    for (int i = 0; i < split.length; i++) {
                        sendcargo.setId(split[i]);
                        sendcargo.setType(Byte.parseByte("1"));
                        sendcargoMapper.updateByPrimaryKeySelective(sendcargo);
                    }
                    return "success";
                }
            });

        }
    }
}