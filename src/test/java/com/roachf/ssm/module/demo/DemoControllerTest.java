package com.roachf.ssm.module.demo;

import org.junit.Test;

import com.roachf.ssm.utils.HttpClientUtils;

public class DemoControllerTest {
	
	private static String requestUrl = "http://localhost:8290/springmvc_mybatis/demo";

	@Test
	public void insert(){
		String param = "{\"key\":\"德国\", \"value\":\"慕尼黑\"}";
		String result = HttpClientUtils.httpPostRequest(requestUrl, param);
		System.out.println(result);
	}
}
