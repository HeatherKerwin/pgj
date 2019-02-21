package com.ry.core.dao;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.ReturnVisitRecord;
import com.ry.core.form.MemOrder.MemOrderPageRequest;
import com.ry.util.page.PageResults;

public interface ReturnVisitRecordDao {
	
	/**
	 * 根据主键获取对象
	 * @author cx
	 * @param id
	 */
	public ReturnVisitRecord getById(Integer id);
	
	/**
	 * 分页获取意见反馈列表
	 * cx
	 * @return
	 */
	public PageResults<ReturnVisitRecord> getPageReturn(MemOrderPageRequest mem);
	
	/**
	 * 按条件查询获取意见反馈列表
	 * cx
	 * month:月份   state:回访状态   memberid:用户ID
	 * @return
	 */
	public PageResults<Map<String,Object>> getPageSearch(Integer month,Integer state,Long telphone,Integer start ,Integer length);
	
	/**
	 * 根据memberid查询数据
	 * cx
	 * memberid:用户ID
	 * @return
	 */
	public List<ReturnVisitRecord> getList(Integer memberid);
	
	/**
	 * 更新订单
	 * cx
	 * @param returnVisitRecord
	 */
	public void updateReturnVisitRecord (ReturnVisitRecord returnVisitRecord);
	
	/**
	 * 定时任务
	 * @param n
	 */
	public List<Map<String,Object>> dingShiOne(Integer n);
	
	/**
	 * 添加
	 * cx
	 * @param returnVisitRecord
	 */
	public void saveReturnVisitRecord(ReturnVisitRecord returnVisitRecord);
	
	/**
	 * 查询相同
	 * cx
	 * @param memberid
	 * @param type
	 */
	public ReturnVisitRecord loadReturnVisitRecord(String date,Integer memberid,Integer type);
	
	/**
	 * 导出EXCEL
	 * cx
	 * @param memberid
	 * @param type
	 */
	public List<ReturnVisitRecord> getByObj(String time,String state,String memberid);
	
	/**
	 * 查询是否已经存在
	 * cx
	 * @param memberid
	 * @param time
	 */
	public List<Map<String,Object>> exist(String time,String memberid);
}
