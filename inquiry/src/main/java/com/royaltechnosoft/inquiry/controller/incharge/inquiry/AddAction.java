package com.royaltechnosoft.inquiry.controller.incharge.inquiry;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.model.Course;
import com.royaltechnosoft.inquiry.model.Inquiry;
import com.royaltechnosoft.inquiry.service.CourseService;
import com.royaltechnosoft.inquiry.service.InquiryService;

public class AddAction extends ControllerSupport implements ModelDriven<Inquiry>{
	@Autowired private CourseService courseService;
	@Autowired private InquiryService inquiryService;
	private final static List<String> studentLevels;
	private List<Course> courses;
	private Inquiry inquiry;
	
	static{
		studentLevels = new ArrayList<String>();
		for (int i = 1; i <= 12; i++) 
			studentLevels.add("Grade " + i);
		for (int i = 1; i <= 10; i++) 
			studentLevels.add("Semester " + i);
	}
	
	public void prepareMake() {
		courses = courseService.getCourses();
	}
	
	public String execute() {
		System.out.println("Here");
		inquiryService.saveNew(inquiry);
		addActionMessage((getActionProperty("successMsg")));
		return SUCCESS;
	}
	
	public List<String> getStudentlevels() {
		return studentLevels;
	}
	
	public Inquiry getModel() {
		inquiry = new Inquiry();
		return inquiry;
	}
	
	// Auto generated setters and getters
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Inquiry getInquiry() {
		return inquiry;
	}
	
	@VisitorFieldValidator(appendPrefix=false)
	public void setInquiry(Inquiry inquiry) {
		this.inquiry = inquiry;
	}

}



