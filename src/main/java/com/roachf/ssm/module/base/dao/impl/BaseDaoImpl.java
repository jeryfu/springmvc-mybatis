package com.roachf.ssm.module.base.dao.impl;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.roachf.ssm.module.base.dao.BaseDao;
import com.roachf.ssm.pojo.annotation.Column;
import com.roachf.ssm.pojo.annotation.Id;
import com.roachf.ssm.pojo.annotation.Table;
import com.roachf.ssm.pojo.entity.Page;

//@Repository("baseDao")
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

	/********************************** 就是这么华丽的分隔线 **********************************/

	@Override
	public List<T> findList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T findInfo(PK id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 新增保存一条记录
	 * <p>
	 * 新增，我们需要知道表名，字段名，以及字段对应的值
	 * <p>
	 * 利用 @Table 判断表名; 利用 @Column 判断字段名; 利用 @Id 判断主键
	 * <p>
	 * 
	 */
	@Override
	public Object save(T entity) throws IllegalArgumentException, IllegalAccessException {

		// sql = insert into demo ('d_key', 'd_value') values ( '', '');
		String tableName = getTableName();

		Field[] fields = this.classEntity.getDeclaredFields();
		// 字段名以逗号分隔
		StringBuffer columns = new StringBuffer(128);
		// 字段对应的值, 以逗号分隔
		StringBuffer values = new StringBuffer(128);

		Map<String, Object> parameter = new HashMap<String, Object>();
		int flag = 0;
		for (int i = 0; i < fields.length; i++) {
			// 必须开放权限, 否则获取不到属性对应的值
			fields[i].setAccessible(true);

			// 判读属性是否定义get(), set()方法
			boolean hasMethod = validateField(fields[i]);
			if (hasMethod && fields[i].get(entity) != null) {
				String columnName = getColumnName(fields[i]);
				if (flag++ > 0) {
					columns.append(", ");
					values.append(", ");
				}
				columns.append("`").append(columnName).append("`");
				values.append("#{").append(fields[i].getName()).append("}");
				parameter.put(fields[i].getName(), fields[i].get(entity));
			}
		}
		logger.info("insert into " + tableName + " ( " + columns.toString() + ") values ( " + values.toString() + " )");

		parameter.put("TABLE_NAME", tableName);
		parameter.put("COLUMNS", columns.toString());
		parameter.put("VALUES", values.toString());
		// 返回的主键值会封装在parameter中
		getSqlSession().insert("base.save", parameter);

		return parameter.get("id");
	}

	/**
	 * 根据id更新数据
	 * 
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	@Override
	public boolean replace(T entity)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		// update tableName set `key` = #{key}, `value` = #{value} where id =
		// #{id}

		String tableName = getTableName();
		String pkName = getPkName();

		StringBuffer keyValues = new StringBuffer();
		Map<String, Object> parameter = new HashMap<String, Object>();
		int flag = 0;
		Field[] fields = this.classEntity.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			boolean hasMethod = validateField(field);

			/* ①. 属性要有getter, setter方法; ②. 属性要有值; ③. 属性上要有 @Column 注解 */
			if (hasMethod && field.get(entity) != null && field.getAnnotation(Column.class) != null) {
				if (flag++ > 0) {
					keyValues.append(", ");
				}
				keyValues.append("`").append(field.getName()).append("`=#{").append(field.getName()).append("}");
				parameter.put(field.getName(), field.get(entity));
			}
		}

		parameter.put("TABLE_NAME", tableName);
		parameter.put("KEY_VALUE", keyValues.toString());

		Field pkField = this.getClassEntity().getDeclaredField(pkName);
		pkField.setAccessible(true);
		Object pkValue = pkField.get(entity);
		logger.info("pkName==" + pkName + ", pkValue==" + pkValue);
		parameter.put("WHERE_CONDITION", "`" + pkName + "`=" + pkValue);

		return this.getSqlSession().update("base.replace", parameter) > 0;
	}

	@Override
	public boolean remove(PK id) {
		String tableName = getTableName();
		String pkName = getPkName();

		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("TABLE_NAME", tableName);
		whereMap.put("WHERE_CONDITION", pkName + "= #{" + pkName + "}");
		whereMap.put("id", id);

		String sql = "delete from " + tableName + " where " + pkName + "= #{" + pkName + "}";
		logger.info("sql==" + sql);
		return true;
	}

	/**
	 * 反射获取表名
	 * <p>
	 * 如果没有注解, 表名为实体名的小写
	 * <p>
	 * 
	 * @return
	 */
	private String getTableName() {
		String tableName = this.namespace;
		Table table = this.classEntity.getAnnotation(Table.class);
		if (table != null && !"".equals(table.name())) {
			tableName = table.name();
		}
		return tableName;
	}

	/**
	 * 反射获取列名
	 * <p>
	 * 如果没有@Column注解, 则字段名 = 属性名
	 * <p>
	 * 
	 * @return
	 */
	private String getColumnName(Field field) {
		String columnName = field.getName();
		Column column = field.getAnnotation(Column.class);
		if (column != null && !"".equals(column.name())) {
			columnName = column.name();
		}
		return columnName;
	}

	/**
	 * 反射获取主键名, 默认为id
	 * 
	 * @return
	 */
	private String getPkName() {
		String pkName = "id";
		Id pk = this.classEntity.getAnnotation(Id.class);
		if (pk != null) {
			pkName = pk.name();
		}
		return pkName;
	}

	/**
	 * 判读属性是否有setter, getter
	 * 
	 * @return
	 */
	private boolean validateField(Field field) {
		try {
			new PropertyDescriptor(field.getName(), this.classEntity);
		} catch (IntrospectionException e) {
			logger.warn("属性【" + field.getName() + "】没有设置get, set方法, 请查看是否必要. ");
			return false;
		}
		return true;
	}

	/**
	 * 获取sqlSession
	 * 
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
