package com.roachf.ssm.pojo.entity;

import java.io.Serializable;

public class Page implements Serializable{
	
	private static final long serialVersionUID = 8956860176967040588L;
	
	/** 是否分页【true:分页; false：不分页】, 默认分页 */
	private boolean isPage;
	/** 页码 */
	private int pageNo;
	/** 页大小 */
	private int pageSize;
	
	public Page() {
		this.isPage = true;
		this.pageNo = 1;
		this.pageSize = 10;
	}
	
	public Page(int pageNo, int pageSize){
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public boolean isPage() {
		return isPage;
	}

	public void setPage(boolean isPage) {
		this.isPage = isPage;
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
	
	public int getPageStart(){
		return (this.pageNo-1) * this.pageSize;
	}
	
	@Override
	public String toString() {
		return org.apache.commons.lang.builder.ReflectionToStringBuilder.toString(this);
	}
}
