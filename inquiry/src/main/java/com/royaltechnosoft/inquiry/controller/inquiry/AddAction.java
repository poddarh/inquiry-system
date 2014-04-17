package com.royaltechnosoft.inquiry.controller.inquiry;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.model.Course;
import com.royaltechnosoft.inquiry.model.Inquiry;
import com.royaltechnosoft.inquiry.model.Student;
import com.royaltechnosoft.inquiry.service.InquiryService;

public class AddAction extends ControllerSupport {
	@Autowired
	private InquiryService inquiryService;
	private Inquiry inquiry;
	private Student student;
	private Integer courseId;

	public String execute() {
		inquiry.setStudent(student);
		inquiryService.saveNew(inquiry);

		// Reset values of the inputs
		inquiry = null;
		student = null;
		courseId = null;

		addActionMessage("Successfully added!");
		return SUCCESS;
	}

	// Getters and setters
	public Inquiry getInquiry() {
		return inquiry;
	}

	@VisitorFieldValidator
	public void setInquiry(Inquiry inquiry) {
		this.inquiry = inquiry;
	}

	@RequiredFieldValidator(key = "fieldErrors.required")
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
		if (inquiry == null)
			inquiry = new Inquiry();
		inquiry.setCourse(new Course());
		inquiry.getCourse().setCourseId(courseId);
	}

	public Integer getCourseId() {
		return courseId;
	}

	public Student getStudent() {
		return student;
	}

	@VisitorFieldValidator
	public void setStudent(Student student) {
		this.student = student;
	}

}