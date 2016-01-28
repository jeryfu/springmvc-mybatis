package com.roachf.survey.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.roachf.survey.pojo.entity.Survey;
import com.roachf.survey.pojo.entity.User;
import com.roachf.survey.service.SurveyService;
import com.roachf.survey.web.UserAware;

@Controller
@RequestMapping("/survey")
public class SurveyController implements UserAware {
	
	private Logger logger = Logger.getLogger(SurveyController.class);
	
	private User user;
	
	private SurveyService surveyService;
	
	@Autowired
	public void setSurveyService(SurveyService surveyService) {
		this.surveyService = surveyService;
	}
	
	public SurveyService getSurveyService() {
		return surveyService;
	}

	/**
	 * 获取我创建的调查
	 * @param request
	 * @return
	 */
	@RequestMapping(value="mine", method=RequestMethod.GET)
	public String mySurvey(Model model){
		
		List<Survey> surveys = surveyService.getMySurvey(this.user.getId());
		logger.info("surveys.size==" + surveys.size());
		model.addAttribute("surveys", surveys);
		
		return "survey/list";
	}
	
	/**
	 * 获取调查数据, 并跳转到设计页面
	 * 
	 * @param surveyId		调查id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{surveyId}", method = RequestMethod.GET)
	public String design(@PathVariable("surveyId")Integer surveyId, Model model){
		Survey survey = surveyService.getSurveyInfo(surveyId);
		logger.info("survey==" + survey.getLogoUrl());
		model.addAttribute("survey", survey);
		return "survey/design";
	}
	
	/**
	 * 新建调查
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String input(Model model){
		Survey survey = new Survey();
		survey.setUserId(this.user.getId());
		surveyService.insert(survey);
		logger.info("surveyId==" + survey.getId());
		model.addAttribute("survey", survey);
		return "survey/design";
	}
	
	/**
	 * 跳转到编辑页面
	 */
	@RequestMapping(value="edit/{surveyId}", method=RequestMethod.GET)
	public String toEdit(@PathVariable("surveyId")Integer surveyId, Model model){
		model.addAttribute("survey", surveyService.getInfo(surveyId));
		return "survey/edit";
	}
	
	/**
	 * 更新调查
	 * @param survey
	 * @return
	 */
	@RequestMapping(method=RequestMethod.PUT)
	public String update(Survey survey){
		survey.setUserId(user.getId());
		surveyService.update(survey);
		return "redirect:survey/" + survey.getId();
	}

	/**
	 * 清除调查-删除答案
	 * @param surveyId
	 * @return
	 */
	@RequestMapping(value="clear/{surveyId}", method = RequestMethod.DELETE)
	public String clearAnswers(@PathVariable("surveyId") Integer surveyId){
		surveyService.clearAnswers(surveyId);
		return "redirect:/survey/mine";
	}
	
	/**
	 * 删除调查
	 * @param surveyId	调查id
	 * @return
	 */
	@RequestMapping(value = "/{surveyId}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("surveyId") Integer surveyId){
		surveyService.deleteSurvey(surveyId);
		return "redirect:/survey/mine";
	}
	
	/**
	 * 切换调查状态
	 */
	@RequestMapping(value = "toggle/{surveyId}", method = RequestMethod.PUT)
	public String toggleStatus(@PathVariable("surveyId") Integer surveyId){
		surveyService.toggleStatus(surveyId);
		return "redirect:/survey/mine";
	}
	
	/**
	 * 跳转到logo添加页面
	 * @return
	 */
	@RequestMapping(value="logo/{surveyId}", method = RequestMethod.GET)
	public String toLogo(@PathVariable("surveyId")Integer surveyId, Model model){
		model.addAttribute("surveyId", surveyId);
		return "/survey/survey-logo";
	}
	
	/**
	 * 跳转到logo添加页面
	 * @return
	 */
	@RequestMapping(value="logo", method = RequestMethod.POST)
	public String addLogo(Integer surveyId, MultipartFile file){
		
		if(!file.isEmpty()){
			InputStream in = null;
			OutputStream out = null;
			
			File folder = new File("F:/apache-tomcat-7.0.54/webapps/upload/" + this.user.getId());
			if(!folder.exists())	folder.mkdirs();
			
			String logoUrl = System.nanoTime() + ".png";
			logger.info("logoUrl==" + logoUrl);
			try {
				in = file.getInputStream();
				out = new BufferedOutputStream(new FileOutputStream(folder + "/" + logoUrl));
				byte[] b = new byte[1024];
				int count = -1;
				while( (count = in.read(b)) != -1){
					out.write(b, 0, count);
				}
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if(out != null) out.close();
					if(in != null) in.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
			/* 将文件地址保存到数据库 */
			surveyService.addLogo("upload/" + this.user.getId() + "/" +logoUrl, surveyId);
		}
		
		return "redirect:/survey/" + surveyId;
	}
	
	@Override
	public void setUser(User user) {
		this.user = user;
	}

}
