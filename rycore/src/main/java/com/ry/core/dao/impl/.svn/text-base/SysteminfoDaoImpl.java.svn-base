package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.SysteminfoDao;
import com.ry.core.entity.Systeminfo;
import com.ry.core.entity.Systeminfo.ReadState;
import com.ry.core.entity.Systeminfo.Type;
import com.ry.core.form.SysteminfoForm;
import com.ry.util.page.PageResults;

@Repository
public class SysteminfoDaoImpl extends BaseDao<Systeminfo, Integer> implements SysteminfoDao {

	@Override
	public void updateSysteminfo(Systeminfo systeminfo) {
		update(systeminfo);
	}

	@Override
	public void addSysteminfo(Systeminfo systeminfo) {
		save(systeminfo);	
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.SysteminfoDao#getPageList(com.ry.core.form.SysteminfoForm, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<Systeminfo> getPageList(SysteminfoForm nf,Integer currentPage,Integer pageSize) {
		StringBuffer hql=new StringBuffer("from systeminfo where 1=1 ");
		List<Object> paramList = new ArrayList<Object>();
		if(nf!=null){
			if(nf.getMemberId()>0){
				hql.append(" and memberId = ?");
				paramList.add(nf.getMemberId());
			}
			if(nf.getType()!=null){
				hql.append(" and type = ?");
				paramList.add(nf.getType());
			}else if(nf.getTypes()!=null && nf.getTypes().size()>0){
				hql.append(" and type in (");
				String h = "";
				for(Type t:nf.getTypes()){
					if(StringUtils.hasText(h))h += ",";
					h += "?";
					paramList.add(t);
				}
				hql.append(h+")");
			}
		}
		hql.append(" order by createTime desc");
		String countHql = "select count(*) "+hql.toString();
		return findPageByFetchedHql(hql.toString(), countHql, currentPage, pageSize, paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.SysteminfoDao#getByMemberIdAndReadState(java.lang.Integer, com.ry.core.entity.Systeminfo.ReadState)
	 */
	public List<Systeminfo> getByMemberIdAndReadState(Integer memberId,ReadState readState) {
		StringBuffer hql = new StringBuffer("from systeminfo where memberId = ? and readState = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(memberId);
		paramList.add(readState);
		return queryByHql(hql.toString(),paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.SysteminfoDao#getById(java.lang.Integer)
	 */
	public Systeminfo getById(Integer id) throws Exception{
		return get(id);
	}
	
	@Override
	public void updateToReadByMemberId(Integer memberId,List<Type> types) {
		StringBuffer hql = new StringBuffer("UPDATE systeminfo SET readState=? WHERE memberId=? ");
		List<Object> params = new ArrayList<Object>();
		params.add(ReadState.READ);
		params.add(memberId);
		
		if(types!=null && types.size()>0){
			hql.append(" and type in (");
			String h = "";
			for(Type t:types){
				if(StringUtils.hasText(h))h += ",";
				h += "?";
				params.add(t);
			}
			hql.append(h+")");
		}
		executeHql(hql.toString(), params.toArray());
	}
}