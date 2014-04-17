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

	// List all follow-ups scheduled for today or earlier which is yet pending
	public List<Followup> list(int page) {
		List<Followup> followups = followupDAO.listScheduledBeforeTime(
				new Date(), page);

		// Code to retrieve the inquiry objects for the follow-ups
		for (Followup followup : followups) {
			Inquiry inquiry = inquiryDAO.findOne(followup.getInquiryId());
			followup.setInquiry(inquiry);
		}

		return followups;
	}

	// Total number of pages for all follow-ups scheduled for today or earlier
	// which is yet pending
	public int getTotalPageNumber() {
		return followupDAO.countPageScheduledBeforeTime(new Date());
	}

	// Add a new follow-up.
	public void add(Followup followup, Character inquiryStatus) {
		Followup queryModel = new Followup();
		queryModel.setInquiryId(followup.getInquiryId());
		queryModel.setIsNextPending(true);

		Followup updateModel = new Followup();
		updateModel.setIsNextPending(false);

		// Update all follow-up records that matches with the non-null fields in
		// queryModel with the non-null fields in updateModel
		followupDAO.update(queryModel, updateModel);

		// Set next pending and add current time to follow-up and add it to the
		// database
		followup.setIsNextPending(true);
		followup.setTime(new Date());
		followupDAO.insert(followup);

		// Check if the object is successfully saved and if the inquiry status
		// was fresh. If it was, then change it to open and update it in the
		// database
		if (followup.getFollowupId() != null
				&& inquiryStatus == Inquiry.STATUS_FRESH) {
			Inquiry inquiry = new Inquiry();
			inquiry.setStatus(Inquiry.STATUS_OPEN);
			inquiryDAO.update(followup.getInquiryId(), inquiry);
		}
	}

}
