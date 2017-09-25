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
		if (method == ToolConfig.REDIS_TIMEUNIT_SECONDS) {
			getValueOperation().set(name, value, timeout, TimeUnit.SECONDS);
		} else {
			getValueOperation().set(name, value, timeout, TimeUnit.SECONDS);
		}
	}

	@Override
	public void setWithSeconds(String name, String value, int timeout) {
		setWithTimeout(name, value, timeout, ToolConfig.REDIS_TIMEUNIT_SECONDS);
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
