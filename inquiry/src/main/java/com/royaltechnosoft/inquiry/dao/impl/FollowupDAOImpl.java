package com.royaltechnosoft.inquiry.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.royaltechnosoft.inquiry.dao.FollowupDAO;
import com.royaltechnosoft.inquiry.model.Followup;

public class FollowupDAOImpl extends DAOSupport<Followup> implements FollowupDAO{
	public FollowupDAOImpl() {
		setGenericType(Followup.class);
	}

	public List<Followup> listScheduledBeforeTime(Date time, int page) {
		Query query = new Query();
		query.addCriteria(Criteria.where("nextScheduledDate").lt(time));
		query.addCriteria(Criteria.where("isNextPending").is(true));
		query.with(new Sort(DESCENDING, "time"));
		query.skip((page - 1) * LIMIT_PER_PAGE);
		query.limit(LIMIT_PER_PAGE);
		return getMongoOperation().find(query, Followup.class);
	}

	public int countPagesScheduledBeforeTime(Date time) {
		Query query = new Query();
		query.addCriteria(Criteria.where("nextScheduledDate").lt(time));
		query.addCriteria(Criteria.where("isNextPending").is(true));
		return getPageCount(getMongoOperation().count(query,Followup.class));
	}
}


