package com.service.sparrow.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.config.enums.UserTypeEnum;
import com.service.config.ServiceConfig;
import com.service.config.enums.ResponseCommonMsgEnum;
import com.service.config.enums.ResponseSparrowMsgEnum;
import com.service.model.LoginInfoModel;
import com.service.model.ResponseModel;
import com.service.sparrow.nozzle.SpUserMobileServiceI;
import com.service.sparrow.nozzle.SpUserServiceI;
import com.service.sparrow.nozzle.SpUserFuncServiceI;
import com.service.tool.SessionTool;
import com.service.tool.WarnMsgTool;
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
	public ResponseModel registerByMobile(String mobile) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		SparrowUserMobile userMobile = new SparrowUserMobile(mobile);
		int mobileId = mobileService.insert(userMobile);
		if (mobileId > 0) {
			Map<String, Object> pwdSet = userService.createUserPwd();
			SparrowUser user = new SparrowUser();
			user.setNickname(mobile);
			user.setUserType(UserTypeEnum.MOBILE_USER_TYPE.getValue());
			user.setSalt((String) pwdSet.get("salt"));
			user.setLoginPwd((String) pwdSet.get("password"));
			user.setUserMobileId(mobileId);
			int userId = userService.insert(user);
			if (userId > 0) {
				userMobile.setUserId(userId);
				mobileService.updateById(userMobile);
				responseService.successStatus();
			}
		}
		if (responseService.isSuccess()) {
			responseService.setMessage(WarnMsgTool.getSparrowValue(ResponseSparrowMsgEnum.USER_REGIST_SUCCESS.getValue()));
		}
		return responseService.combineResponse(WarnMsgTool.getSparrowValue(ResponseSparrowMsgEnum.USER_REGIST_FAILURE.getValue()));
	}

	@Override
	public void setLoginInfo(SessionTool tool, LoginInfoModel loginInfo) {
		tool.setSessionParam(ServiceConfig.USER_LOGIN_INFO, loginInfo);
	}

	@Override
	public LoginInfoModel getLoginInfo(SessionTool tool) {
		LoginInfoModel loginInfo = tool.getSessionParam(ServiceConfig.USER_LOGIN_INFO);
		return loginInfo;
	}

	@Override
	public void clearLoginInfo(SessionTool tool) {
		tool.removeSession(ServiceConfig.USER_LOGIN_INFO);
	}

	@Override
	public ResponseModel editUser(int userId, String nickname, SparrowUser sparrowUser) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		if (sparrowUser == null) {
			sparrowUser = userService.getUserById(userId);
		}
		if (sparrowUser != null) {
			responseService.setMessage(WarnMsgTool.getCommonValue(ResponseSparrowMsgEnum.USER_NOEXISTS.getValue()));
		} 
		sparrowUser.setNickname(nickname);
		if (userService.updateById(sparrowUser) > 0) {
			responseService.successStatus();
		} else {
			responseService.setMessage(WarnMsgTool.getCommonValue(ResponseCommonMsgEnum.SUBMIT_ERROR.getValue()));
		}
		return responseService.combineResponse();
	}

	@Override
	public ResponseModel editUser(int userId, String nickname) {
		return editUser(userId, nickname, null);
	}

}
