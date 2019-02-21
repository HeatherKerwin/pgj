package com.bbs.kit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import com.bbs.model.LoginUser;

public class MyStackKit {

	/**
	 * 添加对象（栈）
	 * @author WKX
	 * @param obj
	 * @since 2016年11月7日 下午6:22:54
	 */
	public static Stack<LoginUser> user_stack = null;
	public static int user_stack_size = 20;
	public static void push(LoginUser obj){
		if(user_stack==null)user_stack = new Stack<LoginUser>();
		if(user_stack.search(obj)==-1){//搜索标示相同的
			boolean flag = true;
			for (LoginUser x : user_stack) {//搜索标示不靠谱（依然要比对用户主键）
				if(x!=null && obj!=null && x.getUid()!=null && obj.getUid()!=null && x.getUid()==obj.getUid())flag = false;
			}
			if(flag){
				if(user_stack.size()>=user_stack_size){
					user_stack.remove(0);
				}
				user_stack.push(obj);
			}
		}
	}
	
	/**
	 * 获取栈（近段时间登录的用户）
	 * @author WKX
	 * @since 2016年11月7日 下午6:26:38
	 */
	public static List<LoginUser> getStack(){
		List<LoginUser> result = new ArrayList<LoginUser>();
		if(user_stack==null)return result;
		for (LoginUser x : user_stack) { 
			result.add(x);
		}
		Collections.shuffle(result);//打乱顺序
		return result;
	}
	
	/**
	 * 测试
	 * @author WKX
	 * @param args
	 * @since 2016年11月7日 下午7:54:39
	 */
	public static void main(String[] args) {
		user_stack = new Stack<LoginUser>();
		LoginUser u1 = new LoginUser();
		u1.setUid(1L);
		push(u1);
		
		LoginUser u2 = new LoginUser();
		u2.setUid(2L);
		push(u2);
		
		push(u2);
		
		for (LoginUser x : getStack()) { 
            System.out.println(x.getUid()); 
		}
	}
}