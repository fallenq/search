package com.service.config;

public class SparrowConfig {
	// validate
	public static int LOGIN_VALIDATE_TYPE = 1;
	public static int MOBILE_VALIDATE_ACCESS_TYPE = 2;
	public static int MOBILE_VALIDATE_SEND_TYPE = 3;
	// Login
	public static int LOGIN_CODE_TIME_LIMIT = 60;
	public static String LOGIN_CODE_REDIS_KEY_PREFIX = "test:login:vcaccess:";
	// Mobile
	public static int MOBILE_USER_TYPE = 1;
	public static String MOBILE_VALIDATE_CODE_REDIS_KEY_PREFIX = "test:mobile:vcode:";
	public static int MOBILE_SEND_TIME_LIMIT = 60;
	public static String MOBILE_SEND_REDIS_KEY_PREFIX = "test:mobile:vcsend:";
	public static int MOBILE_ACCESS_TIME_LIMIT = 60;
	public static String MOBILE_ACCESS_REDIS_KEY_PREFIX = "test:mobile:vcaccess:";
}
