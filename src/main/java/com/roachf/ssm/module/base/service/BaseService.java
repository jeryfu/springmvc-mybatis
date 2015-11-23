package com.roachf.ssm.module.base.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T, PK extends Serializable> {
	/** 获取所有列表 */
	List<T> getList();
	
	/** 获取详情 */
	T getInfo(PK id);
	
	/** 插入 */
	boolean insert(T entity);
	
	/** 更新 */
	boolean update(T entity);
	
	/** 删除 */
	boolean delete(PK id);
}
