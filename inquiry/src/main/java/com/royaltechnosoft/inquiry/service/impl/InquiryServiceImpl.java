package com.royaltechnosoft.inquiry.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.dao.InquiryDAO;
import com.royaltechnosoft.inquiry.model.Inquiry;
import com.royaltechnosoft.inquiry.service.InquiryService;

public class InquiryServiceImpl extends ServiceSupport implements
		InquiryService {
	@Autowired
	private InquiryDAO inquiryDAO;

	public void saveNew(Inquiry inquiry) {
		inquiry.setStatus(Inquiry.STATUS_FRESH);
		inquiry.setDateCreated(new Date());
		inquiryDAO.save(inquiry);
	}
	
	public Inquiry getInquiry(String inquiryId) {
		Inquiry inquiry = inquiryDAO.findOne(inquiryId);
		return inquiry;
	}

	public List<Inquiry> search(String name, Date newerThan, Date olderThan,
			Integer courseID, Character status, int page) {
		return inquiryDAO.search(name, newerThan, olderThan, courseID, status, page);
	}

	public int countPages(String name, Date newerThan, Date olderThan,
			Integer courseID, Character status) {
		return inquiryDAO.countPages(name, newerThan, olderThan, courseID, status);
	}

	public List<Inquiry> listFresh(int page) {
		Inquiry inquiry = new Inquiry();
		inquiry.setStatus(Inquiry.STATUS_FRESH);
		return inquiryDAO.find(inquiry, page);
	}

	public int getFreshPages() {
		Inquiry inquiry = new Inquiry();
		inquiry.setStatus(Inquiry.STATUS_FRESH);
		return inquiryDAO.countPages(inquiry);
	}

	public void close(String inquiryID) {
		Inquiry inquiry = new Inquiry();
		inquiry.setStatus(Inquiry.STATUS_CLOSED);
		inquiryDAO.update(inquiryID, inquiry);
		
	}

}
