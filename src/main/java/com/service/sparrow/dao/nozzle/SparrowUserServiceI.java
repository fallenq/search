package com.service.sparrow.dao.nozzle;

import com.sparrow.entity.SparrowUser;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户基本信息 服务类
 * </p>
 *
 * @author Fallen
 * @since 2017-09-10
 */
public interface SparrowUserServiceI extends IService<SparrowUser> {

	public SparrowUser getSparrowUserById(int userId);
	public SparrowUser getSparrowUserByMobile(String mobile);
	
}
