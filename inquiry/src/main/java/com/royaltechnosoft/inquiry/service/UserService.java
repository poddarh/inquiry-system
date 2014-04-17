package com.royaltechnosoft.inquiry.service;

import com.royaltechnosoft.inquiry.model.PasswordResetRequest;
import com.royaltechnosoft.inquiry.model.User;

public interface UserService extends Service {
	User authenticate(String email, String password);
	boolean forgotPassword(String email);
	boolean resetPassword(PasswordResetRequest passwordRequest);
	User updateUserDetails(User user, User modifiedUser);
}
