package com.kingdee.eas.hrp.sms.service.impl.report;

import com.kingdee.eas.hrp.sms.service.api.report.IFixedRecordService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yadda<silenceisok@163.com>
 * @since 2017/12/8
 */
@Service
public class FixedRecordService implements IFixedRecordService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取设备维修记录报表数据
     *
     * @param pageSize
     * @param pageNo
     * @return
     */
    @Override
    public Map<String, Object> getFixedRecord(Integer pageSize, Integer pageNo) {

/*        {
            workload: {
                currentYearQty: currentYearQty,
                        currentMonthQty: currentMonthQty,
                        todayQty: todayQty,
                        todayUndoneQty: todayUndoneQty,
                        todayDoneQty: todayQty - todayUndoneQty,
                        totalUndoneQty: totalUndoneQty,
            },
            trendQty: {
                d1: 33,
                        d2: 22,
                        d3: 55,
                        d4: 18,
                        d5: 99,
                        d6: 31,
                        d7: 9,
            },
            list: list
        }*/

        Map<String, Object> ret = new HashMap<>(6);

        int currentYearQty = getCurrentYearQty();
        int currentMonthQty = getCurrentMonthQty();
        int todayQty = getTodayQty();
        int todayUndoneQty = getTodayUndoneQty();
        int todayDoneQty = getTodayDoneQty();
        int totalUndoneQty = getTotalUndoneQty();
        Map<String, Object> trendQty = getTrendQty();

        List<Map<String, Object>> list = getList(pageSize, pageNo);

        Map<String, Object> workload = new HashMap<>(12);
        workload.put("currentYearQty", currentYearQty);
        workload.put("currentMonthQty", currentMonthQty);
        workload.put("todayQty", todayQty);
        workload.put("todayUndoneQty", todayUndoneQty);
        workload.put("todayDoneQty", todayDoneQty);
        workload.put("totalUndoneQty", totalUndoneQty);

        ret.put("workload", workload);
        ret.put("trendQty", trendQty);
        ret.put("list", list);
        return ret;
    }

    /**
     * 所有未完工单量
     *
     * @return
     */
    private int getTotalUndoneQty() {

        String sql = "select username from user where id=?";
        int c = jdbcTemplate.queryForObject(sql, Integer.class);
        return c;
    }

    /**
     * 今天完工单量
     *
     * @return
     */
    private int getTodayDoneQty() {
        return 0;
    }

    /**
     * 今天未完工单量
     *
     * @return
     */
    private int getTodayUndoneQty() {
        return 0;
    }

    /**
     * 今天单量
     *
     * @return
     */
    private int getTodayQty() {
        return 0;
    }

    /**
     * 当月总单量
     *
     * @return
     */
    private int getCurrentMonthQty() {
        return 0;
    }

    /**
     * 今年总单量
     *
     * @return
     */
    private int getCurrentYearQty() {
        return 0;
    }

    /**
     * 截止到当天，未完成的申请单明细
     *
     * @param pageSize
     * @param pageNo
     * @return
     */
    private List<Map<String, Object>> getList(Integer pageSize, Integer pageNo) {
        return null;
    }

    /**
     * 近7日的申请单数量
     *
     * @return
     */
    private Map<String, Object> getTrendQty() {
        return null;
    }

}
