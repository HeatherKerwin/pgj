package com.ry.core.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.DiscountRebateDao;
import com.ry.core.dao.DiscountrecordDao;
import com.ry.core.entity.DiscountRebate;
import com.ry.core.entity.Discountrecord;
import com.ry.core.form.DiscountRebateForm;
import com.ry.core.service.DiscountRebateService;
import com.ry.core.util.Utility;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;

@Service
public class DiscountRebateServiceImpl extends BaseDao<DiscountRebate, Integer> implements DiscountRebateService {

	@Resource
	DiscountRebateDao discountRebateDao;
	
	@Resource
	DiscountrecordDao discountrecordDao;
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountRebateService#updateModel(com.ry.core.entity.DiscountRebate)
	 */
	public void updateModel(DiscountRebate discountRebate){
		discountRebateDao.updateModel(discountRebate);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountRebateService#getById(java.lang.Integer)
	 */
	public DiscountRebate getById(Integer id){
		return discountRebateDao.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountRebateService#findPageModel(java.lang.Integer, java.lang.Integer, com.ry.core.entity.DiscountRebate)
	 */
	public PageResults<Map<String,Object>> findPageModel(Integer pageIndex,Integer pageSize,DiscountRebate from){
		return discountRebateDao.findPageModel(pageIndex, pageSize, from);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountRebateService#saveFromFinish(com.ry.core.entity.Discountrecord)
	 */
	public void saveFromFinish(Discountrecord discountrecord) throws ParseException{
		String _min = "2016-10-01";
		String _max = "2017-01-01";
		Date now = new Date();
		Date min = DateUtil.parser(_min, DateUtil.FORMART3);
		Date max = DateUtil.parser(_max, DateUtil.FORMART3);
		
		List<DiscountRebate> disc_list = discountRebateDao.getByDcrdId(discountrecord.getId());//防止重复订单返利
		if(disc_list!=null && disc_list.size()>0)return;
		
		if(min.getTime()<=now.getTime() && now.getTime()<max.getTime()){//在活动期间内
			DiscountRebateForm form = new DiscountRebateForm();
			form.setMemberId(discountrecord.getMemberid());
			form.setStart(min);
			form.setEnd(max);
			List<Map<String,Object>> list = discountRebateDao.getList(form);
			if((list==null || list.size()<5) && discountrecord.getDepositState()!=null && discountrecord.getDepositState()==2){//五单以下
				String mobile = discountrecord.getMembermobile();
				Integer index = 1;
				DiscountRebate rebate = new DiscountRebate();
				rebate.setDcrdId(discountrecord.getId());
				rebate.setMemberMobile(Utility.decodeMobile(mobile));
				rebate.setMemberName(discountrecord.getMembername());
				rebate.setCard(discountrecord.getCard());//账号
				rebate.setState(0);//待处理
				rebate.setCreateTime(new Date());
				
				if(list!=null)index += list.size();
				rebate.setIndex(index);//第几单
				rebate.setMoney((float)index*10);//返现金额
				discountRebateDao.saveModel(rebate);
			}
		}
	}
}