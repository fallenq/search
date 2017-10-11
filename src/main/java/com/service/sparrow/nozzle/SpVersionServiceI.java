package com.service.sparrow.nozzle;

import com.sparrow.entity.SparrowVersion;

public interface SpVersionServiceI {
	public SparrowVersion getLastedVersion(int type);
	public boolean compareVersion(SparrowVersion version, String versionCode);
}
