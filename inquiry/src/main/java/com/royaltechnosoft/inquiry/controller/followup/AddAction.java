package com.royaltechnosoft.inquiry.controller.followup;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.model.Followup;
import com.royaltechnosoft.inquiry.service.FollowupService;

public class AddAction extends ControllerSupport implements ModelDriven<Followup> {
	@Autowired
	private FollowupService followupService;
	private Followup followup;
	private Character inquiryStatus;
	
	public String execute(){
		// Add followup and if inquiry status in fresh, change it to open
		followupService.add(followup,inquiryStatus);
		return SUCCESS;
	}
	
	// Getters and setters
	
	@VisitorFieldValidator(appendPrefix=false)
	public void setFollowup(Followup followup) {
		this.followup = followup;
	}
	
	@RequiredFieldValidator
	public void setInquiryStatus(Character inquiryStatus) {
		this.inquiryStatus = inquiryStatus;
	}
	
	public Followup getModel() {
		followup = new Followup();
		return followup;
	}
	
	public Followup getFollowup() {
		return followup;
	}

	public Character getInquiryStatus() {
		return inquiryStatus;
	}
	
}
