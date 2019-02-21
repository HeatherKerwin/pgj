package com.ry.core.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.entity.Message;
import com.ry.core.form.MessageForm;
import com.ry.util.page.PageResults;

@Repository
public interface  MessageDao {

	PageResults<Map<String, Object>> getPageList(MessageForm nf,Integer currentPage,Integer pageSize);
	
	public void updateMessage(Message s);
	public	Message getById(Integer id);
	public	void deleteMessage(Integer id);
	void addMessage(Message message);
//	public Integer saveMessage(Message s);
	
	/**
	 * 根据主键获取对象[DTO含用户基本信息]
	 * @param id
	 * @throws Exception
	 * @author WKX
	 */
	public Map<String, Object> getMessageDTOById(Integer id) throws Exception;
}
