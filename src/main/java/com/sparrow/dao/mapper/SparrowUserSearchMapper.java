package com.sparrow.dao.mapper;

import com.sparrow.dao.model.SparrowUserSearch;

public interface SparrowUserSearchMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sparrow_user_search
	 * @mbg.generated  Sat Sep 02 11:40:28 CST 2017
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sparrow_user_search
	 * @mbg.generated  Sat Sep 02 11:40:28 CST 2017
	 */
	int insert(SparrowUserSearch record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sparrow_user_search
	 * @mbg.generated  Sat Sep 02 11:40:28 CST 2017
	 */
	int insertSelective(SparrowUserSearch record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sparrow_user_search
	 * @mbg.generated  Sat Sep 02 11:40:28 CST 2017
	 */
	SparrowUserSearch selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sparrow_user_search
	 * @mbg.generated  Sat Sep 02 11:40:28 CST 2017
	 */
	int updateByPrimaryKeySelective(SparrowUserSearch record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sparrow_user_search
	 * @mbg.generated  Sat Sep 02 11:40:28 CST 2017
	 */
	int updateByPrimaryKey(SparrowUserSearch record);
}