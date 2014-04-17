package com.royaltechnosoft.inquiry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.dao.CourseDAO;
import com.royaltechnosoft.inquiry.dao.InquiryDAO;
import com.royaltechnosoft.inquiry.model.Course;
import com.royaltechnosoft.inquiry.model.Inquiry;
import com.royaltechnosoft.inquiry.service.CourseService;

public class CourseServiceImpl extends ServiceSupport implements CourseService {
	@Autowired private CourseDAO courseDAO;
	@Autowired private InquiryDAO inquiryDAO;
	
	// Returns a list of all the courses in the database in ascending order of course names.
	public List<Course> getCourses() {
		return courseDAO.find(new Course(),"name",CourseDAO.ASCENDING);
	}
	
	// Indicates weather a course with the the same name exists in the database.
	public boolean exists(String name){
		Course course = new Course();
		course.setName(name);
		return courseDAO.count(course)==1;
	}
	
	// Adds a course with the course name as the parameter
	public void addCourse(String name) {
		Course course = new Course();
		course.setName(name);
		courseDAO.insert(course);
	}
	
	// Delete a course with this courseId from the database
	public void removeCourse(Integer courseId) {
		Course course = new Course();
		course.setCourseId(courseId);
		
		// Delete inquiries with this course from the database before deleting the course itself
		Inquiry inquiry = new Inquiry();
		inquiry.setCourse(course);
		inquiryDAO.destroyMany(inquiry);
		
		courseDAO.destroy(course);
	}
	
}
