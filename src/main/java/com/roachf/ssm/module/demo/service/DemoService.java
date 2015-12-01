package com.roachf.ssm.module.demo.service;

import java.util.List;

import com.roachf.ssm.module.base.service.BaseService;
import com.roachf.ssm.module.demo.model.entity.Demo;

public interface DemoService extends BaseService<Demo, Integer>{
	public List<Demo> getList();
}
