package com.sparrow.common.impl;

import com.service.config.SparrowConfig;

public class LoginCodeValidateImpl extends BaseValidateModel {
	
	public static LoginCodeValidateImpl getInstance() {
		return new LoginCodeValidateImpl();
	}
	
	public LoginCodeValidateImpl() {
		super();
		redisPrefix = SparrowConfig.LOGIN_CODE_REDIS_KEY_PREFIX;
		redisLeftTime = SparrowConfig.LOGIN_CODE_TIME_LIMIT;
	}

}
