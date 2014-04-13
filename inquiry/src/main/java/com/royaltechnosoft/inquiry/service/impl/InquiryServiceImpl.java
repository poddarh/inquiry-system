package com.royaltechnosoft.inquiry.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.dao.FollowupDAO;
import com.royaltechnosoft.inquiry.dao.InquiryDAO;
import com.royaltechnosoft.inquiry.model.Followup;
import com.royaltechnosoft.inquiry.model.Inquiry;
import com.royaltechnosoft.inquiry.service.InquiryService;

public class InquiryServiceImpl extends ServiceSupport implements
		InquiryService {
	@Autowired
	private InquiryDAO inquiryDAO;
	@Autowired
	private FollowupDAO followupDAO;
	
	public void saveNew(Inquiry inquiry) {
		inquiry.setStatus(Inquiry.STATUS_FRESH);
		inquiry.setDateCreated(new Date());
		// Persist the inquiry object
		inquiryDAO.save(inquiry);
	}

	public Inquiry getInquiry(Integer inquiryId) {
		Inquiry inquiry = inquiryDAO.findOneWithFollowups(inquiryId);
		Collections.sort(inquiry.getFollowups());
		return inquiry;
	}

	public List<Inquiry> search(String name, Date newerThan, Date olderThan,
			Integer courseID, Character status, int page) {
		return inquiryDAO.search(name, newerThan, olderThan, courseID, status,page);
	}

	public int countPages(String name, Date newerThan, Date olderThan,
			Integer courseID, Character status) {
		return inquiryDAO.countPage(name, newerThan,olderThan, courseID, status);
	}

	public List<Inquiry> listFresh(int page) {
		Inquiry inquiry = new Inquiry();
		inquiry.setStatus(Inquiry.STATUS_FRESH);
		return inquiryDAO.find(inquiry, "date", InquiryDAO.ASCENDING, page);
	}

	public int getFreshPages() {
		Inquiry inquiry = new Inquiry();
		inquiry.setStatus(Inquiry.STATUS_FRESH);
		return inquiryDAO.countPage(inquiry);
	}

	public void close(Integer inquiryID) {
		Followup queryModel = new Followup();
		queryModel.setInquiryID(inquiryID);
		queryModel.setIsNextPending(true);
		
		Followup updateModel = new Followup();
		updateModel.setIsNextPending(false);
		
		followupDAO.update(queryModel, updateModel);
		
		Inquiry inquiry = new Inquiry();
		inquiry.setStatus(Inquiry.STATUS_CLOSED);
		inquiryDAO.update(inquiryID, inquiry);
		
	}

}
