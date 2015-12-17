package com.roachf.survey.pojo.entity;

import java.io.Serializable;

import com.roachf.survey.pojo.annotation.Column;
import com.roachf.survey.pojo.annotation.Id;
import com.roachf.survey.pojo.annotation.Table;


@Table
public class Demo implements Serializable {

	private static final long serialVersionUID = -3749053857538538696L;

	@Id
	private Integer id;

	@Column
	private String key;

	@Column
	private String value;
	
	public Demo() {
	}
	
	public Demo(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Demo [id=" + id + ", key=" + key + ", value=" + value + "]";
	}
}