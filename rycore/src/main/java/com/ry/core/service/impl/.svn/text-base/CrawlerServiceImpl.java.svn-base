package com.ry.core.service.impl;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.ry.core.entity.Shibor;
import com.ry.core.service.CrawlerService;
import com.ry.core.service.ShiborService;
import com.ry.util.HttpUtil;
import com.ry.util.Result;

@Service
public class CrawlerServiceImpl implements CrawlerService{
	
	@Resource
	ShiborService shiborService;

	@Override
	public void getGongcuiInfo() {
		try {
			Document doc = Jsoup.connect("http://www.live.chinacourt.org/fygg/index/kindid/5.shtml").get();
			Elements list = doc.select("tr.fygg_contents");
			for(Element temp:list){
				Element td1 = temp.child(0);
				System.out.println(td1.text());
			}
			System.out.println(list.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		new CrawlerServiceImpl().crawShiborInfo();
	}
	@Override
	public void crawShiborInfo() {
		
			Result result = HttpUtil.sendGet("http://www.shibor.org/shibor/web/html/shibor.html", null, null, "UTF-8");
			String html = HttpUtil.getParam(result);
	        Document doc=Jsoup.parse(html);//转换为Dom树
	        Elements date = doc.select("td.infoTitleW");
	        String tdStr = date.html();
	        String dateStr = "";
	        if(tdStr.length()>10){
	        	dateStr = tdStr.substring(0, 10);
	        }
	        
	        Shibor temp = shiborService.getShibor(dateStr);
	        if(temp!=null){//防止重复数据
	        	return;
	        }
	        
			Elements tblist = doc.select("table.shiborquxian");
			for(Element tb:tblist){
				Elements trList = tb.child(0).children();
				if(trList.size()>=8){
					Shibor shibor =  new Shibor();
					shibor.setDay(dateStr);
					shibor.setShibor1(trList.get(0).child(2).text());
					shibor.setShibor2(trList.get(1).child(2).text());
					shibor.setShibor3(trList.get(2).child(2).text());
					shibor.setShibor4(trList.get(3).child(2).text());
					shibor.setShibor5(trList.get(4).child(2).text());
					shibor.setShibor6(trList.get(5).child(2).text());
					shibor.setShibor7(trList.get(6).child(2).text());
					shibor.setShibor8(trList.get(7).child(2).text());
					shibor.setUpdown1(checkUpDown(trList.get(0).child(3).child(0).attr("src"),trList.get(0).child(4).text()));
					shibor.setUpdown2(checkUpDown(trList.get(1).child(3).child(0).attr("src"),trList.get(1).child(4).text()));
					shibor.setUpdown3(checkUpDown(trList.get(2).child(3).child(0).attr("src"),trList.get(2).child(4).text()));
					shibor.setUpdown4(checkUpDown(trList.get(3).child(3).child(0).attr("src"),trList.get(3).child(4).text()));
					shibor.setUpdown5(checkUpDown(trList.get(4).child(3).child(0).attr("src"),trList.get(4).child(4).text()));
					shibor.setUpdown6(checkUpDown(trList.get(5).child(3).child(0).attr("src"),trList.get(5).child(4).text()));
					shibor.setUpdown7(checkUpDown(trList.get(6).child(3).child(0).attr("src"),trList.get(6).child(4).text()));
					shibor.setUpdown8(checkUpDown(trList.get(7).child(3).child(0).attr("src"),trList.get(7).child(4).text()));
					shiborService.saveShibor(shibor);
				}
				for(Element tr:trList){
					Elements tdList = tr.children();
					if(tdList.size()<5){
						continue;
					}
//					System.out.println(tdList.get(0).text()+"0|"+tdList.get(1).text()+"1|"+tdList.get(2).text()+"2|"
//							+tdList.get(3).child(0).attr("src")+"3|"+tdList.get(4).text());
				}
			}
			System.out.println(tblist.size());
		
	}
	/**
	 * 正负判断
	 * @param str
	 * @param data
	 * @return
	 */
	public String checkUpDown(String str, String data) {
		String temp = data.trim().replace(" ", "");
		String numF = "\\d+.+\\d";
		Pattern pattern = Pattern.compile(numF);
		Matcher ma = pattern.matcher(temp);
		String result = "";
		while (ma.find()) {
			result += ma.group();
		}
		if (str.indexOf("downicon") > -1) {
			return "-" + result;
		} else {
			return result;
		}
	}
}
