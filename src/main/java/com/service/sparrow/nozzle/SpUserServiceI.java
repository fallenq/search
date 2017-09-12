package com.service.sparrow.nozzle;

import com.sparrow.entity.SparrowUser;

public interface SpUserServiceI {
	public SparrowUser getUserById(int userId);
	public SparrowUser getUserByNickname(String nickname);
	public SparrowUser getUserByMobile(String mobile);
	public SparrowUser getUserByParams(String refer, Integer type);
	public boolean compareUserLoginPwd(SparrowUser sparrowUser, String password);
	public SparrowUser getUserByInfo(String refer, Integer type, String... args);
}
