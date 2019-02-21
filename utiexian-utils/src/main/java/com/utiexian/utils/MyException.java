package com.utiexian.utils;

public class MyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MyException(String message) {
        super(message);
    }
	
	public MyException() {
        super("请求失败，请联系客服");
    }
}