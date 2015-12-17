package com.roachf.survey.spring.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.roachf.survey.pojo.entity.Demo;
import com.roachf.survey.pojo.entity.Page;
import com.roachf.survey.service.impl.DemoServiceImpl;

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
}
