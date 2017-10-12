package com.service.sparrow.nozzle;

import com.service.model.ResponseModel;
import com.sparrow.entity.SparrowVersion;

public interface SpVersionServiceI {
	public SparrowVersion getLastedVersion(int type);
	public int compareVersion(SparrowVersion version, String code, Object... params);
	public ResponseModel compareLastVersion(int type, String code);
}
