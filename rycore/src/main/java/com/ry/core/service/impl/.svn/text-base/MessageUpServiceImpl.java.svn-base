package com.ry.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.MessageUpDao;
import com.ry.core.entity.MessageUp;
import com.ry.core.service.MessageUpService;

@Service(value="messageUpService")
public class MessageUpServiceImpl implements MessageUpService {

	@Resource
	MessageUpDao messageUpDao;
	/* (non-Javadoc)
	 * @see com.ry.core.service.MessageUpService#saveModel(com.ry.core.entity.MessageUp)
	 */
	public void saveModel(MessageUp messageup) {
		messageUpDao.saveModel(messageup);
	}

}
