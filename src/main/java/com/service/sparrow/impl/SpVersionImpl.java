package com.service.sparrow.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.service.sparrow.dao.nozzle.SparrowVersionServiceI;
import com.service.sparrow.nozzle.SpVersionServiceI;
import com.service.tool.StringTool;
import com.sparrow.entity.SparrowVersion;

public class SpVersionImpl implements SpVersionServiceI {

	@Autowired
	private SparrowVersionServiceI versionService;

	/**
	 * Get the lasted version by type
	 */
	@Override
	public SparrowVersion getLastedVersion(int type) {
		SparrowVersion version = null;
		switch (type) {
		case 1:
			version = versionService.selectOne(new EntityWrapper<SparrowVersion>().orderBy("code DESC, update_at DESC"));
			break;
		case 2:
			break;
		}
		return version;
	}

	/**
	 * Compare version to know whether allow use
	 */
	@Override
	public boolean compareVersion(SparrowVersion version, String code) {
		// TODO 增加判定具体逻辑
		String[] codeList = StringTool.splitString(code, "\\.");
		String[] versionList = StringTool.splitString(version.getCode(), "\\.");
		for (int codeIndex = 0; codeIndex < codeList.length; codeIndex++) {
			if (codeIndex < versionList.length - 1) {
			}
		}
		return false;
	}

	/**
	 * Compare last version to know whether allow use
	 */
	@Override
	public boolean compareVersion(int type, String code) {
		SparrowVersion version = getLastedVersion(type);
		if (version != null) {
			return compareVersion(version, code);
		}
		return false;
	}

}
