package com.sparrow.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.service.config.enums.ResponseCommonMsgEnum;
import com.service.config.enums.ResponseSparrowMsgEnum;
import com.service.config.enums.SparrowValidateEnum;
import com.service.model.LoginInfoModel;
import com.service.model.ResponseModel;
import com.service.sparrow.nozzle.SpUserFuncServiceI;
import com.service.sparrow.nozzle.SpUserMobileServiceI;
import com.service.sparrow.nozzle.SpUserServiceI;
import com.service.tool.CommonTool;
import com.service.tool.SessionTool;
import com.service.tool.StringTool;
import com.service.tool.WarnMsgTool;
import com.service.tool.impl.ResponseImpl;
import com.sparrow.common.ValidateTool;
import com.sparrow.common.nozzle.ValidateModelServiceI;
import com.sparrow.entity.SparrowUser;
import com.sparrow.entity.SparrowUserMobile;

@RestController
@RequestMapping("/api/sparrow/user")
public class UserApiController {

	@Autowired
	private SpUserServiceI userService;
	@Autowired
	private SpUserMobileServiceI mobileService;
	@Autowired
	private SpUserFuncServiceI userFuncService;

	/**
	 * Check the user's info for login
	 * 
	 * @param username
	 * @param password
	 * @param type
	 *            1-mobile 2-nickname
	 * @return
	 */
	@RequestMapping(value = "/login/check", method = RequestMethod.POST)
	public ResponseModel loginCheck(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Integer type = Integer.parseInt(request.getParameter("type"));
		boolean isPass = false;
		SparrowUser sparrowUser = userService.getUserByParams(username, type);
		try {
			isPass = userService.compareUserLoginPwd(sparrowUser, password);
			if (isPass) {
				userFuncService.setLoginInfo(SessionTool.getInstance(request), new LoginInfoModel(sparrowUser.getId(), sparrowUser.getNickname()));
				responseService.successStatus();
			} else {
				responseService.setMessage(WarnMsgTool.getSparrowValue(ResponseSparrowMsgEnum.USER_LOGININFO_ERROR.getValue()));
			}
		} catch (Exception e) {
			return responseService.noSpUserCombine();
		}
		return responseService.combineResponse();
	}

	/**
	 * Login through third platform
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login/third", method = RequestMethod.POST)
	public ResponseModel loginThird(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		return responseService.combineResponse();
	}

	/**
	 * Register user through mobile
	 * 
	 * @param mobile
	 * @param vcode
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseModel register(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		String mobile = request.getParameter("mobile");
		String vcode = request.getParameter("vcode");
		ValidateModelServiceI validateService = ValidateTool.getInstance().getValidateService(SparrowValidateEnum.MOBILE_VALIDATE_SEND_TYPE.getValue());
		if (!validateService.determine(mobile, vcode)) {
			return responseService.combineResponse(WarnMsgTool.getCommonValue(ResponseCommonMsgEnum.VALIDATE_CODE_ERROR.getValue()));
		}
		SparrowUserMobile userMobile = mobileService.getUserMobileByMobile(mobile);
		if (userMobile == null) {
			return userFuncService.registerByMobile(mobile);
		}
		return responseService.combineResponse(WarnMsgTool.getSparrowValue(ResponseSparrowMsgEnum.USER_MOBILE_EXISTS.getValue()));
	}
	
	/**
	 * Edit info of user
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edit/info", method = RequestMethod.POST)
	public ResponseModel editInfo(HttpServletRequest request) {
		LoginInfoModel loginInfo = userFuncService.getLoginInfo(SessionTool.getInstance(request));
		Map<String, Object> userInfo = CommonTool.emptyMap();
		userInfo.put("nickname", StringTool.parseString(request.getParameter("nickname")));
		userInfo.put("face", StringTool.parseString(request.getParameter("face")));
		userFuncService.editUser(loginInfo.getUserId(), userInfo);
		return ResponseImpl.getInstance().successCombine();
	}

	/**
	 * Get user info
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/my/info", method = RequestMethod.POST)
	public ResponseModel userInfo(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		LoginInfoModel loginInfo = userFuncService.getLoginInfo(SessionTool.getInstance(request));
		ResponseModel userModel = userFuncService.getUserInfo(loginInfo.getUserId(), 1);
		if (responseService.isSuccess(userModel)) {
			SparrowUser sparrowUser = (SparrowUser) userModel.getData().get("user");
			SparrowUserMobile userMobile = (SparrowUserMobile) userModel.getData().get("mobile");
			responseService.setDataValue("nickname", sparrowUser.getNickname());
			responseService.setDataValue("face", sparrowUser.getFace());
			responseService.setDataValue("mobile", "");
			if (userMobile != null) {
				responseService.setDataValue("mobile", userMobile.getMobile());
			}
			responseService.successStatus();
		} else {
			return responseService.noSpUserCombine();
		}
		return responseService.combineResponse();
	}
	
	/**
	 * Edit password of user
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edit/password", method = RequestMethod.POST)
	public ResponseModel editPassword(HttpServletRequest request) {
		LoginInfoModel loginInfo = userFuncService.getLoginInfo(SessionTool.getInstance(request));
		String password = request.getParameter("password");
		return userFuncService.editPassword(loginInfo.getUserId(), password);
	}
	
	/**
	 * Edit password of user through mobile
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edit/mobile/password", method = RequestMethod.POST)
	public ResponseModel editMobilePassword(String mobile, String password) {
		return userFuncService.editPassword(mobile, password);
	}

	/**
	 * Login out
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseModel logout(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		SessionTool sessionTool = SessionTool.getInstance(request);
		userFuncService.clearLoginInfo(sessionTool);
		return responseService.successCombine();
	}

}
