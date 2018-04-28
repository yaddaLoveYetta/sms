package com.kingdee.eas.hrp.sms.service.impl.purreceival;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.dao.generate.PurReceivalEntryMapper;
import com.kingdee.eas.hrp.sms.dao.generate.PurReceivalMapper;
import com.kingdee.eas.hrp.sms.dao.generate.SendcargoMapper;
import com.kingdee.eas.hrp.sms.model.PurReceival;
import com.kingdee.eas.hrp.sms.model.PurReceivalEntry;
import com.kingdee.eas.hrp.sms.model.Sendcargo;
import com.kingdee.eas.hrp.sms.model.SendcargoExample;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.api.purreceival.IPurReceivalService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class PurReceivalService extends BaseService implements IPurReceivalService {

    @Resource
    ITemplateService iTemplateService;

    /**
     * 收货单同步接口-垃圾代码
     */
    @Override
    @Transactional
    public String purReceival(JSONArray jsonarray) {

        PurReceival purReceival = new PurReceival();
        PurReceivalEntry purReceivalEntry = new PurReceivalEntry();

        // add by yadda HRP收货单审核后，将供应商平台上关联的发货单设置为签收状态（完成状态）
        // 审核或审核后状态的发货单id
        Set<String> sendCarGoCheckIds = new HashSet<String>(8);
        // 审核前状态的发货单id
        Set<String> sendCarGoUnCheckIds = new HashSet<String>(8);

        for (int i = 0; i < jsonarray.size(); i++) {
            JSONObject jsonObject = jsonarray.getJSONObject(i);
            purReceival.setId(jsonObject.getString("id"));
            purReceival.setNumber(jsonObject.getString("number"));
            if (jsonObject.getDate("bizDate") != null) {
                purReceival.setBizDate(jsonObject.getDate("bizDate"));
            }
            purReceival.setBaseStatus(jsonObject.getByte("baseStatus"));
            // 发货单id-add by yadda
            purReceival.setSendCargoId(jsonObject.getString("sendCargoID"));

            purReceival.setSourceBillType(jsonObject.getString("sourceBillType"));
            purReceival.setSupplier(jsonObject.getString("supplier"));
            PurReceivalMapper purReceivalMapper = sqlSession.getMapper(PurReceivalMapper.class);

            iTemplateService.delItem(2021, jsonObject.getString("id"));
            purReceivalMapper.insertSelective(purReceival);

            JSONObject entry = (JSONObject) jsonObject.get("entry");
            JSONArray purEntryArray = (JSONArray) entry.get("1");
            for (int j = 0; j < purEntryArray.size(); j++) {
                JSONObject purEntryObject = purEntryArray.getJSONObject(j);
                purReceivalEntry.setId(purEntryObject.getString("id"));
                purReceivalEntry.setParent(purEntryObject.getString("parent"));
                purReceivalEntry.setSeq(purEntryObject.getInteger("seq"));

                // add by yadda HRP收货单审核后，将供应商平台上关联的发货单设置为签收状态
                if (jsonObject.getIntValue("baseStatus") >= 4) {
                    sendCarGoCheckIds.add(purEntryObject.getString("orderId"));
                } else {
                    sendCarGoUnCheckIds.add(purEntryObject.getString("orderId"));
                }


                purReceivalEntry.setOrderId(purEntryObject.getString("orderId"));
                purReceivalEntry.setOrderSeq(purEntryObject.getString("orderSeq"));
                purReceivalEntry.setMaterial(purEntryObject.getString("material"));
                purReceivalEntry.setLot(purEntryObject.getString("lot"));
                purReceivalEntry.setInnercode(purEntryObject.getString("innercode"));
                purReceivalEntry.setUnit(purEntryObject.getString("unit"));
                purReceivalEntry.setPrice(purEntryObject.getBigDecimal("price"));
                purReceivalEntry.setQty(purEntryObject.getBigDecimal("qty"));
                purReceivalEntry.setActualQty(purEntryObject.getBigDecimal("actualQty"));
                if (purEntryObject.getDate("dyProDate") != null) {
                    purReceivalEntry.setDyProDate(purEntryObject.getDate("dyProDate"));
                }
                purReceivalEntry.setDyManufacturer(purEntryObject.getString("dyManufacturer"));
                purReceivalEntry.setRegistrationNo(purEntryObject.getString("registrationNo"));
                purReceivalEntry.setAmount(purEntryObject.getBigDecimal("amount"));
                if (purEntryObject.getDate("effectiveDate") != null) {
                    purReceivalEntry.setEffectiveDate(purEntryObject.getDate("effectiveDate"));
                }
                purReceivalEntry.setQualifiedQty(purEntryObject.getBigDecimal("qualifiedQty"));
                purReceivalEntry.setUnqualifiedQty(purEntryObject.getBigDecimal("unqualifiedQty"));
                PurReceivalEntryMapper purReceivalEntryMapper = sqlSession.getMapper(PurReceivalEntryMapper.class);
                if (jsonObject.getByte("baseStatus") == 4) {
                    purReceivalEntryMapper.insertSelective(purReceivalEntry);
                }

            }
        }

        if (!sendCarGoCheckIds.isEmpty()) {

            // add by yadda HRP收货单审核后，将供应商平台上关联的发货单设置为签收状态 禅道bug#5589
            Sendcargo sendcargo = new Sendcargo();
            sendcargo.setStatus(101);

            SendcargoMapper sendcargoMapper = sqlSession.getMapper(SendcargoMapper.class);
            SendcargoExample sendcargoExample = new SendcargoExample();
            SendcargoExample.Criteria sendcargoExampleCriteria = sendcargoExample.createCriteria();
            sendcargoExampleCriteria.andIdIn(new ArrayList<>(sendCarGoCheckIds));
            // 更新发货单发货状态为已签收
            sendcargoMapper.updateByExampleSelective(sendcargo, sendcargoExample);


        }

        if (!sendCarGoUnCheckIds.isEmpty()) {

            // add by yadda HRP收货单反审核后，将供应商平台上关联的发货单设置为签收状态 禅道bug#5589
            Sendcargo sendcargo = new Sendcargo();
            sendcargo.setStatus(100);

            SendcargoMapper sendcargoMapper = sqlSession.getMapper(SendcargoMapper.class);
            SendcargoExample sendcargoExample = new SendcargoExample();
            SendcargoExample.Criteria sendcargoExampleCriteria = sendcargoExample.createCriteria();
            sendcargoExampleCriteria.andIdIn(new ArrayList<>(sendCarGoUnCheckIds));
            // 更新发货单发货状态为未签收
            sendcargoMapper.updateByExampleSelective(sendcargo, sendcargoExample);

        }

        return "success";
    }

}
