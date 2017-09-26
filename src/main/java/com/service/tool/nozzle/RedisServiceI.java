package com.service.tool.nozzle;

public interface RedisServiceI {
	public void set(String name, String value);
//	public void setWithTimeout(String name, String value, int timeout, int method);
	public void setWithSeconds(String name, String value, int timeout);
	public void setWithMinutes(String name, String value, int timeout);
	public void setWithHours(String name, String value, int timeout);
	public void setWithDays(String name, String value, int timeout);
	public void setForever(String name, String value);
	public String get(String name);
}
