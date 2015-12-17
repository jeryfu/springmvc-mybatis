package com.roachf.survey.utils;

/**
 * 表情的工具类
 * 
 * @author roach
 * 
 * @see (1). 表情都是Unicode编码的, 手机直接支持显示Unicode, 会自动转换成表情
 * @see (2). web必须以图片的形式显示, 其实mac下可以直接显示的. 但是我们要兼容所有的.
 * @see (3). 一种方案就是转换, 前端将表情转换成特定的形式. 例如：[/cry] 这种形式. 这需要前端和后台约定所有的表情
 * @see (4). 手机的输入法也自带表情, 这样上面的一种方式就不行了. 所以就直接存呗.
 * @see (5). MYSQL保存的Unicode表情都是些【？？？】这种, 这可读性什么的都不好, 肯定是不建议这个弄的
 * @see (6). 什么叫柳暗花明又一村, 经过验证手机和输入法自带的表情都是emoji表情, 所以明显是可以统一的, 所以(3)的方案是可行的.
 * @see (7). 有个问题就是, 要是增加了emoji表情怎么办. 放心, 手机自带表情是修改不了了, 输入法想增加表情也是要等emoji新增,
 *      那我们只要在出现新的emoji之后, 生成新的匹配就行了.
 * @see (8). (5)中说过形如 \ud83c\udc00 这种Unicode编码存储在mysql中的可读性是非常的差的, 所以我们可以做下转换.
 *      这个工具类就应运而生.
 */
public class EmotionUtils {

	/**
	 * 场景一
	 * <p>
	 * 前端将表情全部修改为一种特定的形式：例如[/cry]. 其实也可以用自定义的标签：例如<emoji>#cry#</emoji>
	 */

	private static final String separate = "/";

	/**
	 * 加密文本 String 转 byte, 封装成String
	 * 
	 * @param content
	 * @return
	 */
	public static String encode(String content) {
		StringBuffer sb = null;
		try {
			byte bytes[] = content.getBytes("UTF-8");
			sb = new StringBuffer(3000);
			for (int i = 0; i < bytes.length; i++) {
				sb.append(bytes[i]).append(separate);
			}
		} catch (Exception e) {
			return null;
		}
		return sb.toString();
	}

	/**
	 * 解码文本
	 * 
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static String decode(String content) throws Exception {
		// 转换
		byte[] result = new byte[content.getBytes().length];
		String contentArray[] = content.split(separate);
		for (int i = 0; i < contentArray.length; i++) {
			result[i] = new Byte(contentArray[i]);
		}

		content = new String(result, "utf-8");
		return content;
	}

}
