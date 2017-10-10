package com.sparrow.common.impl;

import com.service.config.SparrowConfig;

public class MobileSendValidateImpl extends BaseValidateModel {
	
	public static MobileSendValidateImpl getInstance() {
		return new MobileSendValidateImpl();
	}
	
	public MobileSendValidateImpl() {
		super();
		redisPrefix = SparrowConfig.MOBILE_SEND_REDIS_KEY_PREFIX;
		redisLeftTime = SparrowConfig.MOBILE_SEND_TIME_LIMIT;
	}

}
