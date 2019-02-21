package com.ry.core.service;

import java.util.List;

import com.ry.core.entity.Systeminfo;
import com.ry.core.entity.Systeminfo.ReadState;
import com.ry.core.entity.Systeminfo.Type;
import com.ry.core.form.SysteminfoForm;
import com.ry.util.page.PageResults;

public interface SysteminfoService {
	
	void updateSysteminfo(Systeminfo systeminfo);
	
	void addSysteminfo(Systeminfo systeminfo);
	
	/**
	 * 根据用户主键获取消息分页
	 * @author WKX
	 * @param nf
	 * @param currentPage
	 * @param pageSize
	 * @since 2016年1月6日 下午4:52:14
	 */
	public PageResults<Systeminfo> getPageList(SysteminfoForm nf,Integer currentPage,Integer pageSize);
	
	/**
	 * 根据用户获取未读信息
	 * @author WKX
	 * @param memberId
	 * @param readState
	 * @since 2016年1月8日 上午11:08:24
	 */
	public List<Systeminfo> getByMemberIdAndReadState(Integer memberId,ReadState readState);
	
	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id
	 * @throws Exception
	 * @since 2016年1月12日 上午10:56:16
	 */
	public Systeminfo getById(Integer id) throws Exception;
	
	/**
	 * 阅读该信息
	 * @author WKX
	 * @param id
	 * @throws Exception
	 * @since 2016年1月12日 上午10:57:33
	 */
	public Systeminfo updateSysteminfoToReadById(Integer id) throws Exception;
	
	/**
	 * 全部已读（根据用户和类型）
	 * @author WKX
	 * @param memberId 用户主键
	 * @param type 类型
	 * @since 2016年11月20日 下午6:26:43
	 */
	public void updateToReadByMemberId(Integer memberId,List<Type> types);
	
	/**
	 * 获取未读消息
	 * @author WKX
	 * @param memberId 用户主键
	 * @since 2016年11月20日 下午7:23:17
	 */
	public Integer getMessage(Integer memberId);
}