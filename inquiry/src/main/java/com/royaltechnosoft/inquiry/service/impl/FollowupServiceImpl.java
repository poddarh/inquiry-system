package com.royaltechnosoft.inquiry.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.dao.FollowupDAO;
import com.royaltechnosoft.inquiry.model.Followup;
import com.royaltechnosoft.inquiry.service.FollowupService;

public class FollowupServiceImpl extends ServiceSupport implements
		FollowupService {
	@Autowired
	private FollowupDAO followupDAO;
	private final int MAX_RESULTS = 15;

	public List<Followup> list(int page) {
		int firstResult = (page - 1) * MAX_RESULTS;
		return followupDAO.listScheduledBeforeTime(new Date(), MAX_RESULTS,
				firstResult);
	}

	public int getTotalPageNumber() {
		long totalResults = followupDAO.countScheduledBeforeTime(new Date());

		if (totalResults % MAX_RESULTS == 0)
			return (int) (totalResults / MAX_RESULTS);
		else
			return (int) (totalResults / MAX_RESULTS) + 1;
	}

	public void save(Followup followup) {
		followup.setTime(new Date());
		followupDAO.save(followup);
	}

}
