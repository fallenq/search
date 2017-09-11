package com.service.dao.nozzle;

import com.sparrow.dao.model.SparrowUser;

public interface SparrowUserServiceI {
	public SparrowUser getSparrowUserById(Integer userId);
	public SparrowUser getSparrowUserById(Integer userId, Integer method);
	public SparrowUser getSparrowUserByName(String username);
	public SparrowUser getSparrowUserByName(String username, Integer method);
	public SparrowUser getSparrowUserByPhone(String phone);
	public SparrowUser getSparrowUserByPhone(String phone, Integer method);
}
