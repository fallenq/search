package com.service.tool.nozzle;

import java.util.concurrent.TimeUnit;

public interface RedisServiceI {
	public void set(String name, String value);
	public void set(String name, String value, int timeout);
	public void setForever(String name, String value);
	public void setWithTimeout(String name, String value, int timeout, TimeUnit unit);
	public String get(String name);
}
