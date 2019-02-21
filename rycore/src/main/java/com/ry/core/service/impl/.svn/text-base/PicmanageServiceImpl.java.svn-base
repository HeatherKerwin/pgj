package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.PicmanageDao;
import com.ry.core.entity.Appimage;
import com.ry.core.service.PicmanageService;


@Service
public class PicmanageServiceImpl implements PicmanageService {

	@Resource
	PicmanageDao picmanageDao;

	@Override
	public List<Appimage> getPicList(String code) {
		List<Appimage> appimages = picmanageDao.getList(code);
		return appimages;
	}

	@Override
	public void deleteAll(String code) {
		picmanageDao.deleteAll(code);
	}

	@Override
	public void addAll(List<Appimage> appimages) {
		picmanageDao.saveAll(appimages);
	}		

	/* (non-Javadoc)
	 * @see com.ry.core.service.PicmanageService#getInCode(java.lang.String[])
	 */
	public List<Appimage> getInCode(String[] code){
		return picmanageDao.getInCode(code);
	}
	
	/**
	 * 根据编号获取图片[可能会同时获取多个]
	 * @author WKX
	 * @param code 类型
	 * @param sort 根据sort获取确切的日历
	 * @since 2016年8月30日 
	 */
	public List<Appimage> getNewInCode(String code,String sort){
		return picmanageDao.getNewInCode(code,sort);
	}

	/**
	 * 根据参数选择，返回的图片集合
	 * @param code 参数，判断选择的是机构端，还是企业端
	 * @return 返回查询到的集合
	 */
	public List<Appimage> getXzPiclist(String code) {
		List<Appimage> appimages = picmanageDao.getXzPiclist(code);
		return appimages;
	}
}