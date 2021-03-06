package com.kingdee.eas.hrp.sms.controller.sendcargo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;
import com.kingdee.eas.hrp.sms.util.SystemParamUtil;

@Controller
@RequestMapping(value = "/codefile/")
public class CodeAttachmentController {

	/**
	 * MultipartContent方式上传
	 * 
	 * @Title upload
	 * @param request
	 * @param response
	 * @return void
	 * @date 2017-06-22 11:57:37 星期四
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public void upload(HttpServletRequest request, HttpServletResponse response) {

		if (!ServletFileUpload.isMultipartContent(request)) {
			ResponseWriteUtil.output(response, StatusCode.PIC_UPLOAD_FAIL, "请选择附件且已Multipart方式上传！");
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

			if (!item.isFormField()) {
				file.add(item);
			}
		}

		if (file.isEmpty()) {
			throw new BusinessLogicRunTimeException("请选择附件!");
		}

		// 构建附件存放路径
		String fileDirector = SystemParamUtil.getString("sys", "FILE_PATH"); // 文件存放目录
		fileDirector = fileDirector + "sendcargo";
		File f = new File(fileDirector);

		if (!f.exists()) {
			// 路径不存在-创建
			f.mkdirs();
		}

		for (FileItem fileItem : file) {
			String fileName = fileItem.getName();

			if (fileName == null || fileName.trim().equals("")) {
				continue;
			}

			String filePath = fileDirector + "\\\\" + fileName;

			File realFile = new File(filePath);
			realFile.setReadable(true);
			try {
				fileItem.write(realFile);
			} catch (Exception e) {

				throw new BusinessLogicRunTimeException(e);
			}
		}

		ResponseWriteUtil.output(response, StatusCode.SUCCESS, "上传成功");
	}

	/**
	 * 流形式上传
	 * 
	 * @Title upload2
	 * @param request
	 * @param response
	 * @throws IOException
	 * @return void
	 * @date 2017-06-22 11:57:16 星期四
	 */
	@RequestMapping(value = "upload2", method = RequestMethod.POST)
	public void upload2(HttpServletRequest request, HttpServletResponse response) throws IOException {

		if (request.getContentLength() > 0) {

			InputStream in = null;
			FileOutputStream out = null;

			try {
				in = request.getInputStream();

				// 构建附件存放路径
				String fileDirector = SystemParamUtil.getString("sys", "FILE_PATH"); // 文件存放目录
				fileDirector = fileDirector + "sendcargo";
				File f = new File(fileDirector);

				if (!f.exists()) {
					// 路径不存在-创建
					f.mkdirs();
				}

				String filePath = fileDirector + "\\\\" + "codeConfig.xml";

				File file = new File(filePath);
				file.createNewFile();

				out = new FileOutputStream(file);

				byte temp[] = new byte[1024];
				int size = -1;
				while ((size = in.read(temp)) != -1) {
					out.write(temp, 0, size);
				}

				ResponseWriteUtil.output(response, StatusCode.SUCCESS, "上传成功");

			} catch (IOException e) {

			} finally {

				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			}
		} else {
			throw new BusinessLogicRunTimeException("请选择文件上传!");
		}

	}

	@RequestMapping(value = "download")
	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String fileName = ParameterUtils.getParameter(request, "fileName", ""); // 文件名

		if ("".equals(fileName)) {
			throw new BusinessLogicRunTimeException("参数错误：必须提交下载文件名fileName");
		}
		// 设置文件MIME类型
		response.setContentType("application/force-download");
		// 设置Content-Disposition
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		String fileDirector = SystemParamUtil.getString("sys", "FILE_PATH"); // 文件存放目录
		fileDirector = fileDirector + "sendcargo";
		File f = new File(fileDirector);
		if (!f.exists()) {
			// 路径不存在
			throw new BusinessLogicRunTimeException("文件不存在");
		}

		String filePath = fileDirector + "\\\\" + fileName;

		f = new File(filePath);

		if (!f.exists()) {
			// 路径不存在
			throw new BusinessLogicRunTimeException("文件不存在");
		}

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

			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

}
