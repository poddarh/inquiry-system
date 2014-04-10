package com.royaltechnosoft.inquiry.dao.impl;

import com.royaltechnosoft.inquiry.dao.CourseDAO;
import com.royaltechnosoft.inquiry.model.Course;

public class CourseDAOImpl extends DAOSupport<Course> implements CourseDAO{
	public CourseDAOImpl() {
		setGenericType(Course.class);
	}
}
