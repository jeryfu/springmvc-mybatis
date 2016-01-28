package com.roachf.survey.pojo.entity;

import java.io.Serializable;
import java.util.Date;

public class Answer implements Serializable {
	private static final long serialVersionUID = -5063862173552781526L;
	private Integer id;

	private String answerIds;

	private String otherAnswer;

	private String uuid;

	private Date answerTime;

	private Integer questionId;

	private Integer suveryId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnswerIds() {
		return answerIds;
	}

	public void setAnswerIds(String answerIds) {
		this.answerIds = answerIds == null ? null : answerIds.trim();
	}

	public String getOtherAnswer() {
		return otherAnswer;
	}

	public void setOtherAnswer(String otherAnswer) {
		this.otherAnswer = otherAnswer == null ? null : otherAnswer.trim();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid == null ? null : uuid.trim();
	}

	public Date getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getSuveryId() {
		return suveryId;
	}

	public void setSuveryId(Integer suveryId) {
		this.suveryId = suveryId;
	}
	
	@Override
	public String toString() {
		return org.apache.commons.lang.builder.ReflectionToStringBuilder.toString(this);
	}
}
