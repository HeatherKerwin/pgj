package com.ry.core.dao.impl;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.MessageDao;
import com.ry.core.entity.Message;
import com.ry.core.form.MessageForm;
import com.ry.core.util.Utility;
import com.ry.util.page.PageResults;

@Repository
public class MessageDaoImpl extends BaseDao<Message,Integer > implements MessageDao{
   
	@Override
	public PageResults<Map<String, Object>> getPageList(MessageForm nf,Integer currentPage,Integer pageSize) {
		StringBuffer hql = new StringBuffer("select m.id,mm.username,mm.mobile,m.messagetime,m.messagecontent,m.returnVisit,m.source,m.messagenumber FROM message AS m LEFT JOIN member AS mm ON  m.memberid=mm.id  where 1=1 ");
		StringBuffer count = new StringBuffer("select count(m.id) from message as m where 1=1 ");
		
		List<Object> paramList = new ArrayList<Object>();
		if(nf!=null){
			if(nf.getBeginDate()!=null){
				hql.append(" and messagetime >= ?");
				count.append(" and messagetime >= ?");
				paramList.add(nf.getBeginDate());
			}
			if(nf.getEndDate()!=null){
				hql.append(" and messagetime <?");
				count.append(" and messagetime <?");
				paramList.add(nf.getEndDate());
			}
			if(nf.getMessagemobile()!=null && StringUtils.hasText(nf.getMessagemobile())){
				hql.append(" and m.messagenumber LIKE ?");
				count.append(" and m.messagenumber LIKE ?");
				paramList.add("%" + nf.getMessagemobile() + "%");
			}
		}
		hql.append(" order by messagetime desc");
		return Utility.decodeMobile(findPageMapByFetchedSql(hql.toString(),count.toString(),currentPage,pageSize,paramList.toArray()));
	}

	@Override
	public void updateMessage(Message s) {
		 update(s);
	}
	
	@Override
	public Message getById(Integer id) {
		return get(id);
	}
	
	@Override
	public void deleteMessage(Integer id) {
		delete(id);
	}
	
	@Override
	public void addMessage(Message message) {
		save(message);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.MessageDao#getMessageDTOById(java.lang.Integer)
	 */
	@Override
	public Map<String, Object> getMessageDTOById(Integer id) throws Exception{
		StringBuffer hql = new StringBuffer("select m.id,mm.username,mm.mobile,m.messagetime,m.messagecontent,m.returnVisit,m.source,m.messagenumber from message as m LEFT JOIN member as mm ON m.memberid=mm.id where 1=1  ");
		List<Object> param = new ArrayList<Object>();
		hql.append(" and m.id=?");
		param.add(id);
		List<Map<String, Object>> listmap= getListMapBySQL(hql.toString(),param.toArray());
		if(listmap!=null){
			return Utility.decodeMobile(listmap.get(0));
		}
		return null;
	}
}