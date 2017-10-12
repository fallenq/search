package com.service.tool.nozzle;

import java.util.concurrent.TimeUnit;

public interface RedisServiceI {
	public String ping();
	public void selectDb(int dbIndex);
	public void set(String name, String value);
	public void set(String name, String value, int timeout);
	public void setForever(String name, String value);
	public void setWithTimeout(String name, String value, int timeout, TimeUnit unit);
	public String get(String name);
	public long incrementLong(String name, long disc);
	public void delete(String name);
}
