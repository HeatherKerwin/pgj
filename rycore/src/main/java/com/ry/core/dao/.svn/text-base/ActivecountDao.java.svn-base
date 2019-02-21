package com.ry.core.dao;

import java.util.List;

import com.ry.core.entity.Accountrecord;
import com.ry.core.entity.Activecount;
import com.ry.core.form.ActivecountForm;

public interface ActivecountDao {
	/**
	 * 
	* @Title: getAllCount
	* @Description: 获取总日活
	* @param @return    参数
	* @return Long    返回类型
	* @throws
	 */
	public Long getAllCount(); 
	/**
	 * 
	* @Title: getCountByRpAndTime
	* @Description: 按时间段和推荐人查询日活
	* @param @param rp 推荐人
	* @param @param beginTime
	* @param @param endTime
	* @param @return    参数
	* @return Long    返回类型
	* @throws
	 */
	public Long getCountByRpAndTime(String rp, Long beginTime, Long endTime);
	
	
	public Long getCountActive(String begintimed, String endtimed, Integer memberid);
	
	public Long countActive(Long begintimed, Long endtimed, Integer memberid);			
	
	public void addActivecount(Activecount activecount);
	
	public List<Activecount> getList(ActivecountForm activecountForm);
}
