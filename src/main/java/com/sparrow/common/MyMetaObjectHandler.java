package com.sparrow.common;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;

/**
 * 自定义填充处理器
 */
public class MyMetaObjectHandler extends MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		Object createdAt = metaObject.getValue("createdAt");
		if (null == createdAt) {
			setFieldValByName("createdAt", new Timestamp(System.currentTimeMillis()), metaObject);
		}    
//		insertFill(metaObject);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
//		metaObject.setValue("updateAt", new Timestamp(System.currentTimeMillis()));
		setFieldValByName("updateAt", new Timestamp(System.currentTimeMillis()), metaObject);
	}
}
