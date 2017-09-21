package com.service.sparrow.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.config.SparrowConfig;
import com.service.model.ResponseModel;
import com.service.sparrow.nozzle.SpUserMobileServiceI;
import com.service.sparrow.nozzle.SpUserServiceI;
import com.service.sparrow.nozzle.SpUserFuncServiceI;
import com.service.tool.ResponseTool;
import com.sparrow.entity.SparrowUser;
import com.sparrow.entity.SparrowUserMobile;
@Service("userFuncServiceImpl")
public class SpUserFuncServiceImpl implements SpUserFuncServiceI {

	private SpUserServiceI userService;
	private SpUserMobileServiceI mobileService;

	public SpUserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(SpUserServiceI userService) {
		this.userService = userService;
	}

	public SpUserMobileServiceI getMobileService() {
		return mobileService;
	}

	@Autowired
	public void setMobileService(SpUserMobileServiceI mobileService) {
		this.mobileService = mobileService;
	}

	@Override
	public ResponseModel registerByMobile(String mobile, String vcode) {
		ResponseTool response = ResponseTool.getInstance();
		SparrowUserMobile userMobile = new SparrowUserMobile();
		userMobile.setMobile(mobile);
		int mobileId = mobileService.insert(userMobile);
		if (mobileId > 0) {
			Map<String, Object> pwdSet = userService.createUserPwd();
			SparrowUser user = new SparrowUser();
			user.setNickname(mobile);
			user.setUserType(SparrowConfig.MOBILE_USER_TYPE);
			user.setSalt((String) pwdSet.get("salt"));
			user.setLoginPwd((String) pwdSet.get("password"));
			user.setUserMobileId(mobileId);
			int userId = userService.insert(user);
			if (userId > 0) {
				userMobile.setUserId(userId);
				mobileService.updateById(userMobile);
				response.successStatus();
				response.setMessage("注册成功");
			}
		}
		if (!response.isSuccess()) {
			response.setMessage("注册失败，请稍后尝试");
		}
		return response.combineMap();
	}

}
