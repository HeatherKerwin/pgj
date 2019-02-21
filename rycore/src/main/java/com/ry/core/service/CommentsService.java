package com.ry.core.service;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.Comments;
import com.ry.util.page.PageResults;

public interface CommentsService {
	
	/**
	 * 根据主键获取评论
	 * @param id
	 * @author WKX
	 */
	public Comments getById(Integer id);
	
	/**
	 * 根据下单表 获取评论
	 * @param dcrdId
	 * @author WKX
	 */
	public List<Comments> getByDcrdId(Integer dcrdId,Integer type);
	
	/**
	 * 根据派单表 获取评论
	 * @param dtboId
	 * @author WKX
	 */
	public List<Comments> getByDtboId(Integer dtboId);
	
	/**
	 * 保存
	 * @param comments
	 * @author WKX
	 */
	public void saveModel(Comments comments);
	
	/**
	 * 统计上月所有机构评价
	 * @author GXW
	 * @date 2016年5月19日
	 */
	public List<Map<String, Object>> getLastMonthRecord();
	
	/**
	 * 根据机构订单主键获取评价（含贴现订单类型）
	 * @author WKX
	 * @param dtboId 机构订单主键
	 * @since 2016年5月30日 上午10:45:00
	 */
	public List<Map<String,Object>> getInfoByDtboId(Integer dtboId);
	
	/**
	 * 统计机构评价（评分）
	 * @author WKX
	 * @param type 类型：银票0、商票1、批量2
	 * @param orgId 机构主键
	 * @throws Exception
	 * @since 2016年8月18日 下午6:15:46
	 */
	public Map<String,Object> getCountByOrgId(Integer type,Integer orgId)throws Exception;
	
	/**
	 * 分页显示评价内容
	 * @author KHC
	 * @param orgId
	 * @param pageIndex
	 * @param pageSize
	 * @throws Exception
	 * @since 2016年8月24日 下午3:25:07
	 */
	public PageResults<Map<String, Object>> getPageListByOrgId(Integer orgId,Integer pageIndex,Integer pageSize) throws Exception;
	
	/**
	 * 根据机构主键统计所有评分（包含银票、商票和批量贴现企业）
	 * @author KHC
	 * @param orgId
	 * @throws Exception
	 * @since 2016年8月25日 上午10:18:02
	 */
	public Map<String,Object> getAllByOrgId(Integer orgId)throws Exception;
	
	/**
	 * 根据类型获取所有的未评论订单
	 * @author ZY
	 * @param memberId 
	 * @param type 0银票 1商票 2批量
	 * @since 2016年9月13日 下午3:14:47
	 */
	public List<Map<String,Object>> getUnCommentsByMemberAndtype(Integer memberId,Integer type)throws Exception;
}