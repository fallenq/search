package com.sparrow.common.impl;

import com.service.config.SparrowConfig;

public class MobileAccessValidateImpl extends BaseValidateModel {

	public static MobileAccessValidateImpl getInstance() {
		return new MobileAccessValidateImpl();
	}

	@Override
	public String getRedisKeyPrefix() {
		return SparrowConfig.MOBILE_ACCESS_REDIS_KEY_PREFIX;
	}

}
