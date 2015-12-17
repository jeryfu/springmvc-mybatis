package com.roachf.ssm.spring.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.roachf.ssm.module.demo.model.entity.Demo;
import com.roachf.ssm.module.demo.service.impl.DemoServiceImpl;
import com.roachf.ssm.pojo.entity.Page;

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
