package com.service.tool;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.service.tool.nozzle.RedisServiceI;

public class RedisTool {
	
	private static ApplicationContext app = null;
	
	public static ApplicationContext getAppContext() {
		if (app == null) {
			app = new ClassPathXmlApplicationContext("classpath:config/spring/spring.xml");
		}
		return app;
	}
	
	public static RedisServiceI getCommonRedis() {
		return (RedisServiceI) getAppContext().getBean("redisService");
	}
	
	public static RedisServiceI getCommonRedis(int dbIndex) {
		RedisServiceI redisService = (RedisServiceI) getAppContext().getBean("redisService");
		redisService.selectDb(dbIndex);
		return redisService;
	}
}
