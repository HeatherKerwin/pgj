package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.PushLogDao;
import com.ry.core.entity.PushLog;
import com.ry.core.form.PushLogForm;
import com.ry.core.util.Utility;
import com.ry.util.page.PageResults;

@Repository
public class PushLogDaoImpl extends BaseDao<PushLog,Integer> implements PushLogDao{

	/* (non-Javadoc)
	 * @see com.ry.core.dao.PushLogDao#getPageList(com.ry.core.form.PushLogForm)
	 */
	public PageResults<PushLog> getPageList(PushLogForm form){
		StringBuffer hql = new StringBuffer("FROM push_log WHERE 1=1");
		List<Object> paras = new ArrayList<Object>();
		if(form.getStart()!=null){
			hql.append(" AND createTime >= ?");
			paras.add(form.getStart());
		}
		if(form.getEnd()!=null){
			hql.append(" AND createTime <= ?");
			paras.add(form.getEnd());
		}
		if(form.getType()!=null){
			hql.append(" AND type = ?");
			paras.add(form.getType());
		}
		if(form.getCityId()!=null){
			hql.append(" AND cityId = ?");
			paras.add(form.getCityId());
		}
		String count = "SELECT count(*) " + hql.toString();
		hql.append(" ORDER BY createTime DESC");
		PageResults<PushLog> list = findPageByFetchedHql(hql.toString(),count, form.getPageNo(), form.getPageSize(), paras.toArray());
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PushLogDao#saveAward(com.ry.core.entity.PushLog)
	 */
	public void saveModel(PushLog pushLog){
		saveOrUpdate(pushLog);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PushLogDao#getMember(com.ry.core.entity.PushLog)
	 */
	public List<Map<String, Object>> getMember(PushLog log) {
		StringBuffer hql = new StringBuffer("SELECT m.id memberId,m.mobile,o.org_id,o.company,o.name,o.phone FROM(");
		List<Object> params = new ArrayList<Object>();
		hql.append(" SELECT * FROM org_info oi WHERE oi.state=2");
		if(log!=null){
			if(log.getType()!=null){
				hql.append(" AND oi.type_=?");
				params.add(log.getType());
			}
			if(log.getCityId()!=null && log.getCityId()!=-1){
				if(log.getType()==1){//机构
					hql.append(" AND oi.org_id IN(SELECT oc.org_id FROM org_city oc WHERE oc.city_id=? GROUP BY oc.org_id)");
					params.add(log.getCityId());
				}else if(log.getType()==2){//企业
					hql.append(" AND oi.member_id IN(SELECT a.member_id FROM address a WHERE a.city_id=? GROUP BY a.member_id)");
					params.add(log.getCityId());
				}
			}
			hql.append(" GROUP BY oi.member_id)o");
			hql.append(" LEFT JOIN");
			hql.append(" member m ON m.id=o.member_id WHERE 1=1");
			if(log.getStartDate()!=null){
				hql.append(" m.regtime>=?");
				params.add(log.getStartDate());
			}
			if(log.getEndDate()!=null){
				hql.append(" m.regtime<=?");
				params.add(log.getEndDate());
			}
		}
		List<Map<String, Object>> list = getListMapBySQL(hql.toString(), params.toArray());
		return Utility.decodeMobile(list);
	}
}