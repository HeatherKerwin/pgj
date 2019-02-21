package com.bbs.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bbs.form.UserForm;
import com.bbs.model.NodeUser;
import com.bbs.model.User;
import com.bbs.service.NodeUserService;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import blade.kit.DateKit;

@Service
public class NodeUserServiceImpl implements NodeUserService {
	
	@Override
	public Page<UserForm> getPageList(QueryParam queryParam){
		if(null != queryParam){
			return  AR.find(queryParam).page(UserForm.class);
		}
		return null;
	}
	
	@Override
	public boolean save(NodeUser nodeUser) {
		try {
			Integer time = DateKit.getCurrentUnixTime();
			AR.update("insert into t_node_user(id,nid,uid,create_time) values (?, ?, ?, ?)", nodeUser.getId(),nodeUser.getNid(),nodeUser.getUid(), time).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean delete(Long id) {
		if(null != id){
			AR.update("DELETE FROM t_node_user WHERE id = ?", id).executeUpdate(true);
			return true;
		}
		return false;
	}
	
	@Override
	public List<NodeUser> getList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).list(NodeUser.class);
		}
		return null;
	}
	
	@Override
	public List<NodeUser> getListByUid(Long uid) {
		if(uid!=null){
			QueryParam np = QueryParam.me();
			np.eq("uid", uid);
			List<NodeUser> nus = getList(np);
			return nus;
		}else{
			return null;
		}
	}
	
	@Override
	public List<NodeUser> getListByNid(Long nid) {
		if(nid!=null){
			QueryParam np = QueryParam.me();
			np.eq("nid", nid);
			List<NodeUser> nus = getList(np);
			return nus;
		}else{
			return null;
		}
	}
	
	@Override
	public List<NodeUser> getListByNidUid(Long nid,Long uid) {
		if(nid!=null && uid!=null){
			QueryParam np = QueryParam.me();
			np.eq("nid", nid);
			np.eq("uid", uid);
			List<NodeUser> nus = getList(np);
			return nus;
		}else{
			return null;
		}
	}
	
	@Override
	public List<User> getUserListByNid(Long nid) {
		if(null != nid){
			QueryParam np = QueryParam.me();
			StringBuffer sql = new StringBuffer("SELECT u.* FROM t_user u LEFT JOIN t_node_user nu ON u.uid=nu.uid WHERE nu.nid = ?");
			List<Object> param = new ArrayList<Object>();
			param.add(nid);
			
			np.add(sql.toString(), param.toArray());
			return AR.find(np).list(User.class);
		}
		return null;
	}
	
	public List<UserForm> getUserFormList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).list(UserForm.class);
		}
		return null;
	}
	
	@Override
	public List<UserForm> getUserByNid(Long nid) {
		if(null != nid){
			QueryParam np = QueryParam.me();
			StringBuffer sql = new StringBuffer("SELECT u.* FROM (SELECT u.*,info.nick_name FROM t_user u LEFT JOIN t_userinfo info ON u.uid=info.uid)u LEFT JOIN t_node_user nu ON u.uid=nu.uid WHERE nu.nid = ?");
			List<Object> param = new ArrayList<Object>();
			param.add(nid);
			
			np.add(sql.toString(), param.toArray());
			return AR.find(np).list(UserForm.class);
		}
		return null;
	}
}