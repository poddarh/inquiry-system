package com.royaltechnosoft.inquiry.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.royaltechnosoft.inquiry.dao.InquiryDAO;
import com.royaltechnosoft.inquiry.model.Inquiry;

public class InquiryDAOImpl extends DAOSupport<Inquiry> implements InquiryDAO{
	public InquiryDAOImpl() {
		setGenericType(Inquiry.class);
	}

	public List<Inquiry> search(String name, Date newerThan, Date olderThan,
			Integer courseID, Character status, int maxResults, int firstResult) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(Inquiry.class);
		
		if(name!=null && name.trim().length()>0)
			criteria.add(Restrictions.like("studentName", name, MatchMode.ANYWHERE));
		if(newerThan!=null)
			criteria.add(Restrictions.ge("date", newerThan));
		if(olderThan!=null)
			criteria.add(Restrictions.lt("date", olderThan));
		if(courseID!=null)
			criteria.add(Restrictions.eq("courseID", courseID));
		if(status!=null)
			criteria.add(Restrictions.eq("status", status));
		
		criteria.addOrder(Order.desc("date"));
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		
		@SuppressWarnings("unchecked")
		List<Inquiry> inquiries = criteria.list();
		
		closeSession(session);
		return inquiries;
	}
	
	public long count(String name, Date newerThan, Date olderThan,
			Integer courseID, Character status) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(Inquiry.class);
		
		if(name!=null && name.trim().length()>0)
			criteria.add(Restrictions.like("studentName", name, MatchMode.ANYWHERE));
		if(newerThan!=null)
			criteria.add(Restrictions.ge("date", newerThan));
		if(olderThan!=null)
			criteria.add(Restrictions.lt("date", olderThan));
		if(courseID!=null)
			criteria.add(Restrictions.eq("courseID", courseID));
		if(status!=null)
			criteria.add(Restrictions.eq("status", status));
		
		criteria.setProjection(Projections.rowCount());
		long count = (Long) criteria.uniqueResult();
		
		closeSession(session);
		return count;
	}

	public List<Inquiry> search(char status, int maxResults, int firstResult) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(Inquiry.class);
		
		criteria.add(Restrictions.eq("status", status));
		
		criteria.addOrder(Order.asc("date"));
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		
		@SuppressWarnings("unchecked")
		List<Inquiry> inquiries = criteria.list();
		
		closeSession(session);
		return inquiries;
	}
}
