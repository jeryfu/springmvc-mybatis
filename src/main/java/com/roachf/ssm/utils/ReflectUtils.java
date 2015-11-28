package com.roachf.ssm.utils;

import java.lang.reflect.Field;

public class ReflectUtils {

	/**
	 * 设置属性值
	 * @param object 对象
	 * @param fieldName 属性名
	 * @param value 属性值
	 */
	public static void setFieldValue(Object object, String fieldName, Object value) {
		Field field = getDeclaredField(object, fieldName);
		if (field == null){
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		}
		field.setAccessible(true);
		
		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 获取属性值
	 * @param object 对象
	 * @param fieldName 属性名
	 * @return
	 * @throws IllegalAccessException
	 */
	public static Object getFieldValue(Object object, String fieldName) throws IllegalAccessException {
		Field field = getDeclaredField(object, fieldName);
		if (field == null) {
			throw new IllegalAccessException("Could not find field [" + fieldName + "] on target [" + object + "]");
		}
		field.setAccessible(true);
		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取属性值, 向父类递归
	 */
	private static Field getDeclaredField(Object obj, String fieldName) {

		for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				return clazz.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {

			}
		}
		return null;
	}
}
