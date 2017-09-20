package com.service.tool;

import java.sql.Timestamp;

public class TimeTool {
	
	public static Timestamp getCurrentTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
}
