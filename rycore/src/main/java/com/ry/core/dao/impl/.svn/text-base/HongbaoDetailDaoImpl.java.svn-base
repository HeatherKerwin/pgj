package com.ry.core.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.HongbaoDetailDao;
import com.ry.core.entity.HongbaoActivity;
import com.ry.core.entity.HongbaoDetail;

@Repository
public class HongbaoDetailDaoImpl extends BaseDao<HongbaoDetail, Integer> implements HongbaoDetailDao {

	/**
	 * 可以使用的红包列表
	 */
	@Override
	public List<HongbaoDetail> getList(Integer memberid) {		
		String hql = "from hongbaoDetail where memberid = ? and flag=0 and status=0 order by createtime desc ";
		List params = new ArrayList();
		params.add(0, memberid);		
		List<HongbaoDetail> list = getListByHQL(hql, params.toArray());
		return list;
	}

	@Override
	public List<HongbaoDetail> getHistoryList(Integer memberid) {
		String hql = "from hongbaoDetail where memberid = ? and flag = 1 and status=0 order by createtime desc ";
		List params = new ArrayList();
		params.add(0, memberid);					
		List<HongbaoDetail> list = getListByHQL(hql, params.toArray());
		return list;
	}
	
	@Override
	public List<HongbaoDetail> getUsedList(Integer memberid) {
		String hql = "from hongbaoDetail where memberid = ? and flag = 2 and status=0 order by createtime desc ";
		List params = new ArrayList();
		params.add(0, memberid);					
		List<HongbaoDetail> list = getListByHQL(hql, params.toArray());
		return list;
	}

	@Override
	public HongbaoDetail getHongbaoDetail(Integer id) {
		
		return get(id);
	}

	@Override
	public void updateHongbaoDeteil(HongbaoDetail hongbaoDetail) {
		
		update(hongbaoDetail);
	}

	@Override
	public HongbaoDetail getHongbaoDetail(String orderNumber) {
		String hql = " from hongbaoDetail where orderNumber = ? and flag=2 ";
		return getByHQL(hql, new Object[]{orderNumber});
	}

	@Override
	public void addHongbaoDetail(HongbaoDetail hongbaoDetail) {
		
		save(hongbaoDetail);
	}

	@Override
	public BigInteger countHongbaoDetail(Integer hid) {
		String hql = "select count(*) from hongbaoDetail where hid = ? and status=0 ";		
		Object obj = (Object)getBySQL(hql, new Object[]{hid});	
		BigInteger bint = (BigInteger) obj;
		return bint;
	}

	@Override
	public List<HongbaoDetail> getHongbaoDetail(Integer sid, String phone) {
		String hql = "from hongbaoDetail where sid = ? and phone = ? and status=0 ";
		List paramList = new ArrayList();
		paramList.add(sid);
		paramList.add(phone);
		return getListByHQL(hql, paramList.toArray());
	}

	@Override
	public void deleteHongbaoDetail(HongbaoDetail hongbaoDetail) {
		
		deleteObject(hongbaoDetail);
	}

	@Override
	public List<HongbaoDetail> getListFlag0() {
		
		return getListByHQL("from hongbaoDetail where flag=0 and status=0 ", null);
	}

	
	@Override
	public Map<String, String> getCount(Integer id) {		
		Map<String, String> maps = new HashMap<String, String>();
		/*统计有效红包数量*/
		String hql1 = "select count(*) from hongbaoDetail where flag = 2 and hid = ? and ordernumber is not null";
		/*List<Object> list = getListBySQL(hql1, new Object[]{id});
		Object objs;
		BigInteger d1 = null;
		if (list != null && list.size() > 0) {
			objs = list.get(0);																	
			if (objs != null) {		
				d1 = (BigInteger)objs;
			}
			
		}*/
		Object d1 = countHongBao(hql1, id);
		maps.put("d1",d1.toString());
		
		/*统计领取红包数*/
		String sql22 = "select count(price) from hongbaoAction  where hid = ? and code = 'get_hongbao' and price > 0";
		Object d22 = countHongBao(sql22, id);
		maps.put("d22",d22.toString());
		
		DecimalFormat df = new DecimalFormat("#.00");   
		
		/*统计有效红包金额*/
		String sql2 = "select sum(price) from hongbaoDetail where flag = 2 and hid = ? and ordernumber is not null";
		
		Object d2 = countHongBao(sql2, id);
		if(d2 == null || "".equals(d2)){
			d2 = 0;
			maps.put("d33", "0");
		}else{
			maps.put("d33",df.format(Double.valueOf(d2.toString())).toString().substring(0,d2.toString().lastIndexOf(".")));
		}
		/*查询领取红包金额*/
		String sql31 = "select sum(price) from hongbaoAction  where hid = ? and code = 'get_hongbao' and price > 0";// and price > 0
		List<Object> list31 = getListBySQL(sql31, new Object[]{id});
		Object objs31;
		BigDecimal d31 = null;
		if (list31 != null && list31.size() > 0) {
			objs31 = list31.get(0);																	
			if (objs31 != null) {		
				d31 = (BigDecimal)objs31;
			}
		}
		if(d31 == null || "".equals(d31)){
			maps.put("d44", "0");
		}else{
			Integer d44 = d31.intValue();
			maps.put("d44", d44+"");
		}
		
		/*统计有效红包转换率*/
		
		Double d55 = Double.valueOf(d2.toString())*100/d31.doubleValue();
		
		String result55 = null;
		if(d55 == 0.0 || d55 == Double.valueOf(0)){
			result55 = 0+"";
		}else{
			String str55 = df.format(d55).toString();
			result55 = str55;
			if(str55.contains(".00")){
				result55 = str55.substring(0,str55.lastIndexOf("."));
			}else if(str55.endsWith("0")&&str55.contains(".")){
				result55 = str55.substring(0,str55.length());
			}
		}
		maps.put("d55",result55 );
		
		/*查询该红包的总金额
		String hql3 = "select totalprice from hongbaoActivity where id = ?";
		List<Object> list3 = getListBySQL(hql3, new Object[]{id});
		Object objs3;
		Integer totalprice = null;
		if (list3 != null && list3.size() > 0) {
			objs3 = list3.get(0);																	
			if (objs3 != null) {		
				totalprice = (Integer)objs3;
			}
		}
		Integer d3 = totalprice - d31.intValue();
		maps.put("d3", d3.toString());
		
		查询该红包的总数量
		String hql4 = "select totalnum from hongbaoActivity where id = ?";
		List<Object> list4 = getListBySQL(hql3, new Object[]{id});
		Object objs4;
		Integer totalnum = null;   
		if (list4 != null && list4.size() > 0) {
			objs4 = list4.get(0);																	
			if (objs4 != null) {		
				totalnum = (Integer)objs4;
			}
		}
		
		
	//	Double d4 = d1.doubleValue()*100/totalnum;
		Double d4 = Double.valueOf(d1.toString())*100/totalnum;
		maps.put("d4", d4.toString());*/
		
		return maps;
	}
	
