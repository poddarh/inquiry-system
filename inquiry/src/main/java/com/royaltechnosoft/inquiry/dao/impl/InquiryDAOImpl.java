package com.royaltechnosoft.inquiry.dao.impl;

import com.royaltechnosoft.inquiry.dao.InquiryDAO;
import com.royaltechnosoft.inquiry.model.Inquiry;

public class InquiryDAOImpl extends DAOSupport<Inquiry> implements InquiryDAO{
	public InquiryDAOImpl() {
		setGenericType(Inquiry.class);
	}
}
