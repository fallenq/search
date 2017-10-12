package com.sparrow.common.nozzle;

public interface ValidateModelServiceI {
//	public ValidateModelServiceI getValidateService();
	public String getRedisKey();
	public void setRedisKey(String redisKey);
	public void setRedisLimitKey(String redisLimitKey);
	public boolean determine();
	public boolean determineLimit();
	public boolean setRedisValue(String value);
	public void incrementLimit();
	public String getRedisValue();
	public void removeRedisValue();
}
