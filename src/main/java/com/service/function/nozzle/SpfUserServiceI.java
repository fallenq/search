package com.service.function.nozzle;

import com.sparrow.dao.model.SparrowUser;

public interface SpfUserServiceI {
	public SparrowUser getUserByInfo(String refer, Integer type, String... args);
}
