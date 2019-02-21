package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.VersionInfoDao;
import com.ry.core.entity.VersionInfo;
import com.ry.core.form.versioninfo.VersionInfoRequest;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;

@Repository
public class VersionInfoDaoImpl extends BaseDao<VersionInfo,Integer> implements VersionInfoDao {

	@Override
	public PageResults<VersionInfo> getPage(VersionInfoRequest req) throws Exception {
		StringBuffer sql = new StringBuffer("from versionInfo where 1=1");
		List<Object> paras = new ArrayList<Object>();
		if (StringUtils.hasText(req.getStartDate())) {
			sql.append(" and shijian>=?");
			paras.add(DateUtil.parser(req.getStartDate(), DateUtil.FORMART3));
		}
		if (StringUtils.hasText(req.getEndDate())) {
			sql.append(" and shijian<?");
			paras.add(DateUtil.parser(req.getEndDate(), DateUtil.FORMART));
		}
		String count = "select count(*)" + sql.toString();
		sql.append(" order by shijian desc ");
		PageResults<VersionInfo> list = findPageByFetchedSql("SELECT * "+ sql.toString(),
				count, req.currentPage().intValue(), req.getLength().intValue(), paras.toArray());
		return list;
	}

	@Override
	public VersionInfo getNewVersionInfo() {
		String hql = "from versionInfo where state=?";
		List<Object> paras = new ArrayList<Object>();
		paras.add("2");
		return getByHQL(hql,paras.toArray());
	}

	@Override
	public VersionInfo getVersionInfo(Integer id) {
		return get(id);
	}

	@Override
	public void updateVersionInfo(VersionInfo versionInfo) {
		update(versionInfo);
	}

	@Override
	public Integer addVersionInfo(VersionInfo versionInfo) {
		return save(versionInfo);
	}

	@Override
	public List<VersionInfo> getMaxDate() {
		String hql = "from versionInfo order by shijian desc";
		return queryByHql(hql,null);
	}

}
