package com.sparrow.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.config.ToolConfig;
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

@Controller
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
	@ResponseBody
	@RequestMapping(value = "/validate/code", method = RequestMethod.POST)
	public ResponseModel validateCode(HttpServletRequest request) {
		int type = SparrowValidateEnum.MOBILE_VALIDATE_SEND_TYPE.getValue();
		String mobile = request.getParameter("mobile");
		String ipAddress = CommonTool.getCLientIp(request);
		ResponseImpl responseService = ResponseImpl.getInstance();
		ValidateTool validateTool = ValidateTool.getInstance();
		ValidateModelServiceI validateService = validateTool.getValidateService(type);
		if (validateTool.determine(type, validateService, mobile, ipAddress)) {
			String validateCode = CommonTool.getValidateNumber(ToolConfig.VALIDATE_CODE_LENGTH_FOUR);
			if (validateCode.isEmpty()) {
				responseService.setMessage(WarnMsgTool.getCommonValue(ResponseCommonMsgEnum.SUBMIT_ERROR.getValue()));
			} else {
				ResponseModel sendResponse = MobileTool.getInstance().sendMobileCode(mobile, validateCode);
				if (responseService.isSuccess(sendResponse)) {
					validateService.setRedisValue(validateCode);
					validateService.incrementLimit();
					responseService.successStatus();
				} else {
					responseService.setMessage(sendResponse.getMessage());
				}
			}
		} else {
			responseService
					.setMessage(WarnMsgTool.getSparrowValue(ResponseSparrowMsgEnum.USER_MOBILE_SENDED.getValue()));
		}
		return responseService.combineResponse();
	}

	/**
	 * Bind mobile to user
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bind", method = RequestMethod.POST)
	public ResponseModel bindMobile(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		return responseService.combineResponse();
	}

	/**
	 * Unbind mobile with user
	 * 
	 * @param request
	 * @return
	 */
	public ResponseModel unbindMobile(HttpServletRequest request) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		LoginInfoModel loginInfoModel = userFuncService.getLoginInfo(SessionTool.getInstance(request));
		
		return responseService.combineResponse();
	}

}
