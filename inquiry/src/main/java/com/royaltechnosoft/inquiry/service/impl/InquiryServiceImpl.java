package com.royaltechnosoft.inquiry.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.dao.CourseDAO;
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
	@Autowired
	private CourseDAO courseDAO;
	private final int MAX_RESULTS = 15;

	public void saveNew(Inquiry inquiry) {
		inquiry.setStatus(Inquiry.STATUS_FRESH);
		inquiry.setDateCreated(new Date());
		// Persist the inquiry object
		inquiryDAO.save(inquiry);
	}

	public Inquiry getInquiry(Integer inquiryId) {
		Inquiry inquiry = inquiryDAO.findOne(inquiryId);
		if (inquiry != null) {
			// Creating a sample followup object for the DAO to search for similar
			Followup followup = new Followup();
			followup.setInquiryID(inquiryId);
			
			// Retrieving the list of followups with the newest first
			List<Followup> followups = followupDAO.find(followup, "time",FollowupDAO.DESCENDING);
			inquiry.setFollowups(followups);
			
			inquiry.setCourse(courseDAO.findOne(inquiry.getCourseID()));
		}
		return inquiry;
	}

	public List<Inquiry> search(String name, Date newerThan, Date olderThan,
			Integer courseID, Character status, int page) {

		int firstResult = (page - 1) * MAX_RESULTS;
		return inquiryDAO.search(name, newerThan, olderThan, courseID, status,
				MAX_RESULTS, firstResult);
	}

	public int countPages(String name, Date newerThan, Date olderThan,
			Integer courseID, Character status) {

		long totalMatchingResults = inquiryDAO.count(name, newerThan,
				olderThan, courseID, status);
		int totalPages = 0;
		if (totalMatchingResults % MAX_RESULTS == 0)
			totalPages = (int) (totalMatchingResults / MAX_RESULTS);
		else
			totalPages = (int) ((totalMatchingResults / MAX_RESULTS) + 1);

		return totalPages;
	}

	public List<Inquiry> listFresh(int page) {
		int firstResult = (page - 1) * MAX_RESULTS;
		char status = 'f';
		return inquiryDAO.search(status, MAX_RESULTS, firstResult);
	}

	public int getFreshPages() {
		Character status = 'f';
		long totalMatchingResults = inquiryDAO.count(null, null, null, null,
				status);
		int totalPages = 0;
		if (totalMatchingResults % MAX_RESULTS == 0)
			totalPages = (int) (totalMatchingResults / MAX_RESULTS);
		else
			totalPages = (int) ((totalMatchingResults / MAX_RESULTS) + 1);

		return totalPages;
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
