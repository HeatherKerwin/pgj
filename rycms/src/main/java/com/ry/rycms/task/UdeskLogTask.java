package com.ry.rycms.task;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import com.ry.core.util.Constant;
import com.ry.util.DateUtil;
import com.ry.util.HttpUtil;
import com.ry.util.MD5Util;
import com.ry.util.Result;
import com.ry.util.baofoo.util.JXMConvertUtil;

import net.sf.json.JSONObject;

public class UdeskLogTask{
	
	private static String KEY = "76f360be075f74f7d51e52a58bc50baf";
	
	@SuppressWarnings("unchecked")
	public void execute(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		String end = DateUtil.formart(cal.getTime(), DateUtil.FORMART3);
		cal.add(Calendar.DATE, -1);
		String start = DateUtil.formart(cal.getTime(), DateUtil.FORMART3);
		
		try {
			String path = Constant.properties.getProperty("UDESK_FILE");//获取配置的UDESK录音文件保存地址
			String sign = MD5Util.getMD5Str("start_time="+start+"&end_time="+end+""+"&"+KEY);

			String url = "http://utiexian.udesk.cn/api/v2/ucpapp/calllogs?start_time="+start+"&end_time="+end+"&sign="+sign;
			Result r = HttpUtil.sendGet(url, null, null,"utf-8");
			HttpEntity he = r.getHttpEntity();
			if(he==null)throw new Exception();
			
			String res = EntityUtils.toString(he);
			Map<String,Object> map = JXMConvertUtil.IteratorHash(JSONObject.fromObject(res));
			List<Map<String,Object>> list = (List<Map<String, Object>>) map.get("item");
			
			if(list!=null && list.size()>0){
				for(Map<String,Object> m:list){
					if(m.get("record_url")!=null){
						String info = "";
						String time = m.get("call_start_at").toString();
						info += DateUtil.formart(DateUtil.parser(time, DateUtil.FORMART), "yyyyMMddHHmmss");
						
						info += "【"+ m.get("call_type") +"】";
						info += "KH" + m.get("call_number");
						info += "KF" + m.get("agent_nick_name");
						
						//下载网络文件
				        String f = m.get("record_url").toString();
				        downLoadFromUrl(f, info+".mp3", path);
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 下载文件（下载通话录音）
	 * @author WKX
	 * @param urlStr 外网文件地址
	 * @param fileName 文件名称
	 * @param savePath 保存路径
	 * @throws IOException
	 * @since 2017年6月27日 下午3:26:43
	 */
	public static void downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(3*1000);//设置超时间为3秒
        
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");//防止屏蔽程序抓取而返回403错误  
        InputStream inputStream = conn.getInputStream();//得到输入流
        byte[] getData = readInputStream(inputStream);//获取自己数组
        
        File saveDir = new File(savePath);//文件保存位置
        if(!saveDir.exists()){
            saveDir.mkdir();
        }
        File file = new File(saveDir+File.separator+fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos!=null){
            fos.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }
    }
	
	/**
	 * 读取文件流
	 * @author WKX
	 * @param inputStream
	 * @throws IOException
	 * @since 2017年6月27日 下午3:27:00
	 */
	public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
}