package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.CurrentpriceDao;
import com.ry.core.entity.Currentprice;
import com.ry.util.page.PageResults;

@Repository
public class CurrentpriceDaoImpl extends BaseDao<Currentprice,Integer > implements CurrentpriceDao{

	@Override
	public PageResults<Currentprice> pageList(Currentprice cp, int pageNo, int pageSize) {
		String hql = "";
		String countHql = "";
		return findPageByFetchedHql(hql, countHql, pageNo, pageSize, null);
	}

	@Override
	public List<Currentprice> queryList(Currentprice cp) {
		StringBuffer hql=new StringBuffer("from currentprice where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		if(cp!=null){
			if(cp.getType1()!=null){
				hql.append(" and type1 = ?");
				paramList.add(cp.getType1());
			}
			if(cp.getType2()!=null){
				hql.append(" and type2 = ?");
				paramList.add(cp.getType2());
			}
			if(cp.getType3()!=null){
				hql.append(" and type3 = ?");
				paramList.add(cp.getType3());
			}
			if(cp.getType4()!=null){
				hql.append(" and type4 = ?");
				paramList.add(cp.getType4());
			}
			if(cp.getType5()!=null){
				hql.append(" and type5 = ?");
				paramList.add(cp.getType5());
			}
			if(cp.getType6()!=null){
				hql.append(" and type6 = ?");
				paramList.add(cp.getType6());
			}
		}
		
		return queryByHql(hql.toString(), paramList.toArray());
	}

	@Override
	public int updateAll(List<Currentprice> currentpriceList) {
		int result = 0;
		if(currentpriceList!=null&&currentpriceList.size()!=0){			
			for(Currentprice currentprice : currentpriceList){
				Integer type1 = currentprice.getType1();
				Integer type2 = currentprice.getType2();
				Integer type3 = currentprice.getType3();
				Integer type4 = currentprice.getType4();
				Integer type5 = currentprice.getType5();
				List<Currentprice> baseEntityList = queryByHql("from currentprice where type1 like ? and type2 like ? and type4 like ? and type3 like ? and type5 like ?",new Object[]{type1,type2,type4,type3,type5});				
				if(baseEntityList==null||baseEntityList.size()==0){
					save(currentprice);
					result++;
				}else{
					Currentprice oldCurrentprice = (Currentprice)baseEntityList.get(0);
					oldCurrentprice.setPrice(currentprice.getPrice());
					update(oldCurrentprice);
				}
			}
		}
		return result;
	}
}
