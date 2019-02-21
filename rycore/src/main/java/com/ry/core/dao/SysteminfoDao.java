package com.ry.core.dao;

import java.util.List;

import com.ry.core.entity.Systeminfo;
import com.ry.core.entity.Systeminfo.ReadState;
import com.ry.core.entity.Systeminfo.Type;
import com.ry.core.form.SysteminfoForm;
import com.ry.util.page.PageResults;

public interface SysteminfoDao {
	
	void updateSysteminfo(Systeminfo systeminfo);
	
	void addSysteminfo(Systeminfo systeminfo);
	
	/**
	 * 根据用户主键获取消息[分页]
	 * @author WKX
	 * @param nf
	 * @param currentPage
	 * @param pageSize
	 * @since 2016年1月6日 下午4:46:06
	 */
	public PageResults<Systeminfo> getPageList(SysteminfoForm nf,Integer currentPage,Integer pageSize);
	
	/**
	 * 获取用户未读信息
	 * @author WKX
	 * @param memberId
	 * @param readState
	 * @since 2016年1月8日 上午11:06:41
	 */
	public List<Systeminfo> getByMemberIdAndReadState(Integer memberId,ReadState readState);
	
	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id
	 * @throws Exception
	 * @since 2016年1月12日 上午10:55:11
	 */
	public Systeminfo getById(Integer id) throws Exception;
	
	/**
	 * 全部已读（根据用户和类型）
	 * @author WKX
	 * @param memberId 用户主键
	 * @param types 类型
	 * @since 2016年11月20日 下午6:25:44
	 */
	public void updateToReadByMemberId(Integer memberId,List<Type> types);
}