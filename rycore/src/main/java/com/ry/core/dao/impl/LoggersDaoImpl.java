package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.LoggersDao;
import com.ry.core.entity.Loggers;
import com.ry.core.form.loggers.LoggersRequest;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;

@Repository
public class LoggersDaoImpl extends BaseDao<Loggers, Integer> implements LoggersDao {

	@Override
	public PageResults<Map<String, Object>> getPage(LoggersRequest logRes) throws Exception {
		StringBuffer sql = new StringBuffer("from loggers l LEFT JOIN admin a on a.id = l.creater_id where 1=1");
		List<Object> paras = new ArrayList<Object>();
		if (logRes.getCreaterId() != null) {
			sql.append(" and creater_id = ?");
			paras.add(logRes.getCreaterId());
		}
		if (StringUtils.hasText(logRes.getStartDate())) {
			sql.append(" and create_time>?");
			paras.add(DateUtil.parser(logRes.getStartDate(), DateUtil.FORMART3));
		}
		if (StringUtils.hasText(logRes.getEndDate())) {
			sql.append(" and create_time<?");
			paras.add(DateUtil.parser(logRes.getEndDate() + " 23:59:59", DateUtil.FORMART));
		}
		String count = "select count(*)" + sql.toString()+" order by create_time desc";
		PageResults<Map<String, Object>> list = findPageMapByFetchedSql(
				"SELECT a.username,(SELECT username FROM admin WHERE id =l.last_editor_id) lastedit ,l.* "
						+ sql.toString(),
				count, logRes.currentPage().intValue(), logRes.getLength().intValue(), paras.toArray());
		return list;
	}

	@Override
	public Integer addLoggers(Loggers loggers) {
		return save(loggers);
	}

	@Override
	public void updateLoggers(Loggers loggers) {
		update(loggers);
	}

	@Override
	public Loggers getLoggers(Integer id) {
		return get(id);
	}

	@Override
	public List<Map<String, Object>> getAdmins() {
		String hql = "SELECT a.id,a.username FROM loggers l LEFT JOIN admin a ON a.id=l.creater_id GROUP BY a.username";
		List<Map<String, Object>> admins = getListMapBySQL(hql, null);
		return admins;
	}

	
	@Override
	public List<Map<String, Object>> getByObj(LoggersRequest logRes) throws Exception {
		StringBuffer sql = new StringBuffer(
				"Select a.username,(SELECT username FROM admin WHERE id =l.last_editor_id) lastedit ,l.* from loggers l LEFT JOIN admin a on a.id = l.creater_id where 1=1");
		List<Object> paras = new ArrayList<Object>();
		// ids代表选中的记录，如果没有选中的
		if (logRes.getIds().length == 0) {
			//只要选择了条件
			if(logRes.getCreaterId() != null || StringUtils.hasText(logRes.getStartDate()) || StringUtils.hasText(logRes.getEndDate())){
				if (logRes.getCreaterId() != null) {
					sql.append(" and creater_id = ?");
					paras.add(logRes.getCreaterId());
				}
				if (StringUtils.hasText(logRes.getStartDate())) {
					sql.append(" and create_time>?");
					paras.add(DateUtil.parser(logRes.getStartDate(), DateUtil.FORMART3));
				}
				if (StringUtils.hasText(logRes.getEndDate())) {
					sql.append(" and create_time<?");
					paras.add(DateUtil.parser(logRes.getEndDate() + " 23:59:59", DateUtil.FORMART));
				}
			}else{	//日期，创建人都没选
				sql.append(" and (TIMESTAMPDIFF(DAY,create_time,Now()))<7");
			}
		} else { // 有选中的
			if (logRes.getIds().length > 0) {
				String tag = "";
				for (Integer id : logRes.getIds()) {
					if (!"".equals(tag))
						tag += ",";
					tag += "?";
					paras.add(id);
				}
				sql.append(" and l.id in(" + tag + ")");
			}
		}
		return getListMapBySQL(sql.toString(), paras.toArray());
	}
}
