package com.roachf.survey.pojo.entity;

import java.util.Date;
import java.util.List;

public class Survey {

	private Integer id;

	private String title = "未命名";
	
	/** 调查的logo地址 */
	private String logoUrl;

	/** 是否关闭：1=关闭; 2=开放 */
	private boolean closed;

	private String preText = "上一步";

	private String nextText = "下一步";

	private String exitText = "退出";

	private String doneText = "完成";

	private Date createTime = new Date();

	/** 对应的页面集 */
	private List<Page> pages;

	private Integer userId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public String getPreText() {
		return preText;
	}

	public void setPreText(String preText) {
		this.preText = preText == null ? null : preText.trim();
	}

	public String getNextText() {
		return nextText;
	}

	public void setNextText(String nextText) {
		this.nextText = nextText == null ? null : nextText.trim();
	}

	public String getExitText() {
		return exitText;
	}

	public void setExitText(String exitText) {
		this.exitText = exitText == null ? null : exitText.trim();
	}

	public String getDoneText() {
		return doneText;
	}

	public void setDoneText(String doneText) {
		this.doneText = doneText == null ? null : doneText.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	@Override
	public String toString() {
		return org.apache.commons.lang.builder.ReflectionToStringBuilder.toString(this);
	}
}