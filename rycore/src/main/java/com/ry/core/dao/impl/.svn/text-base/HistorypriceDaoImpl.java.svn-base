package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.HistorypriceDao;
import com.ry.core.entity.Historyprice;
import com.ry.core.util.Constant;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;

@Repository
public class HistorypriceDaoImpl extends BaseDao<Historyprice,Integer > implements HistorypriceDao{

	@Override
	public PageResults<Historyprice> pageList(Historyprice cp, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.HistorypriceDao#getList(com.ry.core.entity.Historyprice)
	 */
	@Override
	public List<Historyprice> getList(Historyprice historyprice) {
		StringBuffer hql = new StringBuffer("from historyprice where 1 = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if(historyprice!=null){
			if(StringUtils.hasText(historyprice.getDay())){//哪一天的报价
				hql.append(" and day = ?");
				paramList.add(historyprice.getDay());
			}
			if(historyprice.getType1()!=null){//承兑行：1国股、2城商、3外资、4农商、5农合、6农信、7村镇
				hql.append(" and type1 = ?");
				paramList.add(historyprice.getType1());
			}
			if(historyprice.getType2()!=null){//票据金额 :1:500以上 2:100-500 3:50-100  4:<50
				hql.append(" and type2 = ?");
				paramList.add(historyprice.getType2());
			}
			if(historyprice.getType4()!=null){//地域 1长三角2珠三角3华中4环渤海5西南
				hql.append(" and type4 = ?");
				paramList.add(historyprice.getType4());
			}
			if(historyprice.getType3()!=null){// 买断带票 1买断 2带票 
				hql.append(" and type3 = ?");
				paramList.add(historyprice.getType3());
			}
			if(historyprice.getType5()!=null){//1三个月、2六个月、3六个月以上
				hql.append(" and type5 = ?");
				paramList.add(historyprice.getType5());
			}
			if(historyprice.getType6()!=null){//纸票1、电票2
				hql.append(" and type6 = ?");
				paramList.add(historyprice.getType6());
			}
			if(historyprice.getType7()!=null){//@APP2.2  期限：半年期0、一年期1
				hql.append(" and type7 = ?");
				paramList.add(historyprice.getType7());
			}
			if(historyprice.getCityId()!=null){//@APP2.2 cityId
				hql.append(" and cityId = ?");
				paramList.add(historyprice.getCityId());
			}
		}
		hql.append(" ORDER BY day DESC");
		List<Historyprice> hList = getListByHQL(hql.toString(),paramList.toArray());
		return hList;
	}

	@Override
	public void addHistoryprice(Historyprice historyprice) {
		save(historyprice);
	}

	@Override
	public void updateHistoryprice(Historyprice historyprice) {
		update(historyprice);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.HistorypriceDao#findPriceList(java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public List<Historyprice> findPriceList(String start,String end,Integer type1,Integer type2,Integer type4,Integer index, Integer size) {
		StringBuffer hql = new StringBuffer("from historyprice where type1 = ? and type2 = ? and type4 = ? AND ((price IS NOT NULL AND price<>'') OR (price2 IS NOT NULL AND price2<>'')) ");
		List<Object> param = new ArrayList<Object>();
		param.add(type1);
		param.add(type2);
		param.add(type4);
		if(start!=null && end!=null){
			hql.append(" and day BETWEEN ? AND ? ");
			param.add(start);
			param.add(end);
		}
		hql.append("and type6 = 1 and (type5 IS NULL OR type5 = 2 ) and city_id=?");
		param.add(Constant.DEFAULT_CITY_ID);
		return findPageByFetchedHql(hql.toString()+" order by day desc ", "select count(*) "+hql.toString(), index, size, param.toArray()).getResults();
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.HistorypriceDao#findbyDay(java.lang.String)
	 */
	public List<Historyprice> findbyDay(String day){
		StringBuffer hql = new StringBuffer("from historyprice where 1=1 and day =? order by id desc ");
		List<Object> param = new ArrayList<Object>();
		param.add(day);
		return getListByHQL(hql.toString(), param.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.HistorypriceDao#findbyDayAndType(java.lang.String, java.lang.String)
	 */
	public List<Historyprice> findbyDayAndType(String day, String type, Integer cityId){
		StringBuffer hql = new StringBuffer("from historyprice where 1=1");
		List<Object> param = new ArrayList<Object>();
		if (StringUtils.hasText(day)) {
			hql.append(" and day =?");
			param.add(day);
		}
		if (cityId != null) {
			hql.append(" and cityId = ?");
			param.add(cityId);
		}
		if (StringUtils.hasText(type)) {
			if("1".equals(type)){//纸
				hql.append(" and (type6 = ?)");
			}else{//电
				hql.append(" and (type2 = 1 or type6 = ?)");
			}
			param.add(Integer.valueOf(type));
		}
		return getListByHQL(hql.toString(), param.toArray());
	}
	
	public void saveOrUpdate(Historyprice h) {
		StringBuffer hql = new StringBuffer("from historyprice where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if (h.getDay() != null) {
			hql.append(" and day = ?");
			param.add(h.getDay());
		}
		if (h.getType1() != null) {
			hql.append(" and type1 = ?");
			param.add(h.getType1());
		}
		if (h.getType2() != null) {
			hql.append(" and type2 = ?");
			param.add(h.getType2());
		}
		if (h.getType3() != null) {
			hql.append(" and type3 = ?");
			param.add(h.getType3());
		}
		if (h.getType4() != null) {
			hql.append(" and type4 = ?");
			param.add(h.getType4());
		}
		if (h.getType5() != null) {
			hql.append(" and type5 = ?");
			param.add(h.getType5());
		}
		if (h.getType6() != null) {
			hql.append(" and type6 = ?");
			param.add(h.getType6());
		}
		if (h.getType7() != null) {
			hql.append(" and type7 = ?");
			param.add(h.getType7());
		}
		List<Historyprice> list = getListByHQL(hql.toString(), param.toArray());
		if (list.size() == 0)
			save(h);
		else{
			Historyprice oldH = list.get(0);
			oldH.setPrice(h.getPrice());
			oldH.setPrice1(h.getPrice1());
			oldH.setPrice2(h.getPrice2());
			oldH.setUpdown(h.getUpdown());
			update(oldH);
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.HistorypriceDao#getCityListByDay(java.lang.String)
	 */
	public List<Map<String, Object>> getCityListByDay(String day) {
		StringBuffer sql = new StringBuffer("select hp.id hpid,hp.city_id cityid,r.name,r.code from historyprice hp left join region r on r.id = hp.city_id where 1=1");
		List<Object> params = new ArrayList<Object>();
		if ("".equals(day)) {
			day = DateUtil.formart(new Date(), DateUtil.FORMART3);
		}
		sql.append(" and hp.day = ?");
		params.add(day);
		sql.append(" group by cityid");
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	public List<Historyprice> getListOrderByPriceAsc(Historyprice historyprice) {
		StringBuffer hql = new StringBuffer("from historyprice where price IS NOT NULL AND price<>'' ");
		List<Object> paramList = new ArrayList<Object>();
		if(historyprice!=null){
			if(StringUtils.hasText(historyprice.getDay())){//哪一天的报价
				hql.append(" and day = ?");
				paramList.add(historyprice.getDay());
			}
			if(historyprice.getType1()!=null){//承兑行：1国股、2城商、3外资、4农商、5农合、6农信、7村镇
				hql.append(" and type1 = ?");
				paramList.add(historyprice.getType1());
			}
			if(historyprice.getType2()!=null){//票据金额 :1:500以上 2:100-500 3:50-100  4:<50
				hql.append(" and type2 = ?");
				paramList.add(historyprice.getType2());
			}
			if(historyprice.getType4()!=null){//地域 1长三角2珠三角3华中4环渤海5西南
				hql.append(" and type4 = ?");
				paramList.add(historyprice.getType4());
			}
			if(historyprice.getType3()!=null){// 买断带票 1买断 2带票 
				hql.append(" and type3 = ?");
				paramList.add(historyprice.getType3());
			}
			if(historyprice.getType5()!=null){//1三个月、2六个月、3六个月以上
				hql.append(" and type5 = ?");
				paramList.add(historyprice.getType5());
			}
			if(historyprice.getType6()!=null){//纸票1、电票2
				hql.append(" and type6 = ?");
				paramList.add(historyprice.getType6());
			}
			if(historyprice.getType7()!=null){//@APP2.2  期限：半年期0、一年期1
				hql.append(" and type7 = ?");
				paramList.add(historyprice.getType7());
			}
			if(historyprice.getCityId()!=null){//@APP2.2 cityId
				hql.append(" and cityId = ?");
				paramList.add(historyprice.getCityId());
			}
		}
		hql.append(" ORDER BY price ASC");
		List<Historyprice> hList = getListByHQL(hql.toString(),paramList.toArray());
		return hList;
	}
	
	
	public List<Map<String, Object>> getCityList() {
		StringBuffer sql = new StringBuffer("SELECT  oc.city_id cityid,r.name,r.code  FROM org_city oc LEFT JOIN region r ON oc.city_id = r.id  GROUP BY cityid");
		List<Object> params = new ArrayList<Object>();
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	public List<Map<String, Object>> getCity() {
		StringBuffer sql = new StringBuffer("SELECT  his.city_id cityid,r.name,r.code  FROM historyprice his LEFT JOIN region r ON his.city_id = r.id  GROUP BY cityid order by cityid desc");
		List<Object> params = new ArrayList<Object>();
		return getListMapBySQL(sql.toString(), params.toArray());
	}
}