package com.ry.core.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.DiscountrecordSpDao;
import com.ry.core.dao.DistributeOrderSpDao;
import com.ry.core.entity.DiscountrecordSp;
import com.ry.core.entity.DistributeOrderSp;
import com.ry.core.form.DistributeOrderSpForm;
import com.ry.core.form.org.ReviewOrderRequest;
import com.ry.core.service.DistributeOrderSpService;
import com.ry.util.page.PageResults;

@Service
public class DistributeOrderSpServiceImpl implements DistributeOrderSpService{

	@Resource
	DistributeOrderSpDao distributeOrderSpDao;
	
	@Resource
	DiscountrecordSpDao discountrecordSpDao;
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderSpService#getById(java.lang.Integer)
	 */
	public DistributeOrderSp getById(Integer id) {
		return distributeOrderSpDao.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderSpService#saveModel(com.ry.core.entity.DistributeOrderSp)
	 */
	public void saveModel(DistributeOrderSp distributeOrderSp) {
		distributeOrderSpDao.saveModel(distributeOrderSp);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderSpService#updateModel(com.ry.core.entity.DistributeOrderSp)
	 */
	public void updateModel(DistributeOrderSp distributeOrderSp) {
		distributeOrderSpDao.updateModel(distributeOrderSp);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderSpService#getByDcrdId(java.lang.Integer)
	 */
	public List<DistributeOrderSp> getByDcrdId(Integer dcrdId) {
		return distributeOrderSpDao.getByDcrdId(dcrdId);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderSpService#getUnReadByOrgId(java.lang.Integer)
	 */
	public List<Map<String, Object>> getList(Integer memberId) {
		return distributeOrderSpDao.getList(memberId);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderSpService#getAllByDcrdSpId(java.lang.Integer)
	 */
	public List<Map<String,Object>> getAllByDcrdSpId(Integer dcrdSpId){
		return distributeOrderSpDao.getAllByDcrdSpId(dcrdSpId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderSpService#getPcAllByDcrdSpId(java.lang.Integer)
	 */
	public List<Map<String,Object>> getPcAllByDcrdSpId(Integer dcrdSpId){
		return distributeOrderSpDao.getPcAllByDcrdSpId(dcrdSpId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderSpService#getInfoById(java.lang.Integer)
	 */
	public Map<String,Object> getInfoById(Integer dtboId){
		return distributeOrderSpDao.getInfoById(dtboId);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderSpService#getCurrentSelectById(java.lang.Integer)
	 */
	public Map<String,Object> getCurrentSelectById(Integer dcrdId){
		return distributeOrderSpDao.getCurrentSelectById(dcrdId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderSpService#getWaitByOrgId(java.lang.Integer)
	 */
	public List<Map<String,Object>> getWaitByOrgId(Integer orgId){
		return distributeOrderSpDao.getWaitByOrgId(orgId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderSpService#getByOrgId(java.lang.Integer)
	 */
	public List<DistributeOrderSp> getByOrgId(Integer orgId) {
		return distributeOrderSpDao.getByOrgId(orgId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordSPService#getPageList(java.lang.Integer)
	 */
	public PageResults<Map<String, Object>> getPageList(Integer pageIndex,Integer pageSize,DistributeOrderSpForm form){
		return distributeOrderSpDao.getPageList(pageIndex, pageSize, form);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderSpService#getInfoByDtboId(java.lang.Integer)
	 */
	public Map<String,Object> getInfoByDtboId(Integer id){
		return distributeOrderSpDao.getInfoByDtboId(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderSpService#getList(com.ry.core.form.DistributeOrderSpForm)
	 */
	public List<DistributeOrderSp> getList(DistributeOrderSpForm form){
		return distributeOrderSpDao.getList(form);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderSpService#updateToSelect(com.ry.core.entity.DistributeOrderSp)
	 */
	public void updateToSelect(DistributeOrderSp distributeOrderSp) throws Exception{
		DistributeOrderSpForm form = new DistributeOrderSpForm();
		form.setDcrdSpId(distributeOrderSp.getDcrdSpId());
		form.setIsSelect(1);//企业端选择的机构（1选择、其它未选择过）
		List<DistributeOrderSp> list = getList(form);
		if(list!=null && list.size()>0){
			if(list.size()>=2)throw new Exception("您已经更改过一次交易机构，不能再更改");
			for(DistributeOrderSp sp:list){
				sp.setState(0);//无效订单
				distributeOrderSpDao.updateModel(sp);
			}
		}
		distributeOrderSp.setState(2);//交易中
		distributeOrderSpDao.updateModel(distributeOrderSp);
		DiscountrecordSp disc = discountrecordSpDao.getModelById(distributeOrderSp.getDcrdSpId());
		if(disc!=null){
			disc.setOrderflag(2);//交易中（只要是选择机构订单就是交易中）
			discountrecordSpDao.updateModel(disc);
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderSpService#getListByDiscIdAndOrgId(java.lang.Integer, java.lang.Integer)
	 */
	public List<DistributeOrderSp> getListByDiscIdAndOrgId(Integer discId, Integer orgId) {
		return distributeOrderSpDao.getListByDiscIdAndOrgId(discId, orgId);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderSpService#getOrderPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.DistributeOrderSpForm)
	 */
	public PageResults<DistributeOrderSp> getOrderPageList(ReviewOrderRequest req) {
		return distributeOrderSpDao.getOrderPageList(req);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderSpService#getOrderPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.DistributeOrderSpForm)
	 */
	public PageResults<Map<String,Object>> getOrderMemberPageList(ReviewOrderRequest req) {
		return distributeOrderSpDao.getOrderMemberPageList(req);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderSpService#getOverrunByCreateTime(java.util.Date)
	 */
	public List<DistributeOrderSp> getOverrunByCreateTime(Date date) {
		return distributeOrderSpDao.getOverrunByCreateTime(date);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderSpService#getOrderByCreateTime(java.lang.Integer)
	 */
	public List<DistributeOrderSp> getOrderByCreateTime(Integer dcrdSpId){
		return distributeOrderSpDao.getOrderByCreateTime(dcrdSpId);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderSpService#getPageListPC(java.lang.Integer, java.lang.Integer, com.ry.core.form.DistributeOrderSpForm)
	 */
	public PageResults<Map<String, Object>> getPageListPC(Integer pageIndex, Integer pageSize,DistributeOrderSpForm form) throws Exception {
		return distributeOrderSpDao.getPageListPC(pageIndex, pageSize, form);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderSpService#getByNo(java.lang.String)
	 */
	public DistributeOrderSp getByNo(String no) throws Exception {
		return distributeOrderSpDao.getByNo(no);
	}
}