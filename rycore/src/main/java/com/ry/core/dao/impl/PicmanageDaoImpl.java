package com.ry.core.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.PicmanageDao;
import com.ry.core.entity.Appimage;

@Repository
public class PicmanageDaoImpl extends BaseDao<Appimage, Integer> implements PicmanageDao {
	
	//将原来的根据Id查出来，改为了根据排序查出来
	@Override
	public List<Appimage> getList(String code) {
		List<Appimage> appimages = queryByHql("from appimage where code=? order by sort asc", new String[]{code});
		return appimages;
	}

	@Override
	public void deleteAll(String code) {
		Session session = getSession();
		Query query = session.createSQLQuery("delete from appimage where code = ?").setString(0,code);
		query.executeUpdate();				
	}

	@Override
	public void saveAll(List<Appimage> appimages) {
		if (appimages != null && appimages.size() >0 ) {
			for (int i = 0; i < appimages.size(); i++) {
				save(appimages.get(i));
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PicmanageDao#getInCode(java.lang.String[])
	 */
	public List<Appimage> getInCode(String[] code) {
		String token = "";
		if(code!=null && code.length!=0){
			for (int i = 0; i < code.length; i++) {
				if(!"".equals(token))token += ",";
				token += "?";
			}
		}else{
			token = "?";
		}
		List<Appimage> appimages = queryByHql("from appimage where code in("+token+") order by id desc", code);
		return appimages;
	}
	
	/**
	 * 根据编号获取图片[可能会同时获取多个]
	 * @author WKX
	 * @param code 类型
	 * @param sort 根据sort获取确切的日历
	 * @since 2016年9月9号
	 */
	public List<Appimage> getNewInCode(String code,String sort) {
		List<Appimage> appimages = queryByHql("from appimage where code=? and sort=? order by sort asc", new Object[]{code,sort});
		return appimages;
	}
	
	@Override
	/**
	 * 根据参数选择，返回的图片集合
	 * @param code 参数，判断选择的是机构端，还是企业端
	 * @return 返回查询到的集合
	 */
	public List<Appimage> getXzPiclist(String code) {
		List<Appimage> appimages = queryByHql("from appimage where code=? order by sort asc", new String[]{code});
		return appimages;
	}
}