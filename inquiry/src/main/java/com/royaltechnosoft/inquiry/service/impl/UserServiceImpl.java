package com.royaltechnosoft.inquiry.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.royaltechnosoft.inquiry.dao.PasswordRequestDAO;
import com.royaltechnosoft.inquiry.dao.UserDAO;
import com.royaltechnosoft.inquiry.model.PasswordRequest;
import com.royaltechnosoft.inquiry.model.User;
import com.royaltechnosoft.inquiry.service.UserService;
import com.royaltechnosoft.inquiry.util.EmailUtil;
import com.royaltechnosoft.inquiry.util.StringUtil;

public class UserServiceImpl extends ServiceSupport implements UserService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private PasswordRequestDAO passwordRequestDAO;

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

	public boolean forgotPassword(String email) {
		User user = new User();
		user.setEmail(email);
		if (userDAO.count(user) == 1) {
			PasswordRequest passwordRequest = new PasswordRequest();
			passwordRequest.setEmail(email);
			passwordRequest.setTimeCreated(new Date());
			passwordRequest.setToken(StringUtil.generateRandomString(32));
			passwordRequestDAO.save(passwordRequest);
			EmailUtil.sendEmail(email, "Reset your password",
					"Go to http://localhost:8080/inquiry/ResetPassword.action?email="
							+ StringEscapeUtils.escapeHtml4(email) + "&token="
							+ passwordRequest.getToken()+"\nLink only valid for a day");
			return true;
		} else
			return false;
	}

	public boolean resetPassword(PasswordRequest passwordRequest) {
		passwordRequest = passwordRequestDAO.findOne(passwordRequest);
		if (passwordRequest == null) {
			return false;
		} else if (new Date().getTime()
				- passwordRequest.getTimeCreated().getTime() > 86400000) {
			passwordRequestDAO.destroy(passwordRequest);
			return false;
		} else {
			User query = new User();
			query.setEmail(passwordRequest.getEmail());

			User update = new User();
			update.setPassword(StringUtil.generateRandomString(10));

			userDAO.update(query, update);

			EmailUtil.sendEmail(passwordRequest.getEmail(), "Your new password for Inquiry System",
					"Your new password is "+update.getPassword());
			
			passwordRequestDAO.destroy(passwordRequest.getRequestId());
			return true;
		}
	}

	public User updateUserDetails(User user, User modifiedUser) {
		modifiedUser.setRole(null);
		userDAO.update(user.getUserId(), modifiedUser);
		return userDAO.findOne(user.getUserId());
	}

}
