package com.service.sparrow.dao.impl;

import com.sparrow.entity.SparrowUserMobile;
import com.sparrow.mapper.SparrowUserMobileMapper;
import com.service.sparrow.dao.nozzle.SparrowUserMobileServiceI;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户电话列表 服务实现类
 * </p>
 *
 * @author Fallen
 * @since 2017-09-21
 */
@Service
public class SparrowUserMobileServiceImpl extends ServiceImpl<SparrowUserMobileMapper, SparrowUserMobile> implements SparrowUserMobileServiceI {
}
