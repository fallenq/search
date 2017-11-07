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
import com.service.model.ResponseModel;
import com.service.sparrow.nozzle.SpVersionServiceI;
import com.service.tool.CommonTool;
import com.service.tool.WarnMsgTool;
import com.service.tool.impl.ResponseImpl;
import com.sparrow.common.ValidateTool;
import com.sparrow.common.nozzle.ValidateModelServiceI;

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
	@RequestMapping(value = "/address/validate", method = RequestMethod.POST)
	public ResponseModel loginValidate(HttpServletRequest request) {
		int type = Integer.parseInt(request.getParameter("type"));
		ResponseImpl responseService = ResponseImpl.getInstance();
		ValidateTool validateTool = ValidateTool.getInstance();
		ValidateModelServiceI validateService = validateTool.getValidateService(type);
		if (validateTool.determine(type, validateService, CommonTool.getCLientIp(request))) {
			String validateCode = CommonTool.getValidateNumber(ToolConfig.VALIDATE_CODE_LENGTH_FOUR);
			if (validateCode.length() > 0) {
				validateService.setRedisValue(validateCode);
				responseService.setDataValue("code", validateCode);
				responseService.successStatus();
			} else {
				responseService.setMessage(WarnMsgTool.getCommonValue(ResponseCommonMsgEnum.SUBMIT_ERROR.getValue()));
			}
		} else {
			responseService.setMessage(WarnMsgTool.getSparrowValue(ResponseSparrowMsgEnum.CODE_ACCESSED.getValue()));
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
//		int dtype = Integer.parseInt(request.getParameter("dtype"));
		String versionCode = request.getParameter("vcode");
		return versionService.compareLastVersion(1, versionCode);
	}

}
