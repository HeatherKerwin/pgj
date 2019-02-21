package com.ry.core.service;

import com.ry.core.entity.PushLog;
import com.ry.core.form.PushLogForm;
import com.ry.util.page.PageResults;

public interface PushLogService {
	
	/**
	 * 分页显示历史推送消息
	 * @author WKX
	 * @param form 条件
	 * @since 2017年6月1日 下午2:23:42
	 */
	public PageResults<PushLog> getPageList(PushLogForm form);
	
	/**
	 * 保存消息 并推送消息
	 * @author WKX
	 * @param pushLog 实体
	 * @since 2017年6月1日 下午2:23:26
	 */
	public PushLog saveModelAndPushMsg(PushLog pushLog);
}