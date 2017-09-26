package com.service.tool.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.service.config.ToolConfig;
import com.service.tool.nozzle.RedisServiceI;

@Service("redisService")
public class RedisImpl implements RedisServiceI {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	private ValueOperations<String, Object> operationValue = null;

	private ValueOperations<String, Object> getValueOperation() {
		if (operationValue == null) {
			operationValue = redisTemplate.opsForValue();
		}
		return operationValue;
	}

	public static RedisImpl getInstance() {
		return new RedisImpl();
	}

	@Override
	public void set(String name, String value) {
		setWithSeconds(name, value, ToolConfig.REDIS_DEFAULT_TIMEOUT);
	}

	@Override
	public void setWithTimeout(String name, String value, int timeout, int method) {
		if (timeout <= 0) {
			timeout = ToolConfig.REDIS_DEFAULT_TIMEOUT;
		}
		if (method == ToolConfig.REDIS_TIMEUNIT_SECONDS) {
			getValueOperation().set(name, value, timeout, TimeUnit.SECONDS);
		} else if (method == ToolConfig.REDIS_TIMEUNIT_MINUTES) {
			getValueOperation().set(name, value, timeout, TimeUnit.MINUTES);
		} else if (method == ToolConfig.REDIS_TIMEUNIT_HOURS) {
			getValueOperation().set(name, value, timeout, TimeUnit.HOURS);
		} else if (method == ToolConfig.REDIS_TIMEUNIT_DAYS) {
			getValueOperation().set(name, value, timeout, TimeUnit.DAYS);
		} else if (method == ToolConfig.REDIS_TIMEUNIT_MILLISECONDS) {
			getValueOperation().set(name, value, timeout, TimeUnit.MILLISECONDS);
		} else if (method == ToolConfig.REDIS_TIMEUNIT_MICROSECONDS) {
			getValueOperation().set(name, value, timeout, TimeUnit.MICROSECONDS);
		} else {
			getValueOperation().set(name, value, timeout, TimeUnit.SECONDS);
		}
	}

	@Override
	public void setWithSeconds(String name, String value, int timeout) {
		setWithTimeout(name, value, timeout, ToolConfig.REDIS_TIMEUNIT_SECONDS);
	}

	@Override
	public void setWithMinutes(String name, String value, int timeout) {
		setWithTimeout(name, value, timeout, ToolConfig.REDIS_TIMEUNIT_MINUTES);
	}

	@Override
	public void setWithHours(String name, String value, int timeout) {
		setWithTimeout(name, value, timeout, ToolConfig.REDIS_TIMEUNIT_HOURS);
	}

	@Override
	public void setWithDays(String name, String value, int timeout) {
		setWithTimeout(name, value, timeout, ToolConfig.REDIS_TIMEUNIT_DAYS);
	}

	@Override
	public void setForever(String name, String value) {
		getValueOperation().set(name, value);
	}

	@Override
	public String get(String name) {
		return (String) redisTemplate.opsForValue().get(name);
	}

}
