package com.royaltechnosoft.inquiry.dao.impl;

import com.royaltechnosoft.inquiry.dao.PasswordRequestDAO;
import com.royaltechnosoft.inquiry.model.PasswordRequest;


public class PasswordRequestDAOImpl extends DAOSupport<PasswordRequest> implements PasswordRequestDAO{
	public PasswordRequestDAOImpl() {
		setGenericType(PasswordRequest.class);
	}
}
