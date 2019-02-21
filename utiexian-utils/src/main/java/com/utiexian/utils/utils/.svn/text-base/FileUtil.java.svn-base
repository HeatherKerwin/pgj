package com.utiexian.utils.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

import com.utiexian.utils.Constant;
import com.utiexian.utils.MyException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class FileUtil {

	/**
	 * 临时目录文件转移到存储目录
	 * @param tempPath
	 * @param uploadPath
	 * @throws Exception
	 */
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
		}else{
			throw new MyException("文件转存失败！");
		}
	}

	/**
	 * 删除文件夹
	 * @param delpath
	 */
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
	
	/**
	 * 保存BASE64图片
	 * @param base64Image BASE64格式图片
	 * @param suffix (.jpge\.jpg\.png)
	 * @param path 保存路径
	 */
	public static Map<String,Object> image(String base64Image,String suffix,String path) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
    	if (StringUtils.isBlank(base64Image)){//图像数据为空
    		throw new MyException("图片不能为空！");
    	}	
    	BASE64Decoder decoder = new BASE64Decoder();  
    	try{  
    		byte[] b = decoder.decodeBuffer(base64Image);  
    		for(int i=0;i<b.length;++i){  
    			if(b[i]<0){//调整异常数据  
    				b[i]+=256;  
    			}  
    		}  
    		//生成jpeg图片
    		String imgFilePath = path + Constant.TEMP_PATH;//保存目录包名的绝对路径
    		
    		String appimg = Constant.TEMP_PATH;//保存目录包名的绝对路径
    		
    		//检查目录
			File uploadDir = new File(imgFilePath);
			if(!uploadDir.isDirectory()){
				uploadDir.mkdirs();
			}
    		String dirName = null;
    		if (dirName == null) {
    			dirName = "image";
    		}
    		imgFilePath += dirName + "/";
    		appimg += dirName + "/";
    		
    		File saveDirFile = new File(imgFilePath);
    		if (!saveDirFile.exists()) {
    			saveDirFile.mkdirs();
    		}
		 
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    		String ymd = sdf.format(new Date());
    		imgFilePath += ymd + "/";
    		appimg += ymd + "/";
		
    		File dirFile = new File(imgFilePath);
    		if (!dirFile.exists()) {
    			dirFile.mkdirs();
    		}
		
    		if(StringUtils.isBlank(suffix))suffix = ".jpg";//图片类型
    		
    		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    		String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + suffix;
		     
		    OutputStream pic = new FileOutputStream(imgFilePath+newFileName);  
		    result.put("base64Image", appimg+newFileName);
		    pic.write(b);  
		    pic.flush();  
		    pic.close();  
		 }   
		 catch (Exception e){  
		     e.printStackTrace();
		     throw new MyException("图片保存失败！");
		 }  
    	 return result;
    }
	
    /**
     * 图片转化成base64字符串  
     */
    public static String GetImageStr(String imgFile) {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
        InputStream in = null;  
        byte[] data = null;//读取图片字节数组  
        try{
            in = new FileInputStream(imgFile);          
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        }catch (IOException e){  
            e.printStackTrace();  
        }  
        //对字节数组Base64编码  
        BASE64Encoder encoder = new BASE64Encoder();  
        return encoder.encode(data);//返回Base64编码过的字节数组字符串  
    }  
      
    /**
     * base64字符串转化成图片 
     * @param imgStr
     */
    public static boolean GenerateImage(String imgStr){   //对字节数组字符串进行Base64解码并生成图片  
        if (imgStr == null)return false; //图像数据为空  
        BASE64Decoder decoder = new BASE64Decoder();  
        try {  
            //Base64解码  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i) {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  
            String imgFilePath = "D:\\tupian\\new.jpg";//新生成的图片  
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return true;  
        }catch (Exception e){  
            return false;  
        }
    }
    
    /**
     * 根据url拿取file
     * @author WKX
     * @param url 访问路径
     * @param suffix 文件后缀名（jpg、png等）
     */
    public static File createFileByUrl(String url, String suffix) {
        byte[] byteFile = getImageFromNetByUrl(url);
        if (byteFile != null) {
            File file = getFileFromBytes(byteFile, suffix);
            return file;
        } else {
            return null;
        }
    }
    
    /**
     * 根据地址获得数据的字节流
     * @author WKX
     * @param strUrl 网络连接地址
     */
    private static byte[] getImageFromNetByUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
            byte[] btImg = readInputStream(inStream);// 得到图片的二进制数据
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 从输入流中获取数据
     * @author WKX
     * @param inStream 输入流
     */
    private static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
    
    /**
     * 创建临时文件
     * @author WKX
     * @param b
     * @param suffix 文件后缀名（jpg、png等）
     */
    private static File getFileFromBytes(byte[] b, String suffix) {
        BufferedOutputStream stream = null;
        File file = null;
        try {
            file = File.createTempFile("pattern", "." + suffix);
            System.out.println("临时文件位置："+file.getCanonicalPath());
            FileOutputStream fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }
    
    /**
	 * 保存BASE64文件
     * @author ZWD
	 * @param base64File BASE64文件
	 * @param path 保存路径（含文件名）
	 */
	public static Map<String,Object> base64ToFile(String base64File,String path) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
    	if (StringUtils.isBlank(base64File)){//数据为空
    		throw new MyException("文件不能为空！");
    	}	
    	BASE64Decoder decoder = new BASE64Decoder();  
    	try{  
    		byte[] b = decoder.decodeBuffer(base64File);  
    		for(int i=0;i<b.length;++i){  
    			if(b[i]<0){//调整异常数据  
    				b[i]+=256;  
    			}
    		}
		    OutputStream pic = new FileOutputStream(path);
		    result.put("base64Image", path);
		    pic.write(b);
		    pic.flush();
		    pic.close();
		 }catch (Exception e){  
		     e.printStackTrace();
		     throw new MyException("图片保存失败！");
		 }  
    	 return result;
    }
}