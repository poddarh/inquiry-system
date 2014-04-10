package com.royaltechnosoft.inquiry.dao;

import java.util.Date;
import java.util.List;

import com.royaltechnosoft.inquiry.model.Followup;

public interface FollowupDAO extends DAO<Followup> {
	List<Followup> listScheduledBeforeTime(Date time, int maxResults, int firstResult);
	long countScheduledBeforeTime(Date time);
}
