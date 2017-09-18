package com.kingdee.eas.hrp.sms.controller.supplier;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.exception.PlugInRuntimeException;
import com.kingdee.eas.hrp.sms.model.FormClass;
import com.kingdee.eas.hrp.sms.model.FormFields;
import com.kingdee.eas.hrp.sms.service.api.ITemplateService;
import com.kingdee.eas.hrp.sms.service.api.supplier.IFileUploadService;
import com.kingdee.eas.hrp.sms.util.ExcelUtil;
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
			// response.sendError(HttpServletResponse.SC_NOT_FOUND, "参数错误：必须提交classId");
			response.getWriter().write("<script>alert('参数错误：必须提交classId');history.back();</script>");
			return;
		}

		if ("".equals(fileName)) {
			// response.sendError(HttpServletResponse.SC_NOT_FOUND, "参数错误：必须提交fileName");
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
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));

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

	/**
	 * 证件预览
	 * 
	 * @Title preview
	 * @param request
	 * @param response
	 * @return void
	 * @throws IOException
	 * @date 2017-09-15 13:45:50 星期五
	 */
	@RequestMapping(value = "preview")
	public void preview(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int classId = ParameterUtils.getParameter(request, "classId", -1);

		String fileName = ParameterUtils.getParameter(request, "fileName", ""); // 文件名

		String itemId = ParameterUtils.getParameter(request, "itemId", "");
		
		Map<String, Object> itemById = templateService.getItemById(classId, itemId);
		String number = (String) itemById.get("number");
		String code = filter(number);

		if (classId < 0) {
			response.getWriter().write("<script>alert('参数错误：必须提交classId');history.back();</script>");
			return;
		}

		if ("".equals(fileName)) {
			response.getWriter().write("<script>alert('参数错误：必须提交fileName');history.back();</script>");
			return;
		}

		// 读取目标文件，通过response将目标文件写到客户端
		// 获取目标文件的绝对路径
		String fileDirector = SystemParamUtil.getString("sys", "FILE_PATH"); // 文件存放目录
		String classDirector = fileDirector.endsWith("\\") ? classId + "\\\\" : "\\\\" + classId + "\\\\";
		fileDirector = fileDirector + classDirector + code;// 真实存放路径

		File f = new File(fileDirector);

		if (!f.exists()) {
			response.getWriter().write("<script>alert('文件不存在');history.back();</script>");
			return;
		}

		String filePath = fileDirector + "\\\\" + fileName;

		f = new File(filePath);

		if (!f.exists()) {
			// 路径不存在
			response.getWriter().write("<script>alert('文件不存在');history.back();</script>");
			return;
		}

		// 设置文件MIME类型
		// response.setContentType("application/force-download");
		// 设置Content-Disposition
		// response.setHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode(fileName, "UTF-8"));

		response.setHeader("Content-Disposition", "attachment;fileName=test.pdf");
		response.setContentType("multipart/form-data");

		BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());

		IOUtils.write(IOUtils.toByteArray(in), out);
//		try {
//			// 读取文件
//			// 写文件
//			byte[] b = new byte[1024];
//			int len = 0;
//
//			while ((len = in.read(b)) != -1) {
//				out.write(b, 0, len);
//			}
//
//			in.close();
//			out.close();
//
//		} catch (IOException e) {
//
//			response.sendError(HttpServletResponse.SC_NOT_FOUND);
//
//			if (in != null) {
//				in.close();
//			}
//			if (out != null) {
//				out.close();
//			}
//		}
	}

	private String filter(String str) {
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	// 供应商资质/物料证件导出excel
	@RequestMapping(value = "export")
	public void export(HttpServletRequest request, HttpServletResponse response) {

		int classId = ParameterUtils.getParameter(request, "classId", -1);
		String condition = ParameterUtils.getParameter(request, "condition", ""); // 过滤条件json

		Map<String, Object> formTemplate = templateService.getFormTemplate(classId, 0); // 模板
		Map<String, Object> items = templateService.getItems(classId, condition, "", 1, 100000);// 最多导出100000条记录

		FormClass formClass = (FormClass) formTemplate.get("formClass");
		String title = formClass.getName();

		// 主表字段模板
		Map<String, Object> formFields0 = (Map<String, Object>) ((Map<String, Object>) formTemplate.get("formFields")).get("0");
		List<Map<String, Object>> list = (List<Map<String, Object>>) items.get("list");

		JSONArray array = JSONArray.parseArray(JSON.toJSONString(list, SerializerFeature.WriteDateUseDateFormat));

		List<Map<String, Object>> head = new ArrayList<Map<String, Object>>();

		for (Iterator<Entry<String, Object>> it = formFields0.entrySet().iterator(); it.hasNext();) {
			Entry<String, Object> item = it.next();
			FormFields fields = (FormFields) item.getValue();

			if (fields.getDisplay() == 0)
				// 简单处理
				continue;

			String key = item.getKey();
			String name = fields.getName();

			if (fields.getLookUpType() == 1) {
				// 引用类型-显示名称
				key += "_DspName";
			}

			Map<String, Object> headMap = new LinkedHashMap<String, Object>();

			headMap.put("key", key);
			headMap.put("name", name);
			headMap.put("type", fields.getDataType());

			head.add(headMap);
		}

		SXSSFWorkbook excel = ExcelUtil.exportExcelX(title, head, array, false);

		try {
			// OutputStream outXlsx = new FileOutputStream("E://b.xlsx");
			// excel.write(outXlsx);

			ByteArrayOutputStream os = new ByteArrayOutputStream();

			excel.write(os);

			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);

			// 设置response参数，可以打开下载页面
			response.reset();
			// response.setContentType("application/force-download");
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(title + ".xlsx", "UTF-8"));
			response.setContentLength(content.length);

			ServletOutputStream outputStream = response.getOutputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			BufferedOutputStream bos = new BufferedOutputStream(outputStream);
			byte[] buff = new byte[1024];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			bis.close();
			bos.close();
			os.close();
			outputStream.flush();
			outputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}