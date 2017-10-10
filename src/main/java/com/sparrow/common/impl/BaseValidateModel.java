package com.sparrow.common.impl;

import com.service.tool.RedisTool;
import com.service.tool.impl.RedisImpl;
import com.sparrow.common.nozzle.ValidateModelServiceI;

public class BaseValidateModel implements ValidateModelServiceI {
	
	protected String redisKey = "";
	protected String redisPrefix = "";
	protected int redisLeftTime = 0;
	
	@Override
	public String getRedisKey() {
		return redisKey;
	}
	
	@Override
	public void setRedisKey(String redisKey) {
		this.redisKey = redisPrefix + redisKey;
	}

	@Override
	public boolean determineLimit() {
		RedisImpl redisService = (RedisImpl) RedisTool.getCommonRedis(1);
		if (redisService.get(redisKey).isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean setRedisValue(String value) {
		
		return false;
	}

}
