package com.service.sparrow.nozzle;

import com.service.model.LoginInfoModel;
import com.service.model.ResponseModel;
import com.service.tool.SessionTool;
import com.sparrow.entity.SparrowUser;

public interface SpUserFuncServiceI {
	/**
	 * Register user by mobile
	 * 
	 * @param mobile
	 * @return
	 */
	public ResponseModel registerByMobile(String mobile);

	/**
	 * Set login info of user
	 * 
	 * @param tool
	 * @param loginInfo
	 */
	public void setLoginInfo(SessionTool tool, LoginInfoModel loginInfo);

	/**
	 * Get login info of user
	 * 
	 * @param tool
	 * @return
	 */
	public LoginInfoModel getLoginInfo(SessionTool tool);

	/**
	 * Clear login info of user
	 * 
	 * @param tool
	 */
	public void clearLoginInfo(SessionTool tool);

	/**
	 * Edit user info
	 * 
	 * @param nickname
	 * @param mobile
	 * @param sparrowUser
	 * @return
	 */
	public ResponseModel editUser(String nickname, String mobile, SparrowUser sparrowUser);
}
