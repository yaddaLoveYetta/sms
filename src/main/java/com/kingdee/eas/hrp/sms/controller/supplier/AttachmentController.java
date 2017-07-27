package com.kingdee.eas.hrp.sms.controller.supplier;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.exception.PlugInRuntimeException;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.api.supplier.IFileUploadService;
import com.kingdee.eas.hrp.sms.service.impl.TemplateService;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;
import com.kingdee.eas.hrp.sms.util.SystemParamUtil;

@Controller
@RequestMapping(value = "/file/")
public class AttachmentController {

	@Resource
	IFileUploadService fileUploadService;
	@Resource
	ITemplateService templateService;

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

		// 是否已审核
		Map<String, Object> itemById = templateService.getItemById(classId, itemId);
		String number = (String) itemById.get("number");
		String code = filter(number);
		int review = Integer.parseInt(itemById.get("review").toString());
		if (1 == review) {
			throw new PlugInRuntimeException("记录已审核，无法进行操作！");
		}

		// 构建附件存放路径
		String fileDirector = SystemParamUtil.getString("sys", "FILE_PATH"); // 文件存放目录

		String classDirector = fileDirector.endsWith("\\") ? classId + "\\\\" : "\\\\" + classId + "\\\\";

		fileDirector = fileDirector + classDirector + code;// 真实存放路径

		File f = new File(fileDirector);

		if (!f.exists()) {
			// 路径不存在-创建
			f.mkdirs();
		}

		// 保存文件名--保存到数据库中
		List<String> fileNames = new ArrayList<String>();

		for (FileItem fileItem : file) {

			String fileName = fileItem.getName();

			if (fileName == null || fileName.trim().equals("")) {
				continue;
			}

			String filePath = fileDirector + "\\\\" + fileName;
			fileNames.add(fileName); // 文件名

			File realFile = new File(filePath);
			realFile.setReadable(true);

			try {
				fileItem.write(realFile);
			} catch (Exception e) {
				throw new BusinessLogicRunTimeException(e);
			}

		}

		// 保存映射路径到数据库

		fileUploadService.saveUrlToDb(classId, itemId, fileNames);

	}

	@RequestMapping(value = "download")
	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int classId = ParameterUtils.getParameter(request, "classId", -1);

		String fileName = ParameterUtils.getParameter(request, "fileName", ""); // 文件名

		String itemId = ParameterUtils.getParameter(request, "itemId", "");

		Map<String, Object> itemById = templateService.getItemById(classId, itemId);
		String number = (String) itemById.get("number");
		String code = filter(number);

		if (classId < 0) {
			// ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR,
			// "参数错误：必须提交classId");
			//response.sendError(HttpServletResponse.SC_NOT_FOUND, "参数错误：必须提交classId");
			response.getWriter().write("<script>alert('参数错误：必须提交classId');history.back();</script>");
			return;
		}

		if ("".equals(fileName)) {
			//response.sendError(HttpServletResponse.SC_NOT_FOUND, "参数错误：必须提交fileName");
			response.getWriter().write("<script>alert('参数错误：必须提交fileName');history.back();</script>");
			// ResponseWriteUtil.output(response, StatusCode.PARAMETER_ERROR,
			// "参数错误：必须提交itemId及fileName");
			return;
		}

		// 读取目标文件，通过response将目标文件写到客户端
		// 获取目标文件的绝对路径
		String fileDirector = SystemParamUtil.getString("sys", "FILE_PATH"); // 文件存放目录
		String classDirector = fileDirector.endsWith("\\") ? classId + "\\\\" : "\\\\" + classId + "\\\\";
		fileDirector = fileDirector + classDirector + code;// 真实存放路径

		File f = new File(fileDirector);

		if (!f.exists()) {
			// 路径不存在
			response.getWriter().write("<script>alert('文件不存在');history.back();</script>");
			return;
			// throw new BusinessLogicRunTimeException("文件不存在");
		}

		String filePath = fileDirector + "\\\\" + fileName;

		f = new File(filePath);

		if (!f.exists()) {
			// 路径不存在
			// response.sendError(HttpServletResponse.SC_NOT_FOUND, "文件不存在");
			// response.getWriter().write("<div>文件不存在www</div>");
			response.getWriter().write("<script>alert('文件不存在');history.back();</script>");
			// response.getWriter().write("<script>SMS.Tips.error('请选择要操作的项', 1500);</script>");
			return;
			// throw new BusinessLogicRunTimeException("文件不存在");
		}

		// 设置文件MIME类型
		response.setContentType("application/force-download");
		// 设置Content-Disposition
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

		BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());

		try {
			// 读取文件
			// 写文件
			byte[] b = new byte[1024];
			int len = 0;

			while ((len = in.read(b)) != -1) {
				out.write(b, 0, len);
			}

			in.close();
			out.close();

		} catch (IOException e) {

			response.sendError(HttpServletResponse.SC_NOT_FOUND);

			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

	public String filter(String str) {
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

}