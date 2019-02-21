package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.LianhangDao;
import com.ry.core.entity.Lianhang;
import com.ry.util.page.PageResults;

@Repository
public class LianhangDaoImpl extends BaseDao<Lianhang, Integer> implements LianhangDao {

	@Override
	public void addLianhang(Lianhang lianhang) {
		save(lianhang);
	}

	@Override
	public List<Lianhang> getList(Lianhang lianhang) {
		String hql = "from lianhang where 1=1 ";
		List paramList = new ArrayList();
		if (lianhang != null) {
			if (lianhang.getYinhang() != null && !"".equals(lianhang.getYinhang())) {
				hql += " and yinhang like ? ";
				paramList.add(lianhang.getYinhang()+"%");
			}
			if (lianhang.getProvice() != null && !"".equals(lianhang.getProvice())) {
				hql += " and provice like ? ";
				paramList.add(lianhang.getProvice()+"%");
			}
			if (lianhang.getCity() != null && !"".equals(lianhang.getCity())) {
				hql += " and city like ? ";
				paramList.add(lianhang.getCity()+"%");
			}
			if (lianhang.getYinhangdesc() != null && !"".equals(lianhang.getYinhangdesc())) {
				hql += " and (yinhang like ? or provice like ? or city like ? or yinhangdesc like ?) ";
				paramList.add("%"+lianhang.getYinhangdesc()+"%");
				paramList.add("%"+lianhang.getYinhangdesc()+"%");
				paramList.add("%"+lianhang.getYinhangdesc()+"%");
				paramList.add("%"+lianhang.getYinhangdesc()+"%");
			}
		}
		return getListByHQL(hql, paramList.toArray());
	}

	public PageResults<Lianhang> getPageList(Lianhang lianhang, Integer currentPage, Integer pageSize) {
		String hql = "from lianhang where 1=1 ";
		List paramList = new ArrayList();
		if (lianhang != null) {
			if (lianhang.getYinhang() != null && !"".equals(lianhang.getYinhang())) {
				hql += " and yinhang like ? ";
				paramList.add(lianhang.getYinhang()+"%");
			}
			if (lianhang.getProvice() != null && !"".equals(lianhang.getProvice())) {
				hql += " and provice like ? ";
				paramList.add(lianhang.getProvice()+"%");
			}
			if (lianhang.getCity() != null && !"".equals(lianhang.getCity())) {
				hql += " and city like ? ";
				paramList.add(lianhang.getCity()+"%");
			}
			if (lianhang.getYinhangdesc() != null && !"".equals(lianhang.getYinhangdesc())) {
				hql += " and (yinhang like ? or provice like ? or city like ? or yinhangdesc like ?) ";
				paramList.add("%"+lianhang.getYinhangdesc()+"%");
				paramList.add("%"+lianhang.getYinhangdesc()+"%");
				paramList.add("%"+lianhang.getYinhangdesc()+"%");
				paramList.add("%"+lianhang.getYinhangdesc()+"%");
			}
		}
		String countHql = "select count(*) "+hql.toString();
		return findPageByFetchedHql(hql.toString(), countHql, currentPage, pageSize, paramList.toArray());
	}

	@Override
	public PageResults<Lianhang> getListBylianhang(Lianhang lianhang,Integer pageIndex,Integer pageSize) {	
		String hql = "from lianhang where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		if (lianhang != null) {
			hql += " and yinhang like ? ";
			paramList.add("%"+lianhang.getYinhang()+"%");
			hql += " and provice like ? ";
			paramList.add("%"+lianhang.getProvice()+"%");
			hql += " and city like ? ";
			paramList.add("%"+lianhang.getCity()+"%");
			if(StringUtils.hasText(lianhang.getYinhangdesc())){
				if(lianhang.getYinhangdesc()=="");
				hql += " and yinhangdesc like ? ";
				String reg="\\s+";
				String []key=lianhang.getYinhangdesc().split(reg);
				if(key.length<=3){
					if(key.length==3){
						paramList.add("%"+key[0]+"%"+key[1]+"%"+key[2]+"%");
					}else if(key.length==2){
						paramList.add("%"+key[0]+"%"+key[1]+"%");
					}else if(key.length==1){
						paramList.add("%"+key[0]+"%");
					}
				}
			}
			
		}
		String countHql = "select count(*) "+hql;
		return findPageByFetchedHql(hql, countHql, pageIndex, pageSize, paramList.toArray());
	}
	
}
