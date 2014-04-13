package com.royaltechnosoft.inquiry.dao;

import java.util.Date;
import java.util.List;

import com.royaltechnosoft.inquiry.model.Followup;

public interface FollowupDAO extends DAO<Followup> {
	List<Followup> listScheduledBeforeTime(Date time, int page);
	int countPageScheduledBeforeTime(Date time);
}
