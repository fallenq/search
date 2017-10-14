package com.service.config.enums;

import com.service.config.enums.nozzle.IntEnumI;

public enum ResponseSparrowMsgEnum implements IntEnumI {
	
	USER_SUBMIT_SUCCESS(1), USER_SUBMIT_FAILURE(2), USER_REGIST_SUCCESS(3),
	USER_REGIST_FAILURE(4), USER_MOBILE_EXISTS(5), USER_NOEXISTS(6),
	USER_LOGININFO_ERROR(7), USER_MOBILE_SENDED(8), CODE_ACCESSED(9);
	
	private int value;
	
	private ResponseSparrowMsgEnum(int value) {
		this.value = value;
	}

	@Override
	public int getValue() {
		return value;
	}
}
