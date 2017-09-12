package com.service.sparrow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.service.sparrow.dao.nozzle.SparrowUserServiceI;
import com.service.sparrow.nozzle.SpUserServiceI;
import com.service.tool.EncodeTool;
import com.sparrow.entity.SparrowUser;

@Service("spUserImpl")
public class SpUserImpl implements SpUserServiceI {
	
	private SparrowUserServiceI userService;

	public SparrowUserServiceI getUserService() {
		return userService;
	}
	
	@Autowired
	public void setUserService(SparrowUserServiceI userService) {
		this.userService = userService;
	}
	
	/**
	 * Get user through userId
	 */
	@Override
	public SparrowUser getUserById(int userId) {
		return userService.selectById(userId);
	}

	/**
	 * Get user through mobile
	 */
	@Override
	public SparrowUser getUserByMobile(String mobile) {
		return userService.getSparrowUserByMobile(mobile);
	}

	/**
	 * Get user through information of user and type
	 */
	@Override
	public SparrowUser getUserByParams(String refer, Integer type) {
		return getUserByInfo(refer, type);
	}
	
	@Override
	public SparrowUser getUserByInfo(String refer, Integer type, String... args) {
		SparrowUser sparrowUser = null;
		SparrowUser whereSparrowUser = null;
		switch (type) {
		case 1:
			sparrowUser = userService.getSparrowUserByMobile(refer);
			break;
		case 2:
			EntityWrapper whereWrapper = new EntityWrapper<SparrowUser>(new SparrowUser());
//			whereWrapper.eq(column, params)
//			sparrowUser = userService.selectOne();
////			sparrowUser = userService.getSparrowUserByName(refer);
			break;
		default:
			sparrowUser = null;
		}
		return sparrowUser;
	}

	/**
	 * Compare user password
	 */
	@Override
	public boolean compareUserLoginPwd(SparrowUser sparrowUser, String password) {
		return EncodeTool.match(password + sparrowUser.getSalt(), sparrowUser.getLoginPwd());
	}

}
