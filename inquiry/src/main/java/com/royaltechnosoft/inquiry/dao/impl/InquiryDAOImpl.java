package com.royaltechnosoft.inquiry.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.royaltechnosoft.inquiry.dao.InquiryDAO;
import com.royaltechnosoft.inquiry.model.Inquiry;
import com.royaltechnosoft.inquiry.util.DAOUtil;

public class InquiryDAOImpl extends DAOSupport<Inquiry> implements InquiryDAO{
	public InquiryDAOImpl() {
		setGenericType(Inquiry.class);
	}

	public List<Inquiry> search(String name, Date newerThan, Date olderThan,
			Integer courseId, Character status, int page) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(Inquiry.class);
		
		if(name!=null && name.trim().length()>0){
			criteria.createAlias("student", "student");
			criteria.add(Restrictions.like("student.name", name, MatchMode.ANYWHERE));
		}
		if(newerThan!=null)
			criteria.add(Restrictions.ge("dateCreated", newerThan));
		if(olderThan!=null)
			criteria.add(Restrictions.lt("dateCreated", olderThan));
		if(courseId!=null)
			criteria.add(Restrictions.eq("course.courseId", courseId));
		if(status!=null)
			criteria.add(Restrictions.eq("status", status));
		
		criteria.addOrder(Order.desc("dateCreated"));
		criteria.setFirstResult((page-1)*LIMIT_PER_PAGE);
		criteria.setMaxResults(LIMIT_PER_PAGE);
		
		@SuppressWarnings("unchecked")
		List<Inquiry> inquiries = criteria.list();
		
		closeSession(session);
		return inquiries;
	}
	
	public int countPage(String name, Date newerThan, Date olderThan,
			Integer courseId, Character status) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(Inquiry.class);
		
		if(name!=null && name.trim().length()>0){
			criteria.createAlias("student", "student", JoinType.LEFT_OUTER_JOIN);
			criteria.add(Restrictions.like("student.name", name, MatchMode.ANYWHERE));
		}
		if(newerThan!=null)
			criteria.add(Restrictions.ge("dateCreated", newerThan));
		if(olderThan!=null)
			criteria.add(Restrictions.lt("dateCreated", olderThan));
		if(courseId!=null)
			criteria.add(Restrictions.eq("course.courseId", courseId));
		if(status!=null)
			criteria.add(Restrictions.eq("status", status));
		
		criteria.setProjection(Projections.rowCount());
		int pages = getPageCount((Long) criteria.uniqueResult());
		
		closeSession(session);
		return pages;
	}
	
	// Returns the requested inquiry model using queryModel with the follow-ups list populated
	public Inquiry findOneWithFollowups(Inquiry queryModel) {
		Session session = getSession();
		Criteria criteria = DAOUtil.createCriteria(queryModel, session);
		Inquiry inquiry = (Inquiry) criteria.uniqueResult();
		inquiry.getFollowups().size();
		closeSession(session);
		return inquiry;
	}

	// Returns the requested inquiry model using inquiryId with the follow-ups list populated
	public Inquiry findOneWithFollowups(Integer inquiryId) {
		Session session = getSession();
		Criteria criteria = DAOUtil.createCriteriaFromId(inquiryId, Inquiry.class, session);
		Inquiry inquiry =  (Inquiry) criteria.uniqueResult();
		inquiry.getFollowups().size();
		closeSession(session);
		return inquiry;
	}

}
