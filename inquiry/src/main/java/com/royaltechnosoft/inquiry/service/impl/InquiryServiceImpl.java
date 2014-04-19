package com.royaltechnosoft.inquiry.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.dao.FollowupDAO;
import com.royaltechnosoft.inquiry.dao.InquiryDAO;
import com.royaltechnosoft.inquiry.model.Inquiry;
import com.royaltechnosoft.inquiry.service.InquiryService;

public class InquiryServiceImpl extends ServiceSupport implements
		InquiryService {
	@Autowired
	private InquiryDAO inquiryDAO;

	// Saves a new inquiry
	public void saveNew(Inquiry inquiry) {
		inquiry.setStatus(Inquiry.STATUS_FRESH);
		if (inquiry.getDateCreated() == null)
			inquiry.setDateCreated(new Date());
		inquiryDAO.insert(inquiry);
	}

	// Reurns an inquiry object with the inquiryId as passes in the argument
	public Inquiry getInquiry(Integer inquiryId) {
		// Returns the inquiry with the follow-up objects as a member list.
		Inquiry inquiry = inquiryDAO.findOneWithFollowups(inquiryId);
		// Sort the follow-ups with the latest on the top
		Collections.sort(inquiry.getFollowups());
		return inquiry;
	}

	// Searches the database for the inquiries with the matching criteria and
	// returns a list of page of objects
	public List<Inquiry> search(String name, Date newerThan, Date olderThan,
			Integer courseId, Character status, int page) {
		return inquiryDAO.search(name, newerThan, olderThan, courseId, status,
				page);
	}

	// Counts the database for the inquiries with the matching criteria and
	// returns the maximum number of pages
	public int countSearchPages(String name, Date newerThan, Date olderThan,
			Integer courseId, Character status) {
		return inquiryDAO.countPage(name, newerThan, olderThan, courseId,
				status);
	}

	// Returns a list of all the inquiries with the status as fresh
	public List<Inquiry> listFresh(int page) {
		Inquiry inquiry = new Inquiry();
		inquiry.setStatus(Inquiry.STATUS_FRESH);
		// Get the list with similar inquiries in acscending order of date
		// created
		return inquiryDAO.find(inquiry, "dateCreated", InquiryDAO.ASCENDING,
				page);
	}

	// Returns maximum number of pages for all the inquiries with the status as
	// fresh
	public int countFreshPages() {
		Inquiry inquiry = new Inquiry();
		inquiry.setStatus(Inquiry.STATUS_FRESH);
		return inquiryDAO.countPage(inquiry);
	}

	// Changes the status of an inquiry with the given inquiryId to close.
	public void close(Integer inquiryId) {
		Inquiry inquiry = new Inquiry();
		inquiry.setStatus(Inquiry.STATUS_CLOSED);
		inquiryDAO.update(inquiryId, inquiry);
	}
	
	public List<Inquiry> listScheduled(int page) {
		return inquiryDAO.listScheduledBeforeTime(new Date(), page);
	}
	
	public int countScheduledPages() {
		return inquiryDAO.countPageScheduledBeforeTime(new Date());
	}
	
	public void updateScheduledFollowupDate(Character inquiryStatus, int inquiryId, Date scheduledFollowupDate) {
		Inquiry inquiry = new Inquiry();
		if(inquiryStatus == Inquiry.STATUS_FRESH) {
			inquiry.setStatus(Inquiry.STATUS_OPEN);
		}
		inquiry.setScheduledFollowupDate(scheduledFollowupDate);
		inquiryDAO.update(inquiryId, inquiry);
	}
	
}
