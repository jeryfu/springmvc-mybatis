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
import com.roachf.survey.pojo.entity.Page;

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
	public Page<T> getListByPage(Page<T> page, Object parameters) {
		List<T> list = getSqlSession().selectList(this.namespace + ".list", parameters,
				new RowBounds(page.getPageStart(), page.getPageSize()));
		page.setList(list);
		page.setTotalCount(count(parameters));
		return page;
	}
	
	@Override
	public Long count(Object parameters) {
		 return getSqlSession().selectOne(this.namespace + ".count", parameters);
	}

	public List<T> getList(Object parameter) {
		return getSqlSession().selectList(this.namespace, parameter);
	}

	@Override
	public T getInfo(PK id) {
		return getSqlSession().selectOne(this.namespace + ".info", id);
	}

	@Override
	public boolean insert(T entity) {
		return getSqlSession().insert(this.namespace + ".insert", entity) > 0;
	}

	@Override
	public boolean update(T entity) {
		return getSqlSession().update(this.namespace + ".update", entity) > 0;
	}

	@Override
	public boolean delete(PK id) {
		return getSqlSession().delete(this.namespace + ".delete", id) > 0;
	}

	/**
	 * 获取sqlSession
	 * @return
	 */
	private SqlSession getSqlSession() {
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
