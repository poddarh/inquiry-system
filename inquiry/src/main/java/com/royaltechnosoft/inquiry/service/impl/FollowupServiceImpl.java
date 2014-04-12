package com.royaltechnosoft.inquiry.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.dao.CourseDAO;
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
	@Autowired
	private CourseDAO courseDAO;
	private final int MAX_RESULTS = 15;

	public List<Followup> list(int page) {
		int firstResult = (page - 1) * MAX_RESULTS;
		List<Followup> followups = followupDAO.listScheduledBeforeTime(new Date(), MAX_RESULTS, firstResult);
		
		// Code to retrieve the inquiry objects for the followups
		for (Followup followup : followups) {
			Inquiry inquiry = inquiryDAO.findOne(followup.getInquiryID());
			inquiry.setCourse(courseDAO.findOne(inquiry.getCourseID()));
			followup.setInquiry(inquiry);
		}
		
		System.out.println(followups);
		return followups;
	}

	public int getTotalPageNumber() {
		long totalResults = followupDAO.countScheduledBeforeTime(new Date());

		if (totalResults % MAX_RESULTS == 0)
			return (int) (totalResults / MAX_RESULTS);
		else
			return (int) (totalResults / MAX_RESULTS) + 1;
	}

	public void add(Followup followup, Character inquiryStatus) {
		Followup queryModel = new Followup();
		queryModel.setInquiryID(followup.getInquiryID());
		queryModel.setIsNextPending(true);
		
		Followup updateModel = new Followup();
		updateModel.setIsNextPending(false);
		
		followupDAO.update(queryModel, updateModel);
		
		followup.setIsNextPending(true);
		followup.setTime(new Date());
		followupDAO.save(followup);
		if(followup.getFollowupID()!=null && inquiryStatus==Inquiry.STATUS_FRESH){
			Inquiry inquiry = new Inquiry();
			inquiry.setStatus(Inquiry.STATUS_OPEN);
			inquiryDAO.update(followup.getInquiryID(), inquiry);
		}
	}
	
}
