package com.ry.core.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.AccountrecordDao;
import com.ry.core.dao.BaseDao;
import com.ry.core.dao.OrgDao;
import com.ry.core.entity.Accountrecord;
import com.ry.core.entity.Org;
import com.ry.core.form.AccountrecordForm;
import com.ry.core.service.AccountrecordService;
import com.ry.util.page.PageResults;

@Service
public class AccountrecordServiceImpl extends BaseDao<Accountrecord, Integer> implements AccountrecordService {

	@Resource
	AccountrecordDao accountrecordDao;
	
	@Resource
	OrgDao orgDao;
	
	@Override
	public List<Accountrecord> getList(Integer memberId,String day) {
		
		return accountrecordDao.getList(memberId, day);
	}

	@Override
	public Integer saveAccountrecord(Accountrecord accountrecord) {
		return accountrecordDao.addAccountrecord(accountrecord);
			
	}

	@Override
	public void updateAccountrecord(Accountrecord accountrecord) {
		accountrecordDao.updateAccountrecord(accountrecord);		
	}

	@Override
	public List<Double> allprice(String memberid, String publishtime) {
		
		return accountrecordDao.allprice(memberid, publishtime);
	}

	@Override
	public List<Double> tiexianlixi(String memberid, String publishtime) {
		
		return accountrecordDao.tiexianlixi(memberid, publishtime);
	}

	@Override
	public Double sallprice(String memberid, String publishtime, List paramList) {
		
		return accountrecordDao.sallprice(memberid, publishtime, paramList);
	}

	@Override
	public Accountrecord getAccountrecord(Integer id) {

		return accountrecordDao.getAccountrecord(id);
	}

	@Override
	public void delete(Accountrecord accountrecord) {
		
		accountrecordDao.delete(accountrecord);
	}
	
	@Override
	public void update(Accountrecord accountrecord) {
		
		accountrecordDao.updateAccountrecord(accountrecord);
	}

	@Override
	public BigDecimal countPriceByTypeAndMonth(String type) {
		return accountrecordDao.countPriceByTypeAndMonth(type);
		
	}
	
	@Override
	public BigDecimal countPriceByTypeAndWeek(String type) {
		return accountrecordDao.countPriceByTypeAndWeek(type);
		
	}
	
	@Override
	public BigDecimal countPriceByTypeAday(String type) {
		return accountrecordDao.countPriceByTypeAday(type);
		
	}

	@Override
	public List<Map<String,Object>> countPriceByDayAndMonth(Integer memberid,Integer belong) {
		return accountrecordDao.countPriceByDayAndMonth(memberid,belong);
	}

	@Override
	public List<Map<String,Object>> countPriceByWeek(Integer memberid) {
		return accountrecordDao.countPriceByWeek(memberid);
	}

	@Override
	public List<Accountrecord> findDiscountAday(Integer memberid,String day) {
		return accountrecordDao.findDiscountAday(memberid,day);
	}

	@Override
	public List<Accountrecord> findDiscountByWeek(String type,String start,String end) {
//		return accountrecordDao.findDiscountByWeek(type, start, end);
		return new ArrayList<Accountrecord>();
	}

	@Override
	public List<Accountrecord> findDiscountByMonth(Integer memberid,String start,String end) {
		return accountrecordDao.findByTwoDates(memberid, start, end);
	}

	@Override
	public List<Map<String,Object>> countPiaoju(String begin, String end, String type,Integer memberid) {
		return accountrecordDao.countPiaoju(begin,end,type,memberid);
	}

	@Override
	public List<Accountrecord> findAccountrecordByDaoqidate() {
		return accountrecordDao.findAccountrecordByDaoqidate();
	}

	@Override
	public List<Accountrecord> findByCustomizedParam(AccountrecordForm form) {
		return accountrecordDao.findByCustomizedParam(form);
	}

