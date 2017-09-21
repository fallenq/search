package com.service.sparrow.nozzle;

import java.util.Map;

import com.sparrow.entity.SparrowUser;

public interface SpUserServiceI {
	public int insert(SparrowUser sparrowUser);
	public int updateById(SparrowUser sparrowUser);
	public SparrowUser getUserById(int userId);
	public SparrowUser getUserByNickname(String nickname);
	public SparrowUser getUserByMobile(String mobile);
	public SparrowUser getUserByParams(String refer, Integer type);
	public boolean compareUserLoginPwd(SparrowUser sparrowUser, String password);
	public SparrowUser getUserByInfo(String refer, Integer type, String... args);
	public Map<String, Object> createUserPwd();
	public Map<String, Object> createUserPwd(String password);
	public Map<String, Object> createUserPwd(SparrowUser sparrowUser, String password);
}
