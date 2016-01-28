package com.roachf.survey.service.impl;

import org.springframework.stereotype.Service;

import com.roachf.survey.pojo.entity.Question;
import com.roachf.survey.service.QuestionService;

@Service("questionService")
public class QuestionServiceImpl extends BaseServiceImpl<Question, Integer> implements QuestionService{
	
}
