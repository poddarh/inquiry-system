package com.royaltechnosoft.inquiry.dao;

import java.util.Date;
import java.util.List;

import com.royaltechnosoft.inquiry.model.Inquiry;

public interface InquiryDAO extends DAO<Inquiry> {
	List<Inquiry> search(String name, Date newerThan, Date olderThan,
			Integer courseId, Character status, int page);
	int countPage(String name, Date newerThan, Date olderThan,
			Integer courseId, Character status);
	public Inquiry findOneWithFollowups(Inquiry model);
	public Inquiry findOneWithFollowups(Integer inquiryId);
}
