package com.sparrow.common.impl;

import com.service.tool.RedisTool;
import com.service.tool.impl.RedisImpl;
import com.sparrow.common.nozzle.ValidateModelServiceI;

public class BaseValidateModel implements ValidateModelServiceI {
	
	protected String redisKey = "";

	@Override
	public String getRedisKeyPrefix() {
		return "";
	}
	
	@Override
	public String getRedisKey() {
		return redisKey;
	}
	
	@Override
	public void setRedisKey(String redisKey) {
		this.redisKey = getRedisKeyPrefix() + redisKey;
	}

	@Override
	public boolean determineLimit() {
		RedisImpl redisService = (RedisImpl) RedisTool.getCommonRedis(1);
		if (Integer.parseInt(redisService.get(redisKey)) > 0) {
			return false;
		}
		return true;
	}

}
