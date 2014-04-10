package com.royaltechnosoft.inquiry.controller.incharge.followup;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.model.Followup;
import com.royaltechnosoft.inquiry.service.FollowupService;

public class AddAction extends ControllerSupport implements ModelDriven<Followup> {
	@Autowired
	private FollowupService followupService;
	private Followup followup;

	public String execute() throws Exception {
		followupService.save(followup);
		return SUCCESS;
	}

	public Followup getModel() {
		followup = new Followup();
		return followup;
	}
	
	public Followup getFollowup() {
		return followup;
	}

	@VisitorFieldValidator(appendPrefix=false)
	public void setFollowup(Followup followup) {
		this.followup = followup;
	}

}
