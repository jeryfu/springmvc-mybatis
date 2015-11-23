package com.roachf.ssm.module.demo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.roachf.ssm.module.demo.model.entity.Demo;
import com.roachf.ssm.module.demo.service.impl.DemoServiceImpl;

public class DemoServiceTest {

	ApplicationContext ctx = null;
	DemoServiceImpl demoService = null;
	

	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		demoService = (DemoServiceImpl) ctx.getBean("demoService");
	}

	@Test
	public void listTest() {
		System.out.println(demoService.getList());
	}
	
	@Test
	public void infoTest() {
		System.out.println(demoService.getInfo(1));
	}
	
	@Test
	public void insertTest() {
		Demo demo = new Demo("鸟语", "花香");
		demoService.insert(demo);
		System.out.println(demo);
	}
	
	@Test
	public void deleteTest() {
		System.out.println(demoService.delete(6));
	}
	
	@Test
	public void updateTest() {
		Demo demo = new Demo("鸟语", "花香");
		demo.setId(7);
		System.out.println(demoService.update(demo));
	}
	
	/*
	@Test
	public void saveTest() throws Exception {
		Demo demo = new Demo("鸟语", "花香");
		System.out.println(demoService.save(demo));
	}
	
	@Test
	public void replaceTest() throws Exception {
		Demo demo = new Demo("美丽", "大方");
		demo.setId(13);
		System.out.println(demoService.replace(demo));
	}
	
	@Test
	public void removeTest() {
		System.out.println(demoService.remove(5));
	}
	*/

}
