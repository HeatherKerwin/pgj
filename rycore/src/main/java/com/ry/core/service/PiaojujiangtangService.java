package com.ry.core.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.entity.News;
import com.ry.core.entity.Piaojujiangtang;
import com.ry.core.entity.Servicemember;
import com.ry.core.form.NewsForm;
import com.ry.core.form.PiaojujiangtangForm;
import com.ry.util.page.PageResults;

@Repository
public interface PiaojujiangtangService {
	List<Piaojujiangtang> getList(Piaojujiangtang piaojujiangtang);
	public List<Piaojujiangtang> getList(Integer start, Integer end);
	PageResults<Piaojujiangtang> getPageList(PiaojujiangtangForm nf,Integer currentPage,Integer pageSize);
	
	void updateAllPiaojujiangtang(List<Piaojujiangtang> piaojujiangtangList);
	public Integer savePiaojujiangtang(Piaojujiangtang s);
	public void removePiaojujiangtang(Integer id);
	public void modifyPiaojujiangtang(Piaojujiangtang s);
	public Piaojujiangtang getPiaojujiangtangById(Integer id);
}
