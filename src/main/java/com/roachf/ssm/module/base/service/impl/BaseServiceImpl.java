package com.roachf.ssm.module.base.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import com.roachf.ssm.module.base.dao.BaseDao;
import com.roachf.ssm.module.base.service.BaseService;

//@Service("baseService")  baseService baseDao 是不需要扫描注入的, 直接实例化它的子类. 否则会多实例化一次基类
public class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {
	
	private Logger logger = Logger.getLogger(BaseServiceImpl.class);

	private BaseDao<T, PK> baseDao;

	public BaseDao<T, PK> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<T, PK> baseDao) {
		logger.info("set base dao . . . ");
		this.baseDao = baseDao;
	}

	@Override
	public List<T> getList() {
		return baseDao.getList();
	}

	@Override
	public T getInfo(PK id) {
		return baseDao.getInfo(id);
	}

	@Override
	public boolean insert(T entity) {
		return baseDao.insert(entity);
	}

	@Override
	public boolean update(T entity) {
		return baseDao.update(entity);
	}

	@Override
	public boolean delete(PK id) {
		return baseDao.delete(id);
	}

}
