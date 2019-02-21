package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.ImageDao;
import com.ry.core.entity.Image;


@Repository
public class ImageDaoImpl extends BaseDao<Image, Integer> implements ImageDao{
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.ImageDao#getById(java.lang.Integer)
	 */
	@Override
	public Image getById(Integer id) {
		return get(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.ImageDao#saveModel(com.ry.core.entity.Image)
	 */
	public void saveModel(Image image) {
		if(image!=null && image.getId()!=null)update(image);
		else save(image);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.ImageDao#getByFkIdAndFkType(java.lang.Integer, java.lang.String)
	 */
	public List<Image> getByFkIdAndFkType(Integer fkId,String fkType) {
		StringBuffer hql = new StringBuffer("from image where fkId=? AND fkType=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(fkId);
		paramList.add(fkType);
		return queryByHql(hql.toString(), paramList.toArray());
	}
}