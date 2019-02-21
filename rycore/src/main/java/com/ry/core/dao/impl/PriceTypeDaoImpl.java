package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.PriceTypeDao;
import com.ry.core.entity.PriceType;

@Repository
public class PriceTypeDaoImpl extends BaseDao<PriceType, Integer> implements PriceTypeDao {

	/**
	 * 获取所有报价类型(用于报价展示)
	 * @author BKY
	 */
	public List<PriceType> getPriceType() {
		String hql = "from price_type where 1=1 and state = 0";
		List<String> paramsList = new ArrayList<String>();
		List<PriceType> priceTypeList = null;
		priceTypeList = getListByHQL(hql, paramsList.toArray());
		return priceTypeList;
	}

	public PriceType getById(Integer id) {
		return get(id);
	}
	
	public List<PriceType> getByPriceType(PriceType pt) {
		StringBuffer hql = new StringBuffer("from price_type where 1=1 and state = 0");
		List<Object> params = new ArrayList<Object>();
		if (pt != null) {
			if (pt.getType1() != null) {
				hql.append(" and type1 = ?");
				params.add(pt.getType1());
			}
			if (pt.getType2() != null) {
				hql.append(" and type2 = ?");
				params.add(pt.getType2());
			}
			if (pt.getType3() != null) {
				hql.append(" and type3 = ?");
				params.add(pt.getType3());
			}
			if (pt.getType4() != null) {
				hql.append(" and type4 = ?");
				params.add(pt.getType4());
			}
			if (pt.getType5() != null) {
				hql.append(" and type5 = ?");
				params.add(pt.getType5());
			}
		}
		return getListByHQL(hql.toString(), params.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceTypeDao#getByIdAndTimeLimit(java.lang.Integer, java.lang.Integer)
	 */
	public List<Map<String, Object>> getByIdAndTimeLimit(Integer priceTypeId, Integer timeLimit) {
		StringBuffer sql = new StringBuffer("select pt.id id,pt.title2 title2 from price_type pt,(select * from price_type pt where pt.id = ?) pt1 where pt.type1 = pt1.type1 and pt.type2 = pt1.type2 ");
		sql.append("and case when pt1.type3 is not null then pt.type3 = pt1.type3 else 1=1 end ");
		sql.append("and case when pt1.type4 is not null then pt.type4 = pt1.type4 else 1=1 end ");
		List<Object> params = new ArrayList<Object>();
		params.add(priceTypeId);
		if (timeLimit != null) {
			sql.append(" and pt.type5 = ?");
			params.add(timeLimit);
		}
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
}
