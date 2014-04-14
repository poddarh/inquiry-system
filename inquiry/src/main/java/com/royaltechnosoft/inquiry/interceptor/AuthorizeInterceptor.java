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
		Map<String, Object> sessionAttributes = invocation.getInvocationContext().getSession();
        User user = (User) sessionAttributes.get("user");
        if(user == null){
            return Action.LOGIN;
        }else{
        	String packageName = invocation.getProxy().getConfig().getPackageName();  
        	if(packageName.equals(user.getRole()))
            	return invocation.invoke();
            else
            	return Action.LOGIN;
        }
	}

}
