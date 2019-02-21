package com.bbs.service.impl;

import java.util.Date;
import java.util.List;

import com.bbs.kit.Utils;
import com.bbs.model.Userlog;
import com.bbs.service.UserlogService;
import com.blade.context.BladeWebContext;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.blade.jdbc.QueryParam;

import blade.kit.DateKit;

@Service
public class UserlogServiceImpl implements UserlogService {
	
	@Override
	public List<Userlog> getUserlogList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).list(Userlog.class);
		}
		return null;
	}
	
	@Override
	public void save(final Long uid, final String action, final String content) {
		final String ip = Utils.getIpAddr(BladeWebContext.request());
		Runnable t = new Runnable() {
			@Override
			public void run() {
				try {
					AR.update("insert into t_userlog(uid, action, content, ip_addr, create_time) values(?, ?, ?, ?, ?)",
							uid, action, content, ip, DateKit.getCurrentUnixTime()).executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		Utils.run(t);
	}
	
	@Override
	public void saveVisit(final Long uid, final String action, final String content) {
		final String ip = Utils.getIpAddr(BladeWebContext.request());
		Date start_ = DateKit.dateFormat(DateKit.dateFormat(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd");
		Date end_ = DateKit.getTomorrow();
		final long start = start_.getTime()/1000;
		final long end = end_.getTime()/1000;
		Runnable t = new Runnable() {
			@Override
			public void run() {
				try {
					List<Userlog> list = AR.find("SELECT * FROM t_userlog WHERE ip_addr=? AND action=? AND create_time BETWEEN ? AND ?",ip,action,start,end).list(Userlog.class);
					if(list==null || list.size()==0){
						AR.update("insert into t_userlog(uid, action, content, ip_addr, create_time) values(?, ?, ?, ?, ?)",
								uid, action, content, ip, DateKit.getCurrentUnixTime()).executeUpdate();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		Utils.run(t);
	}
}