package com.service.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.dao.nozzle.SparrowUserServiceI;
import com.sparrow.dao.mapper.SparrowUserMapper;
import com.sparrow.dao.model.SparrowUser;

@Service("sparrowUserService")
public class SparrowUserImpl implements SparrowUserServiceI {

	private SparrowUserMapper sparrowUserMapper;

	public SparrowUserMapper getSparrowUserMapper() {
		return sparrowUserMapper;
	}

	@Autowired
	public void setSparrowUserMapper(SparrowUserMapper sparrowUserMapper) {
		this.sparrowUserMapper = sparrowUserMapper;
	}

	@Override
	public SparrowUser getSparrowUserById(Integer userId) {
		return sparrowUserMapper.selectByPrimaryKey(userId, null);
	}

	@Override
	public SparrowUser getSparrowUserById(Integer userId, Integer method) {
		return sparrowUserMapper.selectByPrimaryKey(userId, method);
	}

	@Override
	public SparrowUser getSparrowUserByName(String username) {
		return sparrowUserMapper.selectByName(username, null);
	}

	@Override
	public SparrowUser getSparrowUserByName(String username, Integer method) {
		return sparrowUserMapper.selectByName(username, method);
	}

	@Override
	public SparrowUser getSparrowUserByPhone(String phone) {
		return sparrowUserMapper.selectByMobile(phone, null);
	}

	@Override
	public SparrowUser getSparrowUserByPhone(String phone, Integer method) {
		return sparrowUserMapper.selectByMobile(phone, method);
	}

}
