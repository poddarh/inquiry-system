package com.royaltechnosoft.inquiry.controller.inquiry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.model.Inquiry;
import com.royaltechnosoft.inquiry.service.InquiryService;

public class ListScheduledAction extends ControllerSupport {
	@Autowired
	private InquiryService inquiryService;
	private List<Inquiry> inquiries;
	private int page = 1;
	private int totalPages;

	public String execute() throws Exception {
		// Get a page of followups that are scheduled for today or earlier and has not been done
		inquiries = inquiryService.listScheduled(page);
		// Get maximum number of pages required for the complete list to be displayed
		totalPages = inquiryService.countScheduledPages();
		return SUCCESS;
	}


	public List<Inquiry> getInquiries() {
		return inquiries;
	}

	public void setInquiries(List<Inquiry> inquiries) {
		this.inquiries = inquiries;
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
