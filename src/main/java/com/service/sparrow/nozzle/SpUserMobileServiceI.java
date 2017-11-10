package com.service.sparrow.nozzle;

import com.sparrow.entity.SparrowUserMobile;

public interface SpUserMobileServiceI {
	public int insert(SparrowUserMobile record);
	public int updateById(SparrowUserMobile record);
	public boolean delete(int mobileId);
	public SparrowUserMobile getUserMobileByMobile(String mobile);
	public SparrowUserMobile getUserMobileById(int mobileId);
}
