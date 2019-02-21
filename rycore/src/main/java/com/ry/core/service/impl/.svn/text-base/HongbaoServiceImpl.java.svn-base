package com.ry.core.service.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.AppVisitLogDao;
import com.ry.core.dao.DiscountrecordDao;
import com.ry.core.dao.HongbaoActionDao;
import com.ry.core.dao.HongbaoActivityDao;
import com.ry.core.dao.HongbaoDetailDao;
import com.ry.core.dao.HongbaoSendDao;
import com.ry.core.dao.MemberDao;
import com.ry.core.dao.SitestatisticsDao;
import com.ry.core.entity.HongbaoAction;
import com.ry.core.entity.HongbaoActivity;
import com.ry.core.entity.HongbaoDetail;
import com.ry.core.entity.HongbaoSend;
import com.ry.core.form.HongbaoActivityForm;
import com.ry.core.service.HongbaoService;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;

@Service
public class HongbaoServiceImpl implements HongbaoService {

	@Resource
	HongbaoActivityDao hongbaoActivityDao;
	
	@Resource
	HongbaoDetailDao hongbaoDetailDao;
	
	@Resource
	HongbaoSendDao hongbaoSendDao;
	
	@Resource
	HongbaoActionDao hongbaoActionDao;
	
	@Resource
	AppVisitLogDao appVisitLogDao;
	
	@Resource
	DiscountrecordDao discountrecordDao;
	
	@Resource
	SitestatisticsDao sitestatisticsDao;
	
	@Resource
	MemberDao memberDao;

	@Override
	public PageResults<HongbaoActivity> getPageResults(
			HongbaoActivityForm hongbaoActivityForm) {
		
		return hongbaoActivityDao.getPageResults(hongbaoActivityForm);		
	}

	@Override
	public void addActivity(HongbaoActivity activity) {
		
		hongbaoActivityDao.addActivity(activity);
	}

	@Override
	public void updateActivity(HongbaoActivity activity) {
		
		hongbaoActivityDao.updateActivity(activity);
	}

	@Override
	public HongbaoActivity getActivity(HongbaoActivity activity) {

		return hongbaoActivityDao.getActivity(activity);
	}
	
	@Override
	public List<HongbaoActivity> getActivitys(HongbaoActivity activity) {
		return hongbaoActivityDao.getActivitys(activity);
	}

	@Override
	public List<HongbaoDetail> getList(Integer memberid) {
		
		return hongbaoDetailDao.getList(memberid);
	}

	@Override
	public List<HongbaoDetail> getHistoryList(Integer memberid) {

		return hongbaoDetailDao.getHistoryList(memberid);
	}

	@Override
	public HongbaoDetail getHongbaoDetail(Integer id) {
		
		return hongbaoDetailDao.getHongbaoDetail(id);
	}

	@Override
	public void updateHongbaoDetail(HongbaoDetail hongbaoDetail) {
		
		hongbaoDetailDao.updateHongbaoDeteil(hongbaoDetail);
	}

	@Override
	public HongbaoDetail getHongbaoDetail(String orderNumber) {

		return hongbaoDetailDao.getHongbaoDetail(orderNumber);
	}

	@Override
	public HongbaoSend getHongbaoSend(Integer id) {
		
		return hongbaoSendDao.getHongbaoSend(id);
	}

	@Override
	public void addHongbaoDetail(HongbaoDetail hongbaoDetail) {
		
		hongbaoDetailDao.addHongbaoDetail(hongbaoDetail);
	}

	@Override
	public BigInteger countHongbaoDetail(Integer hid) {
		
		return hongbaoDetailDao.countHongbaoDetail(hid);
	}

	@Override
	public void addHongbaoAction(HongbaoAction hongbaoAction) {
		
		hongbaoActionDao.addHongbaoAction(hongbaoAction);
	}

	@Override
	public List<Object[]> countHongbaoPrice(Integer hid) {

		return hongbaoActionDao.countHongbaoPrice(hid);
	}

	@Override
	public List<HongbaoSend> getHongbaoSends(Integer memberid) {
		
		return hongbaoSendDao.getList(memberid);
	}

	@Override
	public List<HongbaoDetail> getHongbaoDetail(Integer sid, String phone) {
		 
		return hongbaoDetailDao.getHongbaoDetail(sid, phone);
	}

	@Override
	public List<HongbaoAction> getHongbaoActionList(Integer hid, String phone, Integer sid) {
		
		return hongbaoActionDao.getHongbaoActionList(hid, phone, sid);
	}
	
