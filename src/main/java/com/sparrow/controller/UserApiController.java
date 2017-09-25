package com.sparrow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.config.WarnMsgConfig;
import com.service.model.ResponseModel;
import com.service.sparrow.nozzle.SpUserFuncServiceI;
import com.service.sparrow.nozzle.SpUserMobileServiceI;
import com.service.sparrow.nozzle.SpUserServiceI;
import com.service.tool.nozzle.ResponseServiceI;
import com.sparrow.entity.SparrowUser;
import com.sparrow.entity.SparrowUserMobile;

@Controller
@RequestMapping("/api/sparrow/user")
public class UserApiController {

	private ResponseServiceI responseService;
	private SpUserServiceI userService;
	private SpUserMobileServiceI mobileService;
	private SpUserFuncServiceI userFuncService;

	public ResponseServiceI getRepsonseService() {
		return responseService;
	}

	@Autowired
	public void setRepsonseService(ResponseServiceI responseService) {
		this.responseService = responseService;
	}

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

	public SpUserFuncServiceI getUserFuncService() {
		return userFuncService;
	}

	@Autowired
	public void setUserFuncService(SpUserFuncServiceI userFuncService) {
		this.userFuncService = userFuncService;
	}

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
	public ResponseModel loginCheck(String username, String password, Integer type) {
		boolean isPass = false;
		SparrowUser sparrowUser = userService.getUserByParams(username, type);
		try {
			isPass = userService.compareUserLoginPwd(sparrowUser, password);
			if (isPass) {
				responseService.successStatus();
			} else {
				responseService.setMessage(WarnMsgConfig.getSparrowValue(WarnMsgConfig.SPARROW_USER_LOGININFO_ERROR));
			}
		} catch (Exception e) {
			responseService.setMessage(WarnMsgConfig.getSparrowValue(WarnMsgConfig.SPARROW_USER_NOEXISTS));
		}
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
	public ResponseModel register(String mobile, String vcode) {
		SparrowUserMobile userMobile = mobileService.getUserMobileByMobile(mobile);
		if (userMobile == null) {
			return userFuncService.registerByMobile(mobile, vcode);
		} else {
			responseService.setMessage(WarnMsgConfig.getSparrowValue(WarnMsgConfig.SPARROW_USER_MOBILE_EXISTS));
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
	public ResponseModel logout() {
		return responseService.combineResponse();
	}

}
