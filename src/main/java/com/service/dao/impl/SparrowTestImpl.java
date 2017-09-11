package com.service.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.dao.nozzle.SparrowTestServiceI;
import com.sparrow.dao.mapper.SparrowTestMapper;
import com.sparrow.dao.model.SparrowTest;

@Service("sparrowTestService")
public class SparrowTestImpl implements SparrowTestServiceI {
	
	private SparrowTestMapper sparrowTestMapper;

	public SparrowTestMapper getSparrowTestMapper() {
		return sparrowTestMapper;
	}

	@Autowired
	public void setSparrowTestMapper(SparrowTestMapper sparrowTestMapper) {
		this.sparrowTestMapper = sparrowTestMapper;
	}

	@Override
	public SparrowTest selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sparrowTestMapper.selectByPrimaryKey(id);
	}

}
