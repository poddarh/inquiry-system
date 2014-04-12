package com.royaltechnosoft.inquiry.dao;

import java.util.Date;
import java.util.List;

import com.royaltechnosoft.inquiry.model.Inquiry;

public interface InquiryDAO extends DAO<Inquiry> {
	List<Inquiry> search(String name, Date newerThan, Date olderThan,
			Integer courseID, Character status, int maxResults, int firstResult);
	long count(String name, Date newerThan, Date olderThan,
			Integer courseID, Character status);
	List<Inquiry> search(char status, int maxResults, int firstResult);
}
