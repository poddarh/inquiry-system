package com.royaltechnosoft.inquiry.controller.inquiry;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.service.InquiryService;

public class CloseAction extends ControllerSupport {
	@Autowired
	private InquiryService inquiryService;
	private Integer inquiryId;

	public String execute() {
		// Update the status for the inquiry for this inquiryId as closed
		inquiryService.close(inquiryId);
		addActionMessage("Successfully closed!");
		return SUCCESS;
	}

	// Getters and setters
	public Integer getInquiryId() {
		return inquiryId;
	}

	public void setInquiryId(Integer inquiryId) {
		this.inquiryId = inquiryId;
	}
}
