package com.service.config.enums;

import com.service.config.enums.nozzle.IntEnumI;

public enum ResponseStatusEnum implements IntEnumI {
	
	SUCCESS(200), FAILURE(500);
	
	private int value;
	
	private ResponseStatusEnum(int value) {
        this.value = value;
    }

	@Override
	public int getValue() {
		return value;
	}
	
}
