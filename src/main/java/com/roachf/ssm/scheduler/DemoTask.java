package com.roachf.ssm.scheduler;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * spring task demo
 * @author roach
 */
public class DemoTask {

	/**
	 * 每天下午三点执行
	 */
	@Scheduled(cron="0 0 15 * * ?")
	public void hello(){
		System.out.println(Thread.currentThread().getName() + ", hello spring task . . . ");
	}
}
