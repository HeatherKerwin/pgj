package com.bbs.service.impl;

import java.util.Date;
import java.util.List;

import com.bbs.model.Ticket;
import com.bbs.service.TicketService;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.blade.jdbc.QueryParam;

@Service
public class TicketServiceImpl implements TicketService {
	
	@Override
	public boolean save(Ticket ticket) {
		try {
			AR.update("insert into t_ticket(id,uid,source,state,create_time,update_time) values (?, ?, ?, ?, ?, ?)",
					ticket.getId(),ticket.getUid(),ticket.getSource(),ticket.getState(),new Date(),null).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean updateToUse(Long id) {
		try {
			AR.update("update t_ticket set state=1,update_time=? where id = ?", new Date(), id).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<Ticket> getList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).list(Ticket.class);
		}
		return null;
	}
	
	@Override
	public List<Ticket> getByUidState(Long uid,Integer state) {
		QueryParam qp = QueryParam.me();
		qp.eq("uid", uid).eq("state", state);
		return this.getList(qp);
	}
	
	@Override
	public boolean save(Long uid,Integer source) {
		Ticket ticket = new Ticket();
		ticket.setUid(uid);
		ticket.setSource(source);
		return this.save(ticket);
	}
	
	@Override
	public List<Ticket> getByUidSource(Long uid,Integer source) {
		QueryParam qp = QueryParam.me();
		qp.eq("uid", uid).eq("source", source);
		return this.getList(qp);
	}
}