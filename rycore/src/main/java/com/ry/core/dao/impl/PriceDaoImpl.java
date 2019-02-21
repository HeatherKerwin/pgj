package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.PriceDao;
import com.ry.core.entity.Price;
import com.ry.core.entity.PriceType;
import com.ry.core.form.PriceForm;
import com.ry.core.util.Constant;
import com.ry.util.DateUtil;

@Repository
public class PriceDaoImpl extends BaseDao<Price, Integer> implements PriceDao{

	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceDao#getDtboDcrdById(java.lang.Integer)
	 */
	public List<Map<String,Object>> getDtboDcrdById(Integer dtboId){
		StringBuffer sql = new StringBuffer("SELECT dt.id dtboId,dt.no,dt.state,dt.price rate,dt.price1 rate1,dt.price2 rate2,dt.way,dt.tzts,dt.jxts,dt.txlx,dt.txje,dc.allmoney,dc.begintime,dc.endtime,dc.type1,dc.type2 cdh FROM distribute_order dt LEFT JOIN discountrecord dc ON dt.dcrd_id=dc.id WHERE dt.id=?");
		List<Object> params = new ArrayList<Object>();
		params.add(dtboId);
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceDao#getPriceById(java.lang.Integer)
	 */
	public List<Map<String,Object>> getPriceById(Integer id){
		StringBuffer sql = new StringBuffer("SELECT p.*,t.type1,t.type2,t.type3,t.type4 FROM price p LEFT JOIN price_type t ON p.price_type_id=t.id WHERE p.id=?");
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return getListMapBySQL(sql.toString(), params.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceDao#getByOrgId(java.lang.Integer, java.lang.String, java.lang.Integer)
	 */
	public List<Price> getByOrgId(Integer orgId, String createTime, Integer priceTypeId, Integer cityId) {
		StringBuffer hql = new StringBuffer("from price p where 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (orgId != null) {
			hql.append(" and p.orgId =?");
			paramsList.add(orgId);
		}
		if (createTime != null) {
			hql.append(" and DATE_FORMAT(create_time,'%Y-%m-%d') = ?");
			paramsList.add(createTime);
		}
		if (cityId != null) {
			hql.append(" and p.cityId =?");
			paramsList.add(cityId);
		}
		if (priceTypeId != null) {
			hql.append(" and p.priceTypeId =?");
			paramsList.add(priceTypeId);
			hql.append(" order by p.orgId, p.createTime desc, p.id");
		} else {
			hql.append(" order by p.orgId, p.createTime asc, p.id");			
		}
		return getListByHQL(hql.toString(), paramsList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceDao#saveByEntity(com.ry.core.entity.Price)
	 */
	public Integer saveByEntity(Price price){
		if(price!=null && price.getId()!=null){
			update(price);
			return price.getId();
		}else{
			return save(price);
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceDao#getById(java.lang.Integer)
	 */
	public List<Map<String, Object>> getByPriceTypeId(Integer id) {
		StringBuffer sql = new StringBuffer("select pt.*,p.create_time,p.org_id,p.way from price_type pt left join price p on pt.id = p.price_type_id where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		sql.append(" and DATE_FORMAT(p.create_time,'%Y-%m-%d') = curdate() ");
		sql.append(" and p.price_type_id =?");
		params.add(id);
		sql.append(" order by pt.id,p.org_id, p.create_time desc");
		return getListMapBySQL(sql.toString(),params.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceDao#infoById(java.lang.Integer, java.lang.Integer)
	 */
	public List<Price> infoById(Integer orgId, Integer priceTypeId, Integer cityId) {
		StringBuffer hql = new StringBuffer("from price p where 1=1 ");
//		StringBuffer hql = new StringBuffer("from price p where 1=1 and DATE_FORMAT(p.createTime,'%Y-%m-%d') = curdate() ");
		List<Object> params = new ArrayList<Object>();
		if (orgId != null) {
			hql.append(" and p.orgId =?");
			params.add(orgId);
		}
		if (priceTypeId != null) {
			hql.append(" and p.priceTypeId =?");
			params.add(priceTypeId);
		}
		if (cityId != null) {
			hql.append(" and p.cityId =?");
			params.add(cityId);
		}
		hql.append(" order by p.createTime desc");
		return getListByHQL(hql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceDao#getByPriceTypeAndOrgId(com.ry.core.entity.PriceType, java.lang.Integer)
	 */
	public List<Map<String, Object>> getByPriceTypeAndOrgId(PriceType priceType,Integer orgId) {
		StringBuffer sql = new StringBuffer("SELECT p.* FROM price p LEFT JOIN price_type t ON p.price_type_id=t.id WHERE p.org_id=? AND DATE_FORMAT(p.create_time,'%Y-%m-%d') = curdate() ");
		List<Object> params = new ArrayList<Object>();
		params.add(orgId);
		if(priceType!=null){
			if(priceType.getType1()!=null){//类型：大票0、小票1
				sql.append(" and t.type1 =?");
				params.add(priceType.getType1());
			}
			if(priceType.getType2()!=null){//类型：纸票0、电票1
				sql.append(" and t.type2 =?");
				params.add(priceType.getType2());
			}
			if(priceType.getType3()!=null){//类型：50万以下0、50-100万1、100万2...
				sql.append(" and t.type3 =?");
				params.add(priceType.getType3());
			}
			if(priceType.getType4()!=null){//类型：小于等于90天0、91-178天1、大于等于179天2
				sql.append(" and t.type4 =?");
				params.add(priceType.getType4());
			}
			if(priceType.getType5()!=null){//期限：半年期0、一年期1
				sql.append(" and t.type5 =?");
				params.add(priceType.getType5());
			}
		}
		return getListMapBySQL(sql.toString(),params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceDao#getByOrgId(java.lang.Integer)
	 */
	public List<Map<String, Object>> getByOrgId(Integer orgId) {
		StringBuffer sql = new StringBuffer("SELECT * FROM price WHERE org_id=? GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d')");
		List<Object> params = new ArrayList<Object>();
		params.add(orgId);
		
		return getListMapBySQL(sql.toString(),params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceDao#getPtid(java.lang.Integer, java.lang.String)
	 */
	public List<Map<String, Object>> getPtid(Integer orgId, String date) {
		StringBuffer sql = new StringBuffer("select p.id,p.price_type_id ptid from price p where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if (orgId != null) {
			sql.append(" and p.org_id = ? ");
			params.add(orgId);
		}
		if (!"".equals(date) && date != null) {
			sql.append(" and date_format(p.create_time, '%Y-%m-%d') = ? ");
			params.add(date);
		}else{
			sql.append(" and date_format(p.create_time, '%Y-%m-%d') = curdate()");
		}
		sql.append(" group by ptid");
		return getListMapBySQL(sql.toString(),params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceDao#getPtidAndDay(java.lang.Integer, java.lang.String)
	 */
	public List<Map<String, Object>> getPtidAndDay(Integer orgId, String date, Integer cityId) {
		StringBuffer sql = new StringBuffer("select p.id,p.ptid ptid,pt.type1,pt.type2,pt.type3,pt.type4,pt.type5,pt.day from (select p.id,p.price_type_id ptid,p.org_id,p.create_time,p.city_id from price p ) p left join price_type pt on p.ptid = pt.id where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if (orgId != null) {
			sql.append(" and p.org_id = ? ");
			params.add(orgId);
		}
		if (cityId != null) {
			sql.append(" and p.city_id = ? ");
			params.add(cityId);			
		}
		if (!"".equals(date) && date != null) {
			sql.append(" and date_format(p.create_time, '%Y-%m-%d') = ? ");
			params.add(date);
		}else{
			sql.append(" and date_format(p.create_time, '%Y-%m-%d') = curdate()");
		}
		sql.append(" group by ptid");
		return getListMapBySQL(sql.toString(),params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceDao#getPriceAndTypeByOrgId(java.lang.Integer, java.lang.String)
	 */
	public List<Map<String, Object>> getPriceAndTypeByOrgId(Integer orgId, String createTime, Integer cityId,Integer memberId) {
		StringBuffer sql = new StringBuffer("select tab1.* from (select p.org_id orgId,p.create_time time,p.id id,p.city_id cityid,p.guogu gg,p.guogu1 gg1,p.guogu2 gg2,p.guogu3 gg3,p.chengshang cs,p.chengshang1 cs1,p.chengshang2 cs2,p.chengshang3 cs3,p.dashang ds,p.dashang1 ds1,p.dashang2 ds2,p.waizi wz,p.waizi1 wz1,p.waizi2 wz2,p.waizi3 wz3,p.nongshang ns,p.nongshang1 ns1,p.nongshang2 ns2,p.nongshang3 ns3,p.nonghe nh,p.nonghe1 nh1,p.nonghe2 nh2,p.nonghe3 nh3,p.nongxin nx,p.nongxin1 nx1,p.nongxin2 nx2,p.nongxin3 nx3,p.cunzhen cz,p.cunzhen1 cz1,p.cunzhen2 cz2,p.cunzhen3 cz3,p.way way,p.price_type_id ptid, pt.type1 t1,pt.type2 t2,pt.type3 t3, pt.type4 t4,pt.type5 t5,pt.day day, pt.title1 title1,pt.title2 title2,pt.title3 title3,pt.title4 title4 from price p left join price_type pt on pt.id=p.price_type_id where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if (orgId != null) {
			sql.append(" and p.org_id = ?");
			params.add(orgId);
		}
		if (cityId != null) {
			sql.append(" and p.city_id = ?");
			params.add(cityId);
		}
		if (!"".equals(createTime)) {
			sql.append(" and date_format(p.create_time, '%Y-%m-%d') = ?");
			params.add(createTime);
		} else {
			sql.append(" and date_format(p.create_time, '%Y-%m-%d') = curdate()");
		}
		sql.append(" order by t4 desc,day desc,ptid asc,time desc) tab1 ,member m,org_info oi where tab1.orgId = oi.org_id and oi.member_id = m.id");
		if (memberId != null) {
			sql.append(" and m.id = ?");
			params.add(memberId);
		}
		sql.append(" group by tab1.ptid");
		return getListMapBySQL(sql.toString(),params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceDao#getAllPrice(java.lang.String, com.ry.core.entity.PriceType)
	 */
	@Override
	public List<Map<String, Object>> getAllPrice(String date, PriceType pt,Integer cityId) {
		StringBuffer sql = new StringBuffer("SELECT pt.day, p.* FROM  price p , price_type pt WHERE p.price_type_id = pt.id AND DATE_FORMAT (p.create_time,'%Y-%m-%d') = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(date);
		if(pt.getType1()!=null&&pt.getType1()!=-1){
			sql.append(" AND pt.type1 = ?");
			params.add(pt.getType1());
		}
		if(pt.getType2()!=null&&pt.getType2()!=-1){
			sql.append(" AND pt.type2 = ?");
			params.add(pt.getType2());
		}
		if(pt.getType3()!=null&&pt.getType3()!=-1){
			sql.append(" AND pt.type3 = ?");
			params.add(pt.getType3());
		}
		if(pt.getType4()!=null&&pt.getType4()!=-1){
			sql.append(" AND pt.type4 = ?");
			params.add(pt.getType4());
		}
		if(cityId!=null){
			sql.append(" AND p.city_id = ?");
			params.add(cityId);
		}
		sql.append(" ORDER BY org_id ");
		return getListMapBySQL(sql.toString(),params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceDao#getPriceGroupByOrgId(com.ry.core.entity.PriceType, java.lang.Integer)
	 */
	public List<Map<String, Object>> getPriceGroupByOrgId(PriceType priceType,Integer cityId) {
		StringBuffer sql = new StringBuffer("SELECT t.type1,t.type2,t.type3,t.type4,p.org_id,p.guogu,p.chengshang,p.waizi,p.nongshang,p.nonghe,p.nongxin,p.cunzhen,p.dashang,p.city_id,p.create_time ");
		sql.append(" FROM price p LEFT JOIN price_type t ON p.price_type_id=t.id WHERE DATE_FORMAT(p.create_time,'%Y-%m-%d') = curdate()");
		List<Object> params = new ArrayList<Object>();
		if(priceType!=null){
			if(priceType.getType1()!=null){//类型：大票0、小票1
				sql.append(" and t.type1 =?");
				params.add(priceType.getType1());
			}
			if(priceType.getType2()!=null){//类型：纸票0、电票1
				sql.append(" and t.type2 =?");
				params.add(priceType.getType2());
			}
			if(priceType.getType3()!=null){//类型：50万以下0、50-100万1、100万2...
				sql.append(" and t.type3 =?");
				params.add(priceType.getType3());
			}
			if(priceType.getType4()!=null){//类型：小于等于90天0、91-178天1、大于等于179天2
				sql.append(" and t.type4 =?");
				params.add(priceType.getType4());
			}
			if(priceType.getType5()!=null){//期限：半年期0、一年期1
				sql.append(" and t.type5 =?");
				params.add(priceType.getType5());
			}
		}
		if(cityId!=null){//报价城市
			sql.append(" and (p.city_id =? OR p.city_id=10000)");
			params.add(cityId);
		}
		sql.append(" GROUP BY p.org_id");
		return getListMapBySQL(sql.toString(),params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceDao#getAllPriceAndOrgName(java.lang.String)
	 */
	public List<Map<String, Object>> getAllPriceAndOrgName(String createTime) {
		StringBuffer sql = new StringBuffer("select oi.company,ol.price,oi.org_id orgid,p.guogu gg,p.guogu1 gg1,p.guogu2 gg2,p.chengshang cs,p.chengshang1 cs1,p.chengshang2 cs2,p.dashang ds,p.dashang1 ds1,"
				+ " p.dashang2 ds2,p.waizi wz,p.waizi1 wz1,p.waizi2 wz2,p.nongxin nx,p.nongxin1 nx1,p.nongxin2 nx2,p.nonghe nh,p.nonghe1 nh1,p.nonghe2 nh2,p.nongshang ns,p.nongshang1 ns1,p.nongshang2 ns2,p.cunzhen cz,p.cunzhen1 cz1,p.cunzhen2 cz2,p.way way,"
				+ " pt.id ptid,pt.type1 t1,pt.type2 t2,pt.type3 t3,pt.type4 t4,type5 t5 from org_info oi left join price p on oi.org_id = p.org_id"
				+ " left join org_limit ol on oi.org_id = ol.org_id and DATE_FORMAT(p.create_time,'%Y-%m-%d') =  DATE_FORMAT(ol.create_time,'%Y-%m-%d')"
				+ " inner join price_type pt on pt.id = p.price_type_id where oi.type_ = 1");
		List<Object> params = new ArrayList<Object>();
		if (StringUtils.isEmpty(createTime)) {
			sql.append(" and DATE_FORMAT(p.create_time,'%Y-%m-%d') = curdate()");
		} else {
			sql.append(" and DATE_FORMAT(p.create_time,'%Y-%m-%d') = ?");
			params.add(createTime);
		}
		return getListMapBySQL(sql.toString(), params.toArray());
	}

	@Override
	public List<Integer> getCityIdListByDate(Date date) {
		StringBuffer sql = new StringBuffer(" SELECT DISTINCT p.city_id FROM price p WHERE city_id IS NOT NULL AND DATE_FORMAT(p.create_time,'%Y-%m-%d') = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(DateUtil.formart(date, DateUtil.FORMART3));
		List<Map<String, Object>> result = getListMapBySQL(sql.toString(), params.toArray());
		if (result.isEmpty())
			return null;
		List<Integer> list = new ArrayList<Integer>();
		for (Map<String, Object> o : result) {
			if (o.get("city_id") != null) {
				Integer cityId = Integer.valueOf(o.get("city_id").toString());
				list.add(cityId);
			}
		}
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceDao#getPriceOrderByTypeGroupByOrgId(com.ry.core.entity.PriceType, java.lang.String)
	 */
	public List<Map<String, Object>> getPriceOrderByTypeGroupByOrgId(PriceType priceType,String type) {
		StringBuffer sql = new StringBuffer("SELECT * FROM (SELECT t.type1,t.type2,t.type3,t.type4,p.org_id,p.guogu,p.chengshang,p.waizi,p.nongshang,p.nonghe,p.nongxin,p.cunzhen,p.dashang,p.city_id,p.create_time ");
		sql.append(" FROM price p LEFT JOIN price_type t ON p.price_type_id=t.id WHERE DATE_FORMAT(p.create_time,'%Y-%m-%d') = curdate()");
		List<Object> params = new ArrayList<Object>();
		if(priceType!=null){
			if(priceType.getType1()!=null){//类型：大票0、小票1
				sql.append(" and t.type1 =?");
				params.add(priceType.getType1());
			}
			if(priceType.getType2()!=null){//类型：纸票0、电票1
				sql.append(" and t.type2 =?");
				params.add(priceType.getType2());
			}
			if(priceType.getType3()!=null){//类型：50万以下0、50-100万1、100万2...
				sql.append(" and t.type3 =?");
				params.add(priceType.getType3());
			}
			if(priceType.getType4()!=null){//类型：小于等于90天0、91-178天1、大于等于179天2
				sql.append(" and t.type4 =?");
				params.add(priceType.getType4());
			}
			if(priceType.getType5()!=null){//期限：半年期0、一年期1
				sql.append(" and t.type5 =?");
				params.add(priceType.getType5());
			}
		}
		if(StringUtils.hasText(type)){//根据-承兑行排序
			sql.append(" ORDER BY "+ type);
		}
		sql.append(")res GROUP BY res.org_id");
		return getListMapBySQL(sql.toString(),params.toArray());
	}

	@Override
	public List<Map<String, Object>> getPriceNTypeByDateNCity(String createTime, Integer cityId) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("select oi.company,ol.price,oi.org_id orgid,p.create_time,p.guogu gg,p.guogu1 gg1,p.guogu2 gg2,p.chengshang cs,p.chengshang1 cs1,p.chengshang2 cs2,p.dashang ds,p.dashang1 ds1,"
				+ " p.dashang2 ds2,p.waizi wz,p.waizi1 wz1,p.waizi2 wz2,p.nongxin nx,p.nongxin1 nx1,p.nongxin2 nx2,p.nonghe nh,p.nonghe1 nh1,p.nonghe2 nh2,p.nongshang ns,p.nongshang1 ns1,p.nongshang2 ns2,p.cunzhen cz,p.cunzhen1 cz1,p.cunzhen2 cz2,p.way way,"
				+ " pt.id ptid,pt.type1 t1,pt.type2 t2,pt.type3 t3,pt.type4 t4,type5 t5 from org_info oi left join price p on oi.org_id = p.org_id");
				
		if (StringUtils.isEmpty(cityId)) {
			sql.append("  and p.city_id =  '"+Constant.DEFAULT_CITY_ID + "' ");
		} else {
			sql.append("  AND p.city_id = ? ");
			params.add(cityId);
		}
		sql.append(" left join org_limit ol on oi.org_id = ol.org_id and DATE_FORMAT(p.create_time,'%Y-%m-%d') =  DATE_FORMAT(ol.create_time,'%Y-%m-%d')");
		sql.append( " inner join price_type pt on pt.id = p.price_type_id where oi.type_ = 1");
		
		if (StringUtils.isEmpty(createTime)) {
			sql.append(" and DATE_FORMAT(p.create_time,'%Y-%m-%d') = curdate()");
		} else {
			sql.append(" and DATE_FORMAT(p.create_time,'%Y-%m-%d') = ?");
			params.add(createTime);
		}
		sql.append(" order by create_time desc ");
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceDao#getByPriceTypeAndOrgIdGroupByCityId(com.ry.core.entity.PriceType, java.lang.Integer)
	 */
	public List<Map<String, Object>> getByPriceTypeAndOrgIdGroupByCityId(PriceType priceType,Integer orgId) {
		StringBuffer sql = new StringBuffer("SELECT res.*,r.name FROM (SELECT p.* FROM price p LEFT JOIN price_type t ON p.price_type_id=t.id WHERE p.org_id=? AND DATE_FORMAT(p.create_time,'%Y-%m-%d') = curdate() ");
		List<Object> params = new ArrayList<Object>();
		params.add(orgId);
		if(priceType!=null){
			if(priceType.getType1()!=null){//类型：大票0、小票1
				sql.append(" and t.type1 =?");
				params.add(priceType.getType1());
			}
			if(priceType.getType2()!=null){//类型：纸票0、电票1
				sql.append(" and t.type2 =?");
				params.add(priceType.getType2());
			}
			if(priceType.getType3()!=null){//类型：50万以下0、50-100万1、100万2...
				sql.append(" and t.type3 =?");
				params.add(priceType.getType3());
			}
			if(priceType.getType4()!=null){//类型：小于等于90天0、91-178天1、大于等于179天2
				sql.append(" and t.type4 =?");
				params.add(priceType.getType4());
			}
			if(priceType.getType5()!=null){//期限：半年期0、一年期1
				sql.append(" and t.type5 =?");
				params.add(priceType.getType5());
			}
		}
		sql.append(" GROUP BY p.city_id)res LEFT JOIN region r ON res.city_id=r.id");
		return getListMapBySQL(sql.toString(),params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceDao#getById(java.lang.Integer)
	 */
	public Price getById(Integer id) {
		return get(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceDao#getSameByOrgId(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	public List<Price> getSameByOrgId(Integer orgId, Integer priceTypeId, Integer cityId, String day) {
		StringBuffer hql = new StringBuffer("from price p where p.orgId =? and p.priceTypeId =? and p.cityId =? and date_format(p.createTime, '%Y-%m-%d') = ?");
		List<Object> params = new ArrayList<Object>();
		params.add(orgId);
		params.add(priceTypeId);
		params.add(cityId);
		params.add(day);
		return getListByHQL(hql.toString(), params.toArray());
	}
	
	public List<Map<String, Object>> getMinPrice(String name,String day,Integer priceTypeId,String cityId){
	 List<Object> params = new ArrayList<Object>();
	 StringBuffer sql = new StringBuffer(" select MIN(CONVERT( " + name + ",DECIMAL(6,2))) m from price where " + name + " <> '--' and " + name + " is not null and " + name + " <> 0 ");	
	 if(StringUtils.hasText(day)){
		 sql.append(" and date_format(create_time, '%Y-%m-%d') = ? ");
		 params.add(day) ;
	 }
	 if(StringUtils.hasText(cityId)){
		 sql.append(" and city_id = ? ");
		 params.add(cityId) ;
	 }
	 sql.append(" and price_type_id = ? ");
	 params.add(priceTypeId) ;
	 return getListMapBySQL(sql.toString(),params.toArray());
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ry.core.dao.PriceDao#getCityIdByOrgIdAndDate(java.lang.Integer, java.lang.String)
	 */
	public List<Map<String, Object>> getCityIdByOrgIdAndDate(Integer orgId, String date){
		StringBuffer sql = new StringBuffer("select * from price where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		sql.append(" and create_time = ( SELECT MIN(create_time) FROM price WHERE org_id= ? AND DATE_FORMAT(create_time,'%Y-%m-%d')= ?) AND org_id= ? ");
		params.add(orgId);
		params.add(date);
		params.add(orgId);
		sql.append(" group by id");
		return getListMapBySQL(sql.toString(),params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceDao#getCityListByDate(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> getCityListByDate(String  date) {
		StringBuffer sql = new StringBuffer("SELECT r.name name,p.city_id cityid FROM region r,price p WHERE  p.city_id = r.id ");
		List<Object> param = new ArrayList<Object>();
		if(date!=null){
			sql.append(" AND  DATE_FORMAT(create_time, '%Y-%m-%d') =? ");
			param.add(date);
		}
		sql.append(" GROUP BY cityid ");
		return getListMapBySQL(sql.toString(), param.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceDao#judgeUpdateCity(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> judgeUpdateCity(Map<String, Object> map) {
		StringBuffer sql = new StringBuffer("select * from price where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if(map.get("orgId") != null && map.get("orgId") != ""){
			sql.append(" and org_id = ?");
			param.add(map.get("orgId"));
		}
		if(map.get("cityId") != null && map.get("cityId") != ""){
			sql.append(" and city_id = ?");
			param.add(map.get("cityId"));
		}
		if(map.get("day") != null && map.get("day") != ""){
			sql.append(" AND  DATE_FORMAT(create_time, '%Y-%m-%d') = ?");
			param.add(map.get("day"));
		}
		return getListMapBySQL(sql.toString(), param.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceDao#getPriceByOrgNoHezuo()
	 */
	public List<Map<String, Object>> getPriceByOrgNoHezuo(Date start,Date end) {
		StringBuffer sql = new StringBuffer("SELECT res1.amount,res2.* FROM (");
		List<Object> param = new ArrayList<Object>();
		//step1汇总报价结构（含机构认证过的筛选）
		sql.append("SELECT res.org_id,COUNT(res.org_id)amount FROM (");
		sql.append("SELECT CONCAT(DATE_FORMAT(p.create_time, '%Y-%m-%d'),p.org_id),p.org_id,p.city_id,p.create_time FROM price p WHERE p.create_time BETWEEN ? AND ?");
		sql.append("AND p.org_id IN(");
		sql.append("SELECT o.org_id FROM (");//认证机构过滤（排除线上推广）
		sql.append("SELECT * FROM org_info i WHERE i.type_=1 AND i.state=2 GROUP BY i.member_id");
		sql.append(")o LEFT JOIN member m ON o.member_id=m.id WHERE m.hezuo IS NULL");
		sql.append(")GROUP BY CONCAT(DATE_FORMAT(p.create_time, '%Y-%m-%d'),p.org_id)");
		sql.append(")res GROUP BY res.org_id");
		sql.append(")res1 LEFT JOIN (");
		//step2认证过的机构
		sql.append("SELECT o.id,o.org_id,o.company,m.mobile,m.username,m.recommendpeople,m.regtime FROM (");
		sql.append("SELECT * FROM org_info i WHERE i.type_=1 AND i.state=2 GROUP BY i.member_id");
		sql.append(")o LEFT JOIN member m ON o.member_id=m.id WHERE m.hezuo IS NULL");
		sql.append(")res2 ON res1.org_id=res2.org_id");
		
		param.add(start);
		param.add(end);
		return getListMapBySQL(sql.toString(), param.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.PriceDao#getPriceSvmDay(com.ry.core.form.PriceForm)
	 */
	@Override
	public List<Map<String, Object>> getPriceSvmDay(PriceForm from) {
		StringBuffer sql = new StringBuffer("select count(*) as count from (select DISTINCT DATE_FORMAT(create_time,'%Y-%m-%d') FROM price WHERE 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if(from.getOrgId() != null ){
			sql.append(" AND org_id = ?");
			param.add(from.getOrgId());
		}
		if(StringUtils.hasText(from.getStartDate())){
			sql.append(" AND  DATE_FORMAT(create_time, '%Y-%m-%d') <= ?");
			param.add(from.getStartDate());
		}
		if(StringUtils.hasText(from.getEndDate())){
			sql.append(" AND  DATE_FORMAT(create_time, '%Y-%m-%d') > ?");
			param.add(from.getEndDate());
		}
		sql.append(")r1");
		return getListMapBySQL(sql.toString(), param.toArray());
	}
}