package com.roachf.survey.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.roachf.survey.pojo.annotation.NoLogin;
import com.roachf.survey.pojo.entity.User;
import com.roachf.survey.utils.SurveyConstants;
import com.roachf.survey.web.UserAware;

public class LoginInterceptor implements HandlerInterceptor{
	
	private Logger logger = Logger.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		logger.info("url==" + request.getRequestURI() + ", method==" + request.getMethod());
		
		// 放过静态资源
		if(!(handler instanceof HandlerMethod)){
			return true;
		}
		/* (1). 判断是否需要登录才能操作*/
		HandlerMethod hm = (HandlerMethod) handler;
		NoLogin noLogin = hm.getMethodAnnotation(NoLogin.class);
		if(noLogin != null){// 不需要登录
			return true;
		}
		
		/* (2). 用户需要登录*/
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SurveyConstants.LOGIN_SESSION_NAME);
		// 如果用户已登录, 放过
		if(user != null){
			if(hm.getBean() != null && hm.getBean() instanceof UserAware){
				// 注入user
				((UserAware) hm.getBean()).setUser(user);
			}
			return true;
		}
		
		request.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);;
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		logger.info("postHandle. . . ");
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		logger.info("afterCompletion. . . ");
	}

}
