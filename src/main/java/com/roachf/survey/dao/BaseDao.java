package com.roachf.survey.dao;

import java.io.Serializable;
import java.util.List;

import com.roachf.survey.utils.page.Page;

public interface BaseDao<T, PK extends Serializable> {
	/** 获取分页列表 */
	Page<T> getListByPage(Page<T> page, Object parameter);
	
	/** 获取所有列表, 默认statement */
	List<T> getList(Object parameter);
	
	/** 自定义statement */
	List<T> getList(String statement, Object parameter);
	
	/** 获取总记录 */
	Long count(Object parameter);
	
	/** 获取详情 */
	T getInfo(Object parameter);
	
	/** 获取整型 */
	Object getObject(String statement, Object parameter);
	
	/** 插入 */
	boolean insert(T entity);
	
	/** 更新:statement默认为 update */
	boolean update(T entity);
	
	/** 更新 */
	boolean update(String statment, Object parameter);
	
	/** 删除：statement默认 delete */
	boolean delete(Object parameter);
	
	/** 删除 */
	boolean delete(String statement, Object parameter);
	
}
