package com.service.model;

import java.util.Map;

import com.service.tool.CommonTool;

public class ResponseModel {
	private Integer status = 0;
	private String message = null;
	private Map<String, Object> data = null;
	
	public ResponseModel(int status, String message, Map<String, Object> data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public boolean isAvailable() {
		if(this.status instanceof Integer == false) {
			return false;
		}
		if(this.message instanceof String == false) {
			return false;
		}
		if(this.data instanceof Map == false) {
			return false;
		}
		return true;
	}
	
	public Map<String, Object> transform(){
		Map<String, Object> map = CommonTool.emptyMap();
		map.put("status", Integer.toString(this.status));
		if (message instanceof String) {
			map.put("msg", this.message);
		} else {
			map.put("msg", "");
		}
		if (data instanceof Map) {
			map.put("data", data);
		} else {
			map.put("data", CommonTool.emptyMap());
		}
		return map;
	}
}
