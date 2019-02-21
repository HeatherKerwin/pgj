package com.ry.core.service;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.InquiryReply;
import com.ry.core.entity.Loggers;
import com.ry.core.form.company.InquiryReplyRequest;
import com.ry.core.form.loggers.LoggersRequest;
import com.ry.util.page.PageResults;

public interface LoggersService {
	//获得所有写过日志的用户以绑定下拉框
	public List<Map<String,Object>> getAdmin ();
	
	public PageResults<Map<String,Object>> getLoggersPage(LoggersRequest logRes) throws Exception;
	
	public Integer addLoggers(Loggers loggers);
	
	public void updateLoggers(Loggers loggers);
	
	public Loggers getLoggers(Integer id);
	
	public List<Map<String, Object>> getByObj(LoggersRequest logRes) throws Exception;
	
}
