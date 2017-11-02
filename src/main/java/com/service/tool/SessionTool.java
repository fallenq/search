package com.service.tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
