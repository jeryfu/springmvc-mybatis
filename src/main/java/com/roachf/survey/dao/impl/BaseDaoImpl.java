package com.roachf.survey.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.roachf.survey.dao.BaseDao;
import com.roachf.survey.utils.page.Page;

public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {

	private Logger logger = Logger.getLogger(BaseDaoImpl.class);

	private SqlSessionFactory sqlSessionFactory;

	private Class<T> classEntity;

	private String namespace;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		this.classEntity = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		this.namespace = this.classEntity.getSimpleName().toLowerCase();
		logger.info("namespace==" + namespace);
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public Page<T> getListByPage(Page<T> page, Object parameter) {
		List<T> list = getSqlSession().selectList(this.namespace + ".list", parameter,
				new RowBounds(page.getPageStart(), page.getPageSize()));
		page.setList(list);
		page.setTotalCount(count(parameter));
		return page;
	}
	
	@Override
	public List<T> getList(Object parameter) {
		return getList("list", parameter);
	}
	
	@Override
	public List<T> getList(String statement, Object parameter) {
		return getSqlSession().selectList(this.namespace + "." + statement, parameter);
	}
	
	@Override
	public Long count(Object parameters) {
		 return getSqlSession().selectOne(this.namespace + ".count", parameters);
	}
	
	@Override
	public T getInfo(Object parameter) {
		return getSqlSession().selectOne(this.namespace + ".info", parameter);
	}
	
	@Override
	public Object getObject(String statement, Object parameter) {
		return getSqlSession().selectOne(this.getNamespace() + "." + statement, parameter);
	}

	@Override
	public boolean insert(T entity) {
		int flag =  getSqlSession().insert(this.namespace + ".insert", entity);
		logger.info("entity==" + entity);
		return flag > 0;
	}

	@Override
	public boolean update(T entity) {
		return update("update", entity);
	}
	
	@Override
	public boolean update(String statement, Object parameter) {
		return getSqlSession().update(this.namespace + "." + statement, parameter) > 0;
	}

	@Override
	public boolean delete(Object parameter) {
		return delete("delete", parameter);
	}
	
	@Override
	public boolean delete(String statement, Object parameter) {
		return getSqlSession().delete(this.namespace + "." + statement, parameter) > 0;
	}

	/**
	 * 获取sqlSession
	 * @return
	 */
	public SqlSession getSqlSession() {
		return getSqlSessionFactory().openSession();
	}

	public Class<T> getClassEntity() {
		return classEntity;
	}

	public void setClassEntity(Class<T> classEntity) {
		this.classEntity = classEntity;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

}
