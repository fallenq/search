package com.service.sparrow.dao.impl;

import com.sparrow.entity.SparrowUser;
import com.sparrow.mapper.SparrowUserMapper;
import com.service.sparrow.dao.nozzle.SparrowUserServiceI;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基本信息 服务实现类
 * </p>
 *
 * @author Fallen
 * @since 2017-09-10
 */
@Service
public class SparrowUserServiceImpl extends ServiceImpl<SparrowUserMapper, SparrowUser> implements SparrowUserServiceI {

	private SparrowUserMapper sparrowUserMapper;
	
	public SparrowUserMapper getSparrowUserMapper() {
		return sparrowUserMapper;
	}
	
	@Autowired
	public void setSparrowUserMapper(SparrowUserMapper sparrowUserMapper) {
		this.sparrowUserMapper = sparrowUserMapper;
	}

	@Override
	public SparrowUser getSparrowUserByMobile(String mobile) {
		// TODO Auto-generated method stub
		return baseMapper.selectByMobile(mobile, null);
	}

	@Override
	public Integer insertRecord(SparrowUser record) {
		record.setCreatedAt(new Date());
		return sparrowUserMapper.insert(record);
	}

	@Override
	public int insertSelective(SparrowUser record) {
		record.setCreatedAt(new Date());
		return sparrowUserMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(SparrowUser record) {
		record = clearUserMobile(record);
		record.setUpdateAt(new Date());
		return sparrowUserMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SparrowUser record) {
		record = clearUserMobile(record);
		record.setUpdateAt(new Date());
		return sparrowUserMapper.updateByPrimaryKey(record);
	}
	
	private SparrowUser clearUserMobile(SparrowUser record) {
		record.setUserMobile(null);
		return record;
	}
	
}
