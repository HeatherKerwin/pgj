package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.ShippingAddressDao;
import com.ry.core.entity.ShippingAddress;
import com.ry.util.page.PageResults;

@Repository
public class ShippingAddressDaoImpl extends BaseDao<ShippingAddress, Integer> implements ShippingAddressDao {

	/* (non-Javadoc)
	 * @see com.ry.core.dao.ShippingAddressDao#saveModel(com.ry.core.entity.ShippingAddress)
	 */
	@Override
	public void saveModel(ShippingAddress shippingAddress) {
		save(shippingAddress);

	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.ShippingAddressDao#getDefaultByMemberId(java.lang.Integer)
	 */
	@Override
	public List<ShippingAddress> getDefaultByMemberId(Integer memberId) {
		String hql = "from shipping_address where memberId=? and state=0";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(memberId);
		return getListByHQL(hql, paramList.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.ShippingAddressDao#getById(java.lang.Integer)
	 */
	@Override
	public ShippingAddress getById(Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.ShippingAddressDao#getPageList(java.lang.Integer, java.lang.Integer, com.ry.core.entity.ShippingAddress)
	 */
	@Override
	public PageResults<ShippingAddress> getPageList(Integer pageIndex, Integer pageSize, ShippingAddress address) {
		StringBuffer hql = new StringBuffer("FROM shipping_address where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(address!=null){
			if(address.getMemberId()!=null){
				hql.append(" AND memberId=?");
				params.add(address.getMemberId());
			}
		}
		hql.append(" order by state");
		String count = "select count(*) "+ hql.toString();
		return findPageByFetchedHql(hql.toString(),count,pageIndex,pageSize,params.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.ShippingAddressDao#updateModel(com.ry.core.entity.ShippingAddress)
	 */
	@Override
	public void updateModel(ShippingAddress shippingAddress) {
		update(shippingAddress);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.ShippingAddressDao#updateDefaultByMemberId(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void updateDefaultByMemberId(Integer id, Integer memberId) {
		StringBuffer hql = new StringBuffer("UPDATE shipping_address SET state=1 WHERE memberId=?");
		List<Object> params = new ArrayList<Object>();
		params.add(memberId);
		executeHql(hql.toString(), params.toArray());
		
		StringBuffer hql_ = new StringBuffer("UPDATE shipping_address SET state=0 WHERE id=?");
		List<Object> params_ = new ArrayList<Object>();
		params_.add(id);
		executeHql(hql_.toString(), params_.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.ShippingAddressDao#deleteById(java.lang.Integer)
	 */
	@Override
	public void deleteById(Integer id) {
		delete(id);
	}

}
