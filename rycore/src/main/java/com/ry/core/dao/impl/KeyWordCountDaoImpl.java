package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.KeyWordCountDao;
import com.ry.core.entity.KeyWordCount;

@Repository
public class KeyWordCountDaoImpl extends BaseDao<KeyWordCount,Integer> implements KeyWordCountDao {

	@Override
	public void saveModel(KeyWordCount keyWordCount) {
		if(keyWordCount!=null && keyWordCount.getId()!=null){
			update(keyWordCount);
		}else{
			save(keyWordCount);
		}
	}
	
	public List<KeyWordCount> getByDayAndKeyword(String day,String keyword) {
		String hql = "from key_word_count where day=? and keyword=?";
		List<Object> paras = new ArrayList<Object>();		
		paras.add(day);
		paras.add(keyword);
		return getListByHQL(hql, paras.toArray());
	}
	
	public List<Map<String,Object>> getByStartAndEndAndKwGroup(String start,String end,String keyword) {
		StringBuffer hql = new StringBuffer("SELECT * FROM (select day,keyword,SUM(a_amount)aAmount,SUM(s_amount)sAmount FROM key_word_count where day BETWEEN ? and ?");
		List<Object> paras = new ArrayList<Object>();
		paras.add(start);
		paras.add(end);
		if(StringUtils.hasText(keyword)){
			hql.append(" and keyword=?");
			paras.add(keyword);
		}
		return getListMapBySQL(hql.toString()+" GROUP BY keyword)res ORDER BY res.aAmount DESC", paras.toArray());
	}
	
	public List<Map<String,Object>> getByStartAndEndAndKw(String start,String end,String keyword) {
		StringBuffer hql = new StringBuffer("select day,keyword,a_amount aAmount,s_amount sAmount FROM key_word_count where day BETWEEN ? and ?");
		List<Object> paras = new ArrayList<Object>();
		paras.add(start);
		paras.add(end);
		if(StringUtils.hasText(keyword)){
			hql.append(" and keyword=?");
			paras.add(keyword);
		}
		return getListMapBySQL(hql.toString(), paras.toArray());
	}
}