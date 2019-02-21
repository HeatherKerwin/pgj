package com.ry.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.MessageDao;
import com.ry.core.entity.Message;
import com.ry.core.form.MessageForm;
import com.ry.core.service.MessageService;
import com.ry.util.page.PageResults;

import cn.jpush.api.utils.StringUtils;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Resource
	MessageDao messageDao;

	@Override
	public List<Message> getList(Message message) {
		return null;
	}

	@Override
	public void updateAllMessage(List<Message> messageList) {
		// TODO Auto-generated method stub
	}

	@Override
	public PageResults<Map<String, Object>> getPageList(MessageForm nf, Integer currentPage, Integer pageSize) {
		return messageDao.getPageList(nf, currentPage, pageSize);
	}

	@Override
	public void modifyMessage(Message s) {
		messageDao.updateMessage(s);
	}

	@Override
	public Message getMessageById(Integer id) {
		return messageDao.getById(id);
	}

	@Override
	public void removeMessage(Integer id) {
		messageDao.deleteMessage(id);
	}

	@Override
	public void saveMessage(Message s) throws Exception {
		if(!StringUtils.isNotEmpty(s.getMessagenumber()))throw new Exception("手机号码不能为空");
		if(!StringUtils.isNotEmpty(s.getMessagecontent()))throw new Exception("反馈内容不能为空");
		messageDao.addMessage(s);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.MessageService#getMessageDTOById(java.lang.Integer)
	 */
	@Override
	public Map<String, Object> getMessageDTOById(Integer id) throws Exception{
		return messageDao.getMessageDTOById(id);
	}
}