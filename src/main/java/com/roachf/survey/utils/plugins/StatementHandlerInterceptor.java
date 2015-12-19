package com.roachf.survey.utils.plugins;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;

import com.roachf.survey.utils.ReflectUtils;


@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class StatementHandlerInterceptor implements Interceptor{
	
	private String dialect;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		RoutingStatementHandler statementHandler = (RoutingStatementHandler)invocation.getTarget();
		BaseStatementHandler handler = (BaseStatementHandler) ReflectUtils.getFieldValue(statementHandler, "delegate");
		RowBounds rowBounds = (RowBounds)ReflectUtils.getFieldValue(handler, "rowBounds");
		/* 判断是否需要分页, 如果不需要则放过 */
		if(rowBounds.getLimit() == RowBounds.NO_ROW_LIMIT){
			return invocation.proceed();
		}
		BoundSql boundSql = statementHandler.getBoundSql();
		String sql = boundSql.getSql();
		if("mysql".equals(this.dialect)){
			sql = getMysqlPageSQL(sql, rowBounds);
		}else if("oracle".equals(this.dialect)){
			sql = getOraclePageSQL(sql, rowBounds);
		}
		
		ReflectUtils.setFieldValue(boundSql, "sql", sql);
		return invocation.proceed();
	}

	private String getOraclePageSQL(String sql, RowBounds rowBounds) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getMysqlPageSQL(String sql, RowBounds rowBounds) {
		StringBuffer pageSQL = new StringBuffer(sql);
		pageSQL.append(" limit ");
		pageSQL.append(rowBounds.getOffset());
		pageSQL.append(",");
		pageSQL.append(rowBounds.getLimit());
		
		return pageSQL.toString();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		this.dialect = properties.getProperty("dialect");
	}

	public String getDialect() {
		return dialect;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}
	
}
