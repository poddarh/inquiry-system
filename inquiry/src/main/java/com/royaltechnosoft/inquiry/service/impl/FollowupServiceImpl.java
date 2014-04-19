package com.royaltechnosoft.inquiry.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.dao.FollowupDAO;
import com.royaltechnosoft.inquiry.model.Followup;
import com.royaltechnosoft.inquiry.service.FollowupService;

public class FollowupServiceImpl extends ServiceSupport implements
		FollowupService {
	@Autowired
	private FollowupDAO followupDAO;

	// Add a new follow-up.
	public void add(Followup followup, Character inquiryStatus) {
		// Set the current time to follow-up and add it to the database
		followup.setTime(new Date());
		followupDAO.insert(followup);
	}

}
