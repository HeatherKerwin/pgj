package com.ry.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ry.core.dao.CommentsDao;
import com.ry.core.dao.OrgDao;
import com.ry.core.dao.OrgDtoDao;
import com.ry.core.entity.Org;
import com.ry.core.form.org.ListRequest;
import com.ry.core.service.OrgService;
import com.ry.util.DateUtil;
import com.ry.util.excel.ViewExcel;
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
public class OrgServiceImpl implements OrgService{
	
	@Resource
	OrgDtoDao orgDtoDao;
	
	@Resource
	OrgDao orgDao;
	
	@Resource
	CommentsDao commentsDao;
	
	@Override
	public PageResults<Map<String,Object>> getPageList(ListRequest list) {
		return orgDtoDao.getPageList(list);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgService#getById(java.lang.Integer)
	 */
	public Map<String,Object> getInfoById(Integer id){
		List<Map<String,Object>> result = orgDtoDao.getInfoById(id);
		if(result!=null && result.size()>0){
			return result.iterator().next();
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgService#getByMemberId(java.lang.Integer)
	 */
	public Org getByMemberId(Integer memberId) throws Exception{
		return orgDao.getByMemberId(memberId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgService#saveModel(com.ry.core.entity.Org)
	 */
	public Org saveModel(Org org){
		orgDao.saveModel(org);
		return org;
	}
	
	public Org getById(Integer id) throws Exception{
		return orgDao.getById(id);
	}
	
	public List<Org> getByType(Integer type) throws Exception{
		return orgDao.getByType(type);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgService#getOrgAndNameByType(java.lang.Integer)
	 */
	public List<Map<String, Object>> getOrgAndNameByType(Integer type) throws Exception{
		return orgDao.getOrgAndNameByType(type);
	}
	
	public List<Map<String, Object>> getOrgAndNameByType1(Integer type) throws Exception{
		return orgDao.getOrgAndNameByType1(type);
	}

	public List<Map<String, Object>> searchCityByOrgId(Integer orgId) throws Exception {
		return orgDao.searchCityByOrgId(orgId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgService#getOrgCityInfoById(java.lang.Integer)
	 */
	public Map<String, Object> getOrgCityInfoById(Integer orgCityId) throws Exception{
		List<Map<String, Object>> list = orgDao.getOrgCityInfoById(orgCityId);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public PageResults<Map<String, Object>> getMemberPageList(ListRequest list) {
		return orgDtoDao.getMemberPageList(list);
	}

	@Override
	public List<Map<String, Object>> getMemberListByCompany(String company) {
		return orgDao.getMemberListByCompany(company);
	}

	@Override
	public List<Map<String, Object>> getCityListByMemId(Integer memberId) {
		return orgDao.getCityListByMemId(memberId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgCityService#getComment(java.util.Map, java.lang.Integer)
	 */
	public Map<String,Object> getComment(Map<String,Object> org,Integer orgId) throws Exception{
		if(org==null)org = new HashMap<String, Object>();
		Double door = 0D;//上门次数
		Double price = 0D;//价格
		Double service = 0D;//服务
		Double speed = 0D;//速度
		Double amount = 0D;//总评论数
		
		for(int i=0;i<3;i++){
			Map<String,Object> count = commentsDao.getCountByOrgId(i, orgId);
			if(count!=null){
				Object d = count.get("door");
				Object p = count.get("price");
				Object se = count.get("service");
				Object sp = count.get("speed");
				Object a = count.get("amount");
				if(d!=null)door+=Double.valueOf(d.toString());
				if(p!=null)price+=Double.valueOf(p.toString());
				if(se!=null)service+=Double.valueOf(se.toString());
				if(sp!=null)speed+=Double.valueOf(sp.toString());
				if(a!=null)amount+=Double.valueOf(a.toString());
			}
		}
		if(amount!=0){
			door = door/amount;
			price = price/amount;
			service = service/amount;
			speed = speed/amount;
		}
		org.put("_door", door!=0?door:"--");
		org.put("_price", price!=0?price:"--");
		org.put("_service", service!=0?service:"--");
		org.put("_speed", speed!=0?speed:"--");
		org.put("_score", price+service+speed);
		return org;
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgService#updateModel(com.ry.core.entity.Org)
	 */
	public void updateModel(Org org) {
		orgDao.updateModel(org);
	}

	@Override
	public ViewExcel getByExcelData(ListRequest req, String orderType) {
		String week = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String[]> dataList = new ArrayList<String[]>();
		String[] endData = null;
		String[] headData = new String[] {
				"注册日期", 
				"注册时间", 
				"注册姓名", 
				"手机号", 
				"注册企业名称", 
				"QQ/微信/电子邮箱", 
				"开户认证状态", 
				"客户归属信息。", 
				"许可营销用户许可日期", 
				"转化营销用户转化日期", 
				"销售营销用户所属业务员信息", 
				"许可营销用户所属业务员信息", 
				"转化营销用户所属业务员信息"};
		try{
			if(req.getList()!=null && req.getList()==1){
				week = "（两周）";
				Calendar c = Calendar.getInstance();
				c.setTime(new Date());
				if(!StringUtils.hasText(req.getEndDate())){
					req.setEndDate(DateUtil.formart(c.getTime(),DateUtil.FORMART3));
				}
				if(!StringUtils.hasText(req.getStartDate())){
					c.add(Calendar.DATE,-14);
					req.setStartDate(DateUtil.formart(c.getTime(),DateUtil.FORMART3));
				}
			}
			List<Map<String,Object>> reply = orgDtoDao.getByObj(req);
			if(reply.size() > 0){
				for(Map<String,Object> rep : reply){
					String[] data = new String[headData.length];
					data[0] = (String) rep.get("content");
					data[1] = (String) rep.get("username");
					data[2] = rep.get("create_time").toString();
					data[3] = (String) rep.get("lastedit");
					if(rep.get("last_edit_time")!=null){
						data[4] = rep.get("last_edit_time").toString();
					}
					dataList.add(data);
				}
			}else{
				String[] data = new String[1];
				data[0] = "暂无数据";
				dataList.add(data);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ViewExcel("机构注册认证列表"+week+sdf.format(new Date()), headData, dataList, endData);
	}
}