package com.service.sparrow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.service.config.enums.ResponseCommonMsgEnum;
import com.service.model.ResponseModel;
import com.service.sparrow.dao.nozzle.SparrowVersionServiceI;
import com.service.sparrow.nozzle.SpVersionServiceI;
import com.service.tool.StringTool;
import com.service.tool.WarnMsgTool;
import com.service.tool.impl.ResponseImpl;
import com.sparrow.entity.SparrowVersion;

@Service
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
			int compareCode = Integer.parseInt(codeList[codeIndex]);
			if (codeIndex + 1 <= versionList.length) {
				int compareVersion = Integer.parseInt(versionList[codeIndex]);
				if (compareVersion < compareCode) {
					return -1;
				} else if (compareVersion == compareCode) {
					continue;
				} else if (compareVersion > compareCode) {
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
	 * Compare lastest version by type
	 */
	@Override
	public ResponseModel compareLastVersion(int type, String code) {
		ResponseImpl responseService = ResponseImpl.getInstance();
		SparrowVersion version = getLastedVersion(type);
		int cmpStatus = compareVersion(version, code);
		if (cmpStatus == 0) {
			responseService.successStatus();
		} else if (cmpStatus == 1) {
			responseService.setMessage(WarnMsgTool.getCommonValue(ResponseCommonMsgEnum.DEVICE_NEED_UPDATE.getValue()));
			responseService.successStatus();
		} else {
			responseService.setMessage(WarnMsgTool.getCommonValue(ResponseCommonMsgEnum.DEVICE_NEED_UPDATE.getValue()));
		}
		if (cmpStatus != 0) {
			String url = "";
			if (version != null) {
				url = version.getUrl();
			}
			responseService.setDataValue("url", url);
		}
		return responseService.combineResponse();
	}

}
