package com.royaltechnosoft.inquiry.controller.officer.inquiry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.model.Followup;
import com.royaltechnosoft.inquiry.model.Inquiry;
import com.royaltechnosoft.inquiry.service.FollowupService;
import com.royaltechnosoft.inquiry.service.InquiryService;

public class GetAction extends ControllerSupport{
	@Autowired private InquiryService inquiryService;
	@Autowired private FollowupService followupService;
	
	private Inquiry inquiry;
	private String inquiryId;
	private List<Followup> followups;
	
	public String execute() {
		inquiry = inquiryService.getInquiry(inquiryId);
		followups = followupService.getForInquiry(inquiry);
		if(inquiry!=null)
			return SUCCESS;
		else
			return ERROR;
	}
	
	// Auto generated setters and getters
	public String getInquiryId() {
		return inquiryId;
	}

	public void setInquiryId(String inquiryId) {
		this.inquiryId = inquiryId;
	}

	public Inquiry getInquiry() {
		return inquiry;
	}
	
	public void setInquiry(Inquiry inquiry) {
		this.inquiry = inquiry;
	}

	public List<Followup> getFollowups() {
		return followups;
	}

	public void setFollowups(List<Followup> followups) {
		this.followups = followups;
	}

}
