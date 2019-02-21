package com.bbs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bbs.Types;
import com.bbs.kit.FileKit;
import com.bbs.kit.NameKit;
import com.bbs.kit.Utils;
import com.bbs.model.LoginUser;
import com.bbs.model.User;
import com.bbs.model.Userinfo;
import com.bbs.model.PointLog.Cosname;
import com.bbs.service.ActivecodeService;
import com.bbs.service.CommentService;
import com.bbs.service.FavoriteService;
import com.bbs.service.NoticeService;
import com.bbs.service.TicketService;
import com.bbs.service.TopicService;
import com.bbs.service.UserService;
import com.bbs.service.UserinfoService;
import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.AR;
import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;

import blade.kit.DateKit;
import blade.kit.EncrypKit;
import blade.kit.StringKit;

@Service
public class UserServiceImpl implements UserService {
	
	@Inject
	private ActivecodeService activecodeService;
	
	@Inject
	private TopicService topicService;
	
	@Inject
	private UserinfoService userinfoService;
	
	@Inject
	private FavoriteService favoriteService;
	
	@Inject
	private CommentService commentService;
	
	@Inject
	private NoticeService noticeService;
	
	@Inject
	private TicketService ticketService;
	
	@Override
	public User getUser(Long uid) {
		return AR.findById(User.class, uid);
	}
	
	@Override
	public User getUser(QueryParam queryParam) {
		return AR.find(queryParam).first(User.class);
	}
		
