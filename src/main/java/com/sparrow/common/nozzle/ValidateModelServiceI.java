package com.sparrow.common.nozzle;

public interface ValidateModelServiceI {
	public String getRedisKeyPrefix();
	public String getRedisKey();
	public void setRedisKey(String redisKey);
	public boolean determineLimit();
}
