package com.ry.core.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BeatCodeDao;
import com.ry.core.entity.BeatCode;
import com.ry.core.service.BeatCodeService;

@Service
public class BeatCodeServiceImpl implements BeatCodeService{

	@Resource
	BeatCodeDao beatCodeDao;
	
	@Override
	public BeatCode getEnableByNo(String no) {
		List<BeatCode> list = beatCodeDao.getEnableByNo(no);
		if(list!=null && list.size()>0){
			return list.iterator().next();
		}else{
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.BeatCodeService#updateModel(com.ry.core.entity.BeatCode)
	 */
	@Override
	public void updateModel(BeatCode beatCode) {
		beatCodeDao.updateModel(beatCode);
	}
	
	/**
	 * 根据电话号码查询该用户已经输入过公测码
	 * @author WKX
	 * @param memberId
	 * @since 2016年3月23日 下午3:17:40
	 */
	public Map<String,Object> getByMemberId(Integer memberId){
		List<Map<String,Object>> list = beatCodeDao.getByMemberId(memberId);
		if(list!=null && list.size()>0){
			return list.iterator().next();
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.BeatCodeService#saveModel(com.ry.core.entity.BeatCode)
	 */
	@Override
	public String saveModel(BeatCode beatCode) {
		if(beatCode!=null && !StringUtils.hasText(beatCode.getNo())){
			String no = random();
			beatCode.setNo(no);
		}
		beatCodeDao.saveModel(beatCode);
		return beatCode.getNo();
	}
	
	/**
	 * 获取公测码
	 * @author WKX
	 * @since 2016年8月5日 下午3:31:37
	 */
	private String random(){
		String no = "";
		for(int i = 0; i <= 6; i++){
			no += (int)(Math.random()*10);
		}
		List<BeatCode> list = beatCodeDao.getByNo(no);
		if(list!=null && list.size()>0)return random();
		else return no;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.BeatCodeService#getByNoAndPhone(java.lang.String, java.lang.String)
	 */
	public BeatCode getByNoAndPhone(String no,String phone) {
		List<BeatCode> list = beatCodeDao.getByNoAndPhone(no,phone);
		if(list!=null && list.size()>0){
			return list.iterator().next();
		}else{
			return null;
		}
	}
}