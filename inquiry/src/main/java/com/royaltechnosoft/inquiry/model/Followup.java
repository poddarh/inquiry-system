package com.royaltechnosoft.inquiry.model;

import java.util.Date;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

@Document(collection = "followups")
public class Followup implements Model {
	
	@Id
	private String followupID;
	private Date time;
	private String remark;
	@Indexed
	private Date nextScheduledDate;
	
	@DBRef(lazy=true)
	private Inquiry inquiry;

	public Inquiry getInquiry() {
		return inquiry;
	}

	public void setInquiry(Inquiry inquiry) {
		this.inquiry = inquiry;
	}

	public String getFollowupID() {
		return followupID;
	}
	
	public void setFollowupID(String followupID) {
		this.followupID = followupID;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getRemark() {
		return remark;
	}

	@RequiredStringValidator(key="fieldErrors.requiredString")
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getNextScheduledDate() {
		return nextScheduledDate;
	}

	@RequiredFieldValidator(key="fieldErrors.required")
	public void setNextScheduledDate(Date nextScheduledDate) {
		this.nextScheduledDate = nextScheduledDate;
	}

	public String toString() {
		try {
			return JSONUtil.serialize(this);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "Error serializing the object!";
	}
}
