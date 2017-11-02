package com.service.sparrow.nozzle;

import com.service.model.LoginInfoModel;
import com.service.model.ResponseModel;
import com.service.tool.SessionTool;

public interface SpUserFuncServiceI {
	public ResponseModel registerByMobile(String mobile, String vcode);
	public LoginInfoModel getLoginInfo(SessionTool tool);
	public void clearLoginInfo(SessionTool tool);
}
