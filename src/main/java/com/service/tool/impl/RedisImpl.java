package com.service.tool.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import com.service.config.CommonConfig;
import com.service.tool.nozzle.RedisServiceI;

@Service("redisService")
public class RedisImpl implements RedisServiceI {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	private ValueOperations<String, Object> operationValue = null;
	private ZSetOperations<String, Object> operationZset = null;

	private ValueOperations<String, Object> getValueOperation() {
		if (operationValue == null) {
			operationValue = redisTemplate.opsForValue();
		}
		return operationValue;
	}
	
	private ZSetOperations<String, Object> getZsetOperation() {
		if (operationZset == null) {
			operationZset = redisTemplate.opsForZSet();
		}
		return operationZset;
	}

	@Override
	public String ping() {
		return redisTemplate.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.ping();
			}
		});
	}

	@Override
	public void selectDb(int dbIndex) {
		JedisConnectionFactory redisConnectFactory = (JedisConnectionFactory) redisTemplate.getConnectionFactory();
		redisConnectFactory.setDatabase(dbIndex);
		redisTemplate.setConnectionFactory(redisConnectFactory);
	}

	@Override
	public boolean hasKey(String name) {
		return redisTemplate.hasKey(name);
	}

	@Override
	public boolean expire(String name, int timeout, TimeUnit unit) {
		return redisTemplate.expire(name, timeout, unit);
	}

	@Override
	public void set(String name, String value) {
		setWithTimeout(name, value, CommonConfig.REDIS_DEFAULT_TIMEOUT, TimeUnit.SECONDS);
	}

	@Override
	public void set(String name, String value, int timeout) {
		setWithTimeout(name, value, timeout, TimeUnit.SECONDS);
	}

	@Override
	public void setForever(String name, String value) {
		getValueOperation().set(name, value);
	}

	@Override
	public void setWithTimeout(String name, String value, int timeout, TimeUnit unit) {
		if (timeout <= 0) {
			timeout = CommonConfig.REDIS_DEFAULT_TIMEOUT;
		}
		if (unit == null) {
			unit = TimeUnit.SECONDS;
		}
		getValueOperation().set(name, value, timeout, unit);
	}

	@Override
	public String get(String name) {
		return (String) getValueOperation().get(name);
	}

	@Override
	public long incrementLong(String name, long disc) {
		return getValueOperation().increment(name, disc);
	}

	@Override
	public void delete(String name) {
		redisTemplate.delete(name);
	}

	@Override
	public boolean zadd(String name, String item, double score) {
		return getZsetOperation().add(name, item, score);
	}

	@Override
	public double zscore(String name, String item) {
		return getZsetOperation().score(name, item);
	}

	@Override
	public double zincrby(String name, String item, double increment) {
		return getZsetOperation().incrementScore(name, item, increment);
	}

}
