package com.kingdee.eas.hrp.sms.controller.system;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.service.api.sys.ISyncService;
import com.kingdee.eas.hrp.sms.util.SessionUtil;

@Controller
@RequestMapping(value = "/sync/")
public class SyncController {

	@Resource
	ISyncService syncService;

	public boolean checkAdminUser(HttpServletRequest request) {
		if (SessionUtil.getUserType(request) != 50810) {
			// 非系统工作人员，禁止操作
			throw new BusinessLogicRunTimeException("非系统工作人员，禁止操作");
		}
		return true;
	}

	// 同步供应商资料
	@RequestMapping(value = "supplier")
	public void smsSupplier(HttpServletRequest request, HttpServletResponse response) {
		try {
			//验证是否系统工作人员
			checkAdminUser(request);
			
			InputStream is;
			is = request.getInputStream();
			DataInputStream input = new DataInputStream(is);
			String str = input.readUTF();
			JSONObject json = JSON.parseObject(str);
			int size = json.getIntValue("size");
			JSONArray list = json.getJSONArray("list");
			
			syncService.supplier(list);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 同步分类
	@RequestMapping(value = "category")
	public void smsCategory(HttpServletRequest request, HttpServletResponse response) {
		try {
			//验证是否系统工作人员
			checkAdminUser(request);
			
			InputStream is;
			is = request.getInputStream();
			DataInputStream input = new DataInputStream(is);
			String str = input.readUTF();
			JSONObject json = JSON.parseObject(str);
			int size = json.getIntValue("size");
			JSONArray list = json.getJSONArray("list");
			syncService.category(list);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 同步证书
	@RequestMapping(value = "certificate")
	public void smsCertificate(HttpServletRequest request, HttpServletResponse response) {
		try {
			//验证是否系统工作人员
			checkAdminUser(request);
			
			InputStream is;
			is = request.getInputStream();
			DataInputStream input = new DataInputStream(is);
			String str = input.readUTF();
			JSONObject json = JSON.parseObject(str);
			int size = json.getIntValue("size");
			JSONArray list = json.getJSONArray("list");
			syncService.certificate(list);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 同步行业
	@RequestMapping(value = "industry")
	public void smsIndustry(HttpServletRequest request, HttpServletResponse response) {
		try {
			//验证是否系统工作人员
			checkAdminUser(request);
			
			InputStream is;
			is = request.getInputStream();
			DataInputStream input = new DataInputStream(is);
			String str = input.readUTF();
			JSONObject json = JSON.parseObject(str);
			int size = json.getIntValue("size");
			JSONArray list = json.getJSONArray("list");
			
			syncService.industry(list);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 同步币别
	@RequestMapping(value = "currency")
	public void smsCurrency(HttpServletRequest request, HttpServletResponse response) {
		try {
			//验证是否系统工作人员
			checkAdminUser(request);
			
			InputStream is;
			is = request.getInputStream();
			DataInputStream input = new DataInputStream(is);
			String str = input.readUTF();
			JSONObject json = JSON.parseObject(str);
			int size = json.getIntValue("size");
			JSONArray list = json.getJSONArray("list");
			
			syncService.currency(list);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 同步结算方式
	@RequestMapping(value = "settlement")
	public void smsSettlement(HttpServletRequest request, HttpServletResponse response) {
		try {
			//验证是否系统工作人员
			checkAdminUser(request);
			
			InputStream is;
			is = request.getInputStream();
			DataInputStream input = new DataInputStream(is);
			String str = input.readUTF();
			JSONObject json = JSON.parseObject(str);
			int size = json.getIntValue("size");
			JSONArray list = json.getJSONArray("list");

			syncService.settlement(list);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 同步付款方式
	@RequestMapping(value = "pay")
	public void smsPay(HttpServletRequest request, HttpServletResponse response) {
		try {
			//验证是否系统工作人员
			checkAdminUser(request);
			
			InputStream is;
			is = request.getInputStream();
			DataInputStream input = new DataInputStream(is);
			String str = input.readUTF();
			JSONObject json = JSON.parseObject(str);
			int size = json.getIntValue("size");
			JSONArray list = json.getJSONArray("list");

			syncService.pay(list);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 同步物料
	@RequestMapping(value = "item")
	public void smsItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			//验证是否系统工作人员
			checkAdminUser(request);
			
			InputStream is;
			is = request.getInputStream();
			DataInputStream input = new DataInputStream(is);
			String str = input.readUTF();
			JSONObject json = JSON.parseObject(str);
			int size = json.getIntValue("size");
			JSONArray list = json.getJSONArray("list");

			syncService.item(list);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 同步税种
	@RequestMapping(value = "taxCategory")
	public void smsTaxCategory(HttpServletRequest request, HttpServletResponse response) {
		try {
			//验证是否系统工作人员
			checkAdminUser(request);
			
			InputStream is;
			is = request.getInputStream();
			DataInputStream input = new DataInputStream(is);
			String str = input.readUTF();
			JSONObject json = JSON.parseObject(str);
			int size = json.getIntValue("size");
			JSONArray list = json.getJSONArray("list");

			syncService.taxCategory(list);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
