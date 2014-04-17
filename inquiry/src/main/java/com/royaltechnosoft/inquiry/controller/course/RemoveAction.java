package com.royaltechnosoft.inquiry.controller.course;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.service.CourseService;

public class RemoveAction extends ControllerSupport {
	@Autowired
	private CourseService courseService;
	private Integer courseId;

	public String execute() {
		// Remove the course form the database using course id
		courseService.removeCourse(courseId);
		addActionMessage("Removed Successfully!");
		return SUCCESS;
	}
	
	// Getters and setters
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
}
