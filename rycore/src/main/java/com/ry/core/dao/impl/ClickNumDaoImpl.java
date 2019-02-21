package com.ry.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.ClickNumDao;
import com.ry.core.entity.ClickNum;

@Repository
public class ClickNumDaoImpl extends BaseDao<ClickNum, Integer> implements ClickNumDao {

	@Override
	public void saveClickNum(ClickNum clickNum) {
		String code = clickNum.getCode();
		if(code ==  null || code.length() == 0){
			
		}else{
			save(clickNum);
		}
		
	}

}
