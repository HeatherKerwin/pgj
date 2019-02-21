package com.ry.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ry.core.dao.MemberDao;
import com.ry.core.dao.OrgDao;
import com.ry.core.dao.PushLogDao;
import com.ry.core.dao.SysteminfoDao;
import com.ry.core.entity.PushLog;
import com.ry.core.entity.Systeminfo;
import com.ry.core.entity.Systeminfo.Type;
import com.ry.core.form.PushLogForm;
import com.ry.core.service.PushLogService;
import com.ry.core.util.JPushUtil;
import com.ry.util.page.PageResults;

import cn.jpush.api.push.PushResult;

@Service
public class PushLogServiceImpl implements PushLogService {

	@Resource
	PushLogDao pushLogDao;
	
	@Resource
	MemberDao memberDao;
	
	@Resource
	OrgDao orgDao;
	
	@Resource
	SysteminfoDao systeminfoDao;
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.PushLogService#getPageList(com.ry.core.form.PushLogForm)
	 */
	@Override
	public PageResults<PushLog> getPageList(PushLogForm form) {
		return pushLogDao.getPageList(form);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.PushLogService#saveModelAndPushMsg(com.ry.core.entity.PushLog)
	 */
	@Override
	public PushLog saveModelAndPushMsg(PushLog pushLog) {
		if(pushLog!=null){
			Integer size = 0;//推送消息，保存推送历史
			Date date = new Date();
			Systeminfo info = null;//保存用户系统消息
    		
			String memberIds = "";//失败主键
			String mobiles = "";//失败手机号
			
			PushLog search = new PushLog();
			BeanUtils.copyProperties(pushLog, search);//复制对象
			List<Map<String,Object>> all = new ArrayList<Map<String,Object>>();
			if(pushLog.getType()==0 || pushLog.getType()==1){//机构
				search.setType(1);
				List<Map<String,Object>> list = pushLogDao.getMember(search);
				if(list!=null)all.addAll(list);
			}
			if(pushLog.getType()==0 || pushLog.getType()==2){//企业
				search.setType(0);
				List<Map<String,Object>> list = pushLogDao.getMember(search);
				if(list!=null)all.addAll(list);
			}
			if(all!=null && all.size()>0){
				PushResult result = null;
				for(Map<String,Object> m:all){
					try {
						result = null;
				    	if(m==null || m.get("mobile")==null)throw new Exception();
			    		
				    	result = JPushUtil.pushToAlias(m.get("mobile").toString(),pushLog.getContent(),Type.SYSTEM);
				    	if(result==null)throw new Exception();
			    		if(m!=null && m.get("id")!=null){
			    			info = new Systeminfo();//保存用户系统消息
			    			info.setMemberId(Integer.valueOf(m.get("id").toString()));
				    		info.setAlert(pushLog.getContent());
				    		info.setContent(pushLog.getContent());
				    		info.setCreateTime(date);
				    		info.setType(Type.SYSTEM);
				    		systeminfoDao.addSysteminfo(info);
			    		}
			    		size ++;
			    		Thread.sleep(300);
				    } catch (Exception e) {
				    	if(StringUtils.hasText(memberIds))memberIds += ",";
				    	if(StringUtils.hasText(mobiles))mobiles += ",";
				    	memberIds += m.get("id");
				    	mobiles += m.get("mobile");
				    	e.printStackTrace();
				    }
				}
				pushLog.setMemberIds(memberIds);
				pushLog.setMobiles(mobiles);
			}
			pushLog.setAmount(all.size());
			pushLog.setSuccessAmount(size);
			pushLog.setCreateTime(new Date());
			pushLogDao.saveModel(pushLog);
		}
		return pushLog;
	}
}