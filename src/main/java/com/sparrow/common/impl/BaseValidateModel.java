package com.sparrow.common.impl;

import com.service.tool.RedisTool;
import com.service.tool.nozzle.RedisServiceI;
import com.sparrow.common.nozzle.ValidateModelServiceI;

public class BaseValidateModel implements ValidateModelServiceI {

	protected String redisKey = "";
	protected String redisPrefix = "";
	protected int redisLeftTime = 0;
	protected RedisServiceI redisService = null;

	public BaseValidateModel() {
		redisService = RedisTool.getCommonRedis(1);
	}

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
		if (redisService.get(redisKey).isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean setRedisValue(String value) {
		redisService.set(redisKey, value, redisLeftTime);
		return false;
	}

	@Override
	public String getRedisValue() {
		return redisService.get(redisKey);
	}

	@Override
	public void removeRedisValue() {
		redisService.delete(redisKey);
	}

}
