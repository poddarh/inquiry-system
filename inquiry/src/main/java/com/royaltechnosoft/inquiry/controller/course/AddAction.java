package com.royaltechnosoft.inquiry.controller.course;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.service.CourseService;

public class AddAction extends ControllerSupport {
	@Autowired
	private CourseService courseService;
	private String courseName;

	public String execute() {

		// Check if the course name already exists and send error message if
		// it does
		if (courseService.exists(courseName.trim())) {
			addActionMessage("Already Exists!");
			return INPUT;
		} else {
			// If it doesn't exist, then add new
			courseService.addCourse(courseName.trim());
			// Reset the field value.
			courseName = null;
			addActionMessage("Added Successfully!");
			return SUCCESS;
		}

	}

	// Setters and getters
	@RequiredStringValidator(key = "fieldErrors.requiredString", trim = true)
	@StringLengthFieldValidator(key = "fieldErrors.stringMaxLength", trim = true, maxLength = "32")
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseName() {
		return courseName;
	}

}
