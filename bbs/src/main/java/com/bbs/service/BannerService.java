package com.bbs.service;

import java.util.List;
import java.util.Map;

import com.bbs.model.Banner;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

public interface BannerService {
	
	Banner getBanner(Long id);
	
	Banner getBanner(QueryParam queryParam);
	
	Map<String, Object> getBannerDetail(Banner banner, Long id);
	
	List<Banner> getBannerList(QueryParam queryParam);
	
	Page<Map<String, Object>> getPageList(QueryParam queryParam);
	
	boolean save(Long sort, String url, String path);
	
	boolean delete(Long nid);
	
	boolean update(Long id, Long sort, String url, String path);
}