package com.roachf.survey.pojo.entity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * 页面实体
 * 
 * @author roach
 */
public class Page implements Serializable {

	private static final long serialVersionUID = -944085646786997255L;

	private Integer id;

	/** 页面标题 */
	private String title;

	/** 描述 */
	private String description;
	
	/** 排序页码 */
	private float orderno;

	/** 调查id */
	private Integer surveyId;

	/** 对应的问题集 */
	private List<Question> questions;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public float getOrderno() {
		return orderno;
	}

	public void setOrderno(float orderno) {
		this.orderno = orderno;
	}

	public Integer getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	/**
	 * 深度复制
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public Page deepCopy() throws IOException, ClassNotFoundException{
		
		/* outputstream 写入数据 */
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(this);
		oos.close();
		out.close();
		
		/* inputstrem 读取数据 */
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(in);
		Page page = (Page)ois.readObject();
		ois.close();
		in.close();
		
		return page;
	}
	
	@Override
	public String toString() {
		return org.apache.commons.lang.builder.ReflectionToStringBuilder.toString(this);
	}
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Page page = new Page();
		page.setId(12);
		
		Page copy = page;
		copy.setId(123);
		System.out.println("page.id==" + page.id); // 123
		System.out.println("copy.id==" + copy.id); // 123
		
		copy = page.deepCopy();
		copy.setId(67);
		System.out.println("page.id==" + page.id); // 123
		System.out.println("copy.id==" + copy.id); // 67
	}
}