package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.ClickCountDao;
import com.ry.core.entity.ClickCount;

@Repository
public class ClickCountDaoImpl extends BaseDao<ClickCount,Integer > implements  ClickCountDao {

	@Override
	public void saveClickCount(ClickCount clickCount) {
		String code = clickCount.getCode();
		Date currentDate = clickCount.getCurrentDate();
		if(code != null){
			save(clickCount);
		}
	}

	@Override
	public int count(String style, String code) {
		String sql = "select count(*) from clickCount where style = ? and code = ?";
		Object obj;
		List<Object> list = getListBySQL(sql, new Object[]{style,code});
		if (list != null && list.size() > 0) {
			obj = list.get(0);																	
			if (obj != null) {		
				return Integer.valueOf(obj.toString());
			}
		}
		return 0;
	}

	@Override
	public int countByIp(String style){
		String sql = "select  count(DISTINCT a.ip) from (SELECT *, DATE_FORMAT(currentDate,'%Y-%m-%d') date from clickCount where code = 'visit' and style = ? ) a GROUP BY a.date"; //0到24点只需要统计一次
		Object obj;
		List<Object> list = getListBySQL(sql, new Object[]{style});
		if (list != null && list.size() > 0) {
			obj = list.get(0);																	
			if (obj != null) {		
				return  Integer.valueOf(obj.toString());
			}
		}
		return 0;
	}

	@Override
	public int countByUuid(String style) {
		String sql = "select  count(DISTINCT a.uuid) from (SELECT *, DATE_FORMAT(currentDate,'%Y-%m-%d') date from clickCount where code = 'visit' and style = ? ) a GROUP BY a.date";
		Object ob = getData(sql, new Object[]{style});
		if(ob == null || "".equals(ob)){
			return 0;
		}else{
			return Integer.valueOf(ob.toString());
		}
	}
	
	/*提取当前页面公用的方法*/
	public Object getData(String sql, Object... ob){
		Object obj;
		List<Object> list = getListBySQL(sql, ob);
		if (list != null && list.size() > 0) {
			obj = list.get(0);																	
			if (obj != null) {		
				return  obj;
			}
		}
		return null;
	}

	@Override
	public List<String> getAllStyle() {
		String sql = "select DISTINCT style from clickCount";
		List<String> list = getListBySQL(sql, null);
		return list;
	}

	@Override
	public List<String> getAllCodes() {
		String sql = "select DISTINCT code from clickCount";
		List<String> list = getListBySQL(sql, null);
		return list;
	}
	
	@Override
	public List<ClickCount> getList() {
		List<ClickCount> list = getListByHQL("from clickCount",null);
		return list;
	}

	@Override
	public void updateClickCount(ClickCount clickCount) {
		update(clickCount);
	}
	
	public List<Map<String,Object>> getCountByCurrentDate(Date start,Date end){
		StringBuffer sql = new StringBuffer("SELECT cc.currentDate,cc.keyword,COUNT(cc.id)s_amount,COUNT(m.id)a_amount FROM (SELECT * FROM clickCount c ");
		List<Object> params = new ArrayList<Object>();
		if(start!=null && end!=null){
			sql.append(" WHERE c.currentDate BETWEEN ? AND ? ");
			params.add(start);
			params.add(end);
		}
		sql.append(" GROUP BY c.uuid) cc LEFT JOIN member m ON cc.uuid=m.uuid WHERE cc.keyword IS NOT NULL AND cc.keyword<>'' GROUP BY DATE_FORMAT(cc.currentDate,'%m-%d-%Y'),cc.keyword");
		return getListMapBySQL(sql.toString(),params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.ClickCountDao#getNoKeyword()
	 */
	public List<ClickCount> getNoKeyword(String url) {
		StringBuffer hql = new StringBuffer("FROM clickCount cc WHERE (cc.keyword IS NULL OR cc.keyword='') AND cc.referrerUrl IS NOT NULL AND cc.referrerUrl<>'' AND cc.code='visit'");
		List<Object> params = new ArrayList<Object>();
		if(StringUtils.hasText(url)){
			hql.append(" AND cc.url LIKE ?");
			params.add("%"+url+"%");
		}
		List<ClickCount> list = getListByHQL(hql.toString(),params.toArray());
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.ClickCountDao#getSrcCountByDate(java.util.Date, java.util.Date)
	 */
	public List<Map<String,Object>> getSrcCountByDate(Date start,Date end){
		StringBuffer sql = new StringBuffer("SELECT result.*,COUNT(result.id)amount FROM (");
		List<Object> params = new ArrayList<Object>();
		sql.append("SELECT res.*,CONCAT(res.temp,res.keyword)srck FROM (");
		sql.append("SELECT cc.*,m.id memId,CASE WHEN cc.url LIKE '%shenma%'THEN 'SHENMA' WHEN cc.url LIKE '%sougou%'THEN 'SOUGOU' END AS temp FROM (");
		sql.append("SELECT * FROM clickCount c WHERE c.code='visit' AND c.keyword IS NOT NULL");
		if(start!=null && end!=null){
			sql.append(" AND c.currentDate BETWEEN ? AND ? ");
			params.add(start);
			params.add(end);
		}
		sql.append(")cc LEFT JOIN member m ON cc.uuid=m.uuid WHERE m.id IS NOT NULL");
		sql.append(" )res");
		sql.append(" )result GROUP BY result.srck ORDER BY result.keyword");
		return getListMapBySQL(sql.toString(),params.toArray());
	}

	@Override
	public List<ClickCount> getListCountByDay(Date start, Date end, String source) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) AS countvisitC ,COUNT(DISTINCT ip) AS countipC  FROM clickCount WHERE 1=1 ");
		if(start != null){
			sql.append(" AND currentDate >= ?");
			params.add(start);
		}
		if(end != null){
			sql.append(" AND currentDate < ?");
			params.add(end);
		}
		if(StringUtils.hasText(source)){
			sql.append(" AND style = ?");
			params.add(source);
		}
		return getListByHQL(sql.toString(), params.toArray());
	}
}