package com.ry.rycms.task;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.aspectj.util.FileUtil;

import com.ry.core.service.DiscountrecordService;
import com.ry.core.util.Constant;

/**
 * 备份贴现订单图片
 * @author BKY
 */
public class BackupDisImgTask{
	
	private static Logger logger = Logger.getLogger(BackupDisImgTask.class);
	
	@Resource
	DiscountrecordService discountrecordService;
	
	public void execute(){
		logger.info("------------------定时任务:备份贴现订单图片------------------");
		String temp = Constant.properties.getProperty("uploadpath");	// 保存目录包名的绝对路径：从这里拿出图片
		String realPath = Constant.properties.getProperty("disImg");	// 保存目录包名的绝对路径：把图片放入此路径下
		try {
			String orderflag = "3";		// 订单状态: 3-已完成
			Date date = new Date();
			String time = new SimpleDateFormat("yyyyMMdd").format(date);
			Date endOrderTime = date;
			
			java.util.Calendar c = java.util.Calendar.getInstance();
			c.setTime(date);
			c.add(java.util.Calendar.DATE, -7);
			Date startOrderTime = c.getTime();
			
			List<Object> discountrecordList = discountrecordService.disImg(orderflag, startOrderTime, endOrderTime);
			List<String> copyList = new ArrayList<String>();	//保存已经复制过的文件，当再遇到时不需再进行复制
			for (Object obj : discountrecordList) {
				String[] imgPath = obj.toString().split(",");
				for (int i = 0; i < imgPath.length; i++) {
					String tempPath = temp + imgPath[i]; 		// 获取图片文件的完整路径(包含文件名)
					String newImgPath = temp + realPath + time; // 图片要保存到的新的路径(不含图片名)
					File fromFile = new File(tempPath);
					if (!copyList.contains(tempPath)) {			//如果数组中该字符串已经存在，说明此文件已copy过了
						if (fromFile.exists()) {
							File toFile = new File(newImgPath);
							if (!toFile.exists()) {
								toFile.mkdirs();
							}
							FileUtil.copyFile(fromFile, toFile);
							copyList.add(tempPath);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("备份贴现订单图片异常："+e.getMessage());
			e.printStackTrace();
		}
	}
}
