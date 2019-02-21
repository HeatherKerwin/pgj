package com.ry.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileUtil {

	// 临时目录文件转移到存储目录
	public static void moveFile(String tempPath, String uploadPath)
			throws Exception {
		
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
						File t = new File(uploadPath + File.separator
								+ fileName);
						if (!t.createNewFile()) {
							//throw new Exception("创建输出文件失败,文件或已存在");
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
		}
	}

	// 删除文件夹
	public static boolean deletefile(String delpath) throws Exception {
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
						deletefile(delpath + "\\" + filelist[i]);
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

	public static void main(String[] args) throws Exception {
		// FileUtil.FileCopy("E:\\temp\\image\\abc.txt","E:\\src\\image\\abc.txt");
		deletefile("E:\\软件工具包\\apache-tomcat-7.0.61\\webapps\\Ruiyin\\upload\\image");

	}
}
