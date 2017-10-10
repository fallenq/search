package com.sparrow.common.nozzle;

public interface ValidateModelServiceI {
	public String getRedisKey();
	public void setRedisKey(String redisKey);
	public boolean determineLimit();
	public boolean setRedisValue(String value);
	public String getRedisValue();
	public void removeRedisValue();
}
