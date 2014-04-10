package com.royaltechnosoft.inquiry.controller.incharge.course;

import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.model.Course;
import com.royaltechnosoft.inquiry.service.CourseService;

public class ManageAction extends ControllerSupport {
	@Autowired private CourseService courseService;
	private List<Course> courses;
	
	private Integer courseId;
	private String courseName;
	
	public void prepareMake(){
		courses = courseService.getCourses();
	}
	
	public String add() {
		courseService.addCourse(courseName.trim());
		addActionMessage("Added Successfully!");
		return SUCCESS;
	}
	
	@SkipValidation
	public String remove() {
		courseService.removeCourse(courseId);
		addActionMessage("Removed Successfully!");
		return SUCCESS;
	}
	
	// Auto generated setters and getters
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}
	
	@RequiredStringValidator(key="fieldErrors.requiredString")
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

}



