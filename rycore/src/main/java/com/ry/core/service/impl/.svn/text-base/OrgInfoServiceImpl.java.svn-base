package com.ry.core.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.MemberDao;
import com.ry.core.dao.OrgDao;
import com.ry.core.dao.OrgExtendInfoDao;
import com.ry.core.dao.OrgInfoDao;
import com.ry.core.dao.ServicememberDao;
import com.ry.core.entity.Member;
import com.ry.core.entity.Org;
import com.ry.core.entity.OrgExtendInfo;
import com.ry.core.entity.OrgInfo;
import com.ry.core.entity.Servicemember;
import com.ry.core.form.OrgInfoForm;
import com.ry.core.form.OrginfoReportForm;
import com.ry.core.form.org.UpdateOrgInfoRequest;
import com.ry.core.service.OrgInfoService;
import com.ry.util.page.PageResults;

/**
 * 名称: OrgDaoImpl.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月2日 下午7:04:32<br>
 * @since  2016年3月2日
 * @author li.xiaofei 
 */
@Service
public class OrgInfoServiceImpl implements OrgInfoService{
	
	@Resource
	OrgInfoDao orgInfoDao;
	
	@Resource
	private OrgDao orgDao;
	
	@Resource
	private MemberDao memberDao;
	
	@Resource
	private ServicememberDao servicememberDao;
	
	@Resource
	private OrgExtendInfoDao orgExtendInfoDao;
	
	@Override
	public List<OrgInfo> getOrgInfoById(Integer orgId ,Integer state) {
		return orgInfoDao.getOrgInfoById(orgId,state);
	}
	
	/**
	 * 根据Id更新认证实体
	 */
	public void updateOrgInfoById(UpdateOrgInfoRequest req){
		OrgInfo orgInfo = orgInfoDao.getById(req.getId());
		try {
			if (orgInfo.getType() != null && orgInfo.getType() == 0) {
				Member member = null;
				if (orgInfo.getMemberId() != null) {
					member = memberDao.getModelById(orgInfo.getMemberId());
				} else {
					Org org = orgDao.getById(orgInfo.getOrgId());
					member = memberDao.getById(org.getMemberId());
				}
				member.setState(req.getState());
				memberDao.updateMember(member);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		Member member = memberDao.getModelById(orgInfo.getMemberId());
		if(req.getState() == 2){//审核信息通过，添加认证信息扩展的信息
			OrgExtendInfo orgExtendInfo = new OrgExtendInfo(); 
			if(member.getRecommendpeople() != null){//注册的时候填写了推荐码
				Servicemember servicemember = servicememberDao.getServicemember(member.getRecommendpeople());
				if(servicemember != null){//确定推荐码是销售人员
					orgExtendInfo.setSalesTime(new Date());
					orgExtendInfo.setAscriptionPerson(servicemember.getId());
					orgExtendInfo.setAscriptionState(0);
				}else{//是平台销售用户
					orgExtendInfo.setPermitTime(new Date());
					orgExtendInfo.setAscriptionState(3);
				}
			}
			orgExtendInfo.setOrgInfoId(orgInfo.getId());
			orgExtendInfoDao.saveOrgExtendInfo(orgExtendInfo);
		}
		orgInfo.setState(req.getState());
		orgInfo.setReason(req.getReason());
		orgInfoDao.updateOrgInfoById(orgInfo);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgInfoService#update(com.ry.core.entity.OrgInfo)
	 */
	public void update(OrgInfo orgInfo){
		orgInfoDao.updateOrgInfoById(orgInfo);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgInfoService#getById(java.lang.Integer)
	 */
	public OrgInfo getById(Integer id){
		return orgInfoDao.getById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgInfoService#getCurrentTypeInfoById(java.lang.Integer)
	 */
	public Map<String,Object> getCurrentTypeInfoById(Integer orgId){
		List<Map<String,Object>> list = orgInfoDao.getCurrentTypeInfoById(orgId);
		if(list!=null && list.size()>0){
			return list.iterator().next();
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgInfoService#getByOrgIdAndType(java.lang.Integer, java.lang.Integer)
	 */
	public Map<String,Object> getByOrgIdAndType(Integer orgId, Integer type) {
		List<Map<String,Object>> list = orgInfoDao.getByOrgIdAndType(orgId, type);
		if(list!=null && list.size()>0){
			return list.iterator().next();
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgInfoService#saveModel(com.ry.core.entity.OrgInfo)
	 */
	public void saveModel(OrgInfo orgInfo){
		orgInfoDao.saveModel(orgInfo);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgInfoService#getByOrgId(java.lang.Integer, java.lang.Integer)
	 */
	public Map<String,Object> getByOrgId(Integer orgId,Integer type){
		List<Map<String,Object>> list = orgInfoDao.getByOrgId(orgId, type);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgInfoService#getByMemberIdAndType(java.lang.Integer, java.lang.Integer)
	 */
	public Map<String,Object> getByMemberIdAndType(Integer memberId,Integer type){
		List<Map<String,Object>> list = orgInfoDao.getByMemberIdAndType(memberId, type);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgInfoService#updateOrgOrMember(com.ry.core.entity.Org, com.ry.core.entity.Member, com.ry.core.entity.OrgInfo)
	 */
	public void updateOrgOrMember(Org org,Member member,OrgInfo orgInfo){
		if(org!=null)orgDao.saveModel(org);//DAO内含更新方法
		if(member!=null)memberDao.updateMember(member);
		orgInfoDao.saveModel(orgInfo);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgInfoService#getAllOrgName(java.lang.Integer)
	 */
	public List<Map<String, Object>> getAllOrgName(Integer type) {
		return orgInfoDao.getAllOrgName(type);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgInfoService#getOrgNameAndLimit(java.lang.Integer, java.lang.String)
	 */
	public List<Map<String, Object>> getOrgNameAndLimit(Integer type, String createTime) {
		return orgInfoDao.getOrgNameAndLimit(type, createTime);
	}
	
	
	public Map<String,Object> getOrginfoId(Integer orginfoid){
		List<Map<String,Object>> list = orgInfoDao.getOrginfoId(orginfoid);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public PageResults<Map<String, Object>> getPageList(Integer currentPage, Integer pageSize,
			OrgInfoForm orgInfoForm) throws ParseException {
		PageResults<Map<String,Object>> pageResults = orgInfoDao.getPageList( currentPage, pageSize,orgInfoForm);
		return pageResults;
	}

	@Override
	public List<Map<String, Object>> getByObj(OrgInfoForm orgInfoForm) throws Exception {
		StringBuffer sql = new StringBuffer();
		return orgInfoDao.getByObj(orgInfoForm);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgInfoService#getOrginfoReport(com.ry.core.form.OrginfoReportForm)
	 */
	@Override
	public PageResults<Map<String, Object>> getOrginfoReport(OrginfoReportForm form) throws ParseException {
		return orgInfoDao.getOrginfoReport(form);
	}

	@Override
	public List<Map<String, Object>> getOrgInfoReportById(Integer Id) {
		return orgInfoDao.getOrgInfoReportById(Id);
	}
	
}