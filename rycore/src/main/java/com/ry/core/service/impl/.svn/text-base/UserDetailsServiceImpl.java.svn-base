package com.ry.core.service.impl;

import javax.annotation.Resource;

import org.jasig.cas.client.validation.Assertion;
import org.springframework.security.cas.userdetails.AbstractCasAssertionUserDetailsService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ry.core.dao.AdminDao;
import com.ry.core.entity.Admin;

@Service
public class UserDetailsServiceImpl extends AbstractCasAssertionUserDetailsService{

	AdminDao adminDao;

	@Override
	protected UserDetails loadUserDetails(Assertion assertion) {
		UserDetails details = null;
		Object userId = assertion.getPrincipal().getAttributes().get("id");
		if(userId!=null){
			Admin u = adminDao.getAdminById1(Integer.valueOf(userId.toString()));
			if(u==null)return null;
			details = new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(),AuthorityUtils.createAuthorityList("ROLE_USER"));
			return details;
		}
		return null;
	}

	public AdminDao getAdminDao() {
		return adminDao;
	}

	@Resource
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
}