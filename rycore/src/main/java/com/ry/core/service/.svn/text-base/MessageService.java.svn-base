package com.ry.core.service;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.Message;
import com.ry.core.form.MessageForm;
import com.ry.util.page.PageResults;


public interface MessageService {
	List<Message> getList(Message message);
	PageResults<Map<String, Object>> getPageList(MessageForm nf,Integer currentPage,Integer pageSize);
	
	void updateAllMessage(List<Message> messageList);
	public void modifyMessage(Message s);
	public Message getMessageById(Integer id);
	public void removeMessage(Integer id);
	public void saveMessage(Message s)  throws Exception;
	
	/**
	 * 根据主键获取对象[DTO含用户基本信息]
	 * @param id
	 * @throws Exception
	 * @author WKX
	 */
	public Map<String, Object> getMessageDTOById(Integer id) throws Exception;
}