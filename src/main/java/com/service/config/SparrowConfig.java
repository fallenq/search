package com.service.config;

public class SparrowConfig {
	// Login
	public static int LOGIN_CODE_TIME_LIMIT = 60;
	public static String LOGIN_CODE_LIMIT_REDIS_KEY_PREFIX = "test:login:vcaccess:limit:";
	// Mobile
	public static int MOBILE_USER_TYPE = 1;
	public static String MOBILE_VALIDATE_CODE_REDIS_KEY_PREFIX = "test:mobile:vcode:";
	public static int MOBILE_SEND_TIME_LIMIT = 60;
	public static String MOBILE_SEND_LIMIT_REDIS_KEY_PREFIX = "test:mobile:vcsend:limit:";
	public static int MOBILE_ACCESS_TIME_LIMIT = 60;
	public static String MOBILE_ACCESS_LIMIT_REDIS_KEY_PREFIX = "test:mobile:vcaccess:limit:";
}
