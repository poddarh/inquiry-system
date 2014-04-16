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
	
	public List<Course> getCourses() {
		return courseDAO.find(new Course(),"name",CourseDAO.ASCENDING);
	}

	public boolean exists(String name){
		Course course = new Course();
		course.setName(name);
		return courseDAO.count(course)==1;
	}
	
	public void addCourse(String name) {
		Course course = new Course();
		course.setName(name);
		courseDAO.save(course);
	}

	public void removeCourse(Integer courseId) {
		Course course = new Course();
		course.setCourseId(courseId);
		
		Inquiry inquiry = new Inquiry();
		inquiry.setCourse(course);
		inquiryDAO.destroyMany(inquiry);
		
		courseDAO.destroy(course);
	}
	
}
