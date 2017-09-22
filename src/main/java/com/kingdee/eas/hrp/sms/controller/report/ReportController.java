package com.kingdee.eas.hrp.sms.controller.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.log.ControllerLog;
import com.kingdee.eas.hrp.sms.service.api.order.IOrderService;
import com.kingdee.eas.hrp.sms.service.api.statistics.IStatisticsService;
import com.kingdee.eas.hrp.sms.util.Environ;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.SessionUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;

@Controller
@RequestMapping(value = "/report/")
public class ReportController {

	@Resource
	IStatisticsService statisticsService;

	@ControllerLog(desc = "订单统计", classId = 0)
	@RequestMapping(value = "orderCount")
	public void orderCount(HttpServletRequest request, HttpServletResponse response) {

		String itemId = ParameterUtils.getParameter(request, "material", "");
		String orderStartDate = ParameterUtils.getParameter(request, "beginDate", "");
		String orderEndDate = ParameterUtils.getParameter(request, "endDate", "");
		String supplier = ParameterUtils.getParameter(request, "supplier", "");
		int pageNo = ParameterUtils.getParameter(request, "pageNo", 1);
		int pageSize = ParameterUtils.getParameter(request, "pageSize", 10);

		if (!(null == SessionUtil.getUserLinkSupplier() || "".equals(SessionUtil.getUserLinkSupplier())))
			supplier = SessionUtil.getUserLinkSupplier();

		if (itemId.equals("") && supplier.equals("") && orderStartDate.equals("") && orderEndDate.equals("")) {
			throw new BusinessLogicRunTimeException("必须提交参数");
		}

		JSONObject result = statisticsService.getRecord(itemId, supplier, orderStartDate, orderEndDate, pageNo, pageSize);
		ResponseWriteUtil.output(response, result);
	}

	@ControllerLog(desc = "订单追踪查询")
	@RequestMapping(value = "traceQuery")
	public void traceQuery(HttpServletRequest request, HttpServletResponse response) {
		String supplierIds = "";
		String pageNo = ParameterUtils.getParameter(request, "pageNo", "");
		String pageSize = ParameterUtils.getParameter(request, "pageSize", "");
		String classId = ParameterUtils.getParameter(request, "classId", "");
		String supplier = ParameterUtils.getParameter(request, "supplier", "");
		String order = ParameterUtils.getParameter(request, "order", "");
		String beginDate = ParameterUtils.getParameter(request, "beginDate", "");
		String endDate = ParameterUtils.getParameter(request, "endDate", "");
		if (SessionUtil.getUserType().equals("B3sMo22ZLkWApjO/oEeDOxACEAI=")) {
			if (!(null == SessionUtil.getUserLinkSupplier() || "".equals(SessionUtil.getUserLinkSupplier()))) {
				supplierIds = SessionUtil.getUserLinkSupplier();
			}
		}
		IOrderService orderService = Environ.getBean(IOrderService.class);

		JSONObject shipSendcargo = orderService.traceQuery(supplierIds, pageNo, pageSize, classId, supplier, order, beginDate, endDate);
		ResponseWriteUtil.output(response, StatusCode.SUCCESS, shipSendcargo);
	}

	/**
	 * 物料证件综合查询
	 * 
	 * @Title itemLicenseQuery
	 * @param request
	 * @param response
	 * @return void
	 * @date 2017-09-21 15:35:06 星期四
	 */
	@RequestMapping(value = "itemLicenseQuery")
	public void itemLicenseQuery(HttpServletRequest request, HttpServletResponse response) {

		String itemNumber = ParameterUtils.getParameter(request, "itemNumber", "");// 物料编码
		String itemName = ParameterUtils.getParameter(request, "itemName", "");// 物料名称
		String itemModel = ParameterUtils.getParameter(request, "itemModel", "");// 物料规格

		String manufacturer = ParameterUtils.getParameter(request, "manufacturer", "");// 生产厂家
		String idNumber = ParameterUtils.getParameter(request, "idNumber", "");// 证书编号
		String idName = ParameterUtils.getParameter(request, "idName", "");// 证书名称
		String idType = ParameterUtils.getParameter(request, "idType", "");// 证书类别
		String authOrg = ParameterUtils.getParameter(request, "authOrg", "");// 发证机关
		String supplier = ParameterUtils.getParameter(request, "supplier", "");// 供应商
		String Expired = ParameterUtils.getParameter(request, "Expired", "");// 过期

		int pageSize = ParameterUtils.getParameter(request, "pageSize", 10);
		int pageNo = ParameterUtils.getParameter(request, "pageNo", 1);

		Map<String, Object> ret = new HashMap<String, Object>();

		ret.put("count", 100);

		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

		Map<String, Object> item = new HashMap<>();

		item.put("itemNumber", "csdfsdfs");
		item.put("itemName", "safdsafsd");
		item.put("itemModel", "fwerefdds");
		item.put("manufacturer", "wty324w");
		item.put("idNumber", "34532523");
		item.put("idName", "eerrw");
		item.put("idType", "xxxxx");
		item.put("authOrg", "rrrrr");
		item.put("supplier", "vxz");

		item.put("beginDate", "2017-02-12");
		item.put("endDate", "2017-02-15");

		data.add(item);

		ret.put("list", data);

		ResponseWriteUtil.output(response, StatusCode.SUCCESS, ret);
	}
	
	public static void main(String[] args) {
		String s[]=new String[4];
		
		s[0]="dfsdf";
		System.out.println(s[0]);
		
		String[] s2=new String[1];
		s2[0]="dfdsfdfdf";
		System.out.println(s2[0]);
		
	}

}