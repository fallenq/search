package com.service.sparrow.nozzle;

import com.sparrow.entity.SparrowVersion;

public interface SpVersionServiceI {
	public SparrowVersion getLastedVersion(int type);
	public int compareVersion(SparrowVersion version, String code, Object... params);
//	public boolean compareVersion(int type, String code);
}
