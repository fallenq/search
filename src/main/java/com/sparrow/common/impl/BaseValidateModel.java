package com.sparrow.common.impl;

import java.text.ParseException;
import java.util.Date;

import com.service.tool.RedisTool;
import com.service.tool.TimeTool;
import com.service.tool.nozzle.RedisServiceI;
import com.sparrow.common.nozzle.ValidateModelServiceI;

public class BaseValidateModel implements ValidateModelServiceI {

	protected String redisKey = "";
	protected String redisPrefix = "";
	protected String redisLimitKey = "";
	protected String redisLimitPrefix = "";
	protected int redisLeftTime = 0;
	protected int limitMax = 0;
	protected RedisServiceI redisService = null;
//	protected ValidateModelServiceI validateService = null;

	public BaseValidateModel() {
		redisService = RedisTool.getCommonRedis(1);
	}

//	@Override
//	public ValidateModelServiceI getValidateService() {
//		return validateService;
//	}

	@Override
	public String getRedisKey() {
		return redisKey;
	}

	@Override
	public void setRedisKey(String redisKey) {
		this.redisKey = redisPrefix + redisKey;
	}

	@Override
	public void setRedisLimitKey(String redisLimitKey) {
		try {
			this.redisLimitKey = redisLimitPrefix + TimeTool.formatDate(new Date(), "yyyy-MM-dd") + ":" + redisLimitKey;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			this.redisLimitKey = "";
		}
	}

	@Override
	public boolean determine() {
		try {
			String redisKeyValue = redisService.get(redisKey);
			if (redisKeyValue.isEmpty()) {
				return true;
			}
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	@Override
	public boolean determine(String redisKey, String compareValue) {
		if (compareValue.isEmpty()) {
			return false;
		}
		try {
			setRedisKey(redisKey);
			String redisKeyValue = redisService.get(this.redisKey);
			if (redisKeyValue.equals(compareValue)) {
				removeRedisValue();
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public boolean determineLimit() {
		if (redisLimitKey.isEmpty()) {
			return false;
		}
		try {
			int limitValue = Integer.parseInt(redisService.get(redisLimitKey));
			if (limitValue < limitMax) {
				return true;
			}
		} catch (NumberFormatException e) {
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
	public void incrementLimit() {
		if (!redisLimitKey.isEmpty()) {
			try {
				Integer.parseInt(redisService.get(redisLimitKey));
				redisService.incrementLong(redisLimitKey, 1);
			} catch (NumberFormatException e) {
				redisService.set(redisLimitKey, String.valueOf(1), 86400);
			}
		}
	}

	@Override
	public void removeRedisValue() {
		redisService.delete(redisKey);
	}

}
