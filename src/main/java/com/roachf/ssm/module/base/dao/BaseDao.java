package com.roachf.ssm.module.base.dao;

import java.io.Serializable;
import java.util.List;

import com.roachf.ssm.pojo.entity.Page;

public interface BaseDao<T, PK extends Serializable> {

	/** 获取所有列表 */
	List<T> getList();
	
	/** 获取分页列表 */
	List<T> getListByPage(Object parameters, Page page);
	
	/** 获取详情 */
	T getInfo(PK id);
	
	/** 插入 */
	boolean insert(T entity);
	
	/** 更新 */
	boolean update(T entity);
	
	/** 删除 */
	boolean delete(PK id);
	
	
	//======================= 利用反射自动化的增删改查 ================//
	
	/** 反射, 获取所有列表 */
	List<T> findList();
	
	/** 反射, 获取所有详情 */
	T findInfo(PK id);
	
	/** 反射, 保存 */
	Object save(T entity) throws Exception;
	
	/** 反射, 更新 */
	boolean replace(T entity) throws Exception;
	
	/** 反射, 删除 */
	boolean remove(PK id);
	
}
