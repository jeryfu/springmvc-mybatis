package com.roachf.survey.dao;

import com.roachf.survey.pojo.entity.User;

public interface UserDao extends BaseDao<User, Integer> {

	/**
	 * 根据用户邮箱和密码查询用户
	 * @param user
	 * @return
	 */
	User validateUser(User user);

}
