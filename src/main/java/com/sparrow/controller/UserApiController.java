package com.sparrow.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.config.SparrowConfig;
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
		response.setStatus(ResponseTool.FAILURE);
		SparrowUser sparrowUser = userService.getUserByParams(username, type);
		try {
			isPass = userService.compareUserLoginPwd(sparrowUser, password);
			if (isPass) {
				response.setStatus(ResponseTool.SUCCESS);
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
		response.setStatus(ResponseTool.FAILURE);
		SparrowUserMobile userMobile = mobileService.getUserMobileByMobile(mobile);
		if (userMobile == null) {
			userMobile = new SparrowUserMobile();
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
					response.setStatus(ResponseTool.SUCCESS);
					response.setMessage("注册成功");
				}
			}
			if (response.getStatus() != ResponseTool.SUCCESS) {
				response.setMessage("注册失败，请稍后尝试");
			}
		} else {
			response.setMessage("该手机号已注册");
		}
		return response.combineMap();
	}

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public Map<String, Object> test() {
		ResponseTool response = ResponseTool.getInstance();
		response.setStatus(ResponseTool.FAILURE);
		return response.combineMap();
	}

}
