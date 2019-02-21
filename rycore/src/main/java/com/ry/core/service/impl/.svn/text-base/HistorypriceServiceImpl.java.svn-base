package com.ry.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ry.core.dao.HistorypriceDao;
import com.ry.core.entity.Historyprice;
import com.ry.core.service.HistorypriceService;

@Service
public class HistorypriceServiceImpl implements  HistorypriceService{
	@Resource
	HistorypriceDao  historypriceDao;

	@Override
	public List<Historyprice> getList(Float allmoney,Historyprice historyprice) {
		List<Historyprice> list = historypriceDao.getList(historyprice);
		list = this.degrade(allmoney,list,historyprice,2);//@WKX ADD添加降级方法（询价）
		return list;
	}
	
	/**
	 * 询价（价格显示）降级
	 * @author WKX
	 * @param list 报价
	 * @param query 询价条件
	 * @param level 级别（降级次数）
	 */
	private List<Historyprice> degrade(Float allmoney,List<Historyprice> list,Historyprice query,int level){
		if(list!=null && list.size()>0)return list;
		if(allmoney==null)return list;
		
		if(query.getType1()!=null && query.getType1()==8){//备注：小电票询价时，无论大小商，均查询城商报价
			query.setType1(2);
			list = historypriceDao.getList(query);
			return list;
		}
		level--;
		
		query.setType3(null);//买断带票 1买断 2带票
		query.setType4(null);//地域 1长三角2珠三角3华中4环渤海5西南
		
		if(query.getType6()!=null && query.getType6()==1){//纸票
			if(query.getType2()!=null && query.getType2()==4){//4:<50
				if(level==1){//第1次降级
					query.setType2(3);
				}else if(level==0){//第2次降级
					query.setType2(2);
				}
			}else if(query.getType2()!=null && query.getType2()==3){//3:50-100
				query.setType2(null);
			}else if(query.getType2()!=null && query.getType2()==2){//2:100-500
				if(level==1){//第1次降级
					query.setType2(3);
				}else if(level==0){//第2次降级
					query.setType2(4);
				}
			}
		}else if(query.getType6()!=null && query.getType6()==2){//电票
			if(allmoney>=500){//大于500（大电票）
				if(level==1){//第1次降级
					if(query.getType7()!=null && query.getType7()==0){
						query.setType7(1);
					}else if(query.getType7()!=null && query.getType7()==1){
						query.setType7(0);
					}
				}else if(level==0){//第2次降级
					query.setType2(2);
					if(query.getType7()!=null && query.getType7()==0)query.setType5(3);
					if(query.getType7()!=null && query.getType7()==1)query.setType5(2);
					query.setType7(null);
				}
			}else{
				query.setType7(null);//小电票无，期限（半年期、一年期）
				if(level==1){//第1次降级
					query.setType2(null);
				}else if(level==0){//第2次降级
					query.setType2(null);
					query.setType5(null);
				}
			}
		}
		
		System.err.println(query.getDay());
		System.err.println(query.getType2()+"--票据金额 :1:500以上 2:100-500 3:50-100  4:<50");
		System.err.println(query.getType5()+"--对应 APP2.1 月份（1三个月、2六个月）APP2.1增加：3六个月以上");
		System.err.println(query.getType6()+"--对应 APP2.1机构报价表Price（纸票1、电票2）");
		System.err.println(query.getType7()+"--期限：半年期0、一年期1");
		
		list = historypriceDao.getList(query);
		if(list!=null && list.size()>0){
			return list;
		}else{
			if(level<1){//2次降级
				return list;
			}else{
				return this.degrade(allmoney,list,query,level);
			}
		}
	}

