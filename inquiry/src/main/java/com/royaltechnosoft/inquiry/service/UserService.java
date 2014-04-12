package com.royaltechnosoft.inquiry.service;

import com.royaltechnosoft.inquiry.model.User;

public interface UserService extends Service {
	void addNewUser(User user);
	User getUserById(Integer userId);
	User authenticate(String email, String password);
}
