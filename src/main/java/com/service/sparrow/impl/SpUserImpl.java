package com.service.sparrow.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.service.sparrow.dao.nozzle.SparrowUserServiceI;
import com.service.sparrow.nozzle.SpUserServiceI;
import com.service.tool.CommonTool;
import com.service.tool.EncodeTool;
import com.service.tool.StringTool;
import com.sparrow.entity.SparrowUser;

@Service("spUserImpl")
public class SpUserImpl implements SpUserServiceI {
	
	private String baseColumnList = "id, user_type AS userType, nickname, user_mobile_id AS userMobileId, salt, login_pwd AS loginPwd, user_device_id AS userDeviceId, status, created_at AS createdAt, update_at AS updateAt, delete_flag AS deleteFlag, delete_at AS deleteAt, last_login_at AS lastLoginAt";
	
	private SparrowUserServiceI userService;
	
	

	public SparrowUserServiceI getUserService() {
		return userService;
	}
	
	@Autowired
	public void setUserService(SparrowUserServiceI userService) {
		this.userService = userService;
	}
	
	/**
	 * Insert user
	 */
	@Override
	public int insert(SparrowUser sparrowUser) {
		return userService.insertSelective(sparrowUser);
	}
	
	/**
	 * Edit user by Id
	 */
	@Override
	public int update(SparrowUser sparrowUser) {
		return userService.updateByPrimaryKeySelective(sparrowUser);
	}
	
	/**
	 * Get user through userId
	 */
	@Override
	public SparrowUser getUserById(int userId) {
		return userService.selectOne(new EntityWrapper<SparrowUser>().setSqlSelect(baseColumnList).eq("id", userId));
	}

	/**
	 * Get user through nickname
	 */
	@Override
	public SparrowUser getUserByNickname(String nickname) {
		return userService.selectOne(new EntityWrapper<SparrowUser>().setSqlSelect(baseColumnList).eq("nickName", nickname));
	}

	/**
	 * Get user through mobile
	 */
	@Override
	public SparrowUser getUserByMobile(String mobile) {
		return userService.getSparrowUserByMobile(mobile);
	}

	/**
	 * Get user through information of user and type
	 */
	@Override
	public SparrowUser getUserByParams(String refer, Integer type) {
		return getUserByInfo(refer, type);
	}
	
	@Override
	public SparrowUser getUserByInfo(String refer, Integer type, String... args) {
		SparrowUser sparrowUser = null;
		switch (type) {
			case 1:
				sparrowUser = getUserByMobile(refer);
				break;
			case 2:
				sparrowUser = getUserByNickname(refer);
				break;
			default:
				sparrowUser = null;
		}
		return sparrowUser;
	}

	/**
	 * Compare user password
	 */
	@Override
	public boolean compareUserLoginPwd(SparrowUser sparrowUser, String password) {
		return EncodeTool.match(password + sparrowUser.getSalt(), sparrowUser.getLoginPwd());
	}
	
	/**
	 * Combine user password set
	 * @param password
	 * @param salt
	 * @return
	 */
	private Map<String, Object> userPwdMap(String password, String salt) {
		Map<String, Object> passwordMap = CommonTool.emptyMap();
		passwordMap.put("salt", salt);
		passwordMap.put("key", password);
		passwordMap.put("password", EncodeTool.encrypt(password + salt));
		return passwordMap;
	}
	
	/**
	 * Create user password set
	 */
	@Override
	public Map<String, Object> createUserPwd() {
		return this.userPwdMap(StringTool.uuCode(), StringTool.uuCode());
	}
	
	/**
	 * Create user password set
	 */
	@Override
	public Map<String, Object> createUserPwd(String password) {
		return this.userPwdMap(password, StringTool.uuCode());
	}
	
	/**
	 * Create user password set
	 */
	@Override
	public Map<String, Object> createUserPwd(SparrowUser sparrowUser, String password) {
		return this.userPwdMap(password, sparrowUser.getSalt());
	}

}