	@Override
	public void updateHongbaoAction(HongbaoAction hongbaoAction) {
		
		hongbaoActionDao.updateHongbaoAction(hongbaoAction);
	}

	@Override
	public List<HongbaoDetail> getUsedList(Integer memberid) {
		
		return hongbaoDetailDao.getUsedList(memberid);
	}

	@Override
	public void deleteHongbaoDetail(HongbaoDetail hongbaoDetail) {
		
		hongbaoDetailDao.deleteHongbaoDetail(hongbaoDetail);
	}

	@Override
	public List<HongbaoSend> getList(Integer memberid, Integer hid) {
		
		return hongbaoSendDao.getList(memberid, hid);
	}

	@Override
	public void addHongbaoSend(HongbaoSend hongbaoSend) {
		
		hongbaoSendDao.addHongbaoSend(hongbaoSend);
	}

	@Override
	public List<HongbaoDetail> getListFlag0() {
		
		return hongbaoDetailDao.getListFlag0();
	}

	@Override
	public Map<String, String> getCount(Integer id) {
		return hongbaoDetailDao.getCount(id);
	}

	@Override
	public List<Integer> getHids() {
		
		return hongbaoDetailDao.getHids();
	}

	@Override
	public HongbaoActivity getCountByBean(Integer id) {
		return hongbaoDetailDao.getCountByBean(id);
		
	}

	@Override
	public HongbaoActivity getActivityById(Integer id) {

		return hongbaoDetailDao.getActivityById(id);
	}

