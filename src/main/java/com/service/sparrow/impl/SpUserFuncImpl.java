package com.service.sparrow.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.config.enums.UserTypeEnum;
import com.alibaba.fastjson.JSON;
import com.service.config.ServiceConfig;
import com.service.config.enums.ResponseSparrowMsgEnum;
import com.service.model.LoginInfoModel;
import com.service.model.ResponseModel;
import com.service.sparrow.nozzle.SpUserMobileServiceI;
import com.service.sparrow.nozzle.SpUserServiceI;
import com.service.sparrow.nozzle.SpUserFuncServiceI;
import com.service.tool.CommonTool;
import com.service.tool.SessionTool;
import com.service.tool.StringTool;
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
			user.setNickname(StringTool.parseMobile(mobile, 1));
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
		tool.setSessionParam(ServiceConfig.USER_LOGIN_INFO, JSON.toJSON(loginInfo));
	}

	@Override
	public LoginInfoModel getLoginInfo(SessionTool tool) {
		String loginInfoContent = tool.getSessionRedisValue(ServiceConfig.USER_LOGIN_INFO);
		return CommonTool.parseObject(loginInfoContent, LoginInfoModel.class);
	}

	@Override
	public void clearLoginInfo(SessionTool tool) {
		tool.removeSession(ServiceConfig.USER_LOGIN_INFO);
	}

	@Override
	public ResponseModel editUser(int userId, Map<String, Object> userInfo, SparrowUser sparrowUser) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		if (sparrowUser == null) {
			sparrowUser = userService.getUserById(userId);
			if (sparrowUser == null) {
				return responseService.noSpUserCombine();
			}
		}
		sparrowUser.setNickname((String) userInfo.getOrDefault("nickname", ""));
		sparrowUser.setFace((String) userInfo.getOrDefault("face", ""));
		if (userService.updateById(sparrowUser) > 0) {
			responseService.successStatus();
		} else {
			return responseService.errorSubmitCombine();
		}
		return responseService.combineResponse();
	}

	@Override
	public ResponseModel editUser(int userId, Map<String, Object> userInfo) {
		return editUser(userId, userInfo, null);
	}

	@Override
	public ResponseModel editPassword(int userId, String password, int type, SparrowUser sparrowUser) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		if (!StringTool.isAvailableString(password)) {
			return responseService.errorParamCombine();
		}
		if (sparrowUser == null) {
			sparrowUser = userService.getUserById(userId);
			if (sparrowUser == null) {
				return responseService.noSpUserCombine();
			}
		}
		if (type == 1) {
			if (userService.compareUserLoginPwd(sparrowUser, password)) {
				return responseService.combineResponse(WarnMsgTool.getSparrowValue(ResponseSparrowMsgEnum.USER_LOGIN_PASSWORD_SAME.getValue()));
			}
			Map<String, Object> pwdSet = userService.createUserPwd(sparrowUser, password);
			sparrowUser.setLoginPwd((String) pwdSet.get("password"));
			int updateUserId = userService.updateById(sparrowUser);
			if (updateUserId > 0) {
				return responseService.successCombine();
			}
		}
		return responseService.errorSubmitCombine();
	}

	@Override
	public ResponseModel editPassword(int userId, String password) {
		return editPassword(userId, password, 1, null);
	}

	@Override
	public ResponseModel editPassword(String mobile, String password) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		if (!StringTool.isAvailableString(mobile)) {
			return responseService.errorParamCombine();
		}
		SparrowUser sparrowUser = userService.getUserByMobile(mobile);
		if (sparrowUser == null) {
			return responseService.noSpUserCombine();
		}
		return editPassword(sparrowUser.getId(), password, 1, sparrowUser);
	}

	@Override
	public ResponseModel getUserInfo(int userId, int method, String...params) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		SparrowUser sparrowUser = userService.getUserById(userId);
		if (sparrowUser != null) {
			responseService.setDataValue("user", sparrowUser);
			responseService.setDataValue("mobile", null);
			if (method == 1) {
				SparrowUserMobile userMobile = mobileService.getUserMobileById(sparrowUser.getUserMobileId());
				if (userMobile != null) {
					responseService.setDataValue("mobile", userMobile);
				} else {
					responseService.setDataValue("mobile", null);
				}
			}
			responseService.successStatus();
		}
		return responseService.combineResponse();
	}

	@Override
	public ResponseModel getUserInfo(int userId) {
		return getUserInfo(userId, 0);
	}

	@Override
	public ResponseModel existMobile(String mobile) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		SparrowUserMobile userMobile = mobileService.getUserMobileByMobile(mobile);
		if (userMobile != null) {
			responseService.successStatus();
		} else {
			responseService.setMessage(WarnMsgTool.getSparrowValue(ResponseSparrowMsgEnum.USER_MOBILE_NOEXIST.getValue()));
		}
		return responseService.combineResponse();
	}

	@Override
	public ResponseModel bindMobile(Integer userId, String mobile, String...params) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		ResponseModel userModel = getUserInfo(userId);
		if (responseService.isSuccess(userModel)) {
			SparrowUser user = (SparrowUser) userModel.getData().get("user");
			if (user.getUserMobileId() > 0) {
				return responseService.combineResponse(WarnMsgTool.getSparrowValue(ResponseSparrowMsgEnum.USER_MOBILE_BINDED.getValue()));
			}
			SparrowUserMobile userMobile = mobileService.getUserMobileByMobile(mobile);
			if (userMobile != null) {
				return responseService.combineResponse(WarnMsgTool.getSparrowValue(ResponseSparrowMsgEnum.USER_MOBILE_EXISTS.getValue()));
			}
			userMobile = new SparrowUserMobile(mobile);
			int mobileId = mobileService.insert(userMobile);
			user.setUserMobileId(mobileId);
			if (userService.updateById(user) > 0) {
				return responseService.successCombine();
			} else {
				return responseService.errorSubmitCombine();
			}
		}
		return responseService.noSpUserCombine();
	}

	@Override
	public ResponseModel unbindMobile(int userId) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		ResponseModel userModel = getUserInfo(userId);
		if (responseService.isSuccess(userModel)) {
			SparrowUser user = (SparrowUser) userModel.getData().get("user");
			if (user.getUserMobileId() == 0) {
				return responseService.combineResponse(WarnMsgTool.getSparrowValue(ResponseSparrowMsgEnum.USER_MOBILE_UNBINDED.getValue()));
			}
			int mobileId = user.getUserMobileId();
			user.setUserMobileId(0);
			if (userService.updateById(user) > 0) {
				mobileService.delete(mobileId);
				return responseService.successCombine();
			} else {
				return responseService.errorSubmitCombine();
			}
		}
		return responseService.noSpUserCombine();
	}

}
