package com.ry.core.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.entity.PushLog;
import com.ry.core.form.PushLogForm;
import com.ry.util.page.PageResults;

@Repository
public interface PushLogDao {

	/**
	 * 分页显示
	 * @author WKX
	 * @param form
	 * @since 2017年6月1日 下午2:14:41
	 */
	public PageResults<PushLog> getPageList(PushLogForm form);
	
	/**
	 * 保存
	 * @author WKX
	 * @param pushLog
	 */
	public void saveModel(PushLog pushLog);
	
	/**
	 * 获取通过认证的，机构、企业（含注册时间，和报价贴现的城市）
	 * @author WKX
	 * @param log 推送记录（查询条件）
	 */
	public List<Map<String, Object>> getMember(PushLog log);
}