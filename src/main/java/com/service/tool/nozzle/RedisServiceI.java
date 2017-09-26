package com.service.tool.nozzle;

import java.util.concurrent.TimeUnit;

public interface RedisServiceI {
	public void set(String name, String value);
	public void setForever(String name, String value);
	public void setWithTimeout(String name, String value, int timeout, TimeUnit unit);
//	public void setWithSeconds(String name, String value, int timeout);
//	public void setWithMinutes(String name, String value, int timeout);
//	public void setWithHours(String name, String value, int timeout);
//	public void setWithDays(String name, String value, int timeout);
	public String get(String name);
}
