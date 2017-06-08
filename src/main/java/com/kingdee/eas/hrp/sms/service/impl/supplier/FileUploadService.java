package com.kingdee.eas.hrp.sms.service.impl.supplier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.stereotype.Service;

import com.kingdee.eas.hrp.sms.service.api.supplier.IFileUploadService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;

@SuppressWarnings("deprecation")
@Service
public class FileUploadService extends BaseService implements IFileUploadService {
	
	/* (non-Javadoc)
	 * @see com.kingdee.eas.hrp.sms.service.impl.sys.IFileUploadService#upload(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@SuppressWarnings({ "rawtypes" })
	public  List<String> upload(HttpServletRequest request,Integer classId) throws IOException {

		List<String> fileList= new ArrayList<String>();
		try {
			DiskFileUpload fu = new DiskFileUpload();
			// 设置允许用户上传文件大小,单位:字节，这里设为2m
			fu.setSizeMax(2 * 1024 * 1024);
			// 设置最多只允许在内存中存储的数据,单位:字节
			fu.setSizeThreshold(4096);
			// 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
			fu.setRepositoryPath("c://windows//temp");
			// 开始读取上传信息
			List fileItems = fu.parseRequest(request);
			// 依次处理每个上传的文件
			Iterator iter = fileItems.iterator();

			// 过滤掉的文件类型
			String[] errorType = { ".exe", ".com", ".cgi", ".asp" };
			// Pattern p = Pattern.compile(regExp);
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				// 忽略其他不是文件域的所有表单信息
				if (!item.isFormField()) {
					String name = item.getName();
					long size = item.getSize();
					if ((name == null || name.equals("")) && size == 0)
						continue;
					// 过滤路径取文件名
					int i = name.lastIndexOf("/");
					if (i == -1)
						i = 0;
					name.substring(i, name.length() - 1);
					for (int temp = 0; temp < errorType.length; temp++) {
						if (name.endsWith(errorType[temp])) {
							throw new IOException(name + ": 错误的文件类型");
						}
					}
					try {
						// 保存上传的文件到指定的目录
						String fileName = "d://temp//" + name;
						item.write(new File(fileName));
						fileList.add(fileName);
						
					} catch (Exception e) {
						// out.println(e);
					}

				}
			}
		} catch (IOException e) {
			// out.println(e);
		} catch (FileUploadException e) {
			// out.println(e);
		}
		
		return fileList;
	}

}
