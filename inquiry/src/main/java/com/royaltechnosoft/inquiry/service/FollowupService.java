package com.royaltechnosoft.inquiry.service;

import java.util.List;

import com.royaltechnosoft.inquiry.model.Followup;

public interface FollowupService extends Service {
	List<Followup> list(int page);
	int getTotalPageNumber();
	void save(Followup followup);
}
