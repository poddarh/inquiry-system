package com.royaltechnosoft.inquiry.controller.officer.inquiry;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.model.Course;
import com.royaltechnosoft.inquiry.model.Inquiry;
import com.royaltechnosoft.inquiry.service.InquiryService;

public class AddAction extends ControllerSupport implements ModelDriven<Inquiry>{
	@Autowired private InquiryService inquiryService;
	private Inquiry inquiry;
	
	public String execute() {
		System.out.println(inquiry);
		inquiryService.saveNew(inquiry);
		addActionMessage((getActionProperty("successMsg")));
		return SUCCESS;
	}
	
	public Inquiry getModel() {
		inquiry = new Inquiry();
		return inquiry;
	}
	
	// Auto generated setters and getters
	public Inquiry getInquiry() {
		return inquiry;
	}
	
	@VisitorFieldValidator(appendPrefix=false)
	public void setInquiry(Inquiry inquiry) {
		this.inquiry = inquiry;
	}

}



