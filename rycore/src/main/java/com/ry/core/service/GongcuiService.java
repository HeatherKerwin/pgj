package com.ry.core.service;

import java.util.List;

import com.ry.core.entity.Gongcui;

public interface GongcuiService {
	
	void addAllGongcui(List<Gongcui> list);

	List<Gongcui> getList(Integer start, Integer end);
	
	List<Gongcui> getList(String gongcuinumber);
}
