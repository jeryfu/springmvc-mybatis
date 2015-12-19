package com.roachf.survey.dao.impl;

import org.springframework.stereotype.Repository;

import com.roachf.survey.dao.DemoDao;
import com.roachf.survey.pojo.entity.Demo;

@Repository("demoDao")
public class DemoDaoImpl extends BaseDaoImpl<Demo, Integer> implements DemoDao{

}
