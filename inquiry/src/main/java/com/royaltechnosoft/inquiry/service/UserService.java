package com.royaltechnosoft.inquiry.service;

import com.royaltechnosoft.inquiry.model.PasswordRequest;
import com.royaltechnosoft.inquiry.model.User;

public interface UserService extends Service {
	void addNewUser(User user);
	User getUserById(Integer userId);
	User authenticate(String email, String password);
	boolean forgotPassword(String email);
	boolean resetPassword(PasswordRequest passwordRequest);
	User updateUserDetails(User user, User modifiedUser);
}
