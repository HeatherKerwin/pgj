package com.ry.core.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.DiscussionDao;
import com.ry.core.entity.Discussion;
import com.ry.core.service.DiscussionService;
import com.ry.util.QrcodeUtil;

@Service
public class DiscussionServiceImpl extends BaseDao<Discussion, Integer> implements DiscussionService {

	@Resource
	DiscussionDao discussionDao;
	
	@Override
	public void saveModel(Discussion discussion) {
		discussionDao.updateModel(discussion);
	}
	
	public void updateModel(Discussion discussion){
		discussionDao.updateModel(discussion);
	}
	
	public Discussion getById(Integer id){
		return discussionDao.getById(id);
	}
	
	public Discussion getByPhone(String phone){
		return discussionDao.getByPhone(phone);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscussionService#saveAndCreateQrcode(com.ry.core.entity.Discussion, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Discussion saveAndCreateQrcode(Discussion discussion,String prefix,String savePath,String path) {
		Discussion temp = discussionDao.getByPhone(discussion.getPhone());
		if(temp==null){
			discussion.setCreateTime(new Date());
			discussion.setImgPath(path);
			discussionDao.saveModel(discussion);
			QrcodeUtil.encode(prefix+"?id="+discussion.getId(), 300, 300, savePath, "png");
			return discussion;
		}else{
			return temp;
		}
	}
}