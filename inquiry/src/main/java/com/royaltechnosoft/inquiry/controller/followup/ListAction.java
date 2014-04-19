package com.royaltechnosoft.inquiry.controller.followup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.model.Followup;
import com.royaltechnosoft.inquiry.service.FollowupService;

public class ListAction extends ControllerSupport {
	@Autowired
	private FollowupService followupService;
	private List<Followup> followups;
	private int page = 1;
	private int totalPages;

	public String execute() throws Exception {
		// Get a page of followups that are scheduled for today or earlier and has not been done
		followups = followupService.list(page);
		// Get maximum number of pages required for the complete list to be displayed
		totalPages = followupService.getTotalPageNumber();
		return SUCCESS;
	}

	public List<Followup> getFollowups() {
		return followups;
	}

	public void setFollowups(List<Followup> followups) {
		this.followups = followups;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

}
