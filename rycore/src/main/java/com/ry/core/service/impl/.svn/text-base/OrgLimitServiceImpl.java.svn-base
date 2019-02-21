/**
 * 
 */
package com.ry.core.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.OrgLimitDao;
import com.ry.core.entity.OrgLimit;
import com.ry.core.form.orgOrder.OrderRequest;
import com.ry.core.service.OrgLimitService;

/**
 * 名称: OrgDaoImpl.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2016年3月2日 下午7:04:32<br>
 * @since  2016年3月2日
 * @author li.xiaofei 
 */
@Service
public class OrgLimitServiceImpl implements OrgLimitService{
	
	@Resource
	OrgLimitDao orgLimitDao;

	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgLimitDtoService#getByType(com.ry.core.form.orgOrder.OrderRequest)
	 */
	@Override
	public List<Map<String, Object>> getOrgByObj(OrderRequest req) {
		// TODO Auto-generated method stub
		return orgLimitDao.getOrgByObj(req);
	}

	/**
	 * 根据机构ID获取该机构当天的报价额数据(时间为当天)
	 * @author BKY
	 */
	public OrgLimit getByOrgIdAndDay(Integer id, String day) {
		List<OrgLimit> list = orgLimitDao.getByOrgIdAndDay(id, day);
		if(list!=null && list.size()>0){
			return list.iterator().next();
		}else{
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgLimitService#saveModel(com.ry.core.entity.OrgLimit)
	 */
	public void saveModel(OrgLimit orgLimit) {
		orgLimitDao.saveModel(orgLimit);
	}

}
