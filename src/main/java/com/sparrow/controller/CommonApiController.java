package com.sparrow.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.config.ToolConfig;
import com.service.config.WarnMsgConfig;
import com.service.model.ResponseModel;
import com.service.sparrow.nozzle.SpVersionServiceI;
import com.service.tool.CommonTool;
import com.service.tool.impl.ResponseImpl;
import com.sparrow.common.AddressTool;
import com.sparrow.common.impl.LoginCodeValidateImpl;
import com.sparrow.common.impl.MobileAccessValidateImpl;
import com.sparrow.entity.SparrowVersion;

@Controller
@RequestMapping("/api/common")
public class CommonApiController {

	@Autowired
	private SpVersionServiceI versionService;

	/**
	 * Get login validate
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login/validate", method = RequestMethod.POST)
	public ResponseModel loginValidate(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		LoginCodeValidateImpl validateService = LoginCodeValidateImpl.getInstance();
		if (AddressTool.getInstance().determineIpLimit(CommonTool.getCLientIp(request), validateService)) {
			String validateCode = CommonTool.getValidateNumber(ToolConfig.VALIDATE_CODE_LENGTH_FOUR);
			if (validateCode.length() > 0) {
				validateService.setRedisValue(validateCode);
				responseService.successStatus();
			} else {
				validateService.removeRedisValue();
				responseService.setMessage(WarnMsgConfig.getCommonValue(WarnMsgConfig.COMMON_SUBMIT_ERROR));
			}
		} else {
			responseService.setMessage(WarnMsgConfig.getSparrowValue(WarnMsgConfig.SPARROW_CODE_ACCESSED));
		}
		return responseService.combineResponse();
	}

	/**
	 * Get mobile code validate
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mobile/validate", method = RequestMethod.POST)
	public ResponseModel mobileValidate(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		MobileAccessValidateImpl validateService = MobileAccessValidateImpl.getInstance();
		if (AddressTool.getInstance().determineIpLimit(CommonTool.getCLientIp(request), validateService)) {
			String validateCode = CommonTool.getValidateNumber(ToolConfig.VALIDATE_CODE_LENGTH_FOUR);
			if (validateCode.length() > 0) {
				validateService.setRedisValue(validateCode);
				responseService.successStatus();
			} else {
				validateService.removeRedisValue();
				responseService.setMessage(WarnMsgConfig.getCommonValue(WarnMsgConfig.COMMON_SUBMIT_ERROR));
			}
		} else {
			responseService.setMessage(WarnMsgConfig.getSparrowValue(WarnMsgConfig.SPARROW_CODE_ACCESSED));
		}
		return responseService.combineResponse();
	}

	/**
	 * Compare device version
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/version/compare", method = RequestMethod.POST)
	public ResponseModel compareVersion(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		int dtype = Integer.parseInt(request.getParameter("dtype"));
		String versionCode = request.getParameter("vcode");
		SparrowVersion version = versionService.getLastedVersion(dtype);
		int cmpStatus = versionService.compareVersion(version, versionCode);
		if (cmpStatus == 0 || cmpStatus == 1) {
			responseService.successStatus();
		} else {
			responseService.setMessage("Need Update");
			String url = "";
			if (version != null) {
				url = version.getUrl();
			}
			responseService.setDataValue("url", url);
		}
		return responseService.combineResponse();
	}

}
