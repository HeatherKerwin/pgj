package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.AddressDao;
import com.ry.core.dao.BaseDao;
import com.ry.core.entity.Address;
import com.ry.core.form.AddressForm;
import com.ry.util.page.PageResults;

@Repository
public class AddressDaoImpl extends BaseDao<Address, Integer> implements AddressDao {

	public Address getById(Integer id) {
		return get(id);
	}

	public void saveModel(Address address) {
		save(address);
	}

	public void updateModel(Address address) {
		update(address);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.AddressDao#getPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.AddressForm)
	 */
	public PageResults<Address> getPageList(Integer pageIndex,Integer pageSize,AddressForm form) {
		StringBuffer hql = new StringBuffer("FROM address where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(form!=null){
			if(form.getMemberId()!=null){
				hql.append(" AND memberId=?");
				params.add(form.getMemberId());
			}
		}
		hql.append(" order by state");
		String count = "select count(*) "+ hql.toString();
		return findPageByFetchedHql(hql.toString(),count,pageIndex,pageSize,params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.AddressDao#updateDefaultByMemberId(java.lang.Integer, java.lang.Integer)
	 */
	public void updateDefaultByMemberId(Integer id,Integer memberId) {
		StringBuffer hql = new StringBuffer("UPDATE address SET state=1 WHERE member_id=?");
		List<Object> params = new ArrayList<Object>();
		params.add(memberId);
		executeHql(hql.toString(), params.toArray());
		
		StringBuffer hql_ = new StringBuffer("UPDATE address SET state=0 WHERE id=?");
		List<Object> params_ = new ArrayList<Object>();
		params_.add(id);
		executeHql(hql_.toString(), params_.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.AddressDao#deleteById(java.lang.Integer)
	 */
	public void deleteById(Integer id) {
		delete(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.AddressDao#getDefault(java.lang.Integer)
	 */
	public List<Address> getDefaultByMemberId(Integer memberId) {
		String hql = "from address where memberId=? and state=0";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(memberId);
		return getListByHQL(hql, paramList.toArray());
	}

	@Override
	public List<Map<String, Object>> getAddressInfoById(Integer cityid){
		StringBuffer sql = new StringBuffer("select ad.*,r.name as cityname,r.parent_id from address ad left join region r on ad.city_id = r.id where ad.id = ?");
		List<Object> param = new ArrayList<Object>();
		param.add(cityid);
		return getListMapBySQL(sql.toString(), param.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.AddressDao#getByMemberId(java.lang.Integer)
	 */
	public List<Address> getByMemberId(Integer memberId) {
		StringBuffer hql = new StringBuffer("FROM address WHERE memberId=?"); 
		List<Object> params = new ArrayList<Object>();
		params.add(memberId);
		return getListByHQL(hql.toString(), params.toArray());
	}
}