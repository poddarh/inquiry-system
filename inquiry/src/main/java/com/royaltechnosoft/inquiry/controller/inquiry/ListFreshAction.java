package com.royaltechnosoft.inquiry.controller.inquiry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.model.Inquiry;
import com.royaltechnosoft.inquiry.service.InquiryService;

public class ListFreshAction extends ControllerSupport {
	@Autowired
	private InquiryService inquiryService;
	private List<Inquiry> inquiries;
	private int page = 1;
	private int totalPages;

	public String execute() {
		// List page of inquiries with the status fresh
		inquiries = inquiryService.listFresh(page);
		
		// Maximum number of pages for inquiries with the status fresh
		totalPages = inquiryService.countFreshPages();
		
		return SUCCESS;
	}
	
	// Getters and setters
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
