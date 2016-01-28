package com.roachf.survey.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Service;

import com.roachf.survey.dao.BaseDao;
import com.roachf.survey.pojo.entity.Answer;
import com.roachf.survey.pojo.entity.Page;
import com.roachf.survey.pojo.entity.Question;
import com.roachf.survey.pojo.entity.Survey;
import com.roachf.survey.service.SurveyService;

@Service("surveyService")
public class SurveyServiceImpl implements SurveyService {
	
	private Logger logger = Logger.getLogger(SurveyServiceImpl.class);

	@Resource(name = "surveyDao")
	private BaseDao<Survey, Integer> surveyDao;
	
	@Resource(name = "pageDao")
	private BaseDao<Page, Integer> pageDao;
	
	@Resource(name = "questionDao")
	private BaseDao<Question, Integer> questionDao;
	
	@Resource(name = "answerDao")
	private BaseDao<Answer, Integer> answerDao;
	
	@Override
	public List<Survey> getMySurvey(Integer userId) {
		return surveyDao.getList(userId);
	}
	
	@Override
	public Survey getInfo(Integer surveyId) {
		return surveyDao.getInfo(surveyId);
	}

	@Override
	public Survey getSurveyInfo(Integer surveyId) {
		/* (1). 获取调查的信息 */
		Survey survey = surveyDao.getInfo(surveyId);
		if(survey == null){
			return new Survey();
		}
		/* (2). 获取页面集合 */
		List<Page> pages = pageDao.getList(surveyId);
		/* (3). 获取页面下问题集合 */
		for (Page page : pages) {
			List<Question> questions = questionDao.getList(page.getId());
			page.setQuestions(questions);
		}
		survey.setPages(pages);

		return survey;
	}
	
	@Override
	public List<Survey> getSurveyWithPages(Integer userId) {
		List<Survey> surveys = surveyDao.getList(userId);
		if(surveys == null)	surveys = new ArrayList<Survey>();
		for (Survey survey : surveys) {
			List<Page> pages = pageDao.getList(survey.getId());
			survey.setPages(pages);
		}
		return surveys;
	}
	
	@Override
	public boolean insert(Survey survey) {
		return surveyDao.insert(survey);
	}
	
	@Override
	public boolean update(Survey survey) {
		return surveyDao.update(survey);
	}
	
	@Override
	public void deleteSurvey(Integer surveyId) {
		/* (1).删除答案  */
		answerDao.delete("deleteBySurveyId", surveyId);
		/* (2).删除问题  */
		questionDao.delete("deleteBySurveyId", surveyId);
		/* (3).删除页面  */
		pageDao.delete("deleteBySurveyId", surveyId);
		/* (4).删除调查  */
		surveyDao.delete(surveyId);
	}

	@Override
	public Page getPageInfo(Integer pageId) {
		return pageDao.getInfo(pageId);
	}
	
	@Override
	public void saveOrUpdatePage(Page page) {
		if(page.getId() == null){
			pageDao.insert(page);
		}else{
			pageDao.update(page);
		}
	}
	
	@Override
	public void deletePage(Integer pageId) {
		/* (1).删除答案 */
		answerDao.delete("deleteByPageId", pageId);
		/* (2).根据pageId删除问题*/
		questionDao.delete("deleteByPageId", pageId);
		/* (3).删除页面*/
		pageDao.delete(pageId);
	}
	
	@Override
	public Question getQuestionInfo(Integer id) {
		return questionDao.getInfo(id);
	}
	
	@Override
	public void saveOrUpdateQuestion(Question question) {
		if(question.getId() == null){
			questionDao.insert(question);
		}else{
			questionDao.update(question);
		}
	}
	
	@Override
	public void deleteQuestion(Integer questionId) {
		/* (1). 删除答案 */
		answerDao.delete(questionId);
		/* (2). 删除问题*/
		questionDao.delete(questionId);
	}
	
	@Override
	public void clearAnswers(Integer surveyId) {
		answerDao.delete("deleteBySurveyId", surveyId);
	}
	
	@Override
	public void toggleStatus(Integer surveyId) {
		surveyDao.update("toggleStatus", surveyId);
	}

	@Override
	public void addLogo(String logoUrl, Integer surveyId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("logoUrl", logoUrl);
		map.put("surveyId", surveyId);
		surveyDao.update("updateLogo", map);
	}
	
	@Override
	public void moveOrCopyPage(Integer srcPid, Integer targPid, Integer pos) throws ClassNotFoundException, IOException {
		Page srcPage = this.getPageInfo(srcPid);
		Page targPage = this.getPageInfo(targPid);
		
		/* 同一个页面, 移动 */
		if(srcPage.getSurveyId().equals(targPage.getSurveyId())){
			setOrderno(srcPage, targPage, pos);
			logger.info("srcPage.orderno==" + srcPage.getOrderno());
		}
		/* 不同页面, 复制 */
		else{
			// 查询page下所有的question
			List<Question> questions = questionDao.getList(srcPage.getId());
			Page copyPage = srcPage.deepCopy();
			copyPage.setQuestions(questions);
			// 将page保存到targePage对应的Survey中
			copyPage.setSurveyId(targPage.getSurveyId());
			pageDao.insert(copyPage);
			for (Question question : questions) {
				question.setPageId(copyPage.getId());
				questionDao.insert(question);
			}
			// 设置页序
			setOrderno(copyPage, targPage, pos);
		}
	}

	private void setOrderno(Page srcPage, Page targPage, Integer pos) {
		/* 之前 */
		if(pos == 0){
			/* 目标页是首页, 那么srcPage.orderno = targPage.orderno - 0.01f */
			if(isFirstPage(targPage)){
				srcPage.setOrderno(targPage.getOrderno() - 0.01f);
			}
			/* 目标页不是首页*/
			else{
				// 获取目标页的上一页 
				float orderno = getPrePage(targPage);
				srcPage.setOrderno((targPage.getOrderno() + orderno) / 2);
			}
		}
		/* 之后 */
		else{
			if(isLastPage(targPage)){
				srcPage.setOrderno(targPage.getOrderno() + 0.01f);
			}else{
				float orderno = getNextPage(targPage);
				srcPage.setOrderno((targPage.getOrderno() + orderno) / 2);
			}
		}
		
		// 修改update
		pageDao.update("updateOrderno", srcPage);
	}

	/**
	 * 获取目标页的前一页
	 * @param targPage
	 * @return
	 */
	private float getPrePage(Page targPage) {
 		return (float) pageDao.getObject("prePage", targPage);
	}
	
	/**
	 * 获取目标页的下一页
	 * @param targPage
	 * @return
	 */
	private float getNextPage(Page targPage) {
 		return (float) pageDao.getObject("nextPage", targPage);
	}

	/**
	 * 判断是否是首页
	 * @param targPage
	 * @return
	 */
	private boolean isFirstPage(Page targPage) {
		Long count = (Long) pageDao.getObject("isFirst", targPage);
		return count < 1;
	}
	
	/**
	 * 判断是否是尾页
	 * @param targPage
	 * @return
	 */
	private boolean isLastPage(Page targPage) {
		Long count = (Long) pageDao.getObject("isLast", targPage);
		return count < 1;
	}
}
