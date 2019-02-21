package com.bbs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bbs.kit.Utils;
import com.bbs.model.Banner;
import com.bbs.service.BannerService;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import blade.kit.DateKit;
import blade.kit.StringKit;

@Service
public class BannerServiceImpl implements BannerService {
	
	@Override
	public Banner getBanner(Long nid) {
		Banner node = AR.findById(Banner.class, nid);
		if(null != node && node.getIs_del() == 0){
			return node;
		}
		return null;
	}
	
	@Override
	public Banner getBanner(QueryParam queryParam) {
		return AR.find(queryParam).first(Banner.class);
	}
		
	@Override
	public List<Banner> getBannerList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).list(Banner.class);
		}
		return null;
	}
	
	@Override
	public Page<Map<String, Object>> getPageList(QueryParam queryParam) {
		if(null != queryParam){
			Page<Banner> nodePage = AR.find(queryParam).page(Banner.class);
			return this.getBannerPageMap(nodePage);
		}
		return null;
	}
	
	private Page<Map<String, Object>> getBannerPageMap(Page<Banner> nodePage){
		
		long totalCount = nodePage.getTotalCount();
		int page = nodePage.getPage();
		int pageSize = nodePage.getPageSize();
		Page<Map<String, Object>> result = new Page<Map<String,Object>>(totalCount, page, pageSize);
		
		List<Banner> banners = nodePage.getResults();
		
		List<Map<String, Object>> bannerMaps = new ArrayList<Map<String,Object>>();
		if(null != banners && banners.size() > 0){
			for(Banner banner : banners){
				Map<String, Object> map = this.getBannerDetail(banner, null);
				if(null != map && !map.isEmpty()){
					bannerMaps.add(map);
				}
			}
		}
		
		result.setResults(bannerMaps);
		
		return result;
	}
	
	@Override
	public Map<String, Object> getBannerDetail(Banner banner, Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(null == banner){
			banner = this.getBanner(id);
		}
		if(null != banner){
			map.put("id", banner.getId());
			map.put("url", banner.getUrl());
			map.put("path", Utils.getPhoto(banner.getPath(),""));
			map.put("sort", banner.getSort());
			map.put("is_del", banner.getIs_del());
			map.put("create_time", banner.getCreate_time());
		}
		return map;
	}
	
	@Override
	public boolean save(Long sort, String url, String path) {
		try {
			String path1 = com.bbs.kit.FileKit.tempToUpload(path);//移动图片
			Integer time = DateKit.getCurrentUnixTime();
			AR.update("insert into t_banner(url, path, sort, create_time) values ( ?, ?, ?, ?)",
					url, path1, sort, time).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean delete(Long id) {
		if(null != id){
			AR.update("update t_banner set is_del = 1 where id = ?", id).executeUpdate(true);
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Long id, Long sort, String url, String path) {
		try {
			if(null == id){
				return false;
			}
			
			StringBuffer updateSql = new StringBuffer("update t_banner set url = ? ");
			List<Object> params = new ArrayList<Object>();
			params.add(url);
			
			if(null != sort){
				updateSql.append(", sort = ?");
				params.add(sort);
			}
			
			String path1 = com.bbs.kit.FileKit.tempToUpload(path);//移动图片
			if(StringKit.isNotBlank(path)){
				updateSql.append(", path = ?");
				params.add(path1);
			}
			
			updateSql.append(" where id = ?");
			params.add(id);
			
			AR.update(updateSql.toString(), params.toArray()).executeUpdate();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
