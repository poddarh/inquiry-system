package com.royaltechnosoft.inquiry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.dao.FollowupDAO;
import com.royaltechnosoft.inquiry.dao.InquiryDAO;
import com.royaltechnosoft.inquiry.model.Followup;
import com.royaltechnosoft.inquiry.model.Inquiry;
import com.royaltechnosoft.inquiry.service.InquiryService;

public class InquiryServiceImpl extends ServiceSupport implements InquiryService {
	@Autowired private InquiryDAO inquiryDAO;
	@Autowired private FollowupDAO followupDAO;
	
	public void saveNew(Inquiry inquiry) {
		// Persist the inquiry object
		inquiryDAO.save(inquiry);
	}

	public Inquiry getInquiry(Integer inquiryId) {
		Inquiry inquiry = inquiryDAO.findOne(inquiryId);
		
		// Creating a sample followup object for the DAO to search for similar
		Followup followup = new Followup();
		followup.setInquiryID(inquiryId);
		
		// Retriving the list of followups with the newest first
		List<Followup> followups = followupDAO.find(followup, "time", FollowupDAO.DESCENDING);
		
		inquiry.setFollowups(followups);
		return inquiry;
	}
	
}
