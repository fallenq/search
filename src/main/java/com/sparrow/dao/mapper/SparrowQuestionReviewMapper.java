package com.sparrow.dao.mapper;

import com.sparrow.dao.model.SparrowQuestionReview;

public interface SparrowQuestionReviewMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sparrow_question_review
	 * @mbg.generated  Sat Sep 02 11:40:28 CST 2017
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sparrow_question_review
	 * @mbg.generated  Sat Sep 02 11:40:28 CST 2017
	 */
	int insert(SparrowQuestionReview record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sparrow_question_review
	 * @mbg.generated  Sat Sep 02 11:40:28 CST 2017
	 */
	int insertSelective(SparrowQuestionReview record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sparrow_question_review
	 * @mbg.generated  Sat Sep 02 11:40:28 CST 2017
	 */
	SparrowQuestionReview selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sparrow_question_review
	 * @mbg.generated  Sat Sep 02 11:40:28 CST 2017
	 */
	int updateByPrimaryKeySelective(SparrowQuestionReview record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sparrow_question_review
	 * @mbg.generated  Sat Sep 02 11:40:28 CST 2017
	 */
	int updateByPrimaryKey(SparrowQuestionReview record);
}