package com.royaltechnosoft.inquiry.controller.user;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.royaltechnosoft.inquiry.controller.ControllerSupport;
import com.royaltechnosoft.inquiry.model.User;
import com.royaltechnosoft.inquiry.service.UserService;

public class UpdateAction extends ControllerSupport implements SessionAware,
		ModelDriven<User> {
	@Autowired
	private UserService userService;
	private Map<String, Object> session;
	private User modifiedUser;
	private String confirmPassword;
	private String currentPassword;

	public void validate() {
		// Initialize password variable with password entered by the user, if at
		// all entered.
		String newPassword = modifiedUser == null ? null : modifiedUser
				.getPassword();

		// Checks if any of the two, confirm password and password, aren't empty
		if (!isEmpty(confirmPassword) || !isEmpty(newPassword)) {

			if (!isEmpty(currentPassword)) {
				// Checks if the current password entered matches with the
				// actual password
				if (!currentPassword.equals(((User) session.get("user"))
						.getPassword())) {
					addFieldError("currentPassword",
							"Incorrect current password");
				}
			} else
				addFieldError("currentPassword", "Incorrect current password");

			// Checks if new password and confirm password matches, if not then
			// sends an error message
			if (!confirmPassword.equals(newPassword)) {
				addFieldError("password",
						"Password and confirm password do not match");
			}
		}
	}

	// Checks if a string is empty or is null
	private boolean isEmpty(String string) {
		return string == null || string.trim().length() == 0;
	}

	public String execute() {
		User user = (User) session.get("user");
		// Update the user record in the database and also replace it in the session map
		session.put("user", userService.updateUserDetails(user, modifiedUser));
		addActionMessage("Successfully updated.");
		return SUCCESS;
	}

	// Getters and setters
	public User getModel() {
		return modifiedUser = new User();
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public User getModifiedUser() {
		return modifiedUser;
	}

	@VisitorFieldValidator(appendPrefix = false)
	public void setModifiedUser(User modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

}
