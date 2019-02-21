package com.ry.core.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.HongbaoActivity;
import com.ry.core.entity.HongbaoDetail;

public interface HongbaoDetailDao {
	
	List<HongbaoDetail> getList(Integer memberid);	
	
	List<HongbaoDetail> getListFlag0();
	
	List<HongbaoDetail> getHistoryList(Integer memberid);
	
	List<HongbaoDetail> getUsedList(Integer memberid);	
	
	HongbaoDetail getHongbaoDetail(Integer id); 
	
	void updateHongbaoDeteil(HongbaoDetail hongbaoDetail);
	
	HongbaoDetail getHongbaoDetail(String orderNumber);
	
	void addHongbaoDetail(HongbaoDetail hongbaoDetail);
	
	BigInteger countHongbaoDetail(Integer hid);
	
	List<HongbaoDetail> getHongbaoDetail(Integer sid,String phone);
		
	void deleteHongbaoDetail(HongbaoDetail hongbaoDetail);

	Map<String, String> getCount(Integer id);

	List<Integer> getHids();

	HongbaoActivity getCountByBean(Integer id);

	HongbaoActivity getActivityById(Integer id);
	
	/**
	 * 根据订单编号获取红包[获取订单用过的红包]
	 * @author WKX
	 * @param ordernumber
	 * @since 2016年1月13日 上午9:42:27
	 */
	public List<HongbaoDetail> getByOrdernumber(String ordernumber);
}