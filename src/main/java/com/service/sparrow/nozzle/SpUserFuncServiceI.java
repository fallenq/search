package com.service.sparrow.nozzle;

import com.service.model.ResponseModel;

public interface SpUserFuncServiceI {
	public ResponseModel registerByMobile(String mobile, String vcode);
}
