package com.ry.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.entity.News;
import com.ry.core.entity.Piaojujiangtang;
import com.ry.core.entity.Servicemember;
import com.ry.core.form.NewsForm;
import com.ry.core.form.PiaojujiangtangForm;
import com.ry.util.page.PageResults;

@Repository
public interface PiaojujiangtangDao {

	PageResults<Piaojujiangtang> getPageList(PiaojujiangtangForm nf,Integer currentPage,Integer pageSize);
	List<Piaojujiangtang> getList(Integer start, Integer end);
	void addPiaojujiangtang(Piaojujiangtang piaojujiangtang);	
//	void updateNews(News news);	
public	void deletePiaojujiangtang(Integer id);
public	Piaojujiangtang getById(Integer id);
public Integer savePiaojujiangtang(Piaojujiangtang s);
public void updatePiaojujiangtang(Piaojujiangtang s);

}
