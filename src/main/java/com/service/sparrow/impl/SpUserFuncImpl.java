package com.service.sparrow.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.config.SparrowConfig;
import com.service.config.WarnMsgConfig;
import com.service.model.ResponseModel;
import com.service.sparrow.nozzle.SpUserMobileServiceI;
import com.service.sparrow.nozzle.SpUserServiceI;
import com.service.sparrow.nozzle.SpUserFuncServiceI;
import com.service.tool.impl.ResponseImpl;
import com.sparrow.entity.SparrowUser;
import com.sparrow.entity.SparrowUserMobile;
@Service("userFuncImpl")
public class SpUserFuncImpl implements SpUserFuncServiceI {
	
	@Autowired
	private SpUserServiceI userService;
	@Autowired
	private SpUserMobileServiceI mobileService;

	@Override
	public ResponseModel registerByMobile(String mobile, String vcode) {
		ResponseImpl responseService = ResponseImpl.getInstance();
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
				responseService.successStatus();
				responseService.setMessage(WarnMsgConfig.getSparrowValue(WarnMsgConfig.SPARROW_USER_REGIST_SUCCESS));
			}
		}
		if (!responseService.isSuccess()) {
			responseService.setMessage(WarnMsgConfig.getSparrowValue(WarnMsgConfig.SPARROW_USER_REGIST_FAILURE));
		}
		return responseService.combineResponse();
	}

}
