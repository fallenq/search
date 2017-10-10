package com.sparrow.common.impl;

import com.service.config.SparrowConfig;

public class MobileAccessValidateImpl extends BaseValidateModel {

	public static MobileAccessValidateImpl getInstance() {
		return new MobileAccessValidateImpl();
	}

	public MobileAccessValidateImpl() {
		super();
		redisPrefix = SparrowConfig.MOBILE_ACCESS_REDIS_KEY_PREFIX;
		redisLeftTime = SparrowConfig.MOBILE_ACCESS_TIME_LIMIT;
	}

}
