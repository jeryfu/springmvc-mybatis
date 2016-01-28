package com.roachf.survey.pojo.entity;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class Question implements Serializable {
	
	private static final String RN = "\r\n";

	private static final long serialVersionUID = 2214772869998818098L;

	private Integer id;

	/**
	 * 问题类型 <br>
	 * 0 : 单选 <br>
	 * 1 : 单选换行 <br>
	 * 2 : 复选 <br>
	 * 3 : 复选换行 <br>
	 * 4 : 下拉列表 <br>
	 * 5 : 文本框 <br>
	 * 6 : 矩阵单选 <br>
	 * 7 : 矩阵复选 <br>
	 * 8 : 矩阵下拉列表 <br>
	 */
	private Integer questionType;

	/** 问题标题 */
	private String title;

	/** 问题选项：\r\n分割 */
	private String options;
	private String[] optionArr;

	/** 是否有其他选项：true=是; false=否 */
	private Boolean other;

	/** 其他样式 : 0-无; 1-文本框; 2-下拉列表 */
	private Integer otherStyle;

	/** 有其他, 其他样式为下拉列表时: 下拉选项 */
	private String otherSelectOptions;
	private String[] otherSelectOptionArr;

	/** 当questionType = 6、7、8时, 矩阵行标题 */
	private String matrixRowTitles;
	private String[] matrixRowTitleArr;

	/** 矩阵列标题 */
	private String matrixColTitles;
	private String[] matrixColTitleArr;

	/** 矩阵下拉选项 */
	private String matrixSelectOptions;
	private String[] matrixSelectOptionArr;

	/** 页面id */
	private Integer pageId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuestionType() {
		return questionType;
	}

	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
		this.optionArr = StringUtils.split(options, RN);
	}

	public String[] getOptionArr() {
		return optionArr;
	}

	public void setOptionArr(String[] optionArr) {
		this.optionArr = optionArr;
	}

	public Boolean getOther() {
		return other;
	}

	public void setOther(Boolean other) {
		this.other = other;
	}

	public Integer getOtherStyle() {
		return otherStyle;
	}

	public void setOtherStyle(Integer otherStyle) {
		this.otherStyle = otherStyle;
	}

	public String getOtherSelectOptions() {
		return otherSelectOptions;
	}

	public void setOtherSelectOptions(String otherSelectOptions) {
		this.otherSelectOptions = otherSelectOptions;
		this.otherSelectOptionArr = StringUtils.split(options, RN);
	}

	public String[] getOtherSelectOptionArr() {
		return otherSelectOptionArr;
	}

	public void setOtherSelectOptionArr(String[] otherSelectOptionArr) {
		this.otherSelectOptionArr = otherSelectOptionArr;
	}

	public String getMatrixRowTitles() {
		return matrixRowTitles;
	}

	public void setMatrixRowTitles(String matrixRowTitles) {
		this.matrixRowTitles = matrixRowTitles;
		this.matrixRowTitleArr = StringUtils.split(matrixRowTitles, RN);
	}

	public String[] getMatrixRowTitleArr() {
		return matrixRowTitleArr;
	}

	public void setMatrixRowTitleArr(String[] matrixRowTitleArr) {
		this.matrixRowTitleArr = matrixRowTitleArr;
	}

	public String getMatrixColTitles() {
		return matrixColTitles;
	}

	public void setMatrixColTitles(String matrixColTitles) {
		this.matrixColTitles = matrixColTitles;
		this.matrixColTitleArr = StringUtils.split(matrixColTitles, RN);
	}

	public String[] getMatrixColTitleArr() {
		return matrixColTitleArr;
	}

	public void setMatrixColTitleArr(String[] matrixColTitleArr) {
		this.matrixColTitleArr = matrixColTitleArr;
	}

	public String getMatrixSelectOptions() {
		return matrixSelectOptions;
	}

	public void setMatrixSelectOptions(String matrixSelectOptions) {
		this.matrixSelectOptions = matrixSelectOptions;
		this.matrixSelectOptionArr = StringUtils.split(matrixSelectOptions, RN);
	}

	public String[] getMatrixSelectOptionArr() {
		return matrixSelectOptionArr;
	}

	public void setMatrixSelectOptionArr(String[] matrixSelectOptionArr) {
		this.matrixSelectOptionArr = matrixSelectOptionArr;
	}

	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	@Override
	public String toString() {
		return org.apache.commons.lang.builder.ReflectionToStringBuilder.toString(this);
	}
}