package com.roachf.survey.pojo.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class User implements Serializable {

	private static final long serialVersionUID = 1846318303193302509L;

	private Integer id;

	@NotBlank
	@Email(message = "邮箱格式有误")
	private String email;

	/** 用户昵称: 由1-6个中文、数字或字母组成 */
	@NotBlank
	//@Pattern(regexp = "^[a-zA-Z0-9\u4e00-\u9fa5]{1,6}$", message = "由1-6个中文、数字或字母组成")
	private String nickname;

	/** 用户密码：由6-16位数字或字母组成 */
	@NotBlank
	//@Pattern(regexp = "^[a-zA-Z0-9]{6,16}$", message = "由6-16位数字或字母组成")
	private String password;

	/** 注册时间 */
	private Date registerDate = new Date();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@Override
	public String toString() {
		return org.apache.commons.lang.builder.ReflectionToStringBuilder.toString(this);
	}

}
