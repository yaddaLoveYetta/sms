package com.kingdee.eas.hrp.sms.service.impl.anomaly;

import com.kingdee.eas.hrp.sms.dao.generate.AnomalyParamsMapper;
import com.kingdee.eas.hrp.sms.model.AnomalyParams;
import com.kingdee.eas.hrp.sms.service.api.Anomaly.IAnomalyService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

@Service
public class AnomalyService extends BaseService implements IAnomalyService {

    @Override
    @Transactional
    public String getUserNameEncode() {

        AnomalyParamsMapper mapper = sqlSession.getMapper(AnomalyParamsMapper.class);

        AnomalyParams currentRecord = mapper.selectByPrimaryKey("1001-userName");

        AnomalyParams newRecord = new AnomalyParams();

        String value;

        if (null == currentRecord) {
            value = "0";
        } else if (null == currentRecord.getValue()) {
            value = "0";
        } else {
            value = currentRecord.getValue();
        }

        int currentCode = Integer.parseInt(value) + 1;

        newRecord.setKey("1001-userName");
        newRecord.setValue(String.valueOf(currentCode));

        if (null == currentRecord) {
            mapper.insert(newRecord);
        } else {
            mapper.updateByPrimaryKey(newRecord);
        }

        DecimalFormat df = new DecimalFormat("00000");

        return "hc-" + df.format(currentCode);
    }

    /**
     * 订单生成发货单时，发货单个体码生成后5位用当天的递增流水号
     * <p>
     * 产生如 11-17-000001 ：11月17好000001的个体码
     *
     * @return
     */
    @Override
    @Transactional
    public synchronized String getDeliverOrderCodeEncode() {

        AnomalyParamsMapper mapper = sqlSession.getMapper(AnomalyParamsMapper.class);

        AnomalyParams currentRecord = mapper.selectByPrimaryKey("2020-code");

        AnomalyParams newRecord = new AnomalyParams();

        String value;

        Calendar calendar = Calendar.getInstance(Locale.CHINA);

        int year = calendar.get(GregorianCalendar.YEAR);
        int month = calendar.get(GregorianCalendar.MONTH) + 1;
        int day = calendar.get(GregorianCalendar.DAY_OF_MONTH);

        String key = year + "-" + month + "-" + day;

        if (null == currentRecord || null == currentRecord.getValue()) {
            value = "00000";
        } else if (!currentRecord.getValue().startsWith(key)) {
            value = "00000";
        } else {
            value = currentRecord.getValue().substring(key.length() + 1);
        }

        int currentCode = Integer.parseInt(value) + 1;

        newRecord.setKey("2020-code");
        newRecord.setValue(key + "-" + currentCode);

        if (null == currentRecord) {
            mapper.insert(newRecord);
        } else {
            mapper.updateByPrimaryKey(newRecord);
        }

        DecimalFormat df = new DecimalFormat("00000");

        return key + "-" + df.format(currentCode);


    }
}
