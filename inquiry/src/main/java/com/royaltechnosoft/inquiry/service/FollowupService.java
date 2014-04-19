package com.royaltechnosoft.inquiry.service;

import com.royaltechnosoft.inquiry.model.Followup;

public interface FollowupService extends Service {
	void add(Followup followup, Character inquiryStatus);
}
