package com.ry.core.dao;

import com.ry.core.entity.MessageUp;

public interface MessageUpDao {

	/** 保存短信上行的信息
	 * @param messageup
	 */
	public void saveModel(MessageUp messageup);
}
