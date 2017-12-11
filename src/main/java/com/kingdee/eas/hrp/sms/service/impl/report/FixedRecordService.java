package com.kingdee.eas.hrp.sms.service.impl.report;

import com.kingdee.eas.hrp.sms.filter.CustomizedPropertyPlaceholderConfigurer;
import com.kingdee.eas.hrp.sms.service.api.report.IFixedRecordService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

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
        workload.put("今年总单量", currentYearQty);
        workload.put("当月总单量", currentMonthQty);
        workload.put("今天单量", todayQty);
        workload.put("今天未完工单量", todayUndoneQty);
        workload.put("今天完工单量", todayDoneQty);
        workload.put("所有未完工单量", totalUndoneQty);

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

        String sql = CustomizedPropertyPlaceholderConfigurer.getContextProperty("totalUndoneQty");
        if (null == sql || sql.isEmpty()) {
            return 0;
        }
        int c = jdbcTemplate.queryForObject(sql, Integer.class);
        return c;
    }

    /**
     * 今天完工单量
     *
     * @return
     */
    private int getTodayDoneQty() {

        String sql = CustomizedPropertyPlaceholderConfigurer.getContextProperty("todayDoneQty");
        if (null == sql || sql.isEmpty()) {
            return 0;
        }
        int c = jdbcTemplate.queryForObject(sql, Integer.class);
        return c;
    }

    /**
     * 今天未完工单量
     *
     * @return
     */
    private int getTodayUndoneQty() {

        String sql = CustomizedPropertyPlaceholderConfigurer.getContextProperty("todayUndoneQty");
        if (null == sql || sql.isEmpty()) {
            return 0;
        }
        int c = jdbcTemplate.queryForObject(sql, Integer.class);
        return c;
    }

    /**
     * 今天单量
     *
     * @return
     */
    private int getTodayQty() {

        String sql = CustomizedPropertyPlaceholderConfigurer.getContextProperty("todayQty");
        if (null == sql || sql.isEmpty()) {
            return 0;
        }

        int c = jdbcTemplate.queryForObject(sql, Integer.class);
        return c;
    }

    /**
     * 当月总单量
     *
     * @return
     */
    private int getCurrentMonthQty() {
        String sql = CustomizedPropertyPlaceholderConfigurer.getContextProperty("currentMonthQty");
        if (null == sql || sql.isEmpty()) {
            return 0;
        }
        int c = jdbcTemplate.queryForObject(sql, Integer.class);
        return c;
    }

    /**
     * 今年总单量
     *
     * @return
     */
    private int getCurrentYearQty() {
        String sql = CustomizedPropertyPlaceholderConfigurer.getContextProperty("currentYearQty");
        if (null == sql || sql.isEmpty()) {
            return 0;
        }

        int c = jdbcTemplate.queryForObject(sql, Integer.class);
        return c;
    }

    /**
     * 截止到当天，未完成的申请单明细
     *
     * @param pageSize
     * @param pageNo
     * @return
     */
    private List<Map<String, Object>> getList(Integer pageSize, Integer pageNo) {

        List<Map<String, Object>> ret = new ArrayList<>();

        int start = pageSize * (pageNo - 1);
        int offset = pageSize * pageNo;

        String sql = CustomizedPropertyPlaceholderConfigurer.getContextProperty("detailList");
        if (null == sql || sql.isEmpty()) {
            return new ArrayList<Map<String, Object>>();
        }
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, start, offset);

        if (null != list && list.size() > 0) {
            Map<String, Object> rowItem;
            for (Map<String, Object> entry : list) {
                rowItem = new HashMap<>(16);
                rowItem.put("reqNumber", entry.get("REQNUMBER"));
                rowItem.put("department", entry.get("DEPARTMENT"));
                rowItem.put("builder", entry.get("BUILDER"));
                rowItem.put("reqDate", entry.get("REQDATE"));

                if (null == entry.get("STATUS")) {
                    rowItem.put("status", "未接单");
                } else if (Integer.parseInt(entry.get("STATUS").toString()) == 1) {
                    rowItem.put("status", "未接单");
                } else if (Integer.parseInt(entry.get("STATUS").toString()) == 2) {
                    rowItem.put("status", "已接单");
                } else if (Integer.parseInt(entry.get("STATUS").toString()) == 3) {
                    rowItem.put("status", "已派工");
                } else if (Integer.parseInt(entry.get("STATUS").toString()) == 4) {
                    rowItem.put("status", "已完成");
                } else if (Integer.parseInt(entry.get("STATUS").toString()) == 5) {
                    rowItem.put("status", "已退单");
                }

                rowItem.put("repairMan", entry.get("REPAIRMAN"));
                rowItem.put("targetItem", entry.get("TARGETITEM"));
                ret.add(rowItem);
            }
        }

        return ret;
    }

    /**
     * 近7日的申请单数量
     *
     * @return
     */
    private Map<String, Object> getTrendQty() {


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        Calendar c = Calendar.getInstance(Locale.CHINA);
        int day = c.get(Calendar.DATE);
        String currentDay = sdf.format(c.getTime());
        // 前一天
        c.set(Calendar.DATE, day - 1);
        String currentDayOffset1 = sdf.format(c.getTime());
        // 前二天
        c.set(Calendar.DATE, day - 2);
        String currentDayOffset2 = sdf.format(c.getTime());
        // 前三天
        c.set(Calendar.DATE, day - 3);
        String currentDayOffset3 = sdf.format(c.getTime());
        // 前四天
        c.set(Calendar.DATE, day - 4);
        String currentDayOffset4 = sdf.format(c.getTime());
        // 前五天
        c.set(Calendar.DATE, day - 5);
        String currentDayOffset5 = sdf.format(c.getTime());
        // 前六天
        c.set(Calendar.DATE, day - 6);
        String currentDayOffset6 = sdf.format(c.getTime());

        Map<String, Object> ret = new TreeMap<String, Object>() {{
            put(currentDay, 0);
            put(currentDayOffset1, 0);
            put(currentDayOffset2, 0);
            put(currentDayOffset3, 0);
            put(currentDayOffset4, 0);
            put(currentDayOffset5, 0);
            put(currentDayOffset6, 0);
        }};

        String sql = CustomizedPropertyPlaceholderConfigurer.getContextProperty("trendQty");
        if (null == sql || sql.isEmpty()) {
            return ret;
        }
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);

        if (null != result && result.size() > 0) {

            ret.put(currentDay, result.get(0).get("DAY0"));
            ret.put(currentDayOffset1, result.get(0).get("DAY1"));
            ret.put(currentDayOffset2, result.get(0).get("DAY2"));
            ret.put(currentDayOffset3, result.get(0).get("DAY3"));
            ret.put(currentDayOffset4, result.get(0).get("DAY4"));
            ret.put(currentDayOffset5, result.get(0).get("DAY5"));
            ret.put(currentDayOffset6, result.get(0).get("DAY6"));

        }

        return ret;
    }

}
