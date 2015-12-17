package com.roachf.survey.utils.mail;

import java.io.Serializable;

public class JavaMail implements Serializable{

	private static final long serialVersionUID = 5753846197635578474L;

	/** 邮件用户, 即发件人的邮箱 */
	private String username;
	
	/** 发件人邮箱密码 */
	private String password;
	
	/** 所使用的邮箱服务 */
	private String host;
	
	/** 邮件接收用户 */
	private String toMail;
	
	/** 抄送邮箱用户 (carbon copy) */
	private String[] ccMails;
	
	/** 密送邮箱用户 blind carbon copy) */
	private String[] bccMails;
	
	/** 邮件主题 */
	private String subject;
	
	/** 邮件内容 */
	private String content;
	
	/** 附件地址 */
	private String[] attachments;
	
	public JavaMail() {
		this.username = "123456@qq.com";
		this.password = "123456";
		this.host = "smtp.qq.com";
	}
	
	public JavaMail(String username, String password, String host){
		this.username = username;
		this.password = password;
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getToMail() {
		return toMail;
	}

	public void setToMail(String toMail) {
		this.toMail = toMail;
	}

	public String[] getCcMails() {
		return ccMails;
	}

	public void setCcMails(String[] ccMails) {
		this.ccMails = ccMails;
	}

	public String[] getBccMails() {
		return bccMails;
	}

	public void setBccMails(String[] bccMails) {
		this.bccMails = bccMails;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[] getAttachments() {
		return attachments;
	}

	public void setAttachments(String[] attachments) {
		this.attachments = attachments;
	}

}
