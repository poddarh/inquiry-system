package com.royaltechnosoft.inquiry.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.royaltechnosoft.inquiry.dao.InquiryDAO;
import com.royaltechnosoft.inquiry.model.Inquiry;

public class InquiryDAOImpl extends DAOSupport<Inquiry> implements InquiryDAO{
	public InquiryDAOImpl() {
		setGenericType(Inquiry.class);
	}
	
	public List<Inquiry> search(String name, Date newerThan, Date olderThan,
			Integer courseID, Character status, int page) {
		Query query = new Query();
		
		if(name!=null && name.trim().length()>0)
			query.addCriteria(Criteria.where("studentName").regex(name));
		if(newerThan!=null)
			query.addCriteria(Criteria.where("date").gte(newerThan));
		if(olderThan!=null)
			query.addCriteria(Criteria.where("date").lt(olderThan));
		if(courseID!=null)
			query.addCriteria(Criteria.where("courseID").is(courseID));
		if(status!=null)
			query.addCriteria(Criteria.where("status").is(status));
		
		query.with(new Sort(DESCENDING, "date"));
		query.skip((page - 1) * LIMIT_PER_PAGE);
		query.limit(LIMIT_PER_PAGE);
		
		return getMongoOperation().find(query, Inquiry.class);
	
	}
	
	public int countPages(String name, Date newerThan, Date olderThan,
			Integer courseID, Character status) {
		Query query = new Query();
		
		if(name!=null && name.trim().length()>0)
			query.addCriteria(Criteria.where("studentName").regex(name));
		if(newerThan!=null)
			query.addCriteria(Criteria.where("date").gte(newerThan));
		if(olderThan!=null)
			query.addCriteria(Criteria.where("date").lt(olderThan));
		if(courseID!=null)
			query.addCriteria(Criteria.where("courseID").is(courseID));
		if(status!=null)
			query.addCriteria(Criteria.where("status").is(status));
		
		return getPageCount(getMongoOperation().count(query, Inquiry.class));
	}

}
