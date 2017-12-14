package com.service.tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.service.config.CommonConfig;

public class SessionTool {

	private HttpSession session;

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public static SessionTool getInstance(HttpServletRequest request) {
		SessionTool tool = new SessionTool();
		tool.setSession(request.getSession());
		return tool;
	}

	/**
	 * Get session by name
	 * 
	 * @param keyName
	 * @return
	 */
	public void setSessionParam(String name, Object object) {
		session.setAttribute(name, object);
	}

	/**
	 * Get session id
	 * 
	 * @return
	 */
	public String getSessionId() {
		return session.getId();
	}

	/**
	 * Get session of redis
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getSessionRedis(String name) {
		if (!StringTool.isAvailableString(name)) {
			return null;
		}
		String sessionKey = CommonConfig.SESSION_DATA_PREFIX + getSessionId();
		String sessionDataKey = CommonConfig.SESSION_COLUMN_PREFIX + name;
		return (T) RedisTool.getCommonRedis().hgetField(sessionKey, sessionDataKey);
	}

	/**
	 * Get session object of redis
	 * 
	 * @param name
	 * @param clazz
	 * @return
	 */
	public <T> T getSessionRedis(String name, Class<T> clazz) {
		JSONObject object = getSessionRedis(name);
		return CommonTool.parseJSONObject(object, clazz);
	}

	/**
	 * Get session by name
	 * 
	 * @param keyName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getSessionParam(String name) {
		T object = (T) session.getAttribute(name);
		return object;
	}

	/**
	 * Remove session by name
	 * 
	 * @param name
	 * @return
	 */
	public void removeSession(String name) {
		session.removeAttribute(name);
	}

}
