package com.service.tool;

import java.util.Map;

import com.service.model.ResponseModel;

/**
 * 
 * 
 * @author Fallen
 *
 */
public class ResponseTool {

	public static int SUCCESS = 200;
	public static int FAILURE = 500;

	private int status = 0;
	private String message = "";
	private Map<String, Object> data = null;

	public ResponseTool() {
		this.data = CommonTool.emptyMap();
		failStatus();
	}

	public static ResponseTool getInstance() {
		return new ResponseTool();
	}

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

	public Map<String, Object> responeseData() {
		return this.data;
	}

	public void setData(String column, String value) {
		this.data.put(column, value);
	}

	protected void emptyData() {
		this.data.clear();
	}
	
	public void successStatus()	{
		this.status = ResponseTool.SUCCESS;
	}
	
	public void failStatus()	{
		this.status = ResponseTool.FAILURE;
	}
	
	public boolean isSuccess() {
		return this.status == ResponseTool.SUCCESS;
	}

	protected Map<String, Object> excuteMap(int status, String message, Map<String, Object> data) {
		ResponseModel resposnseModel = new ResponseModel(status, message, data);
		return resposnseModel.transform();
	}

	public Map<String, Object> combineMap() {
		return this.excuteMap(this.status, this.message, this.data);
	}

	public Map<String, Object> combineMap(String message) {
		return this.excuteMap(this.status, message, this.data);
	}

	public Map<String, Object> combineMap(Map<String, Object> data) {
		return this.excuteMap(this.status, this.message, data);
	}

	public Map<String, Object> combineMap(String message, Map<String, Object> data) {
		return this.excuteMap(this.status, message, data);
	}

}
