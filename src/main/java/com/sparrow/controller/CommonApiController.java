package com.sparrow.controller;

import java.util.ArrayList;

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
	@RequestMapping(value = "/type/validate", method = RequestMethod.POST)
	public ResponseModel loginValidate(HttpServletRequest request) {
		int type = Integer.parseInt(request.getParameter("type"));
		ResponseImpl responseService = ResponseImpl.getInstance();
		ValidateTool validateTool = ValidateTool.getInstance();
		ArrayList<String> params = new ArrayList<String>();
		params.add(CommonTool.getCLientIp(request));
		ValidateModelServiceI validateService = validateTool.getValidateService(type);
		if (validateTool.determine(type, validateService, params)) {
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
		int dtype = Integer.parseInt(request.getParameter("dtype"));
		String versionCode = request.getParameter("vcode");
		return versionService.compareLastVersion(dtype, versionCode);
	}

}
