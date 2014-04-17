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

public class FollowupDAOImpl extends DAOSupport<Followup> implements
		FollowupDAO {
	public FollowupDAOImpl() {
		setGenericType(Followup.class);
	}

	// Returns a paged list of all the follow-ups that has the nextScheduled
	// date before the specified date
	public List<Followup> listScheduledBeforeTime(Date time, int page) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(Followup.class);
		
		// Where nextScheduledDate is less than the time specified
		criteria.add(Restrictions.le("nextScheduledDate", time));
		// Where the next is pending
		criteria.add(Restrictions.eq("isNextPending", true));
		// Order it with the oldest first 
		criteria.addOrder(Order.asc("nextScheduledDate"));
		
		// Paginate the response
		criteria.setFirstResult((page - 1) * LIMIT_PER_PAGE);
		criteria.setMaxResults(LIMIT_PER_PAGE);
		
		List<Followup> followups = criteria.list();
		closeSession(session);
		return followups;
	}

	// Returns a count of paged lists of all the follow-ups that has the
	// nextScheduled date before the specified date
	public int countPageScheduledBeforeTime(Date time) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(Followup.class);
		// Return only row count
		criteria.setProjection(Projections.rowCount());
		// Where nextScheduledDate is less than the time specified
		criteria.add(Restrictions.le("nextScheduledDate", time));
		// Where the next is pending
		criteria.add(Restrictions.eq("isNextPending", true));
		
		int pages = getPageCount((Long) criteria.uniqueResult());
		closeSession(session);
		return pages;
	}

}
