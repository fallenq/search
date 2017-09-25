package com.service.tool.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.service.config.ToolConfig;
import com.service.model.ResponseModel;
import com.service.tool.CommonTool;
import com.service.tool.nozzle.ResponseServiceI;

@Service("responseImpl")
public class ResponseImpl implements ResponseServiceI {

	private int status = 0;
	private String message = "";
	private Map<String, Object> data = null;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	public ResponseImpl() {
		this.data = CommonTool.emptyMap();
		failStatus();
	}

	public static ResponseImpl getInstance() {
		return new ResponseImpl();
	}

	@Override
	public void emptyData() {
		this.data.clear();
	}

	@Override
	public void setDataValue(String column, String value) {
		this.data.put(column, value);
	}

	@Override
	public void setDataValue(String column, Object value) {
		this.data.put(column, value);
	}

	@Override
	public void successStatus()	{
		this.status = ToolConfig.RESPONSE_SUCCESS;
	}

	@Override
	public void failStatus()	{
		this.status = ToolConfig.RESPONSE_FAILURE;
	}

	@Override
	public boolean isSuccess() {
		return this.status == ToolConfig.RESPONSE_SUCCESS;
	}
	
	protected ResponseModel excuteMap(int status, String message, Map<String, Object> data) {
		return new ResponseModel(status, message, data);
	}

	@Override
	public ResponseModel combineResponse() {
		return this.excuteMap(this.status, this.message, this.data);
	}

	@Override
	public ResponseModel combineResponse(String message) {
		return this.excuteMap(this.status, message, this.data);
	}

	@Override
	public ResponseModel combineResponse(Map<String, Object> data) {
		return this.excuteMap(this.status, this.message, data);
	}

	@Override
	public ResponseModel combineResponse(String message, Map<String, Object> data) {
		return this.excuteMap(this.status, message, data);
	}

}
