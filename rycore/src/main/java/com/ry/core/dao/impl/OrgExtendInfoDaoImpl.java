package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.OrgExtendInfoDao;
import com.ry.core.entity.OrgExtendInfo;

@Repository
public class OrgExtendInfoDaoImpl extends BaseDao<OrgExtendInfo, Integer> implements OrgExtendInfoDao {

	public OrgExtendInfo getOrgExtendInfoByInfoId(Integer orgInfoId) {
		StringBuffer hql = new StringBuffer("from org_extend_info where 1=1 ");
		List<Object> paramList = new ArrayList<Object>();
		if(orgInfoId != null){
			hql.append(" and orgInfoId = ?");
			paramList.add(orgInfoId);
		}
		OrgExtendInfo result = getByHQL(hql.toString(),paramList.toArray());
		return result;
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgExtendInfoDao#updateOrgExtendInfo(com.ry.core.entity.OrgExtendInfo)
	 */
	@Override
	public void updateOrgExtendInfo(OrgExtendInfo orgExtendInfo) {
		update(orgExtendInfo);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgExtendInfoDao#saveOrgExtendInfo(com.ry.core.entity.OrgExtendInfo)
	 */
	@Override
	public void saveOrgExtendInfo(OrgExtendInfo orgExtendInfo) {
		save(orgExtendInfo);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgExtendInfoDao#getOrgExtendInfoByInfo()
	 */
	@Override
	public List<OrgExtendInfo> getOrgExtendInfoByInfo() {
		StringBuffer hql = new StringBuffer(" FROM org_extend_info WHERE ascription_state != ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(3);
		return getListByHQL(hql.toString(), paramList.toArray());
	}
	
}
