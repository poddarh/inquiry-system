package com.royaltechnosoft.inquiry.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.royaltechnosoft.inquiry.model.User;

public class AuthorizeInterceptor implements Interceptor {

	public void destroy() {

	}

	public void init() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> sessionAttributes = invocation
				.getInvocationContext().getSession();
		User user = (User) sessionAttributes.get("user");
		// If user not logged in, rend to login page
		if (user == null) {
			return Action.LOGIN;
		}
		// If user looged in and in correct package, invoke the action requested
		// for
		else {
			String packageName = invocation.getProxy().getConfig()
					.getPackageName();
			if (packageName.equals(user.getRole()))
				return invocation.invoke();
			// If user not authorised for package, send to login page
			else
				return Action.LOGIN;
		}
	}

}
