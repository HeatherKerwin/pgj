package com.ry.core.service;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.ReturnVisitRecord;
import com.ry.core.form.MemOrder.MemOrderPageRequest;
import com.ry.util.page.PageResults;

public interface ReturnVisitRecordService {
	
	public PageResults<ReturnVisitRecord> getPageReturn(MemOrderPageRequest mem);
	
	public List<ReturnVisitRecord> getList(Integer memberid);
	
	public ReturnVisitRecord getById(Integer id);
	
	public void updateReturnVisitRecord (ReturnVisitRecord returnVisitRecord);
	
	public PageResults<Map<String,Object>> getPageSearch(Integer month,Integer state,Long telphone,Integer start, Integer length);
	
	public List<Map<String,Object>> dingShiOne(Integer n);
	
	public void saveReturnVisitRecord (ReturnVisitRecord returnVisitRecord);
	
	public List<ReturnVisitRecord> getByObj(String time,String state,String memberid);
	
	public List<Map<String,Object>> exist(String time,String memberid);
}