	@Override
	public List<User> getUserList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).list(User.class);
		}
		return null;
	}
	
	@Override
	public Page<User> getPageList(QueryParam queryParam) {
		if(null != queryParam){
			return AR.find(queryParam).page(User.class);
		}
		return null;
	}
	
	@Override
	public User signup(String loginName, String passWord, String email) {
		if(StringKit.isBlank(loginName) || StringKit.isBlank(passWord) || StringKit.isBlank(email)){
			return null;
		}
		
		User user = this.getUserByLoginName(loginName);
		if(null != user){
			return user;
		}
		
		user = this.getUserByEmail(email);
		if(null != user){
			return user;
		}
		
		int time = DateKit.getCurrentUnixTime();
		String pwd = EncrypKit.md5(passWord);//只对密码加密
		try {
			Long uid = (Long) AR.update("insert into t_user(login_name, pass_word, email, avatar, status, create_time, update_time) values(?, ?, ?, ?, ?, ?, ?)",
					loginName, pwd, email, null, 0, time, time).key();
			
			user = this.getUser(uid);
			
			// 发送邮件通知
			activecodeService.save(user, Types.signup.toString());
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean delete(Long uid) {
		if(null != uid){
			AR.update("delete from t_user where uid = ?", uid).executeUpdate();
			return true;
		}
		return false;
	}

	@Override
	public boolean resetPwd(String email) {
		
		return false;
	}

	@Override
	public User signin(String loginName, String passWord) {
		if(StringKit.isBlank(loginName) || StringKit.isBlank(passWord)){
			return null;
		}
		String pwd = EncrypKit.md5(passWord);//只对密码加密
	    User user = AR.find("select * from t_user where login_name = ? and pass_word = ? and status in (0, 1)",
				loginName, pwd).first(User.class);
	    if(null == user){
	    	user = AR.find("select * from t_user where email = ? and pass_word = ? and status in (0, 1)",
					loginName, pwd).first(User.class);
	    }
		return user;
	}

	@Override
	public Map<String, Object> getUserDetail(Long uid) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(null != uid){
			User user = this.getUser(uid);
			if(null == user){
				return map;
			}
			map.put("username", user.getLogin_name());
			map.put("uid", uid);
			map.put("email", user.getEmail());
			
			String avatar = Utils.getPhoto(user.getAvatar(),"user");
			map.put("avatar", avatar);
			map.put("create_time", user.getCreate_time());
			
			Userinfo userinfo = userinfoService.getUserinfo(uid);
			if(null != userinfo){
				map.put("jobs", userinfo.getJobs());
				map.put("github", userinfo.getGithub());
				map.put("weibo", userinfo.getWeibo());
				map.put("nick_name", userinfo.getNick_name());
				map.put("location", userinfo.getLocation());
				map.put("signature", userinfo.getSignature());
				map.put("web_site", userinfo.getWeb_site());
				map.put("instructions", userinfo.getInstructions());
				
				map.put("name", userinfo.getName());
				map.put("company", userinfo.getCompany());
				map.put("phone", userinfo.getPhone());
				map.put("wechat", userinfo.getWechat());
				map.put("qq", userinfo.getQq());
			}
		}
		return map;
	}
	
	@Override
	public boolean updateStatus(Long uid, Integer status) {
		if(null != uid && null != status){
			try {
				AR.update("update t_user set status = ? where uid = ?", status, uid).executeUpdate(true);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean updateAvatar(Long uid, String avatar_path) {
		try {
			if(null == uid || StringKit.isBlank(avatar_path)){
				return false;
			}
			User user = this.getUser(uid);
			if(null == user){
				return false;
			}
			String path = FileKit.tempToUpload(avatar_path);//移动图片
			if(StringKit.isNotBlank(path)){
				AR.update("update t_user set avatar = ? where uid = ?", path, uid).executeUpdate();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean updatePwd(Long uid, String newpwd) {
		try {	
			if(null == uid || StringKit.isBlank(newpwd)){
				return false;
			}
			AR.update("update t_user set pass_word = ? where uid = ?", newpwd, uid).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public LoginUser getLoginUser(User user, Long uid) {
		if(null == user){
			user = this.getUser(uid);
		}
		if(null != user){
			LoginUser loginUser = new LoginUser();
			loginUser.setUid(user.getUid());
			loginUser.setUser_name(user.getLogin_name());
			loginUser.setPass_word(user.getPass_word());
			loginUser.setStatus(user.getStatus());
			loginUser.setRole_id(user.getRole_id());
			String avatar = Utils.getPhoto(user.getAvatar(),"user");
			loginUser.setAvatar(avatar);
			
			Long comments = commentService.getComments(user.getUid());
			loginUser.setComments(comments);
			
			Long topics = topicService.getTopics(user.getUid());
			loginUser.setTopics(topics);
			
			Long notices = noticeService.getNotices(user.getUid());
			loginUser.setNotices(notices);
			
			Userinfo userinfo = userinfoService.getUserinfo(user.getUid());
			if(null != userinfo){
				loginUser.setJobs(userinfo.getJobs());
				loginUser.setNick_name(userinfo.getNick_name());
			}
			
			Long my_topics = favoriteService.favorites(Types.topic.toString(), user.getUid());
			Long my_nodes = favoriteService.favorites(Types.node.toString(), user.getUid());
			
			loginUser.setMy_topics(my_topics);
			loginUser.setMy_nodes(my_nodes);
			
			Long following = favoriteService.favorites(Types.following.toString(), user.getUid());
			loginUser.setFollowing(following);
			
			return loginUser;
		}
		return null;
	}

	@Override
	public boolean hasUser(String login_name) {
		if(StringKit.isNotBlank(login_name)){
			Long count = AR.find("select count(1) from t_user where login_name = ? and status in (0, 1)", login_name).first(Long.class);
			if(count == 0){
				count = AR.find("select count(1) from t_user where email = ? and status in (0, 1)", login_name).first(Long.class);
			}
			return count > 0;
		}
		return false;
	}

	@Override
	public User getUserByLoginName(String user_name) {
		if(StringKit.isNotBlank(user_name)){
			return AR.find("select * from t_user where login_name = ? and status = 1", user_name).first(User.class);
		}
		return null;
	}
	
	public User getUserByEmail(String email) {
		if(StringKit.isNotBlank(email)){
			return AR.find("select * from t_user where email = ? and status = 1", email).first(User.class);
		}
		return null;
	}

	@Override
	public boolean updateRole(Long uid, Integer role_id) {
		try {
			if(null == uid || null == role_id || role_id == 1){
				return false;
			}
			AR.update("update t_user set role_id = ? where uid = ?", role_id, uid).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Long getScoreByUid(Long uid) {
		if(null != uid){
			return AR.find("SELECT SUM(pl.score)score FROM t_pointlog pl WHERE pl.uid=?", uid).first(Long.class);
		}
		return 0L;
	}
	
	@Override
	public Map<String,Object> getCosnameByUid(Long uid){
		Map<String,Object> result = new HashMap<String, Object>();
		Long score = this.getScoreByUid(uid);
		if(score==null)score = 0L;
		result.put("score", score);
		
		Cosname current = Cosname.getCurrent(score);
		Cosname next = Cosname.getNext(current);
		result.put("current", current);
		result.put("next", next);
		
		result.put("progress", (Double.valueOf(score*100)/Double.valueOf(current.getMax())));
		result.put("tonext", next.getMin()-score);
		return result;
	}
	
	@Override
	public User getUserByMemberId(Long member_id) {
		if(member_id!=null){
			return AR.find("select * from t_user where member_id = ? and status = 1", member_id).first(User.class);
		}
		return null;
	}
	
	@Override
	public User save(String loginName, String passWord, Long member_id) {
		if(StringKit.isBlank(loginName) || StringKit.isBlank(passWord) || member_id==null){
			return null;
		}
		User user = this.getUserByLoginName(loginName);
		if(null != user){
			return user;
		}
		user = this.getUserByMemberId(member_id);
		if(null != user){
			return user;
		}
		
		int time = DateKit.getCurrentUnixTime();
		loginName = this.randomName(loginName);
		String pwd = EncrypKit.md5(passWord);//只对密码加密
		try {
			Long uid = (Long) AR.update("insert into t_user(login_name, pass_word, email, avatar, status, create_time, update_time, member_id) values(?, ?, ?, ?, ?, ?, ?, ?)",
					loginName, pwd, null, null, 1, time, time, member_id).key();
			
			ticketService.save(uid,0);//注册赠送奖券
			userinfoService.save(uid,loginName);//用户基本信息
			user = this.getUser(uid);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 生成用户名
	 * @author WKX
	 * @param phone 手机号
	 * @since 2016年11月15日 下午3:36:02
	 */
	public String randomName(String phone) {
		String temp = NameKit.randomName(phone);
		if(this.hasUser(temp)){
			return this.randomName(phone);
		}
		return temp;
	}
	
	@Override
	public boolean updateLoginName(Long uid,String login_name) {
		try {
			if(null == uid || StringKit.isBlank(login_name)){
				return false;
			}
			User user = this.getUser(uid);
			if(null == user){
				return false;
			}
			AR.update("update t_user set login_name=? where uid = ?",login_name, uid).executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<LoginUser> getUserByNoMemberId() {
		return AR.find("SELECT u.uid,u.login_name user_name,info.nick_name FROM t_user u LEFT JOIN t_userinfo info ON u.uid=info.uid WHERE member_id IS NULL AND STATUS = 1").list(LoginUser.class);
	}
}