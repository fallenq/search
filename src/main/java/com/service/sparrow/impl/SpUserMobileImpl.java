package com.service.sparrow.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.service.sparrow.dao.nozzle.SparrowUserMobileServiceI;
import com.service.sparrow.nozzle.SpUserMobileServiceI;
import com.sparrow.entity.SparrowUserMobile;
@Service("spUserMobileImpl")
public class SpUserMobileImpl implements SpUserMobileServiceI {

	@Autowired
	private SparrowUserMobileServiceI mobileService;
	
	/**
	 * Insert record
	 */
	@Override
	public int insert(SparrowUserMobile record) {
		record.setCreatedAt(new Date());
		boolean insertRes = mobileService.insert(record);
		return insertRes? record.getId(): 0;
	}

	/**
	 * Update record by id
	 */
	@Override
	public int updateById(SparrowUserMobile record) {
		record.setUpdateAt(new Date());
		boolean updateRes = mobileService.updateById(record);
		return updateRes? record.getId(): 0;
	}

	/**
	 * Get mobile of user by mobile
	 */
	@Override
	public SparrowUserMobile getUserMobileByMobile(String mobile) {
		return mobileService.selectOne(new EntityWrapper<SparrowUserMobile>().eq("mobile", mobile));
	}

}
