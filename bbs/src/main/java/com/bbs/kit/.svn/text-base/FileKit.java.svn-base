package com.bbs.kit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bbs.Constant;
import com.blade.Blade;

import blade.kit.StringKit;

public class FileKit {

	//临时目录文件转移到存储目录
	public static void moveFile(String tempPath, String uploadPath) throws Exception {
		File file = new File(tempPath);
		if ((file.exists())) {
			File target = new File(uploadPath);
			if (!target.exists()) {
				target.mkdirs();
			}
			File[] temp = null;
			if (file.listFiles() == null) {
				temp = new File[] { file };
			} else {
				temp = file.listFiles();
			}

			if ((temp != null) && (temp.length > 0)) {
				int i = 0;
				for (int length = temp.length; i < length; i++) {
					if (!temp[i].isDirectory()) {
						String fileName = temp[i].getName();
						File t = new File(uploadPath + File.separator + fileName);
						if (!t.createNewFile()) {
							return;
						}
						FileOutputStream out = new FileOutputStream(t);
						FileInputStream in = new FileInputStream(temp[i]);
						byte[] buffer = new byte[256];
						while (in.read(buffer) > 0) {
							out.write(buffer);
						}
						in.close();
						out.close();
					}
				}				
			}
			deleteFile(tempPath);
		}
	}

	// 删除文件夹
	public static boolean deleteFile(String delpath) throws Exception {
		try {
			File file = new File(delpath);
			// 当且仅当此抽象路径名表示的文件存在且 是一个目录时，返回 true
			if (!file.isDirectory()) {
				file.delete();
			} else if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File delfile = new File(delpath + "\\" + filelist[i]);
					if (!delfile.isDirectory()) {
						delfile.delete();
						System.out
								.println(delfile.getAbsolutePath() + "删除文件成功");
					} else if (delfile.isDirectory()) {
						deleteFile(delpath + "\\" + filelist[i]);
					}
				}
				System.out.println(file.getAbsolutePath() + "删除成功");
				file.delete();
			}

		} catch (FileNotFoundException e) {
			System.out.println("deletefile() Exception:" + e.getMessage());
		}
		return true;
	}
	
	/**
	 * 把临时文件移动到上传的目录中
	 * @author WKX
	 * @param avatar_path 临时文件目录
	 * @throws Exception
	 * @since 2016年11月3日 下午2:39:52
	 */
	public static String tempToUpload(String avatar_path) throws Exception{
		if(StringKit.isBlank(avatar_path))return avatar_path;
		
		String temp = Blade.me().config().get("img.uploadPath");
		String uploadPath = Blade.me().config().get("img.uploadPath") + Constant.UPLOAD_PATH;
		//检查目录
		File uploadDir = new File(uploadPath);
		if(!uploadDir.isDirectory()){
			uploadDir.mkdirs();
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String extpath = df.format(new Date());
		
		String pString = uploadPath + extpath;
		String tString = temp + avatar_path;
		FileKit.moveFile(tString, pString);
		String path = Constant.UPLOAD_PATH + extpath + avatar_path.substring(avatar_path.lastIndexOf(File.separator), avatar_path.length());
		return path;
	}

	public static void main(String[] args) throws Exception {
		// FileUtil.FileCopy("E:\\temp\\image\\abc.txt","E:\\src\\image\\abc.txt");
		deleteFile("E:\\软件工具包\\apache-tomcat-7.0.61\\webapps\\Ruiyin\\upload\\image");

	}
}
