package com.service.config.enums;

import com.service.config.enums.nozzle.IntEnumI;

public enum ResponseCommonMsgEnum implements IntEnumI {
	SUBMIT_ERROR(1), SYSTEM_BUSY(2), DEVICE_NEED_UPDATE(3);
	
	private int value;
	
	private ResponseCommonMsgEnum(int value) {
		this.value = value;
	}

	@Override
	public int getValue() {
		return value;
	}
	
}
