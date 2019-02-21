package com.ry.ryapp.listener;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ry.core.util.Constant;
import com.ry.util.PropertiesUtil;

/**
 * @ClassName: SiteServletContentListener
 * @Description: TODO
 * @author Ry-wk
 * @date 2015年10月30日
 *
 */
public class SiteServletContentListener implements ServletContextListener{

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			//初始化存放项目真实路径
			PropertiesUtil.wwwroot = arg0.getServletContext().getRealPath("/");
			
			Constant.properties = PropertiesUtil.loadProperties(arg0.getServletContext().getRealPath("WEB-INF/classes/config.properties"));
//			Constant.UPLOAD_PATH = Constant.properties.getProperty("uploadpath")+Constant.UPLOAD_PATH ;
//			Constant.TEMP_PATH = Constant.properties.getProperty("uploadpath")+Constant.TEMP_PATH ;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
