package com.sparrow.common.impl;

import com.service.config.SparrowConfig;
import com.service.tool.RedisTool;
import com.service.tool.impl.RedisImpl;

public class LoginCodeValidateImpl extends BaseValidateModel {
	
	public static LoginCodeValidateImpl getInstance() {
		return new LoginCodeValidateImpl();
	}
	
	public LoginCodeValidateImpl() {
		redisPrefix = SparrowConfig.LOGIN_CODE_REDIS_KEY_PREFIX;
		redisLeftTime = SparrowConfig.LOGIN_CODE_TIME_LIMIT;
	}

	@Override
	public boolean setRedisValue(String value) {
		RedisImpl redisService = (RedisImpl) RedisTool.getCommonRedis(1);
		if (redisService.get(redisKey).isEmpty()) {
			return true;
		}
		return false;
	}

}
