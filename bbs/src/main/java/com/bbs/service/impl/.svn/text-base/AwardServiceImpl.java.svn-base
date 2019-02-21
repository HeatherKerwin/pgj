package com.bbs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bbs.model.Award;
import com.bbs.service.AwardService;
import com.bbs.service.TicketService;
import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.blade.jdbc.QueryParam;

import blade.kit.StringKit;

@Service
public class AwardServiceImpl implements AwardService {
	
	@Inject
	private TicketService ticketService;
	
	@Override
	public boolean save(Award award) {
		try {
			AR.update("insert into t_award(id,uid,tid,name,phone,genre,goods,prov,city,dist,address,create_time,remarks,state) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
					award.getId(),award.getUid(),award.getTid(),award.getName(),award.getPhone(),award.getGenre(),award.getGoods(),award.getProv(),award.getCity(),award.getDist(),award.getAddress(),new Date(),award.getRemarks(),award.getState()).executeUpdate();
			if(award.getTid()!=null)ticketService.updateToUse(award.getTid());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Award getByTid(Long tid){
		if(tid==null){
			return null;
		}
		return AR.find("select * from t_award where tid =?",tid).first(Award.class);
	}
	
	@Override
	public Award getById(Long id) {
		return AR.findById(Award.class, id);
	}
	
	@Override
	public boolean update(Long id,String name,String phone,String prov,String city,String dist,String address,String remarks,Integer state) {
		if(null != id){
			StringBuffer updateSql = new StringBuffer("update t_award set ");
			List<Object> params = new ArrayList<Object>();
			if(StringKit.isNotBlank(name)){
				updateSql.append("name = ?, ");
				params.add(name);
			}
			if(StringKit.isNotBlank(phone)){
				updateSql.append("phone = ?, ");
				params.add(phone);
			}
			if(StringKit.isNotBlank(prov)){
				updateSql.append("prov = ?, ");
				params.add(prov);
			}
			if(StringKit.isNotBlank(city)){
				updateSql.append("city = ?, ");
				params.add(city);
			}
			if(StringKit.isNotBlank(dist)){
				updateSql.append("dist = ?, ");
				params.add(dist);
			}
			if(StringKit.isNotBlank(address)){
				updateSql.append("address = ?, ");
				params.add(address);
			}
			if(StringKit.isNotBlank(remarks)){
				updateSql.append("remarks = ?, ");
				params.add(remarks);
			}
			if(state!=null){
				updateSql.append("state = ?, ");
				params.add(state);
			}
			if(params.size() > 0){
				updateSql = new StringBuffer(updateSql.substring(0, updateSql.length() - 2));
				updateSql.append(" where id = ? ");
				params.add(id);
				AR.update(updateSql.toString(), params.toArray()).executeUpdate();
			}
			return true;
		}
		return false;
	}
	
	@Override
	public List<Award> getByUid(Long uid) {
		QueryParam qp = QueryParam.me();
		qp.eq("uid", uid);
		return AR.find(qp).list(Award.class);
	}
}