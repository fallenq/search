package com.sparrow.common.impl;

import com.sparrow.common.nozzle.ValidateModelServiceI;

public class MobileAccessValidateImpl implements ValidateModelServiceI {
	
	private String redisKey = "";
	
	public static MobileAccessValidateImpl getInstance(String redisKey) {
		MobileAccessValidateImpl model = new MobileAccessValidateImpl();
		model.setRedisKey(redisKey);
		return model;
	}

	@Override
	public String getRedisKeyPrefix() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return false;
	}

}
