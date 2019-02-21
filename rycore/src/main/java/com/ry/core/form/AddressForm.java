package com.ry.core.form;

import com.ry.core.entity.Address;

/**
 * 贴现地址
 * @author WKX
 * @date 2016-08-09
 */
public class AddressForm extends Address{
	
	private static final long serialVersionUID = 1L;
	
	private Integer state;//状态（0默认、1其他）

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}