	@Override
	public List<Accountrecord> getDiscount(Integer memberId, String type,
			String start, String end) {
		
		return accountrecordDao.getDiscount(memberId, type, start, end);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.AccountrecordService#getByForm(com.ry.core.form.AccountrecordForm)
	 */
	public List<Accountrecord> getByForm(AccountrecordForm form) throws Exception{
		return accountrecordDao.getByForm(form);
	}

	@Override
	public List<Map<String, Object>> sumLixi(String begin, String end,
			String type, Integer memberid) {
		return accountrecordDao.sumLixi(begin,end,type,memberid);
	}

	@Override
	public Accountrecord getAccountrecordByDiscountrecordId(Integer id) {
		
		return accountrecordDao.getAccountrecordByDiscountrecordId(id);
	}

	@Override
	public List<Map<String, Object>> countPrice(Integer memberid,Integer belong, Date begin, Date end) {
		return accountrecordDao.countPrice(memberid,belong, begin, end);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.AccountrecordService#getSumByMemberId(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public List<Map<String, Object>> getSumByMemberId(Integer memberId,Integer belong,String start,String end) throws Exception{
		return accountrecordDao.getSumByMemberId(memberId,belong, start, end);
	}
	
	@Override
	public List<Accountrecord> alltiexian(Integer memberid,String istiexian){
		return accountrecordDao.alltiexian(memberid, istiexian);
	}
	
	@Override
	public List<Accountrecord> customcount(Integer memberId, String type,String end,String start,Float allprice,Integer type1){
		return accountrecordDao.customcount(memberId, type, end, start, allprice, type1);
	}
	
	@Override
	public List<Map<String, Object>> noticeConnect(Integer memberId, String istiexian,Date end,Date start,Float allprice,Integer type1,Integer belong,String type,Integer limit){
		return accountrecordDao.noticeConnect(memberId, istiexian, end, start, allprice, type1,belong,type,limit);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.AccountrecordService#getForRemindExpire(java.util.Date, java.util.Date, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<Map<String, Object>> getForRemindExpire(Date beforeDaoqishijian, Date daoqishijian, String statue, String tiexianType, String istiexian) {
		return accountrecordDao.getForRemindExpire(beforeDaoqishijian, daoqishijian, statue, tiexianType, istiexian);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.AccountrecordService#getByDcrdIdAndBelong(java.lang.Integer, java.lang.Integer)
	 */
	public Accountrecord getByDcrdIdAndBelong(Integer dcrdId,Integer belong){
		return accountrecordDao.getByDcrdIdAndBelong(dcrdId, belong);
	}
	
	public List<Map<String,Object>> ifExistTiXin(Integer memberid,Integer belong,String date1,String date2){
		return accountrecordDao.ifExistTiXin(memberid, belong,date1,date2);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.AccountrecordService#getListByBelong(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	public List<Accountrecord> getListByBelong(Integer memberId,Integer belong, String day){
		return accountrecordDao.getListByBelong(memberId, belong, day);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.AccountrecordService#getPageList(com.ry.core.form.AccountrecordForm, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<Map<String, Object>> getPageList(AccountrecordForm form, Integer pageIndex, Integer pageSize,Integer memberId) {
		return accountrecordDao.getPageList(form, pageIndex, pageSize, memberId);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.AccountrecordService#getList(com.ry.core.form.AccountrecordForm, java.lang.Integer)
	 */
	public List<Map<String, Object>> getList(AccountrecordForm form, Integer memberId) {
		return accountrecordDao.getList(form, memberId);
	}
	
	@Override
	public PageResults<Map<String, Object>> getNoticePage(Integer memberId,Integer pageIndex,Integer pageSize) throws Exception {
		Integer orgId = null;
		Org org = orgDao.getByMemberId(memberId);
		if(org!=null){
			orgId = org.getId();
		}
		return accountrecordDao.getPageList(orgId, memberId, pageIndex, pageSize);
	}
}