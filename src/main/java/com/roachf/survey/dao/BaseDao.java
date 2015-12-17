package com.roachf.survey.dao;

import java.io.Serializable;

import com.roachf.survey.pojo.entity.Page;

public interface BaseDao<T, PK extends Serializable> {
	/** 获取分页列表 */
	Page<T> getListByPage(Page<T> page, Object parameters);
	
	/** 获取总记录 */
	Long count(Object parameters);
	
	/** 获取详情 */
	T getInfo(PK id);
	
	/** 插入 */
	boolean insert(T entity);
	
	/** 更新 */
	boolean update(T entity);
	
	/** 删除 */
	boolean delete(PK id);
	
}
