package com.ry.core.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.OrgInfo;
import com.ry.core.form.OrgInfoForm;
import com.ry.core.form.OrginfoReportForm;
import com.ry.util.page.PageResults;

/**
 * 名称: OrgDao.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月2日 下午6:55:04<br>
 * @since  2016年3月2日
 * @author li.xiaofei 
 */
public interface OrgInfoDao {
	
	public List<OrgInfo> getOrgInfoById(Integer orgId, Integer state);
	
	public void updateOrgInfoById(OrgInfo orgInfo);
	
	public OrgInfo getById(Integer id);

	/**
	 * 获取当前机构选择的角色的信息（可知是否审核过，或者审核到什么状态）
	 * @author WKX
	 * @param orgId
	 * @since 2016年3月24日 上午10:56:01
	 */
	public List<Map<String,Object>> getCurrentTypeInfoById(Integer orgId);
	
	/**
	 * @APP2.2 获取当前机构选择的角色的信息（可知是否审核过，或者审核到什么状态）
	 * @param orgId	此处可以是orgId也可以是member_id
	 * @param type	此处为登录用户的类型：0-企业(memberDd), 1-机构(orgId)
	 */
	public List<Map<String, Object>> getByOrgIdAndType(Integer orgId, Integer type);
	
	/**
	 * 保存认证
	 * @author WKX
	 * @param orgInfo
	 * @since 2016年3月24日 下午2:02:03
	 */
	public void saveModel(OrgInfo orgInfo);
	
	/**
	 * 获取当前角色的最新认证信息
	 * @author WKX
	 * @param orgId
	 * @param type
	 * @since 2016年4月7日 下午7:33:53
	 */
	public List<Map<String,Object>> getByOrgId(Integer orgId,Integer type);
	
	/**
	 * 根据用户主键和 什么角色 获取最新的认证
	 * @author WKX
	 * @param memberId 用户主键
	 * @param type （角色：企业0、机构1）
	 * @since 2016年5月12日 下午3:49:48
	 */
	public List<Map<String,Object>> getByMemberIdAndType(Integer memberId,Integer type);
	
	/**
	 * 获取所有机构的名称
	 * @param type
	 * @author BKY
	 */
	public List<Map<String, Object>> getAllOrgName(Integer type);
	
	/**
	 * 获取所有机构的名称和报价
	 * @param type
	 * @author BKY
	 */
	public List<Map<String, Object>> getOrgNameAndLimit(Integer type, String createTime);
	
	/**
	 * 机构列表
	 * @author ZY
	 * @since 2016年8月8日 下午4:04:58
	 * @param currentPage
	 * @param pageSize
	 * @param name	
	 * @param isnew
	 * @param orgId
	 * @return
	 */
	public PageResults<Map<String, Object>> getPageList(Integer currentPage, Integer pageSize, OrgInfoForm orgInfoForm)  throws ParseException;
	
	/**
	 * 根据手机号码查询该org_id,app2.3新增(关于预警机构)
	 * @author ZY
	 * @param phone
	 * @since 2016年8月23日 下午2:47:19
	 */
	public OrgInfo getByPhone(String phone);
	/**
	 * 
	 */
	public List<Map<String, Object>> getByObj(OrgInfoForm orgInfoForm) throws Exception;
	
	/**
	 * 获取对象信息
	 * @author MH
	 * @param orginfoid
	 * @return
	 */
	public List<Map<String,Object>> getOrginfoId(Integer orginfoid);
	
	/**
	 * 分页查询认证机构的信息报表
	 * @author MH
	 * @param form
	 * @return
	 */
	public PageResults<Map<String, Object>> getOrginfoReport(OrginfoReportForm form)  throws ParseException;
	
	/**
	 * 通过Id获取认证机构的信息
	 */
	public List<Map<String, Object>> getOrgInfoReportById(Integer Id);
	
}