package com.service.config.enums;

import com.service.config.enums.nozzle.IntEnumI;

public enum UserTypeEnum implements IntEnumI {
	MOBILE_USER_TYPE(1);
	
	private int value;
	
	private UserTypeEnum(int value) {
        this.value = value;
    }

	@Override
	public int getValue() {
		return value;
	}
}
