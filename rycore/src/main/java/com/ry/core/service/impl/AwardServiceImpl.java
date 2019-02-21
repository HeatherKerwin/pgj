package com.ry.core.service.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.AwardDao;
import com.ry.core.dao.HongbaoActionDao;
import com.ry.core.dao.HongbaoDetailDao;
import com.ry.core.dao.MemberDao;
import com.ry.core.entity.Award;
import com.ry.core.entity.HongbaoAction;
import com.ry.core.entity.HongbaoActivity;
import com.ry.core.entity.HongbaoDetail;
import com.ry.core.entity.Member;
import com.ry.core.service.AwardService;
import com.ry.core.util.Constant;
import com.ry.util.DateUtil;

@Service
public class AwardServiceImpl implements AwardService{
	
	@Resource
	AwardDao awardDao;
	
	@Resource
	HongbaoActionDao hongbaoActionDao;
	
	@Resource
	HongbaoDetailDao hongbaoDetailDao;
	
	@Resource
	MemberDao memberDao;
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.AwardService#getByMemberId(java.lang.Integer)
	 */
	@Override
	public List<Award> getByMemberId(Integer memberId) {
		return awardDao.getByMemberId(memberId);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.AwardService#save(com.ry.core.entity.Award)
	 */
	@Override
	public void save(Award award) throws Exception {
		awardDao.saveAward(award);
		if(award!=null && 2==award.getGenre()){//奖品为红包
			HongbaoActivity ac = getActivityByIdAndIsUsable(award.getHid());
			if(ac!=null){//在红包有效期内则分发红包[保存红包明细]
				Date date = new Date();
				Member member = memberDao.getById(award.getMemberId());
				if(member!=null){
					Integer price = getPriceByHb(ac);//根据发放类型取金额
					String mobile = member.getMobile();
					HongbaoDetail hongbaoDetail = new HongbaoDetail();
					hongbaoDetail.setFlag(0);
					hongbaoDetail.setStatus(0);
					hongbaoDetail.setHid(ac.getId());
					hongbaoDetail.setPhone(mobile);
					hongbaoDetail.setMemberid(award.getMemberId());
					hongbaoDetail.setPrice(price);							
					hongbaoDetail.setCreatetime(date);
					hongbaoDetail.setUpdatetime(date);
					hongbaoDetailDao.addHongbaoDetail(hongbaoDetail);
					
					//用户行为记录
					HongbaoAction hongbaoAction = new HongbaoAction();
					hongbaoAction.setCode(Constant.SENDHONGBAO_ZP);//系统活动派送
					hongbaoAction.setCreatetime(date);
					hongbaoAction.setPhone(mobile);
					hongbaoAction.setHid(ac.getId());
					hongbaoAction.setPrice(price);
					hongbaoActionDao.addHongbaoAction(hongbaoAction);
				}
			}
		}
	}
	
	/**
	 * 根据主键获取红包活动（并且该活动有效）
	 * @author WKX
	 * @param hid
	 * @throws Exception
	 */
	private HongbaoActivity getActivityByIdAndIsUsable(Integer hid) throws Exception {
		HongbaoActivity result = hongbaoDetailDao.getActivityById(hid);
		if(result!=null){
			Date start = result.getStartdate();
			Date end = result.getEnddate();
			boolean hb_in = DateUtil.daysBetween(start,new Date())>=0 && DateUtil.daysBetween(new Date(),end)>=0;
			if(!hb_in){//如果过期
				return null;
			}
			//获取已经派送了多少总金额和总个数（任何一方超出都停止该活动）
			List<Object []> price_count = hongbaoActionDao.countHongbaoPrice(result.getId());
			if(price_count!=null && price_count.size()>0){
				Integer totalprice = result.getTotalprice();
				Integer totalnum = result.getTotalnum();
				if(totalprice!=null && totalprice>0){//红包总金额（超出）
					BigInteger temp = new BigInteger(totalprice.toString());
					Object prototype = price_count.get(0)[0];
					BigInteger money = null;
					if(prototype!=null)money = new BigInteger(prototype.toString());
					if(money!=null && money.compareTo(temp)==1)return null;
				}else if(totalnum!=null && totalnum>0){//红包总个数（超出）
					BigInteger temp = new BigInteger(totalnum.toString());
					Object prototype = price_count.get(0)[1];
					BigInteger num = null;
					if(prototype!=null)num = new BigInteger(prototype.toString());
					if(num!=null &&num.compareTo(temp)==1)return null;
				}
			}
		}
		return result;
	}
	
	/**
	 * 获取红包活动的金额
	 * @author WKX
	 * @param hb
	 */
	private Integer getPriceByHb(HongbaoActivity hb){
		Integer result = hb.getPrice();//固定金额（直接取值并返回）
		if(hb.getType()==1){//随机金额
			Integer max = hb.getMaxprice();
			Integer min = hb.getPrice();
			Random random = new Random();
	        if(max!=null && min!=null){
	        	result = random.nextInt(max)%(max-min+1) + min;
	        }
		}
		return result;
	}
}