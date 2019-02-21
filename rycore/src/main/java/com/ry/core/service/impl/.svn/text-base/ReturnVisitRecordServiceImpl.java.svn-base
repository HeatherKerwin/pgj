package com.ry.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.ReturnVisitRecordDao;
import com.ry.core.entity.ReturnVisitRecord;
import com.ry.core.form.MemOrder.MemOrderPageRequest;
import com.ry.core.service.ReturnVisitRecordService;
import com.ry.util.page.PageResults;

@Service
public class ReturnVisitRecordServiceImpl implements ReturnVisitRecordService{
	
	@Resource
	ReturnVisitRecordDao returnVisitRecordDao;

	
	public PageResults<ReturnVisitRecord> getPageReturn(MemOrderPageRequest mem){
		return returnVisitRecordDao.getPageReturn(mem);
	}
	
	public List<ReturnVisitRecord> getList(Integer memberid){
		return returnVisitRecordDao.getList(memberid);
	}

	public ReturnVisitRecord getById(Integer id) {
		return returnVisitRecordDao.getById(id);
	}
	
	public void updateReturnVisitRecord (ReturnVisitRecord returnVisitRecord){
		returnVisitRecordDao.updateReturnVisitRecord(returnVisitRecord);
	}
	
	public PageResults<Map<String,Object>> getPageSearch(Integer month,Integer state,Long telphone,Integer start ,Integer length){
		return returnVisitRecordDao.getPageSearch(month, state, telphone, start ,length);
	}
	
	public List<Map<String,Object>> dingShiOne(Integer n){
		return returnVisitRecordDao.dingShiOne(n);
	}
	
	public void saveReturnVisitRecord (ReturnVisitRecord returnVisitRecord){
		ReturnVisitRecord rv = new ReturnVisitRecord();
		
		Integer memberid = returnVisitRecord.getMemberId();//获取相同的memberid
		Integer type = returnVisitRecord.getType();//获取相同的方式
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");//获取两月份之间相同的
		String date = sf.format(new Date());
		rv = returnVisitRecordDao.loadReturnVisitRecord(date,memberid, type);
		if(rv != null){
			//已经保存过的就不新增
			/*rv.setCreateTime(returnVisitRecord.getCreateTime());
			rv.setMemberId(returnVisitRecord.getMemberId());
			rv.setMobile(returnVisitRecord.getMobile());
			rv.setOperatorId(returnVisitRecord.getOperatorId());
			rv.setRegtime(returnVisitRecord.getRegtime());
			rv.setRemarks(returnVisitRecord.getRemarks());
			rv.setState(returnVisitRecord.getState());
			rv.setType(returnVisitRecord.getType());
			returnVisitRecordDao.updateReturnVisitRecord(rv);*/
		}else{
			returnVisitRecordDao.saveReturnVisitRecord(returnVisitRecord);
		}
	}
	
	public List<ReturnVisitRecord> getByObj(String time,String state,String memberid){
		return returnVisitRecordDao.getByObj(time, state, memberid);
	}
	
	public List<Map<String,Object>> exist(String time,String memberid){
		return returnVisitRecordDao.exist(time, memberid);
	}
}
