package com.bsh.community.board.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component("downUtil")
public class SpringDownload {

	public void upFile(List<MultipartFile> uploadfile, String path) {

		for (MultipartFile file : uploadfile) {
			if (!file.isEmpty()) {

				File convFile = new File(path + file.getOriginalFilename());
				try {
					file.transferTo(convFile);
				} catch (Exception e) {
					System.out.println("upload err");
				}
			}
		}

	}

	public void downFile(HttpServletRequest req, HttpServletResponse resp, String path) {

		if (path.length() == 0)
			path = "d:/upload/";

		String filename = req.getParameter("file").toString();
		
		File realName = new File("addRealName");
		
		
		
		OutputStream outputStream = null;

		try {
			File file = new File(path + filename);
			file.renameTo(realName);
			
			String filetype = filename.substring(filename.indexOf(".") + 1, filename.length());
			if (filetype.trim().equalsIgnoreCase("txt")) {
				resp.setContentType("text/plain");
			} else {
				resp.setContentType("application/octet-stream");
			}

			resp.setContentLength((int) file.length());

			if (req.getHeader("User-Agent").indexOf("MSIE") != -1) {
				filename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", " ");
				// filename = new String(filename.getBytes("UTF-8"), "8859_1");
			} else {
				filename = new String(filename.getBytes("EUC-KR"), "8859_1");
			}

			resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

			outputStream = resp.getOutputStream();
			FileInputStream fis = null;

			try {
				fis = new FileInputStream(file);
				FileCopyUtils.copy(fis, outputStream);
			} finally {
				if (fis != null)
					fis.close();
			}

		} catch (IOException e) {
			throw new RuntimeException(e);

		} finally {
			try {
				outputStream.close();
				resp.flushBuffer();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
