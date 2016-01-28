package com.roachf.survey.dao.impl;

import org.springframework.stereotype.Repository;

import com.roachf.survey.dao.UserDao;
import com.roachf.survey.pojo.entity.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao{

	@Override
	public User validateUser(User user) {
		return this.getSqlSession().selectOne(this.getNamespace() + ".validateUser", user);
	}

}
