package com.royaltechnosoft.inquiry.controller.inquiry;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.model.Inquiry;
import com.royaltechnosoft.inquiry.service.InquiryService;

public class GetAction extends ControllerSupport {
	@Autowired
	private InquiryService inquiryService;
	private Inquiry inquiry;
	private Integer inquiryId;

	public String execute() {
		// Find and retrieve an inquiry using inquiryId
		inquiry = inquiryService.getInquiry(inquiryId);
		
		if (inquiry != null)
			return SUCCESS;
		else
			return ERROR;
	}

	// Getters and setters
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
