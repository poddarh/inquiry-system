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

	public List<Followup> listScheduledBeforeTime(Date time, int page) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(Followup.class);
		criteria.add(Restrictions.le("nextScheduledDate", time));
		criteria.add(Restrictions.eq("isNextPending", true));
		criteria.addOrder(Order.desc("time"));
		criteria.setFirstResult((page-1)*LIMIT_PER_PAGE);
		criteria.setMaxResults(LIMIT_PER_PAGE);
		@SuppressWarnings("unchecked")
		List<Followup> followups = criteria.list();
		closeSession(session);
		return followups;
	}

	public int countPageScheduledBeforeTime(Date time) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(Followup.class);
		criteria.setProjection(Projections.rowCount());
		criteria.add(Restrictions.le("nextScheduledDate", time));
		criteria.add(Restrictions.eq("isNextPending", true));
		int pages = getPageCount((Long) criteria.uniqueResult());
		closeSession(session);
		return pages;
	}

}
