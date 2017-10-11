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
	public int compareVersion(SparrowVersion version, String code, Object... params) {
		// TODO 增加判定具体逻辑
		if (version == null) {
			return -3;
		}
		if (version.getCode() == code) {
			return 0;
		}
		if (version.getForceUpdate() > 0) {
			// Need force update
			return -2;
		}
		String[] codeList = StringTool.splitString(code, "\\.");
		String[] versionList = StringTool.splitString(version.getCode(), "\\.");
		int codeIndex = 0;
		for (; codeIndex < codeList.length; codeIndex++) {
			int tempCode = Integer.parseInt(codeList[codeIndex]);
			if (codeIndex + 1 <= versionList.length) {
				int tempVersion = Integer.parseInt(versionList[codeIndex]);
				if (tempVersion < tempCode) {
					return -1;
				} else if (tempVersion == tempCode) {
					continue;
				} else if (tempVersion > tempCode) {
					return 1;
				}
			}
		}
		if (codeIndex <= versionList.length) {
			return 1;
		}
		return -1;
	}

	/**
	 * Compare last version to know whether allow use
	 */
//	@Override
//	public boolean compareVersion(int type, String code) {
//		SparrowVersion version = getLastedVersion(type);
//		if (version != null) {
//			return compareVersion(version, code);
//		}
//		return false;
//	}

}
