package com.royaltechnosoft.inquiry.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.dao.FollowupDAO;
import com.royaltechnosoft.inquiry.dao.InquiryDAO;
import com.royaltechnosoft.inquiry.model.Followup;
import com.royaltechnosoft.inquiry.model.Inquiry;
import com.royaltechnosoft.inquiry.service.FollowupService;

public class FollowupServiceImpl extends ServiceSupport implements
		FollowupService {
	@Autowired
	private FollowupDAO followupDAO;
	@Autowired
	private InquiryDAO inquiryDAO;

	public List<Followup> list(int page) {
		List<Followup> followups = followupDAO.listScheduledBeforeTime(new Date(), page);
		
		// Code to retrieve the inquiry objects for the followups
		for (Followup followup : followups) {
			Inquiry inquiry = inquiryDAO.findOne(followup.getInquiryId());
			followup.setInquiry(inquiry);
		}
		
		return followups;
	}

	public int getTotalPageNumber() {
		return followupDAO.countPageScheduledBeforeTime(new Date());
	}

	public void add(Followup followup, Character inquiryStatus) {
		Followup queryModel = new Followup();
		queryModel.setInquiryId(followup.getInquiryId());
		queryModel.setIsNextPending(true);
		
		Followup updateModel = new Followup();
		updateModel.setIsNextPending(false);
		
		followupDAO.update(queryModel, updateModel);
		
		followup.setIsNextPending(true);
		followup.setTime(new Date());
		followupDAO.save(followup);
		if(followup.getFollowupId()!=null && inquiryStatus==Inquiry.STATUS_FRESH){
			Inquiry inquiry = new Inquiry();
			inquiry.setStatus(Inquiry.STATUS_OPEN);
			inquiryDAO.update(followup.getInquiryId(), inquiry);
		}
	}
	
}
