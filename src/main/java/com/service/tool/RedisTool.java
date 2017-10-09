package com.service.tool;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.service.tool.nozzle.RedisServiceI;

public class RedisTool {

	private static ApplicationContext app = null;

	/**
	 * Get system app context
	 * 
	 * @return
	 */
	public static ApplicationContext getAppContext() {
		if (app == null) {
			app = new ClassPathXmlApplicationContext("classpath:config/spring/spring.xml");
		}
		return app;
	}

	/**
	 * Get common redis connection
	 * 
	 * @return
	 */
	public static RedisServiceI getCommonRedis() {
		return (RedisServiceI) getAppContext().getBean("redisService");
	}

	/**
	 * Get common redis connection with dbIndex
	 * 
	 * @param dbIndex
	 * @return
	 */
	public static RedisServiceI getCommonRedis(int dbIndex) {
		RedisServiceI redisService = (RedisServiceI) getAppContext().getBean("redisService");
		redisService.selectDb(dbIndex);
		return redisService;
	}
}
