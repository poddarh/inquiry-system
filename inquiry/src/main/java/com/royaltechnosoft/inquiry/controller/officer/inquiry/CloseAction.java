package com.royaltechnosoft.inquiry.controller.officer.inquiry;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.service.InquiryService;

public class CloseAction extends ControllerSupport {
	@Autowired private InquiryService inquiryService;
	private Integer inquiryId;
	
	public String execute() {
		inquiryService.close(inquiryId);
		addActionMessage((getActionProperty("successMsg")));
		return SUCCESS;
	}

	public Integer getInquiryId() {
		return inquiryId;
	}

	public void setInquiryId(Integer inquiryId) {
		this.inquiryId = inquiryId;
	}
}



