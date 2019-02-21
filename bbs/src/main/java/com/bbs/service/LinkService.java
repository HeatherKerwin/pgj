package com.bbs.service;

import java.util.List;

import com.bbs.model.Link;
import com.blade.jdbc.QueryParam;

public interface LinkService {
	
	Link getLink(Integer id);
	
	List<Link> getLinkList(QueryParam queryParam);
	
	boolean save( String title, String url);
	
	boolean delete(Integer id);
		
}
