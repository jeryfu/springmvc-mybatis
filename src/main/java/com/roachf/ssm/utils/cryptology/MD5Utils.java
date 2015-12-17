package com.roachf.ssm.utils.cryptology;

import java.security.MessageDigest;

/**
 * MD5 加密工具方法
 * 
 * @author roach
 */
public class MD5Utils {

	/**
	 * 默认UTF-8字符集的加密方法
	 * @param src
	 * @return
	 */
	public static String md5(String src) {
		return md5(src, "UTF-8");
	}

	/**
	 * 加密工具
	 * @param src 			需要加密的数据
	 * @param charset		字符集编码
	 * @return
	 */
	public static String md5(String src, String charset) {
		try {
			StringBuffer buffer = new StringBuffer();
			char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
			byte[] bytes = src.getBytes(charset);
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] targ = md.digest(bytes);
			for (byte b : targ) {
				buffer.append(chars[(b >> 4) & 0x0F]);
				buffer.append(chars[b & 0x0F]);
			}
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(md5("123456"));
	}
}
