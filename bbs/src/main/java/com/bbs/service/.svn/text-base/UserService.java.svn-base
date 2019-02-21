package com.bbs.service;

import java.util.List;
import java.util.Map;

import com.bbs.model.LoginUser;
import com.bbs.model.User;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

public interface UserService {
	
	User getUser(Long uid);
	
	User getUserByLoginName(String user_name);
	
	User getUser(QueryParam queryParam);
	
	Map<String, Object> getUserDetail(Long uid);
	
	List<User> getUserList(QueryParam queryParam);
	
	Page<User> getPageList(QueryParam queryParam);
	
	User signup(String loginName, String passWord, String email);
	
	User signin(String loginName, String passWord);
	
	LoginUser getLoginUser(User user, Long uid);
	
	boolean hasUser(String login_name);
	
	boolean delete(Long uid);
	
	boolean updateStatus(Long uid, Integer status);
	
	boolean resetPwd(String email);

	boolean updateAvatar(Long uid, String avatar_path);

	boolean updatePwd(Long uid, String new_pwd);

	boolean updateRole(Long uid, Integer role_id);
	
	/**
	 * 根据用户主键获取积分
	 * @author WKX
	 * @param uid
	 * @since 2016年11月9日 下午1:33:44
	 */
	public Long getScoreByUid(Long uid);
	
	/**
	 * 获取等级信息（含升级）
	 * @author WKX
	 * @param uid
	 * @since 2016年11月9日 下午2:19:07
	 */
	public Map<String,Object> getCosnameByUid(Long uid);
	
	/**
	 * 根据APP端用户主键获取 BBS用户
	 * @author WKX
	 * @param member_id
	 * @since 2016年11月11日 下午1:53:59
	 */
	public User getUserByMemberId(Long member_id);
	
	/**
	 * 用户注册（来源APP账号）
	 * @author WKX
	 * @param loginName 手机号
	 * @param passWord 密码
	 * @param member_id APP端用户主键
	 * @since 2016年11月11日 下午2:35:26
	 */
	public User save(String loginName, String passWord, Long member_id);
	
	/**
	 * 更新用户名
	 * @author WKX
	 * @param uid 用户主键
	 * @param login_name 用户名
	 * @since 2016年11月21日 上午11:14:23
	 */
	public boolean updateLoginName(Long uid,String login_name);
	
	/**
	 * 获取未配置memberId的用户（实际上也是内部用户）
	 * @author WKX
	 * @since 2016年11月21日 下午3:35:10
	 */
	public List<LoginUser> getUserByNoMemberId();
}