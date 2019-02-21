package com.ry.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.HistorypriceDao;
import com.ry.core.dao.NewsDao;
import com.ry.core.dao.PiaojujiangtangDao;
import com.ry.core.entity.Currentprice;
import com.ry.core.entity.Dayprice;
import com.ry.core.entity.Historyprice;
import com.ry.core.entity.News;
import com.ry.core.entity.Piaojujiangtang;
import com.ry.core.entity.Servicemember;
import com.ry.core.form.NewsForm;
import com.ry.core.form.PiaojujiangtangForm;
import com.ry.core.service.HistorypriceService;
import com.ry.core.service.NewsService;
import com.ry.core.service.PiaojujiangtangService;
import com.ry.util.page.PageResults;

@Service
public class PiaojujiangtangServiceImpl implements  PiaojujiangtangService{
	@Resource
	PiaojujiangtangDao  piaojujiangtangDao;

	@Override
	public List<Piaojujiangtang> getList(Piaojujiangtang piaojujiangtang) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PageResults<Piaojujiangtang> getPageList(PiaojujiangtangForm nf, Integer currentPage,
			Integer pageSize) {
		PageResults<Piaojujiangtang> pageResults = piaojujiangtangDao.getPageList(nf, currentPage, pageSize);
		List<Piaojujiangtang> piaojujiangtangs = pageResults.getResults();
		List<Piaojujiangtang> piaojujiangtangList = new ArrayList<Piaojujiangtang>();
		if(!(piaojujiangtangs == null || piaojujiangtangs.size() == 0)){
			for(Piaojujiangtang baseEntity : piaojujiangtangs){
				Piaojujiangtang piaojujiangtang = (Piaojujiangtang)baseEntity;
				if (piaojujiangtang.getPublishtime() != null) {
					piaojujiangtang.setPublishtimeStr(new SimpleDateFormat("yyyy-MM-dd").format(piaojujiangtang.getPublishtime()));
				}				
				String title = piaojujiangtang.getTitle();
				if(title!=null){
					piaojujiangtang.setTitleShow(title.length()>10?title.substring(0,10)+"...":title);
				}
				piaojujiangtangList.add(piaojujiangtang);
			}
		}
		pageResults.setResults(piaojujiangtangList);
		return pageResults;
	}

	@Override
	public void updateAllPiaojujiangtang(List<Piaojujiangtang> piaojujiangtangList) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Integer savePiaojujiangtang(Piaojujiangtang s) {
		return piaojujiangtangDao.savePiaojujiangtang(s);
	}
	@Override
	public void removePiaojujiangtang(Integer id) {
		piaojujiangtangDao.deletePiaojujiangtang(id);
	}

	@Override
	public void modifyPiaojujiangtang(Piaojujiangtang s) {
		piaojujiangtangDao.updatePiaojujiangtang(s);
		
	}
	@Override
	public Piaojujiangtang getPiaojujiangtangById(Integer id) {
		return piaojujiangtangDao.getById(id);
	}
	@Override
	public List<Piaojujiangtang> getList(Integer start, Integer end) {
		// TODO Auto-generated method stub
		return piaojujiangtangDao.getList(start, end);
	}
	}
