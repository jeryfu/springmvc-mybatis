package com.roachf.survey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roachf.survey.dao.UserDao;
import com.roachf.survey.pojo.entity.User;
import com.roachf.survey.service.UserService;
import com.roachf.survey.utils.cryptology.MD5Utils;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService{

	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
//		super.setBaseDao(userDao);
		this.userDao = userDao;
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	@Override
	public User validateUser(User user) {
		user.setPassword(MD5Utils.md5(user.getPassword()));
		return userDao.validateUser(user);
	}

	@Override
	public boolean isRegisted(String statement, String email) {
		Integer count = (Integer)userDao.getObject(statement, email);
		return count > 0 ? true : false;
	}

}
