package com.royaltechnosoft.inquiry.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.royaltechnosoft.inquiry.dao.InquiryDAO;
import com.royaltechnosoft.inquiry.model.Inquiry;
import com.royaltechnosoft.inquiry.util.DAOUtil;

public class InquiryDAOImpl extends DAOSupport<Inquiry> implements InquiryDAO{
	public InquiryDAOImpl() {
		setGenericType(Inquiry.class);
	}

	public List<Inquiry> search(String name, Date newerThan, Date olderThan,
			Integer courseID, Character status, int page) {
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
		criteria.setFirstResult((page-1)*LIMIT_PER_PAGE);
		criteria.setMaxResults(LIMIT_PER_PAGE);
		
		@SuppressWarnings("unchecked")
		List<Inquiry> inquiries = criteria.list();
		
		closeSession(session);
		return inquiries;
	}
	
	public int countPage(String name, Date newerThan, Date olderThan,
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
		int pages = getPageCount((Long) criteria.uniqueResult());
		
		closeSession(session);
		return pages;
	}

	public Inquiry findOneWithFollowups(Inquiry model) {
		Session session = getSession();
		Criteria criteria = DAOUtil.createCriteria(model, session);
		Inquiry inquiry = (Inquiry) criteria.uniqueResult();
		inquiry.getFollowups().size();
		closeSession(session);
		return inquiry;
	}

	public Inquiry findOneWithFollowups(Integer inquiryID) {
		Session session = getSession();
		Criteria criteria = DAOUtil.createCriteriaFromId(inquiryID, Inquiry.class, session);
		Inquiry inquiry =  (Inquiry) criteria.uniqueResult();
		inquiry.getFollowups().size();
		closeSession(session);
		return inquiry;
	}

}
