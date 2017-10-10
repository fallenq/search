package com.sparrow.common.impl;

import com.service.config.SparrowConfig;

public class LoginCodeValidateImpl extends BaseValidateModel {
	
	public static LoginCodeValidateImpl getInstance() {
		return new LoginCodeValidateImpl();
	}

	@Override
	public String getRedisKeyPrefix() {
		return SparrowConfig.LOGIN_CODE_LIMIT_REDIS_KEY_PREFIX;
	}

}
