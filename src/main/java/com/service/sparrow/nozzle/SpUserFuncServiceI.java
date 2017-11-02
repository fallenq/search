package com.service.sparrow.nozzle;

import javax.servlet.http.HttpServletRequest;

import com.service.model.LoginInfoModel;
import com.service.model.ResponseModel;

public interface SpUserFuncServiceI {
	public ResponseModel registerByMobile(String mobile, String vcode);
	public LoginInfoModel getLoginInfo(HttpServletRequest request);
	public void clearLoginInfo(HttpServletRequest request);
}
