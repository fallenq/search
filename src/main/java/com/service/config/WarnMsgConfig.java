package com.service.config;

import java.util.Map;

import com.service.model.XmlModel;
import com.service.tool.CommonTool;
import com.service.tool.XmlTool;

public class WarnMsgConfig {
	
	private static Map<Integer, String> commonMap 	= null;
	private static Map<Integer, String> sparrowMap 	= null;
	/**
	 * common
	 */
	public static int COMMON_SUBMIT_ERROR = 1;
	public static int COMMON_SYSTEM_BUSY  = 2;
	/**
	 * sparrow
	 */
	public static int SPARROW_USER_SUBMIT_SUCCESS = 1;
	public static int SPARROW_USER_SUBMIT_FAILURE = 2;
	public static int SPARROW_USER_REGIST_SUCCESS = 3;
	public static int SPARROW_USER_REGIST_FAILURE = 4;
	public static int SPARROW_USER_MOBILE_EXISTS  = 5;
	public static int SPARROW_USER_NOEXISTS  	  = 6;
	public static int SPARROW_USER_LOGININFO_ERROR	= 7;
	
	public static Map<Integer, String> putValue(Map<Integer, String> warnMap, int msgId, String value) {
		warnMap.put(msgId, value);
		return warnMap;
	}
	
	public static synchronized Map<Integer, String> getCommonMap() {
		if (commonMap == null) {
			commonMap = CommonTool.emptyIntMap();
			XmlModel root = XmlTool.getXmlModelList("config/service/warn-common-msg.xml");
			if (root != null && root.getChildList().size() > 0) {
				for(XmlModel model : root.getChildList()){
					Map<String, String> attributes = model.getAttributes();
					commonMap.put(Integer.parseInt(attributes.get("id")), attributes.get("value"));
				}
			}
//			commonMap.put(WarnMsgConfig.COMMON_SUBMIT_ERROR, "提交失败");
//			commonMap.put(WarnMsgConfig.COMMON_SYSTEM_BUSY, "系统繁忙，清稍后");
		}
		return commonMap;
	}
	
	public static synchronized Map<Integer, String> getSparrowMap() {
		if (sparrowMap == null) {
			sparrowMap = CommonTool.emptyIntMap();
			XmlModel root = XmlTool.getXmlModelList("config/service/warn-sparrow-msg.xml");
			if (root != null && root.getChildList().size() > 0) {
				for(XmlModel model : root.getChildList()){
					Map<String, String> attributes = model.getAttributes();
					sparrowMap.put(Integer.parseInt(attributes.get("id")), attributes.get("value"));
				}
			}
//			sparrowMap.put(WarnMsgConfig.SPARROW_USER_SUBMIT_SUCCESS, "提交成功");
//			sparrowMap.put(WarnMsgConfig.SPARROW_USER_SUBMIT_FAILURE, "提交失败");
//			sparrowMap.put(WarnMsgConfig.SPARROW_USER_REGIST_SUCCESS, "注册成功");
//			sparrowMap.put(WarnMsgConfig.SPARROW_USER_REGIST_FAILURE, "注册失败，请稍后尝试");
//			sparrowMap.put(WarnMsgConfig.SPARROW_USER_MOBILE_EXISTS, "该手机号已注册");
//			sparrowMap.put(WarnMsgConfig.SPARROW_USER_NOEXISTS, "账号不存在");
//			sparrowMap.put(WarnMsgConfig.SPARROW_USER_LOGININFO_ERROR, "用户名与密码不一致");
		}
		return sparrowMap;
	}
	
	public static String getCommonValue(int msgId) {
		Map<Integer, String> messageMap = WarnMsgConfig.getCommonMap();
		if (messageMap.containsKey(msgId)) {
			return messageMap.get(msgId);
		}
		return "";
	}
	
	public static String getSparrowValue(int msgId) {
		Map<Integer, String> messageMap = WarnMsgConfig.getSparrowMap();
		if (messageMap.containsKey(msgId)) {
			return messageMap.get(msgId);
		}
		return "";
	}
	
}
