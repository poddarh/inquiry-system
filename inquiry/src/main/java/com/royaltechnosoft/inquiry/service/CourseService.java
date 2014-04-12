package com.royaltechnosoft.inquiry.service;

import java.util.List;

import com.royaltechnosoft.inquiry.model.Course;

public interface CourseService extends Service {
	List<Course> getCourses();
	void addCourse(String trim);
	void removeCourse(String courseID);
}
