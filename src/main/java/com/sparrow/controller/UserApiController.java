package com.sparrow.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.config.enums.ResponseSparrowMsgEnum;
import com.service.model.ResponseModel;
import com.service.sparrow.nozzle.SpUserFuncServiceI;
import com.service.sparrow.nozzle.SpUserMobileServiceI;
import com.service.sparrow.nozzle.SpUserServiceI;
import com.service.tool.WarnMsgTool;
import com.service.tool.impl.ResponseImpl;
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
	@RequestMapping(value = "/register/mobile", method = RequestMethod.POST)
	public ResponseModel register(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		String mobile = request.getParameter("mobile");
		String vcode = request.getParameter("vcode");
		SparrowUserMobile userMobile = mobileService.getUserMobileByMobile(mobile);
		if (userMobile == null) {
			return userFuncService.registerByMobile(mobile, vcode);
		} else {
			responseService.setMessage(WarnMsgTool.getSparrowValue(ResponseSparrowMsgEnum.USER_MOBILE_EXISTS.getValue()));
		}
		return responseService.combineResponse();
	}

	/**
	 * Get user info
	 * 
	 * @param request
	 * @return
	 */
	public ResponseModel userInfo(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
//		LoginInfoModel loginInfo = userFuncService.getLoginInfo(request);
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
		userFuncService.clearLoginInfo(request);
		responseService.successStatus();
		return responseService.combineResponse();
	}

}
