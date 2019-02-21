package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.DaypriceDao;
import com.ry.core.entity.Dayprice;

@Repository
public class DaypriceDaoImpl extends BaseDao<Dayprice, Integer> implements DaypriceDao {

	@Override
	public List<Dayprice> getList(String day, Integer type4, String time) {
		
		List<Dayprice> list = queryByHql("from dayprice where day like ? and type4 like ? and time like ?",new Object[]{day,type4,time});
		return list;
		
	}

	@Override
	public List<Dayprice> getList(String time, String day, Integer type1, Integer type2, Integer type4) {
		StringBuilder hql = new StringBuilder("select time,case when (price='--') then 'null' when (price='') then 'null' else price end as price, id,case when (price2='--') then 'null' when (price2='') then 'null' else price2 end as price2 from dayprice where 1=1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (time != null) {
			hql.append(" and time like ?");
			paramList.add(time);
		}
		if (day != null) {
			hql.append(" and day like ?");
			paramList.add(day);
		}
		if (type1 != null) {
			hql.append(" and type1 like ?");
			paramList.add(type1);
		}
		if (type2 != null) {
			hql.append(" and type2 like ?");
			paramList.add(type2);
		}
		if (type4 != null) {
			hql.append(" and type4 like ?");
			paramList.add(type4);
		}
		hql.append(" and type6 = 1 and (type5 IS NULL OR type5 = 2 )");//APP2.1价格走势显示的是6个月纸票
		return getListBySQL(hql.toString(), paramList.toArray());
	}

	@Override
	public void addDayprice(Dayprice dayprice) {
		save(dayprice);
	}

	@Override
	public void updateDayprice(Dayprice dayprice) {
		update(dayprice);
	}

	@Override
	public List<Dayprice> findDaypriceList(Integer type1, Integer type2,
			Integer type4, String time, Integer start, Integer end) {
		List<Dayprice> list = (List<Dayprice>) getSession().createQuery("from dayprice where time like ? and type1 like ? and type2 like ? and type4 like ? order by day desc ")
				.setParameter(0, time)
				.setParameter(1, type1)
				.setParameter(2, type2)
				.setParameter(3, type4)
				.setFirstResult(start).setMaxResults(end).list();
		return list;
	}

	@Override
	public List<Dayprice> findDaypriceList(String day,Integer type1, Integer type2,
			Integer type4, String time, Integer start, Integer end) {

		StringBuilder hql = new StringBuilder("from dayprice where time like ? and type1 like ? and type2 like ? and type4 like ? and day like ? order by day desc");
		
		List<Dayprice> list = (List<Dayprice>) getSession().createQuery(hql.toString())				
				.setParameter(0, time)
				.setParameter(1, type1)
				.setParameter(2, type2)
				.setParameter(3, type4)
				.setParameter(4, "%"+day+"%")
				.setFirstResult(start).setMaxResults(end).list();
		return list;
	}

	@Override
	public List<Dayprice> findDaypriceList(String day1, String day2,
			String day3, Integer type1, Integer type2, Integer type4,
			String time, Integer start, Integer end) {
		
		StringBuilder hql = new StringBuilder("from dayprice where time like ? and type1 like ? and type2 like ? and type4 like ? and (day like ? or day like ? or day like ?) order by day desc");
		
		List<Dayprice> list = (List<Dayprice>) getSession().createQuery(hql.toString())				
				.setParameter(0, time)
				.setParameter(1, type1)
				.setParameter(2, type2)
				.setParameter(3, type4)
				.setParameter(4, "%"+day1+"%")
				.setParameter(5, "%"+day2+"%")
				.setParameter(6, "%"+day3+"%")
				.setFirstResult(start).setMaxResults(end).list();
		return list;
	}

	@Override
	public List<Dayprice> findDaypriceList(String day1, String day2,
			String day3, String day4, String day5, String day6, Integer type1,
			Integer type2, Integer type4, String time, Integer start,
			Integer end) {

		StringBuilder hql = new StringBuilder("from dayprice where time like ? and type1 like ? and type2 like ? and type4 like ? and (day like ? or day like ? or day like ? or day like ? or day like ? or day like ?) order by day desc");
		
		List<Dayprice> list = (List<Dayprice>) getSession().createQuery(hql.toString())				
				.setParameter(0, time)
				.setParameter(1, type1)
				.setParameter(2, type2)
				.setParameter(3, type4)
				.setParameter(4, "%"+day1+"%")
				.setParameter(5, "%"+day2+"%")
				.setParameter(6, "%"+day3+"%")
				.setParameter(7, "%"+day4+"%")
				.setParameter(8, "%"+day5+"%")
				.setParameter(9, "%"+day6+"%")
				.setFirstResult(start).setMaxResults(end).list();
		return list;
	}

	@Override
	public Dayprice getDayprice(Integer id) {
		
		return get(id);
	}
	
	
}
