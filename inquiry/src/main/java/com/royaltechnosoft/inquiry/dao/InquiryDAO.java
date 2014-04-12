package com.royaltechnosoft.inquiry.dao;

import java.util.Date;
import java.util.List;

import com.royaltechnosoft.inquiry.model.Inquiry;

public interface InquiryDAO extends DAO<Inquiry> {
	List<Inquiry> search(String name, Date newerThan, Date olderThan,
			Integer courseID, Character status, int page);
	int countPages(String name, Date newerThan, Date olderThan,
			Integer courseID, Character status);
}
