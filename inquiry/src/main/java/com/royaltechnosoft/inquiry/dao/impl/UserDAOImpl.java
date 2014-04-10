package com.royaltechnosoft.inquiry.dao.impl;

import com.royaltechnosoft.inquiry.dao.UserDAO;
import com.royaltechnosoft.inquiry.model.User;

public class UserDAOImpl extends DAOSupport<User> implements UserDAO{
	public UserDAOImpl() {
		setGenericType(User.class);
	}
}
