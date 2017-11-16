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
	 * @param userId
	 * @param nickname
	 * @param sparrowUser
	 * @return
	 */
	public ResponseModel editUser(int userId, String nickname, SparrowUser sparrowUser);

	/**
	 * Edit user info
	 * 
	 * @param userId
	 * @param nickname
	 * @return
	 */
	public ResponseModel editUser(int userId, String nickname);

	/**
	 * Edit user password
	 * 
	 * @param userId
	 * @param password
	 * @param type
	 * @param sparrowUser
	 * @return
	 */
	public ResponseModel editPassword(int userId, String password, int type, SparrowUser sparrowUser);

	/**
	 * Edit user password
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	public ResponseModel editPassword(int userId, String password);

	/**
	 * Get User info by userId
	 * 
	 * @param userId
	 * @param method
	 *            0 - basic user info 1 - basic user info with mobile
	 * @param params
	 * @return
	 */
	public ResponseModel getUserInfo(int userId, int method, String... params);

	/**
	 * Get base info by userId
	 * 
	 * @param userId
	 * @return
	 */
	public ResponseModel getUserInfo(int userId);

	/**
	 * Mobile exist
	 * 
	 * @param mobile
	 * @return
	 */
	public ResponseModel existMobile(String mobile);

	/**
	 * Bind mobile to user
	 * 
	 * @param userId
	 * @param mobile
	 * @return
	 */
	public ResponseModel bindMobile(Integer userId, String mobile, String... params);

	/**
	 * Unbind mobile of user by userId
	 * 
	 * @param userId
	 * @return
	 */
	public ResponseModel unbindMobile(int userId);
}
