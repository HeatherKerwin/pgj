package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.ShippingAddressDao;
import com.ry.core.entity.ShippingAddress;
import com.ry.core.service.ShippingAddressService;
import com.ry.util.page.PageResults;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {
	
	@Resource
	ShippingAddressDao  shippingAddressDao; 

	/* (non-Javadoc)
	 * @see com.ry.core.service.ShippingAddressService#saveModel(com.ry.core.entity.ShippingAddress)
	 */
	@Override
	public void saveModel(ShippingAddress shippingAddress) {
		shippingAddressDao.saveModel(shippingAddress);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.ShippingAddressService#getDefaultByMemberId(java.lang.Integer)
	 */
	@Override
	public ShippingAddress getDefaultByMemberId(Integer memberId) {
		List<ShippingAddress> list =  shippingAddressDao.getDefaultByMemberId(memberId);
		if(list != null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.ShippingAddressService#getById(java.lang.Integer)
	 */
	@Override
	public ShippingAddress getById(Integer id) {
		return shippingAddressDao.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.ShippingAddressService#getPageList(java.lang.Integer, java.lang.Integer, com.ry.core.entity.ShippingAddress)
	 */
	@Override
	public PageResults<ShippingAddress> getPageList(Integer pageIndex, Integer pageSize, ShippingAddress address) {
		return shippingAddressDao.getPageList(pageIndex, pageSize, address);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.ShippingAddressService#updateModel(com.ry.core.entity.ShippingAddress)
	 */
	@Override
	public void updateModel(ShippingAddress shippingAddress) {
		shippingAddressDao.updateModel(shippingAddress);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.ShippingAddressService#updateDefaultByMemberId(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void updateDefaultByMemberId(Integer id, Integer memberId) {
		shippingAddressDao.updateDefaultByMemberId(id, memberId);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.ShippingAddressService#deleteById(java.lang.Integer)
	 */
	@Override
	public void deleteById(Integer id) {
		shippingAddressDao.deleteById(id);
	}

}
