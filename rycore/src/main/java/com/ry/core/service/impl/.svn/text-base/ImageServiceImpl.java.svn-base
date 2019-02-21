package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.ImageDao;
import com.ry.core.entity.Image;
import com.ry.core.service.ImageService;
@Service
public class ImageServiceImpl implements ImageService {
	
	@Resource
	ImageDao imagedao;

	@Override
	public Image getById(Integer id) {
		
		return imagedao.getById(id);
	}

	@Override
	public List<Image> getByFkIdAndFkType(Integer fkId, String fkType) {
		
		return imagedao.getByFkIdAndFkType(fkId, fkType);
	}

	@Override
	public void saveModel(Image image) {
		imagedao.saveModel(image);
	}

}
