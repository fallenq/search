package com.service.sparrow.nozzle;

import com.sparrow.entity.SparrowVersion;

public interface SpVersionServiceI {
	public SparrowVersion getLastedVersion(int type);
	public boolean compareVersion(SparrowVersion version, String code);
	public boolean compareVersion(int type, String code);
}
