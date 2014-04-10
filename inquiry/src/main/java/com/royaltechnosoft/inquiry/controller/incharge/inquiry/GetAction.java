package com.royaltechnosoft.inquiry.controller.incharge.inquiry;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.model.Inquiry;
import com.royaltechnosoft.inquiry.service.InquiryService;

public class GetAction extends ControllerSupport implements ModelDriven<Inquiry>{
	@Autowired private InquiryService inquiryService;
	
	private Inquiry inquiry;
	private Integer inquiryId;
	
	public String execute() {
		inquiry = inquiryService.getInquiry(inquiryId);
		if(inquiry!=null)
			return SUCCESS;
		else
			return ERROR;
	}
	
	public Inquiry getModel() {
		inquiry = new Inquiry();
		return inquiry;
	}
	
	
	// Auto generated setters and getters
	public Integer getInquiryId() {
		return inquiryId;
	}

	public void setInquiryId(Integer inquiryId) {
		this.inquiryId = inquiryId;
	}

	public Inquiry getInquiry() {
		return inquiry;
	}
	
	public void setInquiry(Inquiry inquiry) {
		this.inquiry = inquiry;
	}

}
