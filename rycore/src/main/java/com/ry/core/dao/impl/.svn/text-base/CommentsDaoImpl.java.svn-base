package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.Enum.OrderState;
import com.ry.core.dao.BaseDao;
import com.ry.core.dao.CommentsDao;
import com.ry.core.entity.Comments;
import com.ry.core.util.Utility;
import com.ry.util.page.PageResults;
@Repository
public class CommentsDaoImpl extends BaseDao<Comments, Integer> implements CommentsDao {

	/* (non-Javadoc)
	 * @see com.ry.core.dao.CommentsDao#getById(java.lang.Integer)
	 */
	public Comments getById(Integer id) {
		return get(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.CommentsDao#getByDcrdId(java.lang.Integer)
	 */
	public List<Comments> getByDcrdId(Integer dcrdId,Integer type) {
		StringBuffer hql = new StringBuffer("FROM comments WHERE dcrdId=?"); 
		List<Object> params = new ArrayList<Object>();
		params.add(dcrdId);
		if(type!=null){
			hql.append(" and type=?");
			params.add(type);
		}
		return getListByHQL(hql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.CommentsDao#getByDtboId(java.lang.Integer)
	 */
	public List<Comments> getByDtboId(Integer dtboId) {
		StringBuffer hql = new StringBuffer("FROM comments WHERE dtboId=?"); 
		List<Object> params = new ArrayList<Object>();
		params.add(dtboId);
		return getListByHQL(hql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.CommentsDao#saveModel(com.ry.core.entity.Comments)
	 */
	public void saveModel(Comments comments) {
		comments.setRole(0);
		save(comments);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.CommentsDao#getLastMonthRecord()
	 */
	@Override
	public List<Map<String, Object>> getLastMonthRecord() {
		StringBuffer sql = new StringBuffer("SELECT dis.type1 type1, SUM(c.isToDoor)isToDoor,SUM(c.price)price,SUM(c.service)service,SUM(c.speed)speed,COUNT(c.id)amount,d.org_id FROM comments c LEFT JOIN distribute_order d ON c.dtbo_id=d.id , discountrecord dis  WHERE YEAR(c.create_time)=? AND MONTH(c.create_time)=? AND d.dcrd_id=dis.id GROUP BY d.org_id");
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH);//从0开始结果为实际月份-1
		if(month==0) {
			year-=1;
			month=12;
		}
		List<Object> param = new ArrayList<Object>();
		param.add(year);
		param.add(month);
		return getListMapBySQL(sql.toString(), param.toArray());
	}
	
	public List<Map<String,Object>> getInfoByDtboId(Integer dtboId) {
		StringBuffer hql = new StringBuffer("SELECT c.*,d.type1,d.type2 FROM comments c LEFT JOIN discountrecord d ON c.dcrd_id=d.id WHERE c.dtbo_id=?"); 
		List<Object> params = new ArrayList<Object>();
		params.add(dtboId);
		return getListMapBySQL(hql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.CommentsDao#getCountByOrgId(java.lang.Integer, java.lang.Integer)
	 */
	public Map<String,Object> getCountByOrgId(Integer type,Integer orgId)throws Exception{
		if(type==null || orgId==null)throw new Exception("数据异常");
		String table = null;
		List<Object> params = new ArrayList<Object>();
		if(type==0){
			table = "distribute_order";
		}else if(type==1){
			table = "distribute_order_sp";
		}else if(type==2){
			table = "distribute_order_pl";
		}
		StringBuffer sql = new StringBuffer("SELECT d.org_id,SUM(c.isToDoor)door,SUM(c.price)price,SUM(c.service)service,SUM(c.speed)speed,COUNT(c.id)amount FROM comments c LEFT JOIN "+table);
		sql.append(" d ON c.dtbo_id=d.id ");
		if(type==0){
			sql.append(" AND (c.type_=? OR c.type_ IS NULL) WHERE (c.type_=? OR c.type_ IS NULL) AND d.org_id=?");
		}else{
			sql.append(" AND c.type_=? WHERE c.type_=? AND d.org_id=?");
		}
		params.add(type);
		params.add(type);
		params.add(orgId);
		List<Map<String, Object>> list = getListMapBySQL(sql.toString(), params.toArray());
		if(list!=null){
			return list.get(0);
		}else{
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.CommentsDao#getPageListByOrgId(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<Map<String, Object>> getPageListByOrgId(Integer orgId, Integer pageIndex,Integer pageSize) throws Exception {
		if(orgId==null)throw new Exception("数据异常");
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("SELECT c.content,c.isToDoor,c.price,c.speed,c.service,c.create_time AS createtime,res.* ");
		String sql1 = "FROM comments c LEFT JOIN"
				+ "(SELECT dist.id,disc.memberid,disc.membermobile,disc.place,dist.org_id,disc.allmoney,0 t FROM discountrecord disc LEFT JOIN distribute_order dist ON dist.dcrd_id = disc.id UNION"
				+ " SELECT distsp.id,discsp.member_id,discsp.member_mobile,discsp.place,distsp.org_id,discsp.allmoney,1 t FROM discountrecord_sp discsp LEFT JOIN distribute_order_sp distsp ON distsp.dcrd_sp_id = discsp.id UNION"
				+ " SELECT distpl.id,discpl.member_id,discpl.member_mobile,discpl.place,distpl.org_id,discpl.allmoney,2 t FROM discountrecord_pl discpl LEFT JOIN distribute_order_pl distpl ON distpl.dcrd_pl_id = discpl.id)res"
				+ " ON c.dtbo_id=res.id WHERE c.type_=res.t AND res.org_id = ?";
		StringBuffer count = new StringBuffer("SELECT COUNT(*) ");
		sql.append(sql1);
		count.append(sql1);
		params.add(orgId);
		sql.append(" ORDER BY createtime DESC ");
		PageResults<Map<String, Object>> page=findPageMapByFetchedSql(sql.toString(), count.toString(), pageIndex, pageSize, params.toArray());
		return Utility.decodeMobile(page);//@ZY EDIT:2016-09-23手机号转码
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.CommentsDao#getCountByOrgId(java.lang.Integer)
	 */
	public Map<String, Object> getAllByOrgId(Integer orgId) throws Exception {
		if(orgId==null)throw new Exception("数据异常");
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer(" SELECT res.org_id,COUNT(c.id)amount,SUM(c.isToDoor)door,SUM(c.price)price,SUM(c.service)service,SUM(c.speed)speed FROM comments c LEFT JOIN"
				+ " (SELECT dist.id,dist.org_id,0 t FROM distribute_order dist UNION"
				+ " SELECT distsp.id,distsp.org_id,1 t FROM distribute_order_sp distsp UNION"
				+ " SELECT distpl.id,distpl.org_id,2 t FROM distribute_order_pl distpl)"
				+ " res ON c.dtbo_id=res.id WHERE c.type_=res.t AND res.org_id=?");
		params.add(orgId);
		List<Map<String, Object>> list = getListMapBySQL(sql.toString(), params.toArray());
		if(list!=null){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/*(non-Javadoc)
	 * @see com.ry.core.service.CommentsService#getUnCommentsByMember(java.lang.Integer, java.lang.Integer)
	 */
	public List<Map<String,Object>> getUnCommentsByMemberAndtype(Integer memberId,Integer type) throws Exception{
		if(memberId==null || type==null)throw new Exception("数据异常");
		String table = null;
		String member="d.member_id";
		if(type==0){
			table = "discountrecord";member="d.memberid";
		}else if(type==1){
			table = "discountrecord_sp";;
		}else if(type==2){
			table = "discountrecord_pl";
		}
		StringBuffer sql = new StringBuffer("SELECT d.id,c.id commentsId FROM " + table +" d LEFT JOIN comments c ON (d.id=c.dcrd_id And c.type_ = " + type + " )WHERE d.orderflag=? AND " + member + " = ?  AND c.id IS NULL ");
		List<Object> params = new ArrayList<Object>();
		params.add(OrderState.COMPLETE.getCode());
		params.add(memberId);
		if(type==0){
			sql.append(" AND d.deposit IS NOT NULL ");
		}
		return getListMapBySQL(sql.toString(),params.toArray());
	}
}