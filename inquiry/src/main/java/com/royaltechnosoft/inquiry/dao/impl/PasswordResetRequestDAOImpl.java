package com.royaltechnosoft.inquiry.dao.impl;

import com.royaltechnosoft.inquiry.dao.PasswordResetRequestDAO;
import com.royaltechnosoft.inquiry.model.PasswordResetRequest;


public class PasswordResetRequestDAOImpl extends DAOSupport<PasswordResetRequest> implements PasswordResetRequestDAO{
	public PasswordResetRequestDAOImpl() {
		setGenericType(PasswordResetRequest.class);
	}
}
