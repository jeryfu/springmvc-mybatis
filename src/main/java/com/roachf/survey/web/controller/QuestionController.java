package com.roachf.survey.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roachf.survey.pojo.entity.Question;
import com.roachf.survey.service.SurveyService;

@Controller
@RequestMapping("question")
public class QuestionController {
	
	private Logger logger = Logger.getLogger(QuestionController.class);
	
	@Autowired
	private SurveyService surveyService;
	
	/**
	 * 跳转到选择问题类型的页面
	 * @param surveyId 	调查id
	 * @param pageId	页面id
	 * @return
	 */
	@RequestMapping(value = "type", method = RequestMethod.GET)
	public String toSelectType(Integer surveyId, Integer pageId, Model model){
		model.addAttribute("surveyId", surveyId);
		model.addAttribute("pageId", pageId);
		return "question/question-type";
	}
	
	/**
	 * 跳转到问题的编辑页面<br>
	 * @param surveyId		调查id
	 * @param pageId		页面id
	 * @param questionId	问题id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit")
	public String toEdit(Integer surveyId, Question question, Model model){
		if(question.getId() != null){
			question = surveyService.getQuestionInfo(question.getId());
		}
		model.addAttribute("question", question);
		model.addAttribute("surveyId", surveyId);
		
		Integer type = question.getQuestionType();
		logger.info("问题类型为：" + type);
		type = type == null ? 0 : type;
		if(type < 4){
			return "question/type/nonMatrixWithOtherQuestion";
		}else if(type == 4){
			return "question/type/nonMatrixSelectQuestion";
		}else if(type == 5 ){
			return "question/type/nonMatrixTextQuestion";
		}else if(type > 5 && type < 8){
			return "question/type/matrixNormalQuestion";
		}else{
			return "question/type/matrixSelectQuestion";
		}
	}
	
	/**
	 * 保存问题
	 * @param question 	问题
	 * @param surveyId	调查id, 用来重定向
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String save(Question question, Integer surveyId){
		surveyService.saveOrUpdateQuestion(question);
		return "redirect:/survey/" + surveyId;
	}
	
	/**
	 * 更新问题
	 * @param question
	 * @param surveyId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public String update(Question question, Integer surveyId){
		surveyService.saveOrUpdateQuestion(question);
		return "redirect:/survey/" + surveyId;
	}
	
	/**
	 * 删除问题
	 * @param questionId 	问题id
	 * @param surveyId 		调查id
	 * @return
	 */
	@RequestMapping(value="/{questionId}", method=RequestMethod.DELETE)
	public String delete(@PathVariable("questionId")Integer questionId, Integer surveyId){
		surveyService.deleteQuestion(questionId);
		return "redirect:/survey/" + surveyId;
	}

}
