package com.ry.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.CommentsDao;
import com.ry.core.entity.Comments;
import com.ry.core.service.CommentsService;
import com.ry.util.page.PageResults;
@Service
public class CommentsServiceImpl implements CommentsService {
	
	@Resource
	CommentsDao commentsDao;
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.CommentsService#getById(java.lang.Integer)
	 */
	public Comments getById(Integer id) {
		return commentsDao.getById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.CommentsService#getByDcrdId(java.lang.Integer)
	 */
	public List<Comments> getByDcrdId(Integer dcrdId,Integer type) {
		return commentsDao.getByDcrdId(dcrdId,type);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.CommentsService#getByDtboId(java.lang.Integer)
	 */
	public List<Comments> getByDtboId(Integer dtboId) {
		return commentsDao.getByDtboId(dtboId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.CommentsService#saveModel(com.ry.core.entity.Comments)
	 */
	public void saveModel(Comments comments){
		commentsDao.saveModel(comments);
	}

	@Override
	public List<Map<String, Object>> getLastMonthRecord() {
		return commentsDao.getLastMonthRecord();
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.CommentsService#getInfoByDtboId(java.lang.Integer)
	 */
	public List<Map<String,Object>> getInfoByDtboId(Integer dtboId){
		return commentsDao.getInfoByDtboId(dtboId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.CommentsService#getCountByOrgId(java.lang.Integer, java.lang.Integer)
	 */
	public Map<String,Object> getCountByOrgId(Integer type,Integer orgId)throws Exception{
		return commentsDao.getCountByOrgId(type, orgId);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.CommentsService#getPageListByOrgId(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<Map<String, Object>> getPageListByOrgId(Integer orgId, Integer pageIndex,Integer pageSize) throws Exception {
		return commentsDao.getPageListByOrgId(orgId, pageIndex, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.CommentsService#getAllByOrgId(java.lang.Integer)
	 */
	public Map<String, Object> getAllByOrgId(Integer orgId) throws Exception {
		return commentsDao.getAllByOrgId(orgId);
	}
	
	/*(non-Javadoc)
	 * @see com.ry.core.service.CommentsService#getUnCommentsByMember(java.lang.Integer, java.lang.Integer)
	 */
	public List<Map<String,Object>> getUnCommentsByMemberAndtype(Integer memberId,Integer type) throws Exception{
		return commentsDao.getUnCommentsByMemberAndtype(memberId,type);
	}
}