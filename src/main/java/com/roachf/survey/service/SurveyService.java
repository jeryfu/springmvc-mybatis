package com.roachf.survey.service;

import java.util.List;

import com.roachf.survey.pojo.entity.Page;
import com.roachf.survey.pojo.entity.Question;
import com.roachf.survey.pojo.entity.Survey;

public interface SurveyService{
	
	Survey getInfo(Integer surveyId);

	/** 获取我的调查 */
	List<Survey> getMySurvey(Integer userId);
	
	/**
	 * 获取用户的调查列表以及调查列表所对应的页面
	 * @param userId 用户id
	 * @return
	 */
	List<Survey> getSurveyWithPages(Integer userId);
	
	/**
	 * 根据调查id获取调查内容详情
	 * @param surveyId 调查id
	 */
	Survey getSurveyInfo(Integer surveyId);
	
	/**
	 * 新增调查
	 * @param survey 调查
	 */
	boolean insert(Survey survey); 
	
	/**
	 * 更新
	 * @param survey
	 */
	boolean update(Survey survey);
	
	/**
	 * 删除调查
	 * @param surveyId
	 */
	void deleteSurvey(Integer surveyId);
	
	/** 根据问题id删除问题 */
	void deleteQuestion(Integer questionId);

	/**
	 * 根据pageId获取页面详情
	 * @param pageId
	 * @return
	 */
	Page getPageInfo(Integer pageId);

	/**
	 * 更新或者保存页面
	 * @param page
	 */
	void saveOrUpdatePage(Page page);

	/**
	 * 删除页面
	 * @param pageId
	 */
	void deletePage(Integer pageId);

	/**
	 * 更新或添加问题
	 * @param question
	 */
	void saveOrUpdateQuestion(Question question);

	/**
	 * 根据问题id获取问题详情
	 * @param id
	 * @return
	 */
	Question getQuestionInfo(Integer id);

	/**
	 * 清除调查
	 * @param surveyId 问题id
	 */
	void clearAnswers(Integer surveyId);

	/**
	 * 修改调查的状态：开放/关闭
	 * @param surveyId
	 */
	void toggleStatus(Integer surveyId);

	/** 保存logo的图片地址 */
	void addLogo(String logoUrl, Integer surveyId);

	/**
	 * 移动复制页
	 * @param srcPid 	源页面id
	 * @param targPid	目标页面id
	 * @param pos		之前=0/之后=1
	 */
	void moveOrCopyPage(Integer srcPid, Integer targPid, Integer pos) throws Exception;

	
}
