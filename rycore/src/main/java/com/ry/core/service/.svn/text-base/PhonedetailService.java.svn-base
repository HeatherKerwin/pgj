package com.ry.core.service;

import java.util.List;

import com.ry.core.entity.Phonedetail;

public interface PhonedetailService {
	public void addPhonedetail(Phonedetail phonedetail);
	public void updatePhonedetail(Phonedetail phonedetail);
	public List<Phonedetail> getPhonedetail(Phonedetail phonedetail);
	
	/**
	 * 查找固定某一天的某个渠道新增数量
	 * @author ZY
	 * @param qdp 渠道
	 * @param createDate 时间
	 * @since 2016年9月21日 下午3:40:39
	 */
	public Long countByQudaoandDay(String qdp,String createDate);
	
	/**
	 * 查找固定某个月的某个渠道新增数量
	 * @author ZY
	 * @param qdp 渠道
	 * @param createDate 时间
	 * @since 2016年9月21日 下午3:40:39
	 */
	public Long countByQudaoandMonth(String qdp,String createDate);
}
