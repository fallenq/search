package com.sparrow.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.service.config.CommonConfig;
import com.service.config.enums.ResponseCommonMsgEnum;
import com.service.config.enums.ResponseSparrowMsgEnum;
import com.service.config.enums.SparrowValidateEnum;
import com.service.model.LoginInfoModel;
import com.service.model.ResponseModel;
import com.service.sparrow.nozzle.SpUserFuncServiceI;
import com.service.tool.CommonTool;
import com.service.tool.MobileTool;
import com.service.tool.SessionTool;
import com.service.tool.WarnMsgTool;
import com.service.tool.impl.ResponseImpl;
import com.sparrow.common.ValidateTool;
import com.sparrow.common.nozzle.ValidateModelServiceI;

@RestController
@RequestMapping("/api/mobile")
public class MobileApiController {
	
	@Autowired
	private SpUserFuncServiceI userFuncService;

	/**
	 * Send validate code in sms
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/validate/code", method = RequestMethod.POST)
	public ResponseModel sendMobileCode(HttpServletRequest request) {
		int type = SparrowValidateEnum.MOBILE_VALIDATE_SEND_TYPE.getValue();
		String mobile = request.getParameter("mobile");
		String ipAddress = CommonTool.getCLientIp(request);
		ResponseImpl responseService = ResponseImpl.getInstance();
		ValidateTool validateTool = ValidateTool.getInstance();
		ValidateModelServiceI validateService = validateTool.getValidateService(type);
		if (validateTool.determine(validateService, mobile, ipAddress)) {
			String validateCode = CommonTool.getValidateNumber(CommonConfig.VALIDATE_CODE_LENGTH_FOUR);
			if (validateCode.isEmpty()) {
				return responseService.errorSubmitCombine();
			} else {
				ResponseModel sendResponse = MobileTool.getInstance().sendMobileCode(mobile, validateCode);
				if (responseService.isSuccess(sendResponse)) {
					validateService.setRedisValue(validateCode);
					validateService.incrementLimit();
					// TODO: 测试后删除===========
					responseService.setDataValue("vcode", validateCode);
					// TODO: end==============
					responseService.successStatus();
				} else {
					responseService.setMessage(sendResponse.getMessage());
				}
			}
		} else {
			responseService.setMessage(WarnMsgTool.getSparrowValue(ResponseSparrowMsgEnum.USER_MOBILE_SENDED.getValue()));
		}
		return responseService.combineResponse();
	}
	
	/**
	 * Compare validate code in sms
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/compare/code", method = RequestMethod.POST)
	public ResponseModel compareMobileCode(String mobile, String code) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		ValidateModelServiceI validateService = ValidateTool.getInstance().getValidateService(SparrowValidateEnum.MOBILE_VALIDATE_SEND_TYPE.getValue());
		if (validateService.determine(mobile, code)) {
			return responseService.successCombine();
		}
		return responseService.combineResponse(WarnMsgTool.getCommonValue(ResponseCommonMsgEnum.VALIDATE_CODE_ERROR.getValue()));
	}

	/**
	 * Mobile if exist
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/exist", method = RequestMethod.POST)
	public ResponseModel existMobile(String mobile) {
		return userFuncService.existMobile(mobile);
	}

	/**
	 * Bind mobile to user
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/bind", method = RequestMethod.POST)
	public ResponseModel bindMobile(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		LoginInfoModel loginInfo = userFuncService.getLoginInfo(SessionTool.getInstance(request));
		String mobile = request.getParameter("mobile");
		String vcode = request.getParameter("vcode");
		ValidateModelServiceI validateService = ValidateTool.getInstance().getValidateService(SparrowValidateEnum.MOBILE_VALIDATE_SEND_TYPE.getValue());
		if (!validateService.determine(mobile, vcode)) {
			return responseService.combineResponse(WarnMsgTool.getCommonValue(ResponseCommonMsgEnum.VALIDATE_CODE_ERROR.getValue()));
		}
		return userFuncService.bindMobile(loginInfo.getUserId(), mobile);
	}

	/**
	 * Unbind mobile with user
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/unbind", method = RequestMethod.POST)
	public ResponseModel unbindMobile(HttpServletRequest request) {
		LoginInfoModel loginInfo = userFuncService.getLoginInfo(SessionTool.getInstance(request));
		return userFuncService.unbindMobile(loginInfo.getUserId());
	}

}
