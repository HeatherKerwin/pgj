package com.ry.core.dao;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.Loggers;
import com.ry.core.form.loggers.LoggersRequest;
import com.ry.util.page.PageResults;
/**
 * 
 * @author zwd
 * 关于日志的一些操作
 */
public interface LoggersDao {
	/**
	 * 	
	 * @param create_Id 创建人
	 * @param start_Time 起始时间
	 * @param end_time 最终时间
	 * @return 
	 */
	public PageResults<Map<String,Object>> getPage(LoggersRequest logRes) throws Exception;
	
	public Integer addLoggers(Loggers loggers);
	
	public void updateLoggers(Loggers loggers);
	
	public Loggers getLoggers(Integer id);
	
	/**
	 * 查询所有添加过日志的用户
	 * @return
	 */
	public List<Map<String,Object>> getAdmins();
	
	public List<Map<String,Object>> getByObj (LoggersRequest logRes) throws Exception;
}
