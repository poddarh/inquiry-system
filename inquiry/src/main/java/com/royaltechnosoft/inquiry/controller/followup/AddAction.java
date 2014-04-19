package com.royaltechnosoft.inquiry.controller.followup;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.model.Followup;
import com.royaltechnosoft.inquiry.service.FollowupService;
import com.royaltechnosoft.inquiry.service.InquiryService;

public class AddAction extends ControllerSupport implements ModelDriven<Followup> {
	@Autowired
	private FollowupService followupService;
	@Autowired
	private InquiryService inquiryService;
	private Followup followup;
	private Character inquiryStatus;
	private Date scheduledFollowupDate;
	
	public String execute(){
		// Add followup 
		followupService.add(followup,inquiryStatus);
		// Update scheduledFollowupDate and if inquiry status is fresh, change it to open
		inquiryService.updateScheduledFollowupDate(inquiryStatus, followup.getInquiryId(), scheduledFollowupDate);
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

	public Date getScheduledFollowupDate() {
		return scheduledFollowupDate;
	}

	@RequiredFieldValidator(key="fieldErrors.required")
	public void setScheduledFollowupDate(Date scheduledFollowupDate) {
		this.scheduledFollowupDate = scheduledFollowupDate;
	}
	
}
