package com.royaltechnosoft.inquiry.service;

import java.util.Date;
import java.util.List;

import com.royaltechnosoft.inquiry.model.Inquiry;

public interface InquiryService extends Service {
	void saveNew(Inquiry inquiry);
	Inquiry getInquiry(Integer inquiryId);
	List<Inquiry> search(String name, Date newerThan, Date olderThan,
			Integer courseId, Character status, int page);
	int countSearchPages(String name, Date newerThan, Date olderThan,
			Integer courseId, Character status);
	List<Inquiry> listFresh(int page);
	int countFreshPages();
	void close(Integer inquiryId);
	List<Inquiry> listScheduled(int page);
	int countScheduledPages();
	void updateScheduledFollowupDate(Character inquiryStatus, int inquiryId,
			Date scheduledFollowupDate);
}
