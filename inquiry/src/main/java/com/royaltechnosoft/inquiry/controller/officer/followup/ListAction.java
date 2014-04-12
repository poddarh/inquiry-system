package com.royaltechnosoft.inquiry.controller.officer.followup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.model.Followup;
import com.royaltechnosoft.inquiry.service.FollowupService;

public class ListAction extends ControllerSupport {
	@Autowired
	private FollowupService followupService;
	private List<Followup> followups;
	private int page;
	private int totalPages;

	public String execute() throws Exception {
		page = page==0 ? 1 : page;
		followups = followupService.list(page);
		totalPages = followupService.getTotalPageNumber();
		System.out.println(followups);
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
