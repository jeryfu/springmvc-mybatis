package com.roachf.ssm.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

/**
 * 服务端模拟客户端发送请求工具
 * 
 * @author roach
 *
 */
public class HttpClientUtils {

	private static final String UTF_8 = "UTF-8";
	

	/**
	 * POST 请求
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param params
	 *            请求参数
	 */
	public static String httpPostRequest(String requestUrl, String params) {
		PostMethod post = new PostMethod(requestUrl);
		return returnResult(post, params, UTF_8);
	}

	/**
	 * POST 请求
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param charset
	 *            字符集
	 */
	public static String httpPostRequest(String requestUrl, String params, String charset) {
		PostMethod post = new PostMethod(requestUrl);
		return returnResult(post, params, charset);
	}

	/**
	 * GET 方法
	 * 
	 * @param requestUrl
	 * @return
	 */
	public static String httpGetRequest(String requestUrl) {
		return httpGetRequest(requestUrl, UTF_8);
	}

	/**
	 * GET 方法
	 * 
	 * @param requestUrl
	 * @return
	 */
	public static String httpGetRequest(String requestUrl, String charset) {
		GetMethod get = new GetMethod(requestUrl);
		return returnResult(get, charset);
	}

	/**
	 * PUT 请求
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param params
	 *            请求参数
	 */
	public static String httpPutRequest(String requestUrl, String params) {
		return httpPutRequest(requestUrl, params, UTF_8);
	}

	/**
	 * PUT 请求
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param charset
	 *            字符集
	 */
	public static String httpPutRequest(String requestUrl, String params, String charset) {
		PutMethod put = new PutMethod(requestUrl);
		return returnResult(put, params, charset);
	}

	/**
	 * Delete 方法
	 * 
	 * @param requestUrl
	 * @return
	 */
	public static String httpDeleteRequest(String requestUrl) {
		return httpDeleteRequest(requestUrl, UTF_8);
	}

	/**
	 * Delete 方法
	 * 
	 * @param requestUrl
	 * @return
	 */
	public static String httpDeleteRequest(String requestUrl, String charset) {
		DeleteMethod delete = new DeleteMethod(requestUrl);
		return returnResult(delete, charset);
	}

	/**
	 * 抽离封装POST 和 PUT 请求的公共代码
	 * 
	 * @param requestMethod
	 * @param params
	 * @param charset
	 * @return
	 */
	private static String returnResult(EntityEnclosingMethod requestMethod, String params, String charset) {

		String result = null;
		try {
			requestMethod.setRequestHeader("Content-Type", "application/json");
			if (params != null) {
				requestMethod.setRequestEntity(new StringRequestEntity(params, "application/json", charset));
			}

			HttpClient client = new HttpClient();
			int statusCode = client.executeMethod(requestMethod);
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			byte[] bytes = readInputStream(requestMethod.getResponseBodyAsStream());
			result = new String(bytes, charset);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			requestMethod.releaseConnection();
		}
		return result;
	}

	/**
	 * 抽离封装出GET, DELETE公共代码
	 * 
	 * @param requestMethod
	 * @param charset
	 * @return
	 */
	private static String returnResult(HttpMethodBase requestMethod, String charset) {

		requestMethod.setRequestHeader("Content-Type", "application/json");

		String result = null;
		try {
			HttpClient client = new HttpClient();
			int statusCode = client.executeMethod(requestMethod);
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			byte[] bytes = readInputStream(requestMethod.getResponseBodyAsStream());
			result = new String(bytes, charset);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			requestMethod.releaseConnection();
		}
		return result;
	}

	private static byte[] readInputStream(InputStream in) throws IOException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int len = 0;
		while ((len = in.read(b)) != -1) {
			out.write(b, 0, len);
		}
		byte[] array = out.toByteArray();

		out.flush();
		out.close();
		in.close();
		return array;

	}

	public static void main(String[] args) {
		

		String url = "http://localhost:8080/springMVC_study/users";

		// 获取列表
		System.out.println(httpGetRequest(url));

		// 获取1001用户详情
		//System.out.println(httpGetRequest(url + "/1001"));

		// 删除1001
		//System.out.println(httpDeleteRequest(url + "/1001"));

		// 增加一个用户
		//String user = "{\"name\":\"lim\", \"email\":\"lim@qq.com\", \"birth\":\"2015-11-03\"}";
		//System.out.println(httpPostRequest(url, user));

		// 修改1004用户
		//String user4 = "{\"id\":1004, \"name\":\"lim\", \"email\":\"lim@qq.com\", \"birth\":\"2015-11-03\"}";
		//System.out.println(httpPutRequest(url, user4));
		
	}
	
	public enum HttpMethod{
		POST, GET, PUT, DELETE
	}
}
