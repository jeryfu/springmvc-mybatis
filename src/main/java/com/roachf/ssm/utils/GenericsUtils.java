package com.roachf.ssm.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericsUtils {
	/**
	 * 通过反射,获得定义Class时声明的父类的范型参数的类型. 8 * 如public BookManager extends
	 * GenricManager<Book> 9 * 10 * @param clazz The class to introspect 11
	 * * @return the first generic declaration, or <code>Object.class</code> if
	 * cannot be determined 12
	 */
	public static Class getSuperClassGenricType(Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 通过反射,获得定义Class时声明的父类的范型参数的类型. 19 * 如public BookManager extends
	 * GenricManager<Book> 20 * 21 * @param clazz clazz The class to introspect
	 * 22 * @param index the Index of the generic ddeclaration,start from 0. 23
	 */
	public static Class getSuperClassGenricType(Class clazz, int index) throws IndexOutOfBoundsException {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}
}