package com.sparrow.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.config.enums.ResponseCommonMsgEnum;
import com.service.config.enums.ResponseSparrowMsgEnum;
import com.service.config.enums.SparrowValidateEnum;
import com.service.model.LoginInfoModel;
import com.service.model.ResponseModel;
import com.service.sparrow.nozzle.SpUserFuncServiceI;
import com.service.sparrow.nozzle.SpUserMobileServiceI;
import com.service.sparrow.nozzle.SpUserServiceI;
import com.service.tool.SessionTool;
import com.service.tool.WarnMsgTool;
import com.service.tool.impl.ResponseImpl;
import com.sparrow.common.ValidateTool;
import com.sparrow.common.nozzle.ValidateModelServiceI;
import com.sparrow.entity.SparrowUser;
import com.sparrow.entity.SparrowUserMobile;

@Controller
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
	@ResponseBody
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
			responseService.setMessage(WarnMsgTool.getSparrowValue(ResponseSparrowMsgEnum.USER_NOEXISTS.getValue()));
		}
		return responseService.combineResponse();
	}

	/**
	 * Login through third platform
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
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
	@ResponseBody
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
	@ResponseBody
	@RequestMapping(value = "/edit/info", method = RequestMethod.POST)
	public ResponseModel editInfo(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		LoginInfoModel loginInfo = userFuncService.getLoginInfo(SessionTool.getInstance(request));
		String nickname = request.getParameter("nickname");
		userFuncService.editUser(loginInfo.getUserId(), nickname);
		return responseService.successCombine();
	}

	/**
	 * Get user info
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/my/info", method = RequestMethod.POST)
	public ResponseModel userInfo(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		LoginInfoModel loginInfo = userFuncService.getLoginInfo(SessionTool.getInstance(request));
		ResponseModel userModel = userFuncService.getUserInfo(loginInfo.getUserId(), 1);
		if (responseService.isSuccess(userModel)) {
			SparrowUser sparrowUser = (SparrowUser) userModel.getData().get("user");
			SparrowUserMobile userMobile = (SparrowUserMobile) userModel.getData().get("mobile");
			responseService.setDataValue("nickname", sparrowUser.getNickname());
			responseService.setDataValue("mobile", "");
			if (userMobile != null) {
				responseService.setDataValue("mobile", userMobile.getMobile());
			}
			responseService.successStatus();
		} else {
			responseService.setMessage(WarnMsgTool.getSparrowValue(ResponseSparrowMsgEnum.USER_NOEXISTS.getValue()));
		}
		return responseService.combineResponse();
	}

	/**
	 * Login out
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseModel logout(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		SessionTool sessionTool = SessionTool.getInstance(request);
		userFuncService.clearLoginInfo(sessionTool);
		return responseService.successCombine();
	}

}
