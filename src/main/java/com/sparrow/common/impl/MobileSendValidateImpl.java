package com.sparrow.common.impl;

import com.service.config.SparrowConfig;

public class MobileSendValidateImpl extends BaseValidateModel {
	
	public static MobileSendValidateImpl getInstance() {
		return new MobileSendValidateImpl();
	}

	@Override
	public String getRedisKeyPrefix() {
		return SparrowConfig.MOBILE_SEND_LIMIT_REDIS_KEY_PREFIX;
	}

}