	@Override
	public BigInteger countHongbaoAction(Integer hid) {
		
		return hongbaoActionDao.countHongbaoAction(hid);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.HongbaoService#getByOrdernumber(java.lang.String)
	 */
	public HongbaoDetail getByOrdernumber(String ordernumber){
		List<HongbaoDetail> list = hongbaoDetailDao.getByOrdernumber(ordernumber);
		if(list!=null && list.size()>0) return list.iterator().next();
		else return null;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.HongbaoService#countByStartAndEndAndTagIdAndNotId(java.util.Date, java.util.Date, java.lang.Integer, java.lang.Integer)
	 */
	public Long countByStartAndEndAndTagIdAndNotId(Date start,Date end,Integer tagId,Integer id){
		return hongbaoActivityDao.countByStartAndEndAndTagIdAndNotId(start, end, tagId,id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.HongbaoService#getActivityByTagId(java.lang.Integer)
	 */
	public HongbaoActivity getActivityByTagId(Integer tagId) throws Exception {
		HongbaoActivity result = null;
		HongbaoActivity query = new HongbaoActivity();
		query.setStatus(0);//未删除
		query.setFlag(1);//进行中
		query.setTagId(Integer.valueOf(tagId));//类型（引流新用户、贴现已完成...）
		List<HongbaoActivity> hlist = getActivitys(query);
		
		if(hlist!=null && hlist.size()>0){
			for(HongbaoActivity hb_ :hlist){//防止红包状态未及时更新状态（在其中检索进行中未删除的）
				Date start = hb_.getStartdate();
				Date end = hb_.getEnddate();
				boolean hb_in = DateUtil.daysBetween(start,new Date())>=0 && DateUtil.daysBetween(new Date(),end)>=0;
				if(hb_in){
					result = hb_;
					break;
				}
			}
			if(result==null)return result;//如果没有符合要求的就返回空
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
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.HongbaoService#getMapActivitys(com.ry.core.form.HongbaoActivityForm)
	 */
	public List<Map<String,Object>> getMapActivitys(HongbaoActivityForm activity){
		return hongbaoActivityDao.getMapActivitys(activity);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.HongbaoService#getCountByStartAndEnd(java.lang.String, java.lang.Integer, java.lang.Integer, java.util.List)
	 */
	public List<Map<String,Object>> getCountByStartAndEnd(String type,Integer tagId,Integer hid,List<Date> datas){
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		Date start = null,end = null;
		Calendar cal = Calendar.getInstance();
		for(int i=0;i<datas.size();i++){
			List<Map<String,Object>> temp = null;
			if("-1".equals(type)){//按天查询
				start = datas.get(i);
				cal.setTime(start);
				cal.add(Calendar.DAY_OF_MONTH, 1);
				end = cal.getTime();
			}else if("0".equals(type)){//按周查询
				start = datas.get(i);
				cal.setTime(start);
				cal.add(Calendar.WEEK_OF_MONTH, 1);
				end = cal.getTime();
			}else{//按月查询
				start = datas.get(i);
				cal.setTime(start);
				cal.add(Calendar.MONTH, 1);
				end = cal.getTime();
			}
			if(tagId==101){//已完成贴现
				temp = hongbaoSendDao.getCountByStartAndEnd(hid, start, end);
			}else if(tagId==102){//引流新用户（推荐好友页面的记录编号：p0）
				temp = appVisitLogDao.getCountByStartAndEnd("p0", start, end);
			}else if(tagId==103){//市场推广（类型：hongbao）
				temp = sitestatisticsDao.getCountByStartAndEnd("hongbao", start, end);
			}
			if(temp!=null && temp.size()>0){
				Map<String,Object> tmp = temp.get(0);
				tmp.put("createtime", start);
				result.add(tmp);
			}
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.HongbaoService#getCountByStartAndEndRatio(java.lang.String, java.lang.Integer, java.lang.Integer, java.util.List)
	 */
	public List<Map<String,Object>> getCountByStartAndEndRatio(String type,Integer tagId,Integer hid,List<Date> datas) throws ParseException{
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		Date start = null,end = null;
		Calendar cal = Calendar.getInstance();
		for(int i=0;i<datas.size();i++){//拆分时间段查询，汇总数据
			List<Map<String,Object>> up = null;
			List<Map<String,Object>> down = null;
			Map<String,Object> temp = new HashMap<String, Object>();
			if("-1".equals(type)){//按天查询
				start = datas.get(i);
				cal.setTime(start);
				cal.add(Calendar.DAY_OF_MONTH, 1);
				end = cal.getTime();
			}else if("0".equals(type)){//按周查询
				start = datas.get(i);
				cal.setTime(start);
				cal.add(Calendar.WEEK_OF_MONTH, 1);
				end = cal.getTime();
			}else{//按月查询
				start = datas.get(i);
				cal.setTime(start);
				cal.add(Calendar.MONTH, 1);
				end = cal.getTime();
			}
			if(tagId==101){//已完成贴现
				up = hongbaoSendDao.getCountByStartAndEnd(hid, start, end);
				down = discountrecordDao.getFinishByStartAndEnd(start, end);
				if(down!=null && up!=null && down.size()>0 && up.size()>0){//分子分母都有值
					float a = (float)((BigInteger) up.get(0).get("amount")).intValue();
					try {
						float b = (float)((BigInteger) down.get(0).get("amount")).intValue();
						temp.put("amount", a/b*100);
						String token = (a/b*100)+"";
						if("NaN".equals(token) || "Infinity".equals(token)){//假设存在分母为0的情况时会出现NaN的结果
							temp.put("amount", a*100);
						}
					} catch (java.lang.ArithmeticException e) {
						temp.put("amount", 0);
					}
				}else{
					temp.put("amount", 0);
				}
			}else if(tagId==102){//引流新用户（推荐好友页面的记录编号：p0）
				up = hongbaoActionDao.getCountByStartAndEnd(start, end);
				down = appVisitLogDao.getCountByStartAndEnd("p0", start, end);
				if(down!=null && up!=null && down.size()>0 && up.size()>0){//分子分母都有值
					float a = (float)((BigInteger) up.get(0).get("amount")).intValue();
					try {
						float b = (float)((BigInteger) down.get(0).get("amount")).intValue();
						temp.put("amount", a/b*100);
						String token = (a/b*100)+"";
						if("NaN".equals(token) || "Infinity".equals(token)){//假设存在分母为0的情况时会出现NaN的结果
							temp.put("amount", a*100);
						}
					} catch (java.lang.ArithmeticException e) {
						temp.put("amount", a*100);
					}
				}else{
					temp.put("amount", 0);
				}
			}
			if(down!=null && down.size()>0){
				temp.put("createtime", start);
				result.add(temp);
			}
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.HongbaoService#getFromHongbaoCountByStartAndEnd(java.lang.String, java.lang.Integer, java.lang.Integer, java.util.List)
	 */
	public List<Map<String,Object>> getFromHongbaoCountByStartAndEnd(String type,Integer tagId,Integer hid,List<Date> datas){
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		Date start = null,end = null;
		Calendar cal = Calendar.getInstance();
		for(int i=0;i<datas.size();i++){
			List<Map<String,Object>> temp = null;
			if("-1".equals(type)){//按天查询
				start = datas.get(i);
				cal.setTime(start);
				cal.add(Calendar.DAY_OF_MONTH, 1);
				end = cal.getTime();
			}else if("0".equals(type)){//按周查询
				start = datas.get(i);
				cal.setTime(start);
				cal.add(Calendar.WEEK_OF_MONTH, 1);
				end = cal.getTime();
			}else{//按月查询
				start = datas.get(i);
				cal.setTime(start);
				cal.add(Calendar.MONTH, 1);
				end = cal.getTime();
			}
			temp = memberDao.getFromHongbaoCountByStartAndEnd(hid, start, end);
			if(temp!=null && temp.size()>0){
				Map<String,Object> tmp = temp.get(0);
				tmp.put("createtime", start);
				result.add(tmp);
			}
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.HongbaoService#getFromHongbaoCountByStartAndEndRatio(java.lang.String, java.lang.Integer, java.lang.Integer, java.util.List)
	 */
	public List<Map<String,Object>> getFromHongbaoCountByStartAndEndRatio(String type,Integer tagId,Integer hid,List<Date> datas){
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		Date start = null,end = null;
		Calendar cal = Calendar.getInstance();
		for(int i=0;i<datas.size();i++){
			Map<String,Object> temp = new HashMap<String, Object>();
			List<Map<String,Object>> up = null;
			List<Map<String,Object>> down = null;
			if("-1".equals(type)){//按天查询
				start = datas.get(i);
				cal.setTime(start);
				cal.add(Calendar.DAY_OF_MONTH, 1);
				end = cal.getTime();
			}else if("0".equals(type)){//按周查询
				start = datas.get(i);
				cal.setTime(start);
				cal.add(Calendar.WEEK_OF_MONTH, 1);
				end = cal.getTime();
			}else{//按月查询
				start = datas.get(i);
				cal.setTime(start);
				cal.add(Calendar.MONTH, 1);
				end = cal.getTime();
			}
			up = memberDao.getFromHongbaoCountByStartAndEnd(hid, start, end);
			down = memberDao.getCountByStartAndEnd(start, end);
			if(down!=null && up!=null && down.size()>0 && up.size()>0){//分子分母都有值
				float a = (float)((BigInteger) up.get(0).get("amount")).intValue();
				try {
					float b = (float)((BigInteger) down.get(0).get("amount")).intValue();
					temp.put("amount", a/b*100);
					String token = (a/b*100)+"";
					if("NaN".equals(token) || "Infinity".equals(token)){//假设存在分母为0的情况时会出现NaN的结果
						temp.put("amount", a*100);
					}
				} catch (java.lang.Exception e) {
					temp.put("amount", a*100);
				}
			}else{
				temp.put("amount", 0);
			}
			temp.put("createtime", start);
			result.add(temp);
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.HongbaoService#getCountFromNewByStartAndEnd(java.lang.String, java.lang.Integer, java.lang.Integer, java.util.List, java.util.Date, java.util.Date)
	 */
	public List<Map<String,Object>> getCountFromNewByStartAndEnd(String type,Integer tagId,Integer hid,List<Date> datas,Date mStart,Date mEnd){
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		Date start = null,end = null;
		Calendar cal = Calendar.getInstance();
		for(int i=0;i<datas.size();i++){
			List<Map<String,Object>> temp = null;
			if("-1".equals(type)){//按天查询
				start = datas.get(i);
				cal.setTime(start);
				cal.add(Calendar.DAY_OF_MONTH, 1);
				end = cal.getTime();
			}else if("0".equals(type)){//按周查询
				start = datas.get(i);
				cal.setTime(start);
				cal.add(Calendar.WEEK_OF_MONTH, 1);
				end = cal.getTime();
			}else{//按月查询
				start = datas.get(i);
				cal.setTime(start);
				cal.add(Calendar.MONTH, 1);
				end = cal.getTime();
			}
			temp = appVisitLogDao.getCountFromNewByStartAndEnd(hid,mStart, mEnd, start, end);
			if(temp!=null && temp.size()>0){
				Map<String,Object> tmp = temp.get(0);
				tmp.put("createtime", start);
				result.add(tmp);
			}
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.HongbaoService#getLcWeekByStartAndEnd(java.lang.Integer, java.lang.String, java.util.List, java.util.List, java.util.List)
	 */
	public List<List<Object>> getLcWeekByStartAndEnd(Integer hid,String otherDays,List<Date> datas,List<Integer> xTitle,List<String> yTitle) throws ParseException{
		List<List<Object>> result = new ArrayList<List<Object>>();
		Integer day = Integer.valueOf(otherDays);
		List<Object> temp = null;
		Date start = null,end = null;
		Date vStart = null,vEnd = null;
		Calendar cal = Calendar.getInstance();
		Calendar c = null;
		List<Map<String,Object>> down_ = null,up_ = null;
		for(int i=0;i<datas.size();i++){
			temp = new ArrayList<Object>();
			temp.add(yTitle.get(i));//设置y轴
			
			start = datas.get(i);//用户新增的开始日期
			cal.setTime(start);
			cal.add(Calendar.WEEK_OF_MONTH, 1);
			end = cal.getTime();
			down_ = memberDao.getFromHongbaoCountByStartAndEnd(hid, start, end);
			float up = 0f,down = 1f;
			if(down_!=null && down_.size()>0 && down_.get(0).get("amount")!=null){
				down = (float)((BigInteger) down_.get(0).get("amount")).intValue();
			}
			temp.add(down);
			for(Integer x:xTitle){//循环查询几周几月后
				int add = x+1;//添加的日期
				if(day/7>=add){
					c = Calendar.getInstance();
					c.setTime(start);
					c.add(Calendar.WEEK_OF_MONTH, add);
					vStart = c.getTime();//查询访问的开始日期
					vEnd = new Date();//查询访问的结束日期
					if(DateUtil.daysBetween(vStart, vEnd)>0){
						up_ = appVisitLogDao.getTotalCountFromNewByStartAndEnd(hid, start, end, vStart, vEnd);
						if(up_!=null && up_.size()>0 && up_.get(0).get("amount")!=null){
							up = (float)((BigInteger) up_.get(0).get("amount")).intValue();
							Object t = up/down*100;
							if("NaN".equals(t+"") || "Infinity".equals(t+"")){//假设存在分母为0的情况时会出现NaN的结果
								t = up*100;
							}
							temp.add(t);
						}else{
							temp.add(-1);//设置y轴（空值）
						}
					}else{
						temp.add(-1);//设置y轴（空值）
					}
				}else{
					temp.add(-1);//设置y轴（空值）
				}
			}
			result.add(temp);
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.HongbaoService#getLcMonthByStartAndEnd(java.lang.Integer, java.lang.String, java.util.List, java.util.List, java.util.List)
	 */
	public List<List<Object>> getLcMonthByStartAndEnd(Integer hid,String otherDays,List<Date> datas,List<Integer> xTitle,List<String> yTitle) throws ParseException{
		List<List<Object>> result = new ArrayList<List<Object>>();
		Integer day = Integer.valueOf(otherDays);
		List<Object> temp = null;
		Date start = null,end = null;
		Date vStart = null,vEnd = null;
		Calendar cal = Calendar.getInstance();
		Calendar c = null;
		List<Map<String,Object>> down_ = null,up_ = null;
		for(int i=0;i<datas.size();i++){
			temp = new ArrayList<Object>();
			temp.add(yTitle.get(i));//设置y轴
			
			start = datas.get(i);//用户新增的开始日期
			cal.setTime(start);
			cal.add(Calendar.MONTH, 1);
			end = cal.getTime();
			down_ = memberDao.getFromHongbaoCountByStartAndEnd(hid, start, end);
			float up = 0f,down = 1f;
			if(down_!=null && down_.size()>0 && down_.get(0).get("amount")!=null){
				down = (float)((BigInteger) down_.get(0).get("amount")).intValue();
			}
			temp.add(down);
			for(Integer x:xTitle){//循环查询几周几月后
				int add = x+1;//添加的日期
				if(day/30>=add){
					c = Calendar.getInstance();
					c.setTime(start);
					c.add(Calendar.MONTH, add);
					vStart = c.getTime();//查询访问的开始日期
					vEnd = new Date();//查询访问的结束日期
					if(DateUtil.daysBetween(vStart, vEnd)>0){
						up_ = appVisitLogDao.getTotalCountFromNewByStartAndEnd(hid, start, end, vStart, vEnd);
						if(up_!=null && up_.size()>0 && up_.get(0).get("amount")!=null){
							up = (float)((BigInteger) up_.get(0).get("amount")).intValue();
							Object t = up/down*100;
							if("NaN".equals(t+"") || "Infinity".equals(t+"")){//假设存在分母为0的情况时会出现NaN的结果
								t = up*100;
							}
							temp.add(t);
						}else{
							temp.add(-1);//设置y轴（空值）
						}
					}else{
						temp.add(-1);//设置y轴（空值）
					}
				}else{
					temp.add(-1);//设置y轴（空值）
				}
			}
			result.add(temp);
		}
		return result;
	}
}