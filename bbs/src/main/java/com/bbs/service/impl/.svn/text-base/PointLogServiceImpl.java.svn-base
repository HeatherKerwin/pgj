package com.bbs.service.impl;

import java.util.List;

import com.bbs.model.PointLog;
import com.bbs.model.PointLog.Fun;
import com.bbs.service.PointLogService;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.blade.jdbc.QueryParam;

import blade.kit.DateKit;

@Service
public class PointLogServiceImpl implements PointLogService {
	
	@Override
	public boolean save(PointLog pointLog) {
		try {
			Integer time = DateKit.getCurrentUnixTime();
			AR.update("insert into t_pointlog(id, uid, hid,score,fun,fk_id, create_time) values (?, ?, ?, ?, ?, ?, ?)",
					pointLog.getId(), pointLog.getUid(), pointLog.getHid(),pointLog.getScore(),pointLog.getFun(),pointLog.getFk_id(), time).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean save(Long uid,Long hid,Fun fun,Long fk_id) {
		PointLog pointLog = new PointLog();
		pointLog.setUid(uid);
		pointLog.setHid(hid);
		pointLog.setScore(fun.getSc());
		pointLog.setFun(fun);
		pointLog.setFk_id(fk_id);
		return save(pointLog);
	}
	
	@Override
	public List<PointLog> getList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).list(PointLog.class);
		}
		return null;
	}
	
	@Override
	public boolean delete(Long id) {
		if(null != id){
			AR.update("delete from t_pointlog where id = ?", id).executeUpdate();
			return true;
		}
		return false;
	}
	
	@Override
	public void saveFATIE(Long uid,Long fk_id) {
		PointLog temp = AR.find("SELECT * FROM t_pointlog pl WHERE pl.uid=? AND pl.fun=? AND fk_id=?", uid,Fun.FATIE.toString(),fk_id).first(PointLog.class);
		if(temp==null){
			this.save(uid, uid, Fun.FATIE, fk_id);
		}
	}
	
	@Override
	public void saveDIANZAN(Long uid,Long hid,Long fk_id) {
		PointLog temp = AR.find("SELECT * FROM t_pointlog pl WHERE pl.hid=? AND pl.fun=? AND fk_id=?", hid,Fun.DIANZAN.toString(),fk_id).first(PointLog.class);
		if(temp!=null && temp.getId()!=null){
			this.delete(temp.getId());
		}else{
			this.save(hid, hid, Fun.DIANZAN, fk_id);
		}
	}
	
	@Override
	public void saveJINGHUA(Long uid,Long hid,Long fk_id) {
		PointLog temp = AR.find("SELECT * FROM t_pointlog pl WHERE pl.hid=? AND pl.fun=? AND fk_id=?", hid,Fun.JINGHUA.toString(),fk_id).first(PointLog.class);
		if(temp!=null && temp.getId()!=null){
			this.delete(temp.getId());
		}else{
			this.save(hid, hid, Fun.JINGHUA, fk_id);
		}
	}
	
	@Override
	public void saveWANSHAN(Long uid) {
		PointLog temp = AR.find("SELECT * FROM t_pointlog pl WHERE pl.uid=? AND pl.fun=?", uid,Fun.WANSHAN.toString()).first(PointLog.class);
		if(temp==null){
			this.save(uid, uid, Fun.WANSHAN, null);
		}
	}
}