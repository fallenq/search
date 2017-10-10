package com.sparrow.common.impl;

import com.sparrow.common.nozzle.ValidateModelServiceI;

public class MobileSendValidateImpl implements ValidateModelServiceI {
	
	private String redisKey = "";
	
	public static MobileSendValidateImpl getInstance(String redisKey) {
		MobileSendValidateImpl model = new MobileSendValidateImpl();
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
