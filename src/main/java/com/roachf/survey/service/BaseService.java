package com.roachf.survey.service;

import java.io.Serializable;

import com.roachf.survey.utils.page.Page;

public interface BaseService<T, PK extends Serializable> {

	/** 获取所有列表 */
	Page<T> getListByPage(Page<T> page, Object parameter);

	/** 获取详情 */
	T getInfo(PK id);
	
	/** 获取详情 */
	T getInfo(Object parameter);

	/** 插入 */
	boolean insert(T entity);

	/** 更新 */
	boolean update(T entity);

	/** 删除 */
	boolean delete(PK id);
}
