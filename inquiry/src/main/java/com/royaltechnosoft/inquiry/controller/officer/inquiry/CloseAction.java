package com.royaltechnosoft.inquiry.controller.officer.inquiry;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.service.InquiryService;

public class CloseAction extends ControllerSupport {
	@Autowired private InquiryService inquiryService;
	private Integer inquiryID;
	
	public String execute() {
		inquiryService.close(inquiryID);
		addActionMessage((getActionProperty("successMsg")));
		return SUCCESS;
	}

	public Integer getInquiryID() {
		return inquiryID;
	}

	public void setInquiryID(Integer inquiryID) {
		this.inquiryID = inquiryID;
	}
}



