package com.kingdee.eas.hrp.sms.controller.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.model.User;
import com.kingdee.eas.hrp.sms.service.api.user.IUserService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;

@Controller
@RequestMapping(value = "/user/")
public class UserController {

	@Resource
	private IUserService userService;

	/**
	 * 根据用户角色权限获取菜单列表
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getSidebar")
	public void getSidebar(HttpServletRequest request, HttpServletResponse response) {

		User user = (User) request.getSession().getAttribute("user");

		if (user == null) {
			throw new BusinessLogicRunTimeException("用户信息错误，请重新登陆！");
		}

		int type = user.getType();
		int userId = user.getUserId();

		// 获取用户菜单
		List<Map<String, Object>> sidebars = userService.getSysMenu(type, userId);

		if (sidebars == null || sidebars.size() == 0) {
			throw new BusinessLogicRunTimeException("用户无任何权限，请先联系管理员为你配置权限!");
		}

		ResponseWriteUtil.output(response, StatusCode.SUCCESS, sidebars);

		return;

	}

	/**
	 * 
	 * @Title getUserList
	 * @Description 获取用户列表
	 * @param @param
	 *            request
	 * @param @param
	 *            response
	 * @return void
	 * @throws @date
	 *             2017年4月15日 下午7:49:37
	 */
	public void getUserList(HttpServletRequest request, HttpServletResponse response) {

		int pageNum = ParameterUtils.getParameter(request, "pageNum", 1); // 默认获取第一页
		int pageSize = ParameterUtils.getParameter(request, "pageSize", 10); // 默认分页大小10

		String condition = ParameterUtils.getParameter(request, "condition", ""); // 过滤条件
		String orderBy = ParameterUtils.getParameter(request, "orderBy", ""); // 排序字段

		// Map<String, Object> params = new HashMap<String, Object>();
		// params.put("classID", classID);
		// params.put("pageSize", pageSize);
		// params.put("pageNo", pageNo);
		// params.put("condition", condition);
		// params.put("orderBy", orderBy);
		// params.put("orderDirection", "asc");
		//
		// if (orderBy.equals("")) {
		// JSONArray orderByArray = new JSONArray();
		// JSONObject orderByItem = new JSONObject();
		// orderByItem.put("fieldKey", "FNumber");
		// orderByItem.put("orderDirection", "ASC");
		// orderByArray.add(orderByItem);
		//
		// orderByItem = new JSONObject();
		// orderByItem.put("fieldKey", "FName");
		// orderByItem.put("orderDirection", "ASC");
		// orderByArray.add(orderByItem);
		//
		// params.put("orderBy", orderByArray.toString());
		// }

	}
}
