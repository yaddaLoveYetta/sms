package com.kingdee.eas.hrp.sms.controller.supplier;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.log.ControllerLog;
import com.kingdee.eas.hrp.sms.service.api.supplier.IFileUploadService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;
import com.kingdee.eas.hrp.sms.util.SystemParamUtil;

@Controller
@RequestMapping(value = "/file/")
public class FileUploadController {

	@Resource
	IFileUploadService fileUploadService;

	@RequestMapping(value = "upload")
	public void upload(HttpServletRequest request, HttpServletResponse response) {

		int classId = 0;
		String itemId = "";

		if (!ServletFileUpload.isMultipartContent(request)) {
			ResponseWriteUtil.output(response, StatusCode.PIC_UPLOAD_FAIL, "请选择附件！");
			return;
		}

		DiskFileItemFactory ff = new DiskFileItemFactory();
		ServletFileUpload fu = new ServletFileUpload(ff);

		fu.setHeaderEncoding("utf-8");
		fu.setSizeMax(5 * 1024 * 1024);// 限制文件大小为5M

		ff.setRepository(new File(System.getProperty("java.io.tmpdir")));
		ff.setSizeThreshold(1024 * 1024);// 超过内存阀值就保存到temp目录,这里设为系统缓存路径

		List<FileItem> file = new ArrayList<FileItem>();
		List<FileItem> fileList = null;

		try {
			fileList = fu.parseRequest(request);
		} catch (FileUploadException ex) {
			ResponseWriteUtil.output(response, StatusCode.PIC_UPLOAD_FAIL, "文件上传失败！");
			return;
		}

		Iterator<FileItem> it = fileList.iterator();

		while (it.hasNext()) {

			FileItem item = it.next();

			if (item.isFormField()) {
				// 普通表单域
				String fieldName = item.getFieldName();
				if ("classId".equals(fieldName)) {
					classId = Integer.parseInt(item.getString());
				} else if ("itemId".equals(fieldName)) {
					itemId = item.getString();
				}

			} else {
				// 文件表单域
				file.add(item);
			}
		}

		if (classId == 0 || itemId.isEmpty()) {
			throw new BusinessLogicRunTimeException("请指定附件类型!");
		}
		if (file.isEmpty()) {
			throw new BusinessLogicRunTimeException("请选择附件!");
		}

		// 构建附件存放路径
		String fileDirector = SystemParamUtil.getString("SYS", "FILE_PATH"); // 文件存放目录
		String fileUrl = SystemParamUtil.getString("SYS", "FILE_URL"); // 文件映射地址(存储到数据库中)

		String classDirector = fileDirector.endsWith("\\") ? classId + "\\\\" : "\\\\" + classId + "\\\\";
		String fileUrlDirector = fileUrl.endsWith("/") ? classId + "/" : "/" + classId + "/";
		fileDirector = fileDirector + classDirector;// 真实存放路径
		fileUrl = fileUrl + fileUrlDirector;// 映射路径

		File f = new File(fileDirector);

		if (!f.exists()) {
			// 路径不存在-创建
			f.mkdirs();
		}

		// 保存文件到物理目录--生成映射路径
		List<String> fileUrls = new ArrayList<String>();

		for (FileItem fileItem : file) {

			String fileName = fileItem.getName();

			if (fileName == null || fileName.trim().equals("")) {
				continue;
			}

			String filePath = fileDirector + "\\\\" + fileName;
			fileUrls.add(fileUrl + fileName); // 对应的文件映射地址

			File realFile = new File(filePath);
			realFile.setReadable(true);
			try {
				fileItem.write(realFile);
			} catch (Exception e) {
				throw new BusinessLogicRunTimeException(e);
			}

		}

		// 保存映射路径到数据库

		fileUploadService.saveUrlToDb(classId, itemId, fileUrls);

	}
	
	@ControllerLog(desc = "删除附件") // 做日志
	// @Permission(objectType = 0, objectId = 0, accessMask =
	// AccessMaskCode.MASK_SYNC, desc = "同步item") // 权限
	@RequestMapping(value = "delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) {

		Integer classId = ParameterUtils.getParameter(request, "classId", -1);
		String data = ParameterUtils.getParameter(request, "data", "");

		if (classId < 0) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交classId");
			return;
		}

		if (null == data || "".equals(data)) {
			ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR, "参数错误：必须提交data");
			return;
		}

		JSONObject deleteRet = fileUploadService.delete(classId, data);
		if (deleteRet.isEmpty()) {
			ResponseWriteUtil.output(response, StatusCode.SUCCESS, "附件删除成功！");
		} else {
			ResponseWriteUtil.output(response, StatusCode.BUSINESS_LOGIC_ERROR, deleteRet.toString());
		}

	}

}