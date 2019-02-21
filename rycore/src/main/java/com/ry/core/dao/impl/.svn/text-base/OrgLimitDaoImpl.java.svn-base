/**
 * 
 */
package com.ry.core.dao.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.OrgLimitDao;
import com.ry.core.entity.OrgLimit;
import com.ry.core.form.orgOrder.OrderRequest;

/**
 * 名称: OrgDaoImpl.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月2日 下午7:04:32<br>
 * @since  2016年3月2日
 * @author li.xiaofei 
 */
@Repository
public class OrgLimitDaoImpl extends BaseDao<OrgLimit, Integer> implements OrgLimitDao{

	public List<Map<String,Object>> getOrgByObj(OrderRequest req){
		StringBuffer hql = new StringBuffer(" SELECT mit.org_id as orgId , mit.price as price , info.company as company from org_limit mit INNER JOIN (SELECT res.org_id id,res.company FROM (SELECT o.id org_id,i.id org_info_id,i.company,i.type_ FROM org o LEFT JOIN org_info i ON o.id=i.org_id WHERE (i.type_=1 OR (i.type_ IS NULL AND o.type_=1)) ORDER BY i.id DESC)res GROUP BY res.org_id) info ON mit.org_id = info.id where 1 = 1 "); 
		List<Object> paramList = new ArrayList<Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			hql.append(" and mit.day = ?");
			paramList.add(sdf.format(new Date()));
		
		if(req.getOrderMoney() != null){
			hql.append(" and mit.price >= ?");
			paramList.add(req.getOrderMoney());
		}
		List<Map<String,Object>> list = getListMapBySQL(hql.toString(), paramList.toArray());
		return list;
	}

	public List<OrgLimit> getByOrgIdAndDay(Integer orgId, String day) {
		StringBuffer hql = new StringBuffer("from org_limit mit where 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		hql.append(" and mit.day = ?");
		if (StringUtils.hasText(day)) {			
			paramsList.add(day);
		} else {
			paramsList.add(sdf.format(new Date()));
		}
		if (orgId != null) {
			hql.append(" and mit.orgId = ?");
			paramsList.add(orgId);
		}
		hql.append(" order by mit.createTime DESC");
		List<OrgLimit> list = getListByHQL(hql.toString(), paramsList.toArray());
		return list;
	}

	public void saveModel(OrgLimit orgLimit) {
		if (orgLimit != null && orgLimit.getId() != null) {
			update(orgLimit);
		} else {
			save(orgLimit);
		}
	}
	
}
