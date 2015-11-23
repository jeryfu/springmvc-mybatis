package com.roachf.ssm.module.base.controller;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roachf.ssm.module.base.service.BaseService;

public class BaseController<T, PK extends Serializable> {
	
	private Logger logger = Logger.getLogger(BaseController.class);
	
	private BaseService<T, PK> baseService;
	
	public BaseService<T, PK> getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService<T, PK> baseService) {
		logger.info("set base service . . . ");
		this.baseService = baseService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<T> list(){
		return this.baseService.getList();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public T info(@PathVariable("id")PK id){
		return this.baseService.getInfo(id);
	}

}
