package com.royaltechnosoft.inquiry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.dao.UserDAO;
import com.royaltechnosoft.inquiry.model.User;
import com.royaltechnosoft.inquiry.service.UserService;

public class UserServiceImpl extends ServiceSupport implements UserService {
	@Autowired private UserDAO userDAO;

	public void addNewUser(User user) {
		userDAO.save(user);
	}

	public User getUserById(Integer userId) {
		return userDAO.findOne(userId);
	}

	public User authenticate(String email, String password) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		return userDAO.findOne(user);
	}
	
}
