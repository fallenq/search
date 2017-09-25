package com.service.tool;

import java.util.HashMap;
import java.util.Map;

public class CommonTool {

	public static Map<String, Object> emptyMap() {
		Map<String, Object> emptyMap = new HashMap<String, Object>();
		return emptyMap;
	}

	public static Map<String, String> emptyStringMap() {
		Map<String, String> emptyMap = new HashMap<String, String>();
		return emptyMap;
	}

	public static Map<Integer, String> emptyIntMap() {
		Map<Integer, String> emptyMap = new HashMap<Integer, String>();
		return emptyMap;
	}

	/**
	 * Create random int in range
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int createRandomInt(int min, int max) {
		return (int) (min + Math.random() * (max - min + 1));
	}
}
