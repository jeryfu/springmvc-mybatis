package com.roachf.survey.service;

import com.roachf.survey.pojo.entity.User;

public interface UserService extends BaseService<User, Integer>{

	/**
	 * 根据用户邮箱和密码查询用户
	 * @param user 用户
	 */
	User validateUser(User user);

	/**
	 * 判断邮箱使用已用
	 * @param email
	 * @return boolean : true=已注册; false=为注册
	 */
	boolean isRegisted(String statement, String email);

}
