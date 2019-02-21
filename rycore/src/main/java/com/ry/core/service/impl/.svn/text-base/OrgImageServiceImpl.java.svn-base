package com.ry.core.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.ImageDao;
import com.ry.core.dao.OrgImageDao;
import com.ry.core.entity.Image;
import com.ry.core.entity.OrgImage;
import com.ry.core.service.OrgImageService;

@Service
public class OrgImageServiceImpl implements OrgImageService{
	
	@Resource
	OrgImageDao orgImageDao;
	
	@Resource
	ImageDao imageDao;

	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgImageService#getById(java.lang.Integer)
	 */
	@Override
	public OrgImage getById(Integer id) {
		return orgImageDao.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgImageService#getByOrgId(java.lang.Integer)
	 */
	@Override
	public Map<String, Object> getByOrgId(Integer orgId) {
		List<Map<String, Object>> list = orgImageDao.getByOrgId(orgId);
		if(list!=null && list.size()>0){
			Map<String, Object> org_img = list.get(0);
			if(org_img!=null && org_img.get("id")!=null){//承诺函主键（可根据此查询上传的图片）
				List<Image> images = imageDao.getByFkIdAndFkType(Integer.valueOf(org_img.get("id").toString()), "1");//承诺函类型是1
				org_img.put("images",images);
			}
			return org_img;
		}else{
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgImageService#saveModel(com.ry.core.entity.OrgImage)
	 */
	@Override
	public void saveModel(OrgImage orgImage) {
		orgImageDao.saveModel(orgImage);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgImageService#saveModelAndImage(com.ry.core.entity.OrgImage, java.util.List)
	 */
	public void saveModelAndImage(OrgImage orgImage,List<Image> images) {
		orgImageDao.saveModel(orgImage);
		if(images!=null && images.size()>0){
			for(Image img:images){
				img.setFkId(orgImage.getId());
				img.setFkType("1");//外键的关联类型（1：OrgImage机构认证承诺函其他、2：其他）
				img.setCreateTime(new Date());
				imageDao.saveModel(img);
			}
		}
	}
}