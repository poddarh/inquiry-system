package com.royaltechnosoft.inquiry.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.royaltechnosoft.inquiry.dao.FollowupDAO;
import com.royaltechnosoft.inquiry.model.Followup;

public class FollowupDAOImpl extends DAOSupport<Followup> implements FollowupDAO{
	public FollowupDAOImpl() {
		setGenericType(Followup.class);
	}

	public List<Followup> listScheduledBeforeTime(Date time, int maxResults, int firstResult) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(Followup.class);
		criteria.add(Restrictions.le("nextScheduledDate", time));
		criteria.add(Restrictions.eq("isNextPending", true));
		criteria.addOrder(Order.desc("time"));
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		@SuppressWarnings("unchecked")
		List<Followup> followups = criteria.list();
		closeSession(session);
		return followups;
	}

	public long countScheduledBeforeTime(Date time) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(Followup.class);
		criteria.setProjection(Projections.rowCount());
		criteria.add(Restrictions.le("nextScheduledDate", time));
		long count = (Long) criteria.uniqueResult();
		closeSession(session);
		return count;
	}
}
