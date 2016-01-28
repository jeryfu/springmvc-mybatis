package com.roachf.survey.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roachf.survey.pojo.entity.Page;
import com.roachf.survey.pojo.entity.Survey;
import com.roachf.survey.pojo.entity.User;
import com.roachf.survey.service.SurveyService;
import com.roachf.survey.web.UserAware;

@Controller
@RequestMapping("page")
public class PageController implements UserAware  {

	private Logger logger = Logger.getLogger(PageController.class);

	@Autowired
	public SurveyService surveyService;
	
	private User user;

	/**
	 * 跳转到page的编辑页面
	 * 
	 * @param pageId
	 * @return
	 */
	@RequestMapping(value = "edit/{surveyId}", method = RequestMethod.GET)
	public String toEdit(@PathVariable("surveyId") Integer surveyId, Integer pageId, Model model) {
		Page page = new Page();
		page.setSurveyId(surveyId);
		if (pageId != null) {
			page = surveyService.getPageInfo(pageId);
		}
		model.addAttribute("page", page);
		return "page/edit";
	}

	/**
	 * 新增一个页面
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String save(Page page) {
		surveyService.saveOrUpdatePage(page);
		return "redirect:/survey/" + page.getSurveyId();
	}

	/**
	 * 更新一个页面
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public String update(Page page) {
		surveyService.saveOrUpdatePage(page);
		return "redirect:/survey/" + page.getSurveyId();
	}

	/**
	 * 删除一个页面
	 * @param pageId
	 * @param surveyId
	 * @return
	 */
	@RequestMapping(value = "{pageId}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("pageId") Integer pageId, Integer surveyId) {
		surveyService.deletePage(pageId);
		return "redirect:/survey/" + surveyId;
	}
	
	/**
	 * 跳转到设置排序页面
	 * @param srcPid 需要移动或copy的源页面
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "order/{srcPid}", method = RequestMethod.GET)
	public String toSelectTargetPage(@PathVariable("srcPid")Integer srcPid, ModelMap modelMap){
		List<Survey> surveys = surveyService.getSurveyWithPages(this.user.getId());
		modelMap.addAttribute("surveys", surveys);
		modelMap.addAttribute("srcPid", srcPid);
		return "page/page-order";
	}
	
	/**
	 * 页面排序
	 * @param surveyId 	调查id, 重定向使用
	 * @param srcPid	源页面id
	 * @param targPid	目标页面id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="order", method = RequestMethod.POST)
	public String orderPage(Integer surveyId, Integer srcPid, Integer targPid, Integer pos) throws Exception{
		logger.info("surveyId==" + surveyId + ", srcPid==" + srcPid + ", targPid==" + targPid);
		surveyService.moveOrCopyPage(srcPid, targPid, pos);
		return "redirect:/survey/" + surveyId;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}
}
