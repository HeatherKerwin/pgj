package com.ry.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.DiscountrecordPlDao;
import com.ry.core.dao.DistributeOrderPlDao;
import com.ry.core.entity.DiscountrecordPl;
import com.ry.core.entity.DistributeOrderPl;
import com.ry.core.form.DistributeOrderPlForm;
import com.ry.core.form.org.ReviewOrderRequest;
import com.ry.core.service.DistributeOrderPlService;
import com.ry.util.page.PageResults;

@Service
public class DistributeOrderPlServiceImpl implements DistributeOrderPlService {

	@Resource
	DistributeOrderPlDao distributeOrderPlDao;
	
	@Resource
	DiscountrecordPlDao discountrecordPlDao;
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderPlService#getById(java.lang.Integer)
	 */
	public DistributeOrderPl getById(Integer id) {
		return distributeOrderPlDao.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderPlService#saveModel(com.ry.core.entity.DistributeOrderPl)
	 */
	public void saveModel(DistributeOrderPl distributeOrderPl) {
		distributeOrderPlDao.saveModel(distributeOrderPl);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderPlService#updateModel(com.ry.core.entity.DistributeOrderPl)
	 */
	public void updateModel(DistributeOrderPl distributeOrderPl) {
		distributeOrderPlDao.updateModel(distributeOrderPl);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderPlService#deleteModel(java.lang.Integer)
	 */
	public void deleteModel(Integer id) {
		distributeOrderPlDao.deleteModel(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderPlService#getWaitByOrgId(java.lang.Integer)
	 */
	public List<Map<String,Object>> getWaitByOrgId(Integer orgId){
		return distributeOrderPlDao.getWaitByOrgId(orgId);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderPlService#getPageByOrgId(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<Map<String, Object>> getPageList(Integer indexPage, Integer pageSize,DistributeOrderPlForm form) {
		return distributeOrderPlDao.getPageList(indexPage, pageSize, form);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderPlService#getByDcrdId(java.lang.Integer)
	 */
	public List<DistributeOrderPl> getByDcrdId(Integer dcrdId) {
		return distributeOrderPlDao.getByDcrdId(dcrdId);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderPlService#getListByOrgId(java.lang.Integer)
	 */
	public List<Map<String, Object>> getListByOrgId(Integer orgId) {
		return distributeOrderPlDao.getListByOrgId(orgId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderPlService#getInfoById(java.lang.Integer)
	 */
	public Map<String, Object> getInfoById(Integer id){
		return distributeOrderPlDao.getInfoById(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderPlService#getByOrgId(java.lang.Integer)
	 */
	public List<DistributeOrderPl> getByOrgId(Integer orgId) {
		return distributeOrderPlDao.getByOrgId(orgId);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderPlService#getListByDiscIdAndOrgId(java.lang.Integer, java.lang.Integer)
	 */
	public List<DistributeOrderPl> getListByDiscIdAndOrgId(Integer discId, Integer orgId) {
		return distributeOrderPlDao.getListByDiscIdAndOrgId(discId, orgId);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderPlService#getOrderPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.DistributeOrderPlForm)
	 */
	public PageResults<DistributeOrderPl> getOrderPageList(ReviewOrderRequest req) {
		return distributeOrderPlDao.getOrderPageList(req);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderPlService#getOrderPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.DistributeOrderPlForm)
	 */
	public PageResults<Map<String,Object>> getOrderMemberPageList(ReviewOrderRequest req) {
		return distributeOrderPlDao.getOrderMemberPageList(req);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderPlService#updateToAccept(com.ry.core.entity.DistributeOrderPl)
	 */
	public void updateToAccept(DistributeOrderPl distributeOrderPl) throws Exception {
		if(distributeOrderPl==null || distributeOrderPl.getDcrdPlId()==null)throw new Exception("数据异常");
		
		DiscountrecordPl disc = discountrecordPlDao.getModelById(distributeOrderPl.getDcrdPlId());
		if(disc.getOrderflag()!=1)throw new Exception("该订单已过期");
		
		distributeOrderPlDao.updateModel(distributeOrderPl);
		distributeOrderPl.setState(2);//交易中（只要有一家报价就进行）
		disc.setOrderflag(2);//交易中
		discountrecordPlDao.updateModel(disc);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderPlService#getOverrunByCreateTime(java.lang.String)
	 */
	public List<Map<String, Object>> getOverrunByCreateTime(String date) {
		return distributeOrderPlDao.getOverrunByCreateTime(date);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderPlService#getByNo(java.lang.String)
	 */
	public DistributeOrderPl getByNo(String no) throws Exception {
		return distributeOrderPlDao.getByNo(no);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderPlService#getPageListPC(java.lang.Integer, java.lang.Integer, com.ry.core.form.DistributeOrderPlForm)
	 */
	public PageResults<Map<String, Object>> getPageListPC(Integer pageIndex, Integer pageSize,DistributeOrderPlForm form) throws Exception {
		return distributeOrderPlDao.getPageListPC(pageIndex, pageSize, form);
	}
}