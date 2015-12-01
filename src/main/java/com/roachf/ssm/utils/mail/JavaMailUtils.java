package com.roachf.ssm.utils.mail;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class JavaMailUtils {
	
	/**
	 * 发送文本邮件
	 * @param javaMail
	 * @return
	 */
	public static boolean sendTextMail(JavaMail javaMail){
		return sendMail(javaMail, false);
	}
	
	/**
	 * 发送HTML邮件
	 * @param javaMail
	 * @return
	 */
	public static boolean sendHtmlMail(JavaMail javaMail){
		return sendMail(javaMail, true);
	}
	
	private static boolean sendMail(final JavaMail javaMail, boolean isHtml){
		
		boolean flag = true;
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", javaMail.getHost());
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(javaMail.getUsername(), javaMail.getPassword());
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(javaMail.getUsername()));
			
			// 接收邮件邮箱： 只可发送单个用户
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(javaMail.getToMail()));
			
			// 抄送邮箱: 可抄送多个用户 
			if (javaMail.getCcMails() != null && javaMail.getCcMails().length > 0) {
				StringBuffer cc = new StringBuffer(javaMail.getCcMails()[0]);
				for (int i = 1; i < javaMail.getCcMails().length; i++) {
					cc.append(",").append(javaMail.getCcMails()[i]);
				}
				message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc.toString()));
			}
			// 密送邮箱：可密送多个用户 
			if (javaMail.getBccMails() != null && javaMail.getBccMails().length > 0) {
				StringBuffer bcc = new StringBuffer(javaMail.getBccMails()[0]);
				for (int i = 1; i < javaMail.getBccMails().length; i++) {
					bcc.append(",").append(javaMail.getBccMails()[i]);
				}
				message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc.toString()));
			}
			
			// 设置邮箱主题 
			message.setSubject(javaMail.getSubject());
			
			Multipart multipart = new MimeMultipart();
			
			/* 第一部分： 设置邮件body正文 */
			BodyPart bodyPart = new MimeBodyPart();
			if(isHtml){
				bodyPart.setContent(javaMail.getContent(), "text/html;charset=UTF-8");
			}else{
				bodyPart.setText(javaMail.getContent());;
			}
			multipart.addBodyPart(bodyPart);
			
			/* 第二部分： 设置邮件附件 */
			if(javaMail.getAttachments() != null && javaMail.getAttachments().length > 0){
				for (int i = 0; i < javaMail.getAttachments().length; i++) {
					bodyPart = new MimeBodyPart();
			        String filename = javaMail.getAttachments()[i];
			        DataSource source = new FileDataSource(filename);
			        bodyPart.setDataHandler(new DataHandler(source));
			        bodyPart.setFileName(source.getName());
			        multipart.addBodyPart(bodyPart);
				}
			}
			
			message.setContent(multipart);
			Transport.send(message);

		} catch (MessagingException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	
	private static String html(){
		return "<!DOCTYPE html>" +
			"<html lang='en'>" +
			"<head>" +
			    "<meta charset='UTF-8'>" +
			    "<title>test</title>" +
			"</head>" +
			"<body>" +
			    "<div style='color: red; text-align: center'>" +
			        "<h1>Hello World</h1>" +
			    "</div>" +
			"</body>" +
			"</html>";
	}
	
	public static void main(String[] args) {
		JavaMail javamail = new JavaMail();
		javamail.setSubject("test");
		javamail.setContent(html());
		javamail.setToMail("456789@qq.com");
		javamail.setAttachments(new String[]{"D:\\123456.docx","D:\\BugReport.txt"});
		System.out.println(sendMail(javamail, true));
	}
}
