package com.royaltechnosoft.inquiry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.dao.CourseDAO;
import com.royaltechnosoft.inquiry.model.Course;
import com.royaltechnosoft.inquiry.service.CourseService;

public class CourseServiceImpl extends ServiceSupport implements CourseService {
	@Autowired private CourseDAO courseDAO;
	
	public List<Course> getCourses() {
		// Finding all courses using an empty model ensures that all the rows are retrieved.
		return courseDAO.find(new Course());
	}

	public void addCourse(String name) {
		Course course = new Course();
		course.setName(name);
		courseDAO.save(course);
	}

	public void removeCourse(Integer courseID) {
		Course course = new Course();
		course.setCourseId(courseID);
		courseDAO.destroy(course);
	}
	
}
