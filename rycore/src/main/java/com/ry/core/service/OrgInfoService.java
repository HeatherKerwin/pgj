/**
 * 
 */
package com.ry.core.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.Member;
import com.ry.core.entity.Org;
import com.ry.core.entity.OrgInfo;
import com.ry.core.form.OrgInfoForm;
import com.ry.core.form.OrginfoReportForm;
import com.ry.core.form.org.UpdateOrgInfoRequest;
import com.ry.util.page.PageResults;

/**
 * 名称: OrgDao.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月2日 下午6:55:04<br>
 * @since  2016年3月2日
 * @author li.xiaofei 
 */
public interface OrgInfoService {
	
	/**
	 * 通过关联键 orgId 获取认证通过的信息
	 * @param id
	 * @return
	 */
	List<OrgInfo> getOrgInfoById(Integer id,Integer state);
	
	void updateOrgInfoById(UpdateOrgInfoRequest req);
	
	/**
	 * 更新对象
	 * @author WKX
	 * @param orgInfo
	 * @since 2016年3月24日 上午9:34:27
	 */
	public void update(OrgInfo orgInfo);
	
	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id
	 * @since 2016年3月24日 上午9:35:57
	 */
	public OrgInfo getById(Integer id);
	
	/**
	 * 获取当前机构选择的角色的信息（可知是否审核过，或者审核到什么状态）
	 * @author WKX
	 * @param orgId
	 * @since 2016年3月24日 上午10:56:01
	 */
	public Map<String,Object> getCurrentTypeInfoById(Integer orgId);
	
	/**
	 * @APP2.2 获取当前机构选择的角色的信息（可知是否审核过，或者审核到什么状态）
	 * @param orgId	此处可以是orgId也可以是member_id
	 * @param type	此处为登录用户的类型：0-企业(memberDd), 1-机构(orgId)
	 * @author BKY
	 */
	public Map<String,Object> getByOrgIdAndType(Integer orgId, Integer type);
	
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
	public Map<String,Object> getByOrgId(Integer orgId,Integer type);
	
	/**
	 * 根据用户主键和 什么角色 获取最新的认证
	 * @author WKX
	 * @param memberId 用户主键
	 * @param type （角色：企业0、机构1）
	 * @since 2016年5月12日 下午3:52:54
	 */
	public Map<String,Object> getByMemberIdAndType(Integer memberId,Integer type);
	
	/**
	 * 页面填写认证信息（更新机构或企业状态，保存认证明细）
	 * @author WKX
	 * @param org
	 * @param member
	 * @param orgInfo
	 * @since 2016年5月16日 下午3:36:59
	 */
	public void updateOrgOrMember(Org org,Member member,OrgInfo orgInfo);
	
	/**
	 * 获取所有机构的名称
	 * @param type
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
	 * @param currentPage
	 * @param pageSize
	 * @since 2016年8月8日 下午3:59:47
	 */
	public PageResults<Map<String, Object>> getPageList(Integer currentPage, Integer pageSize,OrgInfoForm orgInfoForm) throws ParseException ;
	
	/**
	 * 获取对象信息
	 * @author MH
	 * @param orginfoid
	 * @return
	 */
	public Map<String,Object> getOrginfoId(Integer orginfoid);
	
	/**
	 * 获取导出的数据
	 * @param orgInfoForm
	 * @return
	 * @throws Exception
	 * @author ZWD
	 */
	public List<Map<String, Object>> getByObj(OrgInfoForm orgInfoForm) throws Exception;
	
	/**
	 * 分页查询认证机构的信息报表
	 * @author MH
	 * @param form
	 * @return
	 */
	public PageResults<Map<String, Object>> getOrginfoReport(OrginfoReportForm form) throws ParseException;

	/**
	 * 通过Id获取认证机构的信息
	 */
	public List<Map<String, Object>> getOrgInfoReportById(Integer Id);
}