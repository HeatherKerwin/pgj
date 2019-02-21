package com.ry.core.service;

import com.ry.core.entity.LuckDraw;

public interface LuckDrawService {
	/**
	 * 保存中奖纪录
	 * @param luckDraw 中奖对象
	 */
	public void saveModel(LuckDraw luckDraw);
}
