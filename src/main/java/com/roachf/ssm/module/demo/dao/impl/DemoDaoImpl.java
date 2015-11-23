package com.roachf.ssm.module.demo.dao.impl;

import org.springframework.stereotype.Repository;

import com.roachf.ssm.module.base.dao.impl.BaseDaoImpl;
import com.roachf.ssm.module.demo.dao.DemoDao;
import com.roachf.ssm.module.demo.model.entity.Demo;

@Repository("demoDao")
public class DemoDaoImpl extends BaseDaoImpl<Demo, Integer> implements DemoDao{

	
}
