package com.roachf.survey.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roachf.survey.pojo.annotation.NoLogin;
import com.roachf.survey.pojo.entity.User;
import com.roachf.survey.service.UserService;
import com.roachf.survey.utils.SurveyConstants;
import com.roachf.survey.utils.cryptology.MD5Utils;

/**
 * 登录注册
 * @author roach
 */

@Controller
@RequestMapping("sign")
public class SignController {
	
	private Logger logger = Logger.getLogger(SignController.class);
	
	private UserService userService;

	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public UserService getUserService() {
		return userService;
	}

	@NoLogin
	@RequestMapping(value="tologin", method=RequestMethod.GET)
	public String toLogin(){
		return "index";
	}
	
	/**
	 * 登录
	 */
	@NoLogin
	@RequestMapping(value="/in", method=RequestMethod.POST)
	public String in(User user, HttpServletRequest request){
		
		/* 验证账号是否正确 */
		user = userService.validateUser(user);
		logger.info("user==" + user);
		if(user == null){
			request.setAttribute("errors", "用户名或密码错误！");
		}else{
			logger.info("nickname==" + user.getNickname());
			request.getSession().setAttribute(SurveyConstants.LOGIN_SESSION_NAME, user);
		}
		return "index";
	}
	
	
	/**
	 * 跳转到注册页面
	 */
	@NoLogin
	@RequestMapping(method=RequestMethod.GET)
	public String toReg(Model model){
		model.addAttribute("user", new User());
		return "sign/register";
	}
	
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	@NoLogin
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(@Valid User user, Errors errors, String rePwd,  Model model){
		System.out.println("rePwd==" + rePwd);
		
		/* (1).表单验证 */
		if(errors.hasFieldErrors()){
			for(FieldError error : errors.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			return "sign/register";
		}
		/* (2).重复密码验证 */
		if(!user.getPassword().equals(rePwd)){
			model.addAttribute("rePwdError", "两次密码不相同");
			return "sign/register";
		}
		/* (3).判断邮箱是否已注册 */
		boolean flag = userService.isRegisted("isReg", user.getEmail());
		if(flag){
			model.addAttribute("regError", "邮箱已被占用");
			return "sign/register";
		}
		
		/* (4).注册*/
		user.setPassword(MD5Utils.md5(user.getPassword()));
		flag = userService.insert(user);
		logger.info("flag==" + flag);
		if(flag){
			return "index";
		}else{
			model.addAttribute("regError", "注册失败");
			return "sign/register";
		}
	}
	
}
