package com.royaltechnosoft.inquiry.service;

import java.util.List;

import com.royaltechnosoft.inquiry.model.Course;

public interface CourseService extends Service {
	List<Course> getCourses();
	boolean exists(String name);
	void addCourse(String name);
	void removeCourse(Integer courseId);
}
