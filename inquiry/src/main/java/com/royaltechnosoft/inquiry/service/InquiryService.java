package com.royaltechnosoft.inquiry.service;

import com.royaltechnosoft.inquiry.model.Inquiry;

public interface InquiryService extends Service {
	void saveNew(Inquiry inquiry);
	Inquiry getInquiry(Integer inquiryId);
}
