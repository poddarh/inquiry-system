package com.royaltechnosoft.inquiry.controller.officer.course;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.service.CourseService;

public class RemoveAction extends ControllerSupport {
	@Autowired private CourseService courseService;
	private Integer courseId;
	
	public String execute() {
		courseService.removeCourse(courseId);
		addActionMessage("Removed Successfully!");
		return SUCCESS;
	}
	
	public Integer getCourseId() {
		return courseId;
	}
	
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
}



