package com.royaltechnosoft.inquiry.controller.officer.course;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.service.CourseService;

public class AddAction extends ControllerSupport {
	@Autowired private CourseService courseService;
	private String courseName;
	
	public String execute() {
		courseService.addCourse(courseName.trim());
		addActionMessage("Added Successfully!");
		return SUCCESS;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	@RequiredStringValidator(key="fieldErrors.requiredString")
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

}



