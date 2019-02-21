package com.ry.core.service.impl;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ry.core.dao.OrgInfoDao;
import com.ry.core.dao.OrgWarnDao;
import com.ry.core.dao.PriceDao;
import com.ry.core.dao.PriceTypeDao;
import com.ry.core.dao.VariablesDao;
import com.ry.core.entity.OrgInfo;
import com.ry.core.entity.OrgWarn;
import com.ry.core.entity.Price;
import com.ry.core.entity.PriceType;
import com.ry.core.entity.Variables;
import com.ry.core.form.PriceForm;
import com.ry.core.service.PriceService;
import com.ry.util.DateUtil;

@Service
public class PriceServiceImpl implements PriceService{
	
	@Resource
	PriceDao priceDao;
	
	@Resource
	VariablesDao variablesDao;
	
	@Resource
	OrgInfoDao orgInfoDao;
	
	@Resource
	OrgWarnDao orgWarnDao;
	
	@Resource
	PriceTypeDao priceTypeDao;
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceService#getDtboDcrdById(java.lang.Integer)
	 */
	public Map<String,Object> getDtboDcrdById(Integer dtboId){
		List<Map<String,Object>> result = priceDao.getDtboDcrdById(dtboId);
		if(result!=null && result.size()>0){
			return result.iterator().next();
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceService#getPriceById(java.lang.Integer)
	 */
	public Map<String,Object> getPriceById(Integer id){
		List<Map<String,Object>> result = priceDao.getPriceById(id);
		if(result!=null && result.size()>0){
			return result.iterator().next();
		}else{
			return null;
		} 
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceService#getByOrgId(java.lang.Integer, java.lang.String, java.lang.Integer)
	 */
	public List<Price> getByOrgId(Integer orgId, String createTime, Integer priceTypeId, Integer cityId) {
		return priceDao.getByOrgId(orgId, createTime, priceTypeId, cityId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceService#getPtid(java.lang.Integer, java.lang.String)
	 */
	public List<Map<String, Object>> getPtid(Integer orgId, String date) {
		return priceDao.getPtid(orgId, date);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceService#getPtidAndDay(java.lang.Integer, java.lang.String)
	 */
	public List<Map<String, Object>> getPtidAndDay(Integer orgId, String date, Integer cityId) {
		return priceDao.getPtidAndDay(orgId, date, cityId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceService#saveEntity(com.ry.core.entity.Price)
	 */
	public Integer saveEntity(Price price) {
		PriceType pt = priceTypeDao.getById(price.getPriceTypeId());//将常规报价的存储统一成快速报价一样的方式
		price = getPricePager1(price, pt); 
		warnorg(price);
		return priceDao.saveByEntity(getSameByOrgId(price));
	}
	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceService#saveByList(java.util.List)
	 */
	public void saveByList(List<Price> priceList) {
		if (priceList != null && priceList.size() > 0) {
			for (Price p : priceList) {
				PriceType pt = priceTypeDao.getById(p.getPriceTypeId());//将常规报价的存储统一成快速报价一样的方式
				p = getPricePager1(p, pt);
				warnorg(p);
				priceDao.saveByEntity(getSameByOrgId(p));
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceService#getById(java.lang.Integer)
	 */
	public List<Map<String, Object>> getByPriceTypeId(Integer id) {
		return priceDao.getByPriceTypeId(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceService#infoById(java.lang.Integer, java.lang.Integer)
	 */
	public Price infoById(Integer orgId, Integer priceTypeId, Integer cityId) {
		List<Price> list = priceDao.infoById(orgId, priceTypeId, cityId);
		if(list!=null && list.size()>0){
			return list.iterator().next();
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceService#getByPriceTypeAndOrgId(com.ry.core.entity.PriceType, java.lang.Integer)
	 */
	public Map<String, Object> getByPriceTypeAndOrgId(PriceType priceType,Integer orgId){
		List<Map<String, Object>> list = priceDao.getByPriceTypeAndOrgId(priceType, orgId);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceService#getByOrgId(java.lang.Integer)
	 */
	public List<Map<String, Object>> getByOrgId(Integer orgId){
		return priceDao.getByOrgId(orgId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceService#getPriceAndTypeByOrgId(java.lang.Integer, java.lang.String)
	 */
	public List<Map<String, Object>> getPriceAndTypeByOrgId(Integer orgId, String createTime, Integer cityId,Integer memberId) {
		return priceDao.getPriceAndTypeByOrgId(orgId, createTime, cityId,memberId);
	}

	@Override
	public boolean getPriceByCityAndOrgId(Integer orgId, String createTime, Integer cityId,Integer memberId) {
		List<Map<String, Object>> lists = priceDao.getPriceAndTypeByOrgId(orgId, createTime, cityId,memberId);
		if (lists != null && lists.size() > 0) {
			for(Map<String,Object> list : lists){
				Integer type=Integer.valueOf(list.get("ptid").toString());
				Map<String, Object> result = minOrg(type);
				if( !"--".equals(list.get("gg").toString()) && !"--".equals(result.get("guogu").toString()) ){
		    		 Float min = Float.parseFloat( result.get("guogu").toString());
		    		 Float value = Float.parseFloat(list.get("gg").toString());
		    	     if(value >= min+1.00){ 
		    	    	 return true;
		    	     }
		        }else if( !"--".equals(list.get("ds").toString()) && !"--".equals(result.get("dashang").toString()) ){
		    		 Float min = Float.parseFloat( result.get("dashang").toString());
		    		 Float value = Float.parseFloat(list.get("ds").toString());
		    	     if(value >= min+1.00){ 
		    	    	 return true;
		    	     }
		        }else if( !"--".equals(list.get("cs").toString()) && !"--".equals(result.get("chengshang").toString()) ){
		    		 Float min = Float.parseFloat( result.get("chengshang").toString());
		    		 Float value = Float.parseFloat(list.get("cs").toString());
		    	     if(value >= min+1.00){ 
		    	    	 return true;
		    	     }
		        }else if( !"--".equals(list.get("wz").toString()) && !"--".equals(result.get("waizi").toString()) ){
		    		 Float min = Float.parseFloat( result.get("waizi").toString());
		    		 Float value = Float.parseFloat(list.get("wz").toString());
		    	     if(value >=min+1.00){ 
		    	    	 return true;
		    	     }
		        }else if( !"--".equals(list.get("ns").toString()) && !"--".equals(result.get("nongshang").toString()) ){
		    		 Float min = Float.parseFloat( result.get("nongshang").toString());
		    		 Float value = Float.parseFloat(list.get("ns").toString());
		    	     if(value >= min+1.00){ 
		    	    	 return true;
		    	     }
		        }else if( !"--".equals(list.get("nh").toString()) && !"--".equals(result.get("nonghe").toString()) ){
		    		 Float min = Float.parseFloat( result.get("nonghe").toString());
		    		 Float value = Float.parseFloat(list.get("nh").toString());
		    	     if(value >= min+1.00){ 
		    	    	 return true;
		    	     }
		        }else if( !"--".equals(list.get("nx").toString()) && !"--".equals(result.get("nongxin").toString()) ){
		    		 Float min = Float.parseFloat( result.get("nongxin").toString());
		    		 Float value = Float.parseFloat(list.get("nx").toString());
		    	     if(value >= min+1.00){ 
		    	    	 return true;
		    	     }
		        }else if( !"--".equals(list.get("cz").toString()) && !"--".equals(result.get("cunzhen").toString()) ){
		    		 Float min = Float.parseFloat( result.get("cunzhen").toString());
		    		 Float value = Float.parseFloat(list.get("cz").toString());
		    	     if(value >= min+1.00){ 
		    	    	 return true;
		    	     }
		        }
			}
			return false;
		  }
		else {
			return false;
		}
		}
	
	@Override
	public List<Map<String, Object>> getPriceAndTypeByOrgId1(Integer orgId, String createTime, Integer cityId,Integer memberId) {
		List<Map<String, Object>> lists = priceDao.getPriceAndTypeByOrgId(orgId, createTime, cityId,memberId);
		if (lists != null && lists.size() > 0) {
			for(Map<String,Object> list : lists){
				Integer type=Integer.valueOf(list.get("ptid").toString());
				Map<String, Object> result = minOrg(type);
				 if( !"--".equals(list.get("gg").toString()) && !"--".equals(result.get("guogu").toString()) ){
		    		 Float min = Float.parseFloat( result.get("guogu").toString());
		    		 Float value = Float.parseFloat(list.get("gg").toString());
		    	     if(value < min+1.00){ //报价大于他的最低价+1时
		    	    	 list.put("gg", "--");
		    	     }
		        }
				 if( !"--".equals(list.get("ds").toString()) && !"--".equals(result.get("dashang").toString()) ){
		    		 Float min = Float.parseFloat( result.get("dashang").toString());
		    		 Float value = Float.parseFloat(list.get("ds").toString());
		    	     if(value < min+1.00){ //报价大于他的最低价+1时
		    	    	 list.put("ds", "--");
		    	     }
		        }if( !"--".equals(list.get("cs").toString()) && !"--".equals(result.get("chengshang").toString()) ){
		    		 Float min = Float.parseFloat( result.get("chengshang").toString());
		    		 Float value = Float.parseFloat(list.get("cs").toString());
		    	     if(value < min+1.00){ //报价大于他的最低价+1时
		    	    	 list.put("cs", "--");
		    	     }
		        }if( !"--".equals(list.get("wz").toString()) && !"--".equals(result.get("waizi").toString()) ){
		    		 Float min = Float.parseFloat( result.get("waizi").toString());
		    		 Float value = Float.parseFloat(list.get("wz").toString());
		    	     if(value < min+1.00){ //报价大于他的最低价+1时
		    	    	 list.put("wz", "--");
		    	     }
		        }if( !"--".equals(list.get("ns").toString()) && !"--".equals(result.get("nongshang").toString()) ){
		    		 Float min = Float.parseFloat( result.get("nongshang").toString());
		    		 Float value = Float.parseFloat(list.get("ns").toString());
		    	     if(value < min+1.00){ //报价大于他的最低价+1时
		    	    	 list.put("ns", "--");
		    	     }
		        }if( !"--".equals(list.get("nh").toString()) && !"--".equals(result.get("nonghe").toString()) ){
		    		 Float min = Float.parseFloat( result.get("nonghe").toString());
		    		 Float value = Float.parseFloat(list.get("nh").toString());
		    	     if(value < min+1.00){ //报价大于他的最低价+1时
		    	    	 list.put("nh", "--");
		    	     }
		        }if( !"--".equals(list.get("nx").toString()) && !"--".equals(result.get("nongxin").toString()) ){
		    		 Float min = Float.parseFloat( result.get("nongxin").toString());
		    		 Float value = Float.parseFloat(list.get("nx").toString());
		    	     if(value < min+1.00){ //报价大于他的最低价+1时
		    	    	 list.put("nx", "--");
		    	     }
		        }if( !"--".equals(list.get("cz").toString()) && !"--".equals(result.get("cunzhen").toString()) ){
		    		 Float min = Float.parseFloat( result.get("cunzhen").toString());
		    		 Float value = Float.parseFloat(list.get("cz").toString());
		    	     if(value < min+1.00){ //报价大于他的最低价+1时
		    	    	 list.put("cz", "--");
		    	     }
		        }
			}
		}
		return lists;
	}
	
	@Override
	public List<Map<String, Object>> getAllPrice(String date, PriceType pt, Integer cityId) {
		return priceDao.getAllPrice(date, pt,cityId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceService#getPriceGroupByOrgId(com.ry.core.entity.PriceType, java.lang.Integer)
	 */
	public List<Map<String, Object>> getPriceGroupByOrgId(PriceType priceType,Integer cityId){
		return priceDao.getPriceGroupByOrgId(priceType,cityId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceService#getAllPriceAndOrgName(java.lang.String)
	 */
	public List<Map<String, Object>> getAllPriceAndOrgName(String createTime){
		return priceDao.getAllPriceAndOrgName(createTime);
	}

	@Override
	public List<Integer> getCityIdListByDate(Date date) {
		return priceDao.getCityIdListByDate(date);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceService#getPriceOrderByTypeGroupByOrgId(com.ry.core.entity.PriceType, java.lang.String)
	 */
	public List<Map<String, Object>> getPriceOrderByTypeGroupByOrgId(PriceType priceType,String type){
		return priceDao.getPriceOrderByTypeGroupByOrgId(priceType, type);
	}

	@Override
	public List<Map<String, Object>> getPriceNTypeByDateNCity(String createTime, Integer cityId) {
		return priceDao.getPriceNTypeByDateNCity(createTime, cityId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceService#getByPriceTypeAndOrgIdGroupByCityId(com.ry.core.entity.PriceType, java.lang.Integer)
	 */
	public List<Map<String, Object>> getByPriceTypeAndOrgIdGroupByCityId(PriceType priceType,Integer orgId){
		return priceDao.getByPriceTypeAndOrgIdGroupByCityId(priceType, orgId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceService#getById(java.lang.Integer)
	 */
	public Price getById(Integer id){
		return priceDao.getById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceService#getSameByOrgId(com.ry.core.entity.Price)
	 */
	public Price getSameByOrgId(Price price){
		List<Price> list = priceDao.getSameByOrgId(price.getOrgId(), price.getPriceTypeId(), price.getCityId(), DateUtil.formart(price.getCreateTime(), DateUtil.FORMART3));
		if(list!=null && list.size()>0){
			Price temp = list.get(0);
			temp.setCreateTime(price.getCreateTime());
			temp.setWay(price.getWay());
			temp.setPriceTypeId(price.getPriceTypeId());
			temp.setCityId(price.getCityId());
			temp.setOrgId(price.getOrgId());
			
			temp.setGuogu(price.getGuogu());
			temp.setGuogu1(price.getGuogu1());
			temp.setGuogu2(price.getGuogu2());
			temp.setGuogu3(price.getGuogu3());
			temp.setChengshang(price.getChengshang());
			temp.setChengshang1(price.getChengshang1());
			temp.setChengshang2(price.getChengshang2());
			temp.setChengshang3(price.getChengshang3());
			temp.setDashang(price.getDashang());
			temp.setDashang1(price.getDashang1());
			temp.setDashang2(price.getDashang2());
			temp.setDashang3(price.getDashang3());
			temp.setWaizi(price.getWaizi());
			temp.setWaizi1(price.getWaizi1());
			temp.setWaizi2(price.getWaizi2());
			temp.setWaizi3(price.getWaizi3());
			temp.setNongshang(price.getNongshang());
			temp.setNongshang1(price.getNongshang1());
			temp.setNongshang2(price.getNongshang2());
			temp.setNongshang3(price.getNongshang3());
			temp.setNonghe(price.getNonghe());
			temp.setNonghe1(price.getNonghe1());
			temp.setNonghe2(price.getNonghe2());
			temp.setNonghe3(price.getNonghe3());
			temp.setNongxin(price.getNongxin());
			temp.setNongxin1(price.getNongxin1());
			temp.setNongxin2(price.getNongxin2());
			temp.setNongxin3(price.getNongxin3());
			temp.setCunzhen(price.getCunzhen());
			temp.setCunzhen1(price.getCunzhen1());
			temp.setCunzhen2(price.getCunzhen2());
			temp.setCunzhen3(price.getCunzhen3());
			return temp;
		}else{
			price.setId(null);//这是新增报价（提交的报价可能被使用多次，造成此报价可能只新增一条）
			return price;
		}
	}
	
	//获取最低报价，如果没有，则赋值"--"
	public Map<String,Object> getMinPrice(Integer priceTypeId,String cityId){
		String n="guogu,guogu1,guogu2,guogu3,cunzhen,cunzhen1,cunzhen2,cunzhen3,chengshang,chengshang1,chengshang2,chengshang3,nonghe,nonghe1,nonghe2,nonghe3,nongshang,nongshang1,nongshang2,nongshang3,nongxin,nongxin1,nongxin2,nongxin3,waizi,waizi1,waizi2,waizi3,dashang,dashang1,dashang2,dashang3";
		List<String> names = Arrays.asList(n.split(","));
		Map<String, Object> result = new HashMap<String, Object>();
	    String day=DateUtil.formart(new Date(), DateUtil.FORMART3);
		for(String name: names){
			List<Map<String, Object>> m=priceDao.getMinPrice(name, day,priceTypeId,cityId);
			if( m != null && m.size()>0 && m.get(0).get("m") != null ){
				result.put(name, m.get(0).get("m"));
			}
			else {
				result.put(name, "--");	
			}
		}
		return result;
	}
	
	//获取机构的价格，如果不存在，就默认为最低价格
	public Map<String,Object> minOrg(Integer priceTypeId){
		Map<String, Object> result = getMinPrice(priceTypeId,null);//初始化赋值最低价格
		String guogu=result.get("guogu").toString(),guogu1=result.get("guogu1").toString(),guogu2=result.get("guogu2").toString(),cunzhen=result.get("cunzhen").toString(),cunzhen1=result.get("cunzhen1").toString(),cunzhen2=result.get("cunzhen2").toString(),chengshang=result.get("chengshang").toString(),chengshang1=result.get("chengshang1").toString(),chengshang2=result.get("chengshang2").toString(),nonghe=result.get("nonghe").toString(),nonghe1=result.get("nonghe1").toString(),nonghe2=result.get("nonghe2").toString(),nongshang=result.get("nongshang").toString(),nongshang1=result.get("nongshang1").toString(),nongshang2=result.get("nongshang2").toString();
	    String nongxin=result.get("nongxin").toString(),nongxin1=result.get("nongxin1").toString(),nongxin2=result.get("nongxin2").toString(),waizi=result.get("waizi").toString(),waizi1=result.get("waizi1").toString(),waizi2=result.get("waizi2").toString(),dashang=result.get("dashang").toString(),dashang1=result.get("dashang1").toString(),dashang2=result.get("dashang2").toString();
	    Map<String, Object> min = new HashMap<String, Object>();//综合的最低价格
	    
	    String day=DateUtil.formart(new Date(), DateUtil.FORMART3);
		Variables variables  = variablesDao.getpingtai(priceTypeId);
		if( variables != null && variables.getValue() != null ){
		List<String> phones = Arrays.asList(variables.getValue().split(","));
		//！！！记得增加解密手机号码
		//根据手机号依次获取
		for(String phone : phones){
			OrgInfo orgInfo=orgInfoDao.getByPhone(phone);
			List<Price> list=priceDao.getSameByOrgId(orgInfo.getOrgId(), priceTypeId, 107, day);
			if(list!=null && list.size()>0 ){
				Price temp = list.get(0);
				if(!"--".equals(temp.getGuogu()) || !"--".equals(temp.getGuogu1()) || !"--".equals(temp.getGuogu2())){
					guogu=temp.getGuogu();guogu1=temp.getGuogu1();guogu2=temp.getGuogu2();
					break;
				}
		 }
		}
		for(String phone : phones){
			OrgInfo orgInfo=orgInfoDao.getByPhone(phone);
			List<Price> list=priceDao.getSameByOrgId(orgInfo.getOrgId(), priceTypeId, 107, DateUtil.formart(new Date(), DateUtil.FORMART3));
			if(list!=null && list.size()>0 ){
				Price temp = list.get(0);
				if(!"--".equals(temp.getCunzhen()) || !"--".equals(temp.getCunzhen1()) || !"--".equals(temp.getCunzhen2())){
					cunzhen=temp.getCunzhen();cunzhen1=temp.getCunzhen1();cunzhen2=temp.getCunzhen2();
					break;
				}
			}
		}
		for(String phone : phones){
			OrgInfo orgInfo=orgInfoDao.getByPhone(phone);
			List<Price> list=priceDao.getSameByOrgId(orgInfo.getOrgId(), priceTypeId, 107, DateUtil.formart(new Date(), DateUtil.FORMART3));
			if(list!=null && list.size()>0 ){
				Price temp = list.get(0);//获取该手机号的报价
				if(!"--".equals(temp.getChengshang()) || !"--".equals(temp.getChengshang()) || !"--".equals(temp.getChengshang())){
					chengshang=temp.getChengshang();chengshang1=temp.getChengshang1();chengshang2=temp.getChengshang2();
					break;
				}
			}
		}
		for(String phone : phones){
			OrgInfo orgInfo=orgInfoDao.getByPhone(phone);
			List<Price> list=priceDao.getSameByOrgId(orgInfo.getOrgId(), priceTypeId, 107, DateUtil.formart(new Date(), DateUtil.FORMART3));
			if(list!=null && list.size()>0 ){
				Price temp = list.get(0);//获取该手机号的报价
				if(!"--".equals(temp.getNonghe()) || !"--".equals(temp.getNonghe1()) || !"--".equals(temp.getNonghe2())){
					nonghe=temp.getNonghe();nonghe1=temp.getNonghe1();nonghe2=temp.getNonghe2();
					break;
				}
			}
		}
		for(String phone : phones){
			OrgInfo orgInfo=orgInfoDao.getByPhone(phone);
			List<Price> list=priceDao.getSameByOrgId(orgInfo.getOrgId(), priceTypeId, 107, DateUtil.formart(new Date(), DateUtil.FORMART3));
			if(list!=null && list.size()>0 ){
				Price temp = list.get(0);//获取该手机号的报价
				if(!"--".equals(temp.getNongshang()) || !"--".equals(temp.getNongshang1()) || !"--".equals(temp.getNongshang2())){
					nongshang=temp.getNongshang();nongshang1=temp.getNongshang2();nongshang2=temp.getNongshang2();
					break;
				}
			}
		}
		for(String phone : phones){
			OrgInfo orgInfo=orgInfoDao.getByPhone(phone);
			List<Price> list=priceDao.getSameByOrgId(orgInfo.getOrgId(), priceTypeId, 107, DateUtil.formart(new Date(), DateUtil.FORMART3));
			if(list!=null && list.size()>0 ){
				Price temp = list.get(0);//获取该手机号的报价
				if(!"--".equals(temp.getNongxin()) || !"--".equals(temp.getNongxin1()) || !"--".equals(temp.getNongxin2())){
					nongxin=temp.getNongxin();nongxin1=temp.getNongxin1();nongxin2=temp.getNongxin2();
					break;
				}
			}
		}
		for(String phone : phones){
			OrgInfo orgInfo=orgInfoDao.getByPhone(phone);
			List<Price> list=priceDao.getSameByOrgId(orgInfo.getOrgId(), priceTypeId, 107, DateUtil.formart(new Date(), DateUtil.FORMART3));
			if(list!=null && list.size()>0 ){
				Price temp = list.get(0);//获取该手机号的报价
				if(!"--".equals(temp.getWaizi()) || !"--".equals(temp.getWaizi1()) || !"--".equals(temp.getWaizi2())){
					waizi=temp.getWaizi();waizi1=temp.getWaizi1();waizi2=temp.getWaizi2();
					break;
				}
			}
		}
		for(String phone : phones){
			OrgInfo orgInfo=orgInfoDao.getByPhone(phone);
			List<Price> list=priceDao.getSameByOrgId(orgInfo.getOrgId(), priceTypeId, 107, DateUtil.formart(new Date(), DateUtil.FORMART3));
			if(list!=null && list.size()>0 ){
				Price temp = list.get(0);//获取该手机号的报价
				if(!"--".equals(temp.getDashang()) || !"--".equals(temp.getDashang1()) || !"--".equals(temp.getDashang2())){
					dashang=temp.getDashang();dashang1=temp.getDashang1();dashang2=temp.getDashang2();
					break;
				}
			}
		  }
		
		}
		min.put("guogu",guogu);min.put("guogu1",guogu1);min.put("guogu2",guogu2);min.put("chengshang",chengshang);min.put("chengshang1",chengshang1);min.put("chengshang2",chengshang2);
		min.put("cunzhen",cunzhen);min.put("cunzhen1",cunzhen1);min.put("cunzhen2",cunzhen2);min.put("nonghe",nonghe);min.put("nonghe1",nonghe1);min.put("nonghe2",nonghe2);
		min.put("nongshang",nongshang);min.put("nongshang1",nongshang1);min.put("nongshang2",nongshang2);min.put("nongxin",nongxin);min.put("nongxin1",nongxin1);min.put("nongxin2",nongxin2);
		min.put("waizi",waizi);min.put("waizi1",waizi1);min.put("waizi2",waizi2);min.put("dashang",dashang);min.put("dashang1",dashang1);min.put("dashang2",dashang2);
		return min;
	}
	
	public void warnorg(Price price){//app2.3新增，现在是所有的利率都在默认这一块
		Integer priceTypeId=price.getPriceTypeId();
		Map<String, Object> result = minOrg(priceTypeId);
		Integer orgId = price.getOrgId();
		OrgWarn ow = orgWarnDao.getByOrgId(orgId,null);//获取机构是否已经预警
		OrgWarn orgwarn=new OrgWarn();
		if( ow != null) return;
		out:
		if( ow == null ) {
       if( !"--".equals(price.getGuogu()) && !"--".equals(result.get("guogu").toString()) ){
    		 Float min = Float.parseFloat( result.get("guogu").toString());
    		 Float value = Float.parseFloat( price.getGuogu());
    	     if(value >= min+1.00){//报价大于他的最低价
    	    	 orgwarn.setOrgId(orgId);
    	    	 orgwarn.setState(0);
    	    	 orgwarn.setAmount(0);//以后这里可能会修改，更新会历史最大值
    	    	 orgwarn.setDay(new Date());
    		     orgWarnDao.saveModel(orgwarn);
    		     break out;
    	  }
        }
       if( !"--".equals(price.getChengshang()) && !"--".equals(result.get("chengshang").toString()) ){
  		 Float min = Float.parseFloat( result.get("chengshang").toString());
  		 Float value = Float.parseFloat( price.getChengshang());
  	     if(value >= min+1.00){//报价大于他的最低价
  	    	 orgwarn.setOrgId(orgId);
  	    	 orgwarn.setState(0);
  	    	 orgwarn.setAmount(0);//以后这里可能会修改，更新会历史最大值
  	    	 orgwarn.setDay(new Date());
  		     orgWarnDao.saveModel(orgwarn);
  		     break out;
  	  }
      } if( !"--".equals(price.getCunzhen()) && !"--".equals(result.get("cunzhen").toString()) ){
 		 Float min = Float.parseFloat( result.get("cunzhen").toString());
 		 Float value = Float.parseFloat( price.getCunzhen());
 	     if(value >= min+1.00){//报价大于他的最低价
 	    	 orgwarn.setOrgId(orgId);
 	    	 orgwarn.setState(0);
 	    	 orgwarn.setAmount(0);//以后这里可能会修改，更新会历史最大值
 	    	 orgwarn.setDay(new Date());
 		     orgWarnDao.saveModel(orgwarn);
 		     break out;
 	  }
     } if( !"--".equals(price.getNonghe()) && !"--".equals(result.get("nonghe").toString()) ){
		 Float min = Float.parseFloat( result.get("nonghe").toString());
		 Float value = Float.parseFloat( price.getNonghe());
	     if(value >= min+1.00){//报价大于他的最低价
	    	 orgwarn.setOrgId(orgId);
	    	 orgwarn.setState(0);
	    	 orgwarn.setAmount(0);//以后这里可能会修改，更新会历史最大值
	    	 orgwarn.setDay(new Date());
		     orgWarnDao.saveModel(orgwarn);
		     break out;
	  }
    } if( !"--".equals(price.getNongshang()) && !"--".equals(result.get("nongshang").toString()) ){
		 Float min = Float.parseFloat( result.get("nongshang").toString());
		 Float value = Float.parseFloat( price.getNongshang());
	     if(value >= min+1.00){//报价大于他的最低价
	    	 orgwarn.setOrgId(orgId);
	    	 orgwarn.setState(0);
	    	 orgwarn.setAmount(0);//以后这里可能会修改，更新会历史最大值
	    	 orgwarn.setDay(new Date());
		     orgWarnDao.saveModel(orgwarn);
		     break out;
	  }
   } if( !"--".equals(price.getNongxin()) && !"--".equals(result.get("nongxin").toString()) ){
		 Float min = Float.parseFloat( result.get("nongxin").toString());
		 Float value = Float.parseFloat( price.getNongxin());
	     if(value >= min+1.00){//报价大于他的最低价
	    	 orgwarn.setOrgId(orgId);
	    	 orgwarn.setState(0);
	    	 orgwarn.setAmount(0);//以后这里可能会修改，更新会历史最大值
	    	 orgwarn.setDay(new Date());
		     orgWarnDao.saveModel(orgwarn);
		     break out;
	  }
  } if( !"--".equals(price.getWaizi()) && !"--".equals(result.get("waizi").toString()) ){
		 Float min = Float.parseFloat( result.get("waizi").toString());
		 Float value = Float.parseFloat( price.getWaizi());
	     if(value >= min+1.00){//报价大于他的最低价
	    	 orgwarn.setOrgId(orgId);
	    	 orgwarn.setState(0);
	    	 orgwarn.setAmount(0);//以后这里可能会修改，更新会历史最大值
	    	 orgwarn.setDay(new Date());
		     orgWarnDao.saveModel(orgwarn);
		     break out;
	  }
 } if( !"--".equals(price.getDashang()) && !"--".equals(result.get("dashang").toString()) ){
	 Float min = Float.parseFloat( result.get("dashang").toString());
	 Float value = Float.parseFloat( price.getDashang());
     if(value >= min+1.00){//报价大于他的最低价
    	 orgwarn.setOrgId(orgId);
    	 orgwarn.setState(0);
    	 orgwarn.setAmount(0);//以后这里可能会修改，更新会历史最大值
    	 orgwarn.setDay(new Date());
	     orgWarnDao.saveModel(orgwarn);
	     break out;
  }
}
	  }
		
	}
	
	/**
	 * 将小票的纸票报价里的常规报价中的B方式转化A方式或者A转化为B,这里的目的是再次确认已经转化
	 * @param price 报价
	 * @param ptype 报价类型
	 * @return 计算后的报价
	 * @author ZY
	 */
	public Price getPricePager1(Price price, PriceType ptype) {
		Integer day = Integer.valueOf(StringUtils.hasText(ptype.getDay()) ? ptype.getDay() : "0");
		Integer way = price.getWay();
		DecimalFormat df = new DecimalFormat("#0.00");
		try {
		 if(way != null && way == 1) {	//小票纸票B方式报价
				if ((!"--".equals(price.getGuogu2())) && (!"0".equals(price.getGuogu2())) && ("--".equals(price.getGuogu()) || "".equals(price.getGuogu().trim()))) {
					double money = Double.parseDouble(price.getGuogu2());
					price.setGuogu(df.format((money * 30 * 1000) / 100000 / day));
				}
				if ((!"--".equals(price.getChengshang2())) && (!"0".equals(price.getChengshang2())) && ("--".equals(price.getChengshang()) || "".equals(price.getChengshang().trim()))) {
					double money = Double.parseDouble(price.getChengshang2());
					price.setChengshang(df.format((money * 30 * 1000) / 100000 / day));
				}
				if ((!"--".equals(price.getWaizi2())) && (!"0".equals(price.getWaizi2())) && ("--".equals(price.getWaizi()) || "".equals(price.getWaizi().trim()))) {
					double money = Double.parseDouble(price.getWaizi2());
					price.setWaizi(df.format((money * 30 * 1000) / 100000 / day));
				}
				if ((!"--".equals(price.getNonghe2())) && (!"0".equals(price.getNonghe2())) && ("--".equals(price.getNonghe()) || "".equals(price.getNonghe().trim()))) {
					double money = Double.parseDouble(price.getNonghe2());
					price.setNonghe(df.format((money * 30 * 1000) / 100000 / day));
				}
				if ((!"--".equals(price.getNongxin2())) && (!"0".equals(price.getNongxin2())) && ("--".equals(price.getNongxin()) || "".equals(price.getNongxin().trim()))) {
					double money = Double.parseDouble(price.getNongxin2());
					price.setNongxin(df.format((money * 30 * 1000) / 100000 / day));
				}
				if ((!"--".equals(price.getNongshang2())) && (!"0".equals(price.getNongshang2())) && ("--".equals(price.getNongshang()) || "".equals(price.getNongshang().trim()))) {
					double money = Double.parseDouble(price.getNongshang2());
					price.setNongshang(df.format((money * 30 * 1000) / 100000 / day));
				}
				if ((!"--".equals(price.getCunzhen2())) && (!"0".equals(price.getCunzhen2())) && ("--".equals(price.getCunzhen()) || "".equals(price.getCunzhen().trim()))) {
					double money = Double.parseDouble(price.getCunzhen2());
					price.setCunzhen(df.format((money * 30 * 1000) / 100000 / day));
				}
			}
		 if(way != null && way == 0) {	//小票纸票A方式报价
			 if ((!"--".equals(price.getGuogu())) && (!"0".equals(price.getGuogu())) && ("--".equals(price.getGuogu2()) || "0".equals(price.getGuogu2()))) {
				 double money = Double.parseDouble(price.getGuogu2());
				 double money1 = Double.parseDouble(price.getGuogu1());
				 price.setGuogu2(df.format((money * 30 * 1000) / 100000 / day) +money1);
			 }
			 if ((!"--".equals(price.getChengshang())) && (!"0".equals(price.getChengshang())) && ("--".equals(price.getChengshang2()) || "0".equals(price.getChengshang2()))) {
				 double money = Double.parseDouble(price.getChengshang2());
				 double money1 = Double.parseDouble(price.getChengshang1());
				 price.setChengshang2(df.format((money * 30 * 1000) / 100000 / day) + money1);
			 }
			 if ((!"--".equals(price.getWaizi())) && (!"0".equals(price.getWaizi())) && ("--".equals(price.getWaizi2()) || "0".equals(price.getWaizi2()))) {
				 double money = Double.parseDouble(price.getWaizi2());
				 double money1 = Double.parseDouble(price.getWaizi1());
				 price.setWaizi2(df.format((money * 30 * 1000) / 100000 / day) + money1);
			 }
			 if ((!"--".equals(price.getNonghe())) && (!"0".equals(price.getNonghe())) && ("--".equals(price.getNonghe2()) || "0".equals(price.getNonghe2()))) {
				 double money = Double.parseDouble(price.getNonghe2());
				 double money1= Double.parseDouble(price.getNonghe1());
				 price.setNonghe2(df.format((money * 30 * 1000) / 100000 / day) + money1);
			 }
			 if ((!"--".equals(price.getNongxin())) && (!"0".equals(price.getNongxin())) && ("--".equals(price.getNongxin2()) || "0".equals(price.getNongxin2()))) {
				 double money = Double.parseDouble(price.getNongxin2());
				 double money1 = Double.parseDouble(price.getNongxin1());
				 price.setNongxin2(df.format((money * 30 * 1000) / 100000 / day) + money1);
			 }
			 if ((!"--".equals(price.getNongshang())) && (!"0".equals(price.getNongshang())) && ("--".equals(price.getNongshang2()) || "0".equals(price.getNongshang2()))) {
				 double money = Double.parseDouble(price.getNongshang2());
				 double money1 = Double.parseDouble(price.getNongshang1());
				 price.setNongshang2(df.format((money * 30 * 1000) / 100000 / day) + money1);
			 }
			 if ((!"--".equals(price.getCunzhen())) && (!"0".equals(price.getCunzhen())) && ("--".equals(price.getCunzhen2()) || "0".equals(price.getCunzhen2()))) {
				 double money = Double.parseDouble(price.getCunzhen2());
				 double money1 = Double.parseDouble(price.getCunzhen1());
				 price.setCunzhen2(df.format((money * 30 * 1000) / 100000 / day) + money1);
			 }
		 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return price;
	}
	
	/**
	 * 保存
	 */
	@Override
	public Integer savePrice(Price price) {
		return priceDao.saveByEntity(getSameByOrgId(price));
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ry.core.service.PriceService#getCityIdByOrgIdAndDate(java.lang.Integer, java.lang.String)
	 */
	public List<Map<String, Object>> getCityIdByOrgIdAndDate(Integer orgId, String date){
		return priceDao.getCityIdByOrgIdAndDate(orgId,date);
	}
	
	@Override
	public List<Map<String, Object>> getCityListByDate(String date){
		return priceDao.getCityListByDate(date);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceService#judgeUpdateCity(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Map<String, Object>> judgeUpdateCity(Map<String,Object> map) {
		return priceDao.judgeUpdateCity(map);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceService#getPriceByOrgNoHezuo(java.util.Date, java.util.Date)
	 */
	public List<Map<String, Object>> getPriceByOrgNoHezuo(Date start,Date end){
		return priceDao.getPriceByOrgNoHezuo(start, end);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceService#getPriceSvmDay(com.ry.core.form.PriceForm)
	 */
	@Override
	public Map<String, Object> getPriceSvmDay(PriceForm from) {
		List<Map<String, Object>> list = priceDao.getPriceSvmDay(from);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
}