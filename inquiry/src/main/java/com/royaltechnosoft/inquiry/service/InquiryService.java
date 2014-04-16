package com.royaltechnosoft.inquiry.service;

import java.util.Date;
import java.util.List;

import com.royaltechnosoft.inquiry.model.Inquiry;

public interface InquiryService extends Service {
	void saveNew(Inquiry inquiry);
	Inquiry getInquiry(Integer inquiryId);
	List<Inquiry> search(String name, Date newerThan, Date olderThan,
			Integer courseId, Character status, int page);
	int countPages(String name, Date newerThan, Date olderThan,
			Integer courseId, Character status);
	List<Inquiry> listFresh(int page);
	int getFreshPages();
	void close(Integer inquiryId);
}
