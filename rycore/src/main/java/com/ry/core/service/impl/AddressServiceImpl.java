package com.ry.core.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ry.core.dao.AddressDao;
import com.ry.core.dao.BaseDao;
import com.ry.core.entity.Address;
import com.ry.core.form.AddressForm;
import com.ry.core.service.AddressService;
import com.ry.util.page.PageResults;

@Service
public class AddressServiceImpl extends BaseDao<Address, Integer> implements AddressService {
	
	@Resource
	AddressDao addressDao;
	
	@Override
	public Address getById(Integer id) {
		return addressDao.getById(id);
	}
	
	@Override
	public void saveModel(Address address) {
		addressDao.saveModel(address);
	}
	
	@Override
	public void updateModel(Address address) {
		addressDao.updateModel(address);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.AddressService#getPageList(java.lang.Integer, java.lang.Integer, com.ry.core.form.AddressForm)
	 */
	public PageResults<Address> getPageList(Integer pageIndex,Integer pageSize,AddressForm form){
		return addressDao.getPageList(pageIndex, pageSize, form);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.AddressService#updateDefaultByMemberId(java.lang.Integer, java.lang.Integer)
	 */
	public void updateDefaultByMemberId(Integer id,Integer memberId){
		addressDao.updateDefaultByMemberId(id, memberId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.AddressService#deleteById(java.lang.Integer)
	 */
	public void deleteById(Integer id){
		addressDao.deleteById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.AddressService#getDefaultByMemberId(java.lang.Integer)
	 */
	public Address getDefaultByMemberId(Integer memberId){
		List<Address> list = addressDao.getDefaultByMemberId(memberId);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.AddressService#getAddressInfoById(java.lang.Integer)
	 */
	public Map<String, Object> getAddressInfoById(Integer cityid){
		List<Map<String, Object>> list = addressDao.getAddressInfoById(cityid);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.AddressService#getByMemberId(java.lang.Integer)
	 */
	public List<Address> getByMemberId(Integer memberId){
		return addressDao.getByMemberId(memberId);
	}
}