	@Override
	public void updateAllHistoryprice(List<Historyprice> historypriceList) {
		if(historypriceList!=null&&historypriceList.size()!=0){
			for(Historyprice historyprice : historypriceList){
				//String time = historyprice.getTime();
				List<Historyprice> baseEntityList = historypriceDao.getList(historyprice);				
				if(baseEntityList==null||baseEntityList.size()==0){
					historypriceDao.addHistoryprice(historyprice);
				}else{
					Historyprice oldHistoryprice = (Historyprice)baseEntityList.get(0);
					oldHistoryprice.setPrice(historyprice.getPrice());
					oldHistoryprice.setUpdown(historyprice.getUpdown());
					historypriceDao.updateHistoryprice(oldHistoryprice);
				}
			}
		}		
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.HistorypriceService#findPriceList(java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public List<Historyprice> findPriceList(String start,String end,Integer type1,Integer type2,Integer type4,Integer index, Integer size){
		return historypriceDao.findPriceList(start, end, type1, type2, type4, index, size);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.HistorypriceService#findbyDay(java.lang.String)
	 */
	public List<Historyprice> findbyDay(String day) {
		return historypriceDao.findbyDay(day);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.HistorypriceService#saveOrUpdateHistoryprice(java.util.List)
	 */
	public void saveOrUpdateHistoryprice(List<Historyprice> historypriceList) {
		if(historypriceList!=null&&historypriceList.size()!=0){
			for(Historyprice historyprice : historypriceList){
				List<Historyprice> baseEntityList = historypriceDao.getList(historyprice);				
				if(baseEntityList==null || baseEntityList.size()==0){
					historypriceDao.addHistoryprice(historyprice);
				}else{
					Historyprice oldHistoryprice = (Historyprice)baseEntityList.get(0);
					oldHistoryprice.setPrice(historyprice.getPrice());
					oldHistoryprice.setPrice1(historyprice.getPrice1());
					oldHistoryprice.setPrice2(historyprice.getPrice2());
					double price;
					double price1;
					double price2;
					double matrueprice;
					if(historyprice.getPrice() != null)
						if(!historyprice.getPrice().equals("--") && historyprice.getPrice() != null  && !historyprice.getPrice().equals(""))
							price = Double.parseDouble(historyprice.getPrice());
						else
							price = 0;
					else
						price = 0;
					if(historyprice.getPrice1() != null)
						if(!historyprice.getPrice1().equals("--") && historyprice.getPrice1() != null  && !historyprice.getPrice1().equals(""))
							price1 = Double.parseDouble(historyprice.getPrice1());
						else
							price1 = 0;
					else
						price1 = 0;
					if(historyprice.getPrice2() != null)
						if(!historyprice.getPrice2().equals("--") && historyprice.getPrice2() != null  && !historyprice.getPrice2().equals(""))
							price2 = Double.parseDouble(historyprice.getPrice2());
						else
							price2 = 0;
					else
						price2 = 0;
					if(historyprice.getMatrueprice()!=null)
						if(StringUtils.hasText(historyprice.getMatrueprice()) && !historyprice.getMatrueprice().equals("--"))
							matrueprice = Double.parseDouble(historyprice.getMatrueprice());
						else
							matrueprice = 0;
					else
						matrueprice = 0;
					double p = (100000*187*(price/30000))+price1;
					if(historyprice.getPrice2()==null || historyprice.getPrice2().equals("") || historyprice.getPrice2().equals("--")){
						if(p>matrueprice){
							oldHistoryprice.setMatrueprice(historyprice.getMatrueprice());
						}else{
							oldHistoryprice.setMatrueprice(p+"");
						}
					}else{
						if(price2>matrueprice){
							oldHistoryprice.setMatrueprice(historyprice.getMatrueprice());
						}else{
							oldHistoryprice.setMatrueprice(price2+"");
						}
					}
					historypriceDao.updateHistoryprice(oldHistoryprice);
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.HistorypriceService#findbyDayAndType(java.lang.String, java.lang.String)
	 */
	public List<Historyprice> findbyDayAndType(String day, String type, Integer cityId) {
		return historypriceDao.findbyDayAndType(day, type, cityId);
	}

	@Override
	public void saveOrUpdate(Historyprice h) {
		historypriceDao.saveOrUpdate(h);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.HistorypriceService#getCityListByDay(java.lang.String)
	 */
	public List<Map<String, Object>> getCityListByDay(String day) {
		return historypriceDao.getCityListByDay(day);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.HistorypriceService#getListOrderByPriceAsc(com.ry.core.entity.Historyprice)
	 */
	public List<Historyprice> getListOrderByPriceAsc(Historyprice historyprice){
		return historypriceDao.getListOrderByPriceAsc(historyprice);
	}

	@Override
	public List<Map<String, Object>> getCityList() {
		return historypriceDao.getCityList();
	}
	
	@Override
	public List<Map<String, Object>> getCity() {
		return historypriceDao.getCity();
	}
}