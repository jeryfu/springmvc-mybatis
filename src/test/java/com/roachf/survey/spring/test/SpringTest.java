package com.roachf.survey.spring.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.roachf.survey.pojo.entity.Demo;
import com.roachf.survey.service.impl.DemoServiceImpl;
import com.roachf.survey.service.impl.SurveyServiceImpl;
import com.roachf.survey.utils.page.Page;

public class SpringTest {

	ApplicationContext ctx = null;
	
	@Before
	public void init(){
		ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	}
	
	@Test
	public void getBean(){
		DemoServiceImpl demoService = (DemoServiceImpl)ctx.getBean("demoService");
		System.out.println(demoService.getListByPage(new Page<Demo>(), null));
	}
	
	@Test
	public void tx(){
		SurveyServiceImpl pc = new SurveyServiceImpl();
		
		System.out.println(AopUtils.isAopProxy(pc));

		System.out.println(AopUtils.isCglibProxy(pc)); //cglib

		System.out.println(AopUtils.isJdkDynamicProxy(pc)); //jdk动态代理
	}
}
