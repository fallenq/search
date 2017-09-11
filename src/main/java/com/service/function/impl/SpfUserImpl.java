package com.service.function.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.dao.nozzle.SparrowUserServiceI;
import com.service.function.nozzle.SpfUserServiceI;
import com.service.tool.CommonTool;
import com.service.tool.EncodeTool;
import com.service.tool.StringTool;
import com.sparrow.dao.model.SparrowUser;

@Service("spfUserImpl")
public class SpfUserImpl implements SpfUserServiceI {

	private SparrowUserServiceI sparrowUserService;

	public SparrowUserServiceI getSparrowUserService() {
		return sparrowUserService;
	}

	@Autowired
	public void setSparrowUserService(SparrowUserServiceI sparrowUserService) {
		this.sparrowUserService = sparrowUserService;
	}

	@Override
	public SparrowUser getUserByInfo(String refer, Integer type, String... args) {
		// TODO get user model by args
		SparrowUser sparrowUser = null;
		switch (type) {
		case 1:
			sparrowUser = sparrowUserService.getSparrowUserByPhone(refer);
			break;
		case 2:
			sparrowUser = sparrowUserService.getSparrowUserByName(refer);
			break;
		default:
			sparrowUser = null;
		}
		return sparrowUser;
	}

	public SparrowUser getUserByParams(String refer) {
		return this.getUserByInfo(refer, 1);
	}

	public SparrowUser getUserByParams(String refer, Integer type) {
		return this.getUserByInfo(refer, type);
	}

	public boolean compareUserLoginPwd(SparrowUser sparrowUser, String password) {
		return EncodeTool.match(password + sparrowUser.getSalt(), sparrowUser.getLoginPwd());
	}

	private Map<String, Object> userPwdMap(String password, String salt) {
		Map<String, Object> passwordMap = CommonTool.emptyMap();
		passwordMap.put("salt", salt);
		passwordMap.put("key", password);
		passwordMap.put("password", EncodeTool.encrypt(password + salt));
		return passwordMap;
	}

	public Map<String, Object> createUserPwd() {
		return this.userPwdMap(StringTool.uuCode("-"), StringTool.uuCode("-"));
	}

	public Map<String, Object> createUserPwd(String password) {
		return this.userPwdMap(password, StringTool.uuCode("-"));
	}

	public Map<String, Object> createUserPwd(SparrowUser sparrowUser, String password) {
		return this.userPwdMap(password, sparrowUser.getSalt());
	}

}
