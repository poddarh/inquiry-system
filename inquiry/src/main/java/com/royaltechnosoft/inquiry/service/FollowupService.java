package com.royaltechnosoft.inquiry.service;

import java.util.List;

import com.royaltechnosoft.inquiry.model.Followup;
import com.royaltechnosoft.inquiry.model.Inquiry;

public interface FollowupService extends Service {
	List<Followup> list(int page);
	int getTotalPageNumber();
	void add(Followup followup, Character inquiryStatus);
	List<Followup> getForInquiry(Inquiry inquiry);
}
