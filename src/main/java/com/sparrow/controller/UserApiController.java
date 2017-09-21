package com.sparrow.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.sparrow.nozzle.SpUserFuncServiceI;
import com.service.sparrow.nozzle.SpUserMobileServiceI;
import com.service.sparrow.nozzle.SpUserServiceI;
import com.service.tool.ResponseTool;
import com.sparrow.entity.SparrowUser;
import com.sparrow.entity.SparrowUserMobile;

@Controller
@RequestMapping("/api/sparrow/user")
public class UserApiController {

	private SpUserServiceI userService;
	private SpUserMobileServiceI mobileService;
	private SpUserFuncServiceI userFuncService;

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
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login/check", method = RequestMethod.POST)
	public Map<String, Object> loginCheck(String username, String password, Integer type) {
		boolean isPass = false;
		ResponseTool response = ResponseTool.getInstance();
		SparrowUser sparrowUser = userService.getUserByParams(username, type);
		try {
			isPass = userService.compareUserLoginPwd(sparrowUser, password);
			if (isPass) {
				response.successStatus();
			} else {
				response.setMessage("用户名与密码不一致");
			}
		} catch (Exception e) {
			response.setMessage("账号不存在");
		}
		return response.combineMap();
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
	public Map<String, Object> register(String mobile, String vcode) {
		ResponseTool response = ResponseTool.getInstance();
		SparrowUserMobile userMobile = mobileService.getUserMobileByMobile(mobile);
		if (userMobile == null) {
			return userFuncService.registerByMobile(mobile, vcode);
		} else {
			response.setMessage("该手机号已注册");
		}
		return response.combineMap();
	}

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public Map<String, Object> test() {
		ResponseTool response = ResponseTool.getInstance();
		return response.combineMap();
	}

}
