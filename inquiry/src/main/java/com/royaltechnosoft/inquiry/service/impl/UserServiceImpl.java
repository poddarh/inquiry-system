package com.royaltechnosoft.inquiry.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.royaltechnosoft.inquiry.dao.PasswordResetRequestDAO;
import com.royaltechnosoft.inquiry.dao.UserDAO;
import com.royaltechnosoft.inquiry.model.PasswordResetRequest;
import com.royaltechnosoft.inquiry.model.User;
import com.royaltechnosoft.inquiry.service.UserService;
import com.royaltechnosoft.inquiry.util.EmailUtil;
import com.royaltechnosoft.inquiry.util.StringUtil;

public class UserServiceImpl extends ServiceSupport implements UserService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private PasswordResetRequestDAO passwordRequestDAO;
	private String appURL;

	// Finds a user record with this email and password and returns it. Returns
	// null if not found.
	public User authenticate(String email, String password) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		return userDAO.findOne(user);
	}

	// Sends an email of a link to reset password to this email and returns
	// true. If email is not registered, false is returned.
	public boolean forgotPassword(String email) {
		User user = new User();
		user.setEmail(email);
		// If user with the given email is found, then send email with the link,
		// if not, then return false
		if (userDAO.count(user) == 1) {
			// Save a password reset request object with a random generated
			// string and the specified email and current time
			PasswordResetRequest passwordRequest = new PasswordResetRequest();
			passwordRequest.setEmail(email);
			passwordRequest.setTimeCreated(new Date());
			passwordRequest.setToken(StringUtil.generateRandomString(32));
			passwordRequestDAO.insert(passwordRequest);

			// Send email with the reset link in the body
			EmailUtil.sendEmail(email, "Reset your password", "Go to " + appURL
					+ "ResetPassword.action?email=" + email + "&token="
					+ passwordRequest.getToken()
					+ "\nLink only valid for a day");
			return true;
		} else
			return false;
	}

	// Resets the user password and sends it to the user via email if a similar
	// PasswordResetRequest is found, if not, then false is returned.
	public boolean resetPassword(PasswordResetRequest passwordRequest) {
		passwordRequest = passwordRequestDAO.findOne(passwordRequest);
		// If record not found, return false
		if (passwordRequest == null) {
			return false;
		}
		// If the request was created more than 24 hours ago, delete the record
		// and return false.
		else if (new Date().getTime()
				- passwordRequest.getTimeCreated().getTime() > 86400000) {
			passwordRequestDAO.destroy(passwordRequest);
			return false;
		}
		// Else reset and email the password
		else {
			User query = new User();
			query.setEmail(passwordRequest.getEmail());

			// Create an update model and set the password to a random 10
			// character generated string
			User update = new User();
			update.setPassword(StringUtil.generateRandomString(10));

			// Reset the password
			userDAO.update(query, update);

			// Send user an email with the updated password
			EmailUtil.sendEmail(passwordRequest.getEmail(),
					"Your new password for Inquiry System",
					"Your new password is " + update.getPassword());

			// Delete the PasswordResetRequest record from the database after
			// resetting the password
			passwordRequestDAO.destroy(passwordRequest.getRequestId());
			return true;
		}
	}

	// Update the user object. Updates the current user to the modifiedUser
	public User updateUserDetails(User user, User modifiedUser) {
		// Restrict user from updating role
		modifiedUser.setRole(null);
		// Update user with specific user id to the modified user
		userDAO.update(user.getUserId(), modifiedUser);
		return userDAO.findOne(user.getUserId());
	}
	
	// Getters and setters
	public String getAppURL() {
		return appURL;
	}

	public void setAppURL(String appURL) {
		this.appURL = appURL;
	}

}
