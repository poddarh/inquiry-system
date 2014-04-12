package com.royaltechnosoft.inquiry.controller.officer.inquiry;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.model.Inquiry;
import com.royaltechnosoft.inquiry.service.InquiryService;

public class SearchAction extends ControllerSupport{
	@Autowired private InquiryService inquiryService;
	private List<Inquiry> inquiries;
	private int page;
	private int totalPages;
	private String name;
	private Date newerThan;
	private Date olderThan;
	private Integer courseID;
	private Character status;
	
	public String execute() {
		page = page==0 ? 1 : page;
		inquiries = inquiryService.search(name, newerThan, olderThan, courseID, status, page);
		totalPages = inquiryService.countPages(name, newerThan, olderThan, courseID, status);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getNewerThan() {
		return newerThan;
	}

	public void setNewerThan(Date newerThan) {
		this.newerThan = newerThan;
	}

	public Date getOlderThan() {
		return olderThan;
	}

	public void setOlderThan(Date olderThan) {
		this.olderThan = olderThan;
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}
	
}