	//从getCount()方法中提取的方法
	public Object countHongBao(String hql, Integer id){
		Object obj = null;
		List<Object> list = getListBySQL(hql, new Object[]{id});
		if (list != null && list.size() > 0) {
			obj = list.get(0);																	
			if (obj != null) {		
				return obj;
			}
		}
		return "";
		
	}

	//得到所有的红包活动id
	@Override
	public List<Integer> getHids() {
		String sql = "select id from hongbaoActivity";
		return getListBySQL(sql, null);
	}

	@Override
	public HongbaoActivity getCountByBean(Integer id) {
		
		HongbaoActivity activity = new HongbaoActivity();
		activity.setId(id);
		
		String hql1 = "select count(*) from hongbaoDetail where flag = 2 and hid = ? and ordernumber is not null";
		
		Object d1 = countHongBao(hql1, id);
	//	maps.put("d1",d1.toString());
		activity.setValidenum(Integer.valueOf(d1.toString()));
		
		/*统计领取红包数*/
		String sql22 = "select count(price) from hongbaoAction  where hid = ? and code = 'get_hongbao' and price > 0";
		Object d22 = countHongBao(sql22, id);
	//	maps.put("d22",d22.toString());
		activity.setRecivenum(Integer.valueOf(d22.toString()));
		
		DecimalFormat df = new DecimalFormat("#.00");   
		
		/*统计有效红包金额*/
		String sql2 = "select sum(price) from hongbaoDetail where flag = 2 and hid = ? and ordernumber is not null";
		
		Object d2 = countHongBao(sql2, id);
		if(d2 == null || "".equals(d2)){
			d2 = 0;
	//		maps.put("d33", "0");
			activity.setValidetotalprice(0);
		}else{
			//maps.put("d33",df.format(Double.valueOf(d2.toString())).toString().substring(0,d2.toString().lastIndexOf(".")));
			activity.setValidetotalprice(Integer.valueOf(df.format(Double.valueOf(d2.toString())).toString().substring(0,d2.toString().lastIndexOf("."))));
		}
		/*查询领取红包金额*/
		String sql31 = "select sum(price) from hongbaoAction  where hid = ? and code = 'get_hongbao' and price > 0";// and price > 0
		List<Object> list31 = getListBySQL(sql31, new Object[]{id});
		Object objs31;
		BigDecimal d31 = null;
		if (list31 != null && list31.size() > 0) {
			objs31 = list31.get(0);																	
			if (objs31 != null) {		
				d31 = (BigDecimal)objs31;
			}
		}
		if(d31 == null || "".equals(d31)){
		//	maps.put("d44", "0");
			activity.setRecivetotalprice(0);
		}else{
			Integer d44 = d31.intValue();
			//maps.put("d44", d44+"");
			activity.setRecivetotalprice(d44);
		}
		
		/*统计有效红包转换率*/
		
		Double d55 = Double.valueOf(d2.toString())*100/d31.doubleValue();
		
		String result55 = null;
		if(d55 == 0.0 || d55 == Double.valueOf(0)){
			result55 = 0+"";
		}else{
			String str55 = df.format(d55).toString();
			result55 = str55;
			if(str55.contains(".00")){
				result55 = str55.substring(0,str55.lastIndexOf("."));
			}else if(str55.endsWith("0")&&str55.contains(".")){
				result55 = str55.substring(0,str55.length());
			}
		}
	//	maps.put("d55",result55 );
		activity.setTransrate(Double.valueOf(result55));

		return activity;
	}

	@Override
	public HongbaoActivity getActivityById(Integer id) {
		
		//List<HongbaoActivity> activitys =  getListByHQL("from hongbaoActivity where id = ?", new Object[]{id});
		Query query = this.getSession().createQuery("from hongbaoActivity where id = ?");
		Object[] values = {id};
        if (values != null){
            for (int i = 0; i < values.length; i++){
                query.setParameter(i, values[i]);
            }
        }
        List<HongbaoActivity> activities = query.list();
        if(activities != null && activities.size() > 0){
        	return activities.get(0);
        }
        return null;
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.HongbaoDetailDao#getByOrdernumber(java.lang.String)
	 */
	public List<HongbaoDetail> getByOrdernumber(String ordernumber) {
		String hql = "from hongbaoDetail where ordernumber = ? and flag = 2 and status=0";
		List<Object> params = new ArrayList<Object>();
		params.add(0, ordernumber);
		return getListByHQL(hql, params.toArray());
	}
}