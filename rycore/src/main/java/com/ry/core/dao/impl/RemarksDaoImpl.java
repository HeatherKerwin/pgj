package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.RemarksDao;
import com.ry.core.entity.Remarks;
import com.ry.util.datatable.BasePageRequestData;
import com.ry.util.page.PageResults;

@Repository
public class RemarksDaoImpl extends BaseDao<Remarks, Integer> implements RemarksDao{

	/* (non-Javadoc)
	 * @see com.ry.core.dao.RemarksDao#getById(java.lang.Integer)
	 */
	public Remarks getById(Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.RemarksDao#saveModel(com.ry.core.entity.Remarks)
	 */
	public void saveModel(Remarks remarks) {
		save(remarks);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.RemarksDao#getInfoById(java.lang.Integer)
	 */
	public List<Map<String, Object>> getInfoById(Integer id) {
		StringBuffer sql = new StringBuffer("SELECT rem.*,a.username FROM remarks rem LEFT JOIN member mem ON mem.id = rem.mem_id LEFT JOIN admin a ON a.id = rem.operator_id");
		List<Object> params = new ArrayList<Object>();
		if(id != null){
			sql.append(" where mem.id = ?");
			params.add(id);
		}
		return getListMapBySQL(sql.toString(), params.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.RemarksDao#getPageInfoById(com.ry.util.datatable.BasePageRequestData, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<Map<String, Object>> getPageInfoById(BasePageRequestData bp,Integer fkid,Integer fkType) {
		StringBuffer sql = new StringBuffer(" SELECT rem.*,a.username FROM remarks rem INNER JOIN admin a ON rem.operator_id = a.id where 1=1 ");
		StringBuffer hqlcount = new StringBuffer("SELECT COUNT(*) FROM(SELECT rem.*,a.username FROM remarks rem INNER JOIN admin a ON rem.operator_id = a.id) res where 1=1");
		List<Object> params = new ArrayList<Object>();
		
		if(fkid != null){
			sql.append(" and rem.fk_id = ?");
			hqlcount.append(" and res.fk_id = ?");
			params.add(fkid);
		}
		if(fkType != null){
			sql.append(" and rem.fk_type = ?");
			hqlcount.append(" and res.fk_type = ?");
			params.add(fkType);
		}
		sql.append(" order by rem.create_time desc");
		PageResults<Map<String, Object>> pageResult = findPageMapByFetchedSql(sql.toString(), hqlcount.toString(), bp.currentPage().intValue(), bp.getLength().intValue(), params.toArray());
		return pageResult;
	}
	/*
	 * (non-Javadoc)
	 * @see com.ry.core.dao.RemarksDao#listByTypeAndFkid(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<Map<String, Object>>  listByTypeAndFkid(Integer currentPage,Integer pageSize,Integer fkId,Integer type){
		StringBuffer sql = new StringBuffer("select r.* from remarks r where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(fkId != null){
			sql.append(" and r.fk_id = ? ");
			params.add(fkId);
		}
		if(type != null){
			sql.append(" and r.type = ? ");
			params.add(type);
		}
		sql.append(" order by r.create_time desc");
		String count = "SELECT COUNT(*) FROM ("+ sql.toString() + ") des ";
		return findPageMapByFetchedSql(sql.toString(), count, currentPage, pageSize, params.toArray());
	}
	/* (non-Javadoc)
	 * @see com.ry.core.dao.RemarksDao#getListByFkId(java.lang.Integer)
	 */
	public List<Map<String, Object>> getListByFkId(Integer fkid,Integer type) {
		StringBuffer sql = new StringBuffer("SELECT r.* FROM remarks r LEFT JOIN discountrecord disc ON r.fk_id = disc.id WHERE disc.id=? and r.type=?");
		List<Object> params = new ArrayList<Object>();
		params.add(fkid);
		params.add(type);
		sql.append(" order by r.create_time desc limit 3");
		return getListMapBySQL(sql.toString(), params.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.RemarksDao#getPageList(com.ry.util.datatable.BasePageRequestData, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<Map<String, Object>> getPageList(BasePageRequestData bp, Integer fkId, Integer type) {
		StringBuffer sql = new StringBuffer(" FROM remarks r LEFT JOIN discountrecord disc ON r.fk_id = disc.id WHERE disc.id=? and r.type=?");
		List<Object> params = new ArrayList<Object>();
		params.add(fkId);
		params.add(type);
		sql.append(" order by r.create_time desc");
		String str = new String(" SELECT r.*" + sql.toString());
		String count = new String(" SELECT COUNT(*)" + sql.toString());
		return findPageMapByFetchedSql(str, count, bp.currentPage().intValue(), bp.getLength().intValue(), params.toArray());
	}
}
