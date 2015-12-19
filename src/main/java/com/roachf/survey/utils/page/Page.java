package com.roachf.survey.utils.page;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Page<T> implements Serializable{
	
	private static final long serialVersionUID = 8956860176967040588L;
	
	/** 页码 */
	private int pageNo;
	/** 页大小 */
	private int pageSize;
	/** 总记录数 */
	private long totalCount;
	/** 分页数据列表 */
	private List<T> list;
	
	public Page() {
		this.pageNo = 1;
		this.pageSize = 10;
	}
	
	public Page(int pageNo, int pageSize){
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	@JsonIgnore
	public int getPageStart(){
		return (this.pageNo-1) * this.pageSize;
	}
	
	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return org.apache.commons.lang.builder.ReflectionToStringBuilder.toString(this);
	}
}
