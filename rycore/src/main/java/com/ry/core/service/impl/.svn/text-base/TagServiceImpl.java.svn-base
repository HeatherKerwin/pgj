package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.TagDao;
import com.ry.core.entity.Tag;
import com.ry.core.service.TagService;

@Service
public class TagServiceImpl implements TagService{
	
	@Resource
	TagDao tagDao;

	/* (non-Javadoc)
	 * @see com.ry.core.service.TagService#getByCode(java.lang.String)
	 */
	@Override
	public Tag getByCode(String code) {
		List<Tag> list = tagDao.getByCode(code);
		if(list!=null && list.size()>0){
			return list.iterator().next();
		}else{
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.TagService#getByParentId(java.lang.Integer)
	 */
	@Override
	public List<Tag> getByParentId(Integer parentId) {
		return tagDao.getByParentId(parentId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.TagService#getByParentCode(java.lang.String)
	 */
	public List<Tag> getByParentCode(String code){
		return tagDao.getByParentCode(code);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.TagService#getById(java.lang.Integer)
	 */
	public Tag getById(Integer id){
		return tagDao.getById(id);
	}
}