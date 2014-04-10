package com.royaltechnosoft.inquiry.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

@Entity
@Table(name = "followups")
public class Followup implements Model {

	@Id
	@GeneratedValue
	private Integer followupID;
	@Column(nullable = false)
	private Integer inquiryID;
	@Column(nullable = false)
	private Date time;
	@Column(length = 200, nullable = false)
	private String remark;
	@Column(nullable = false)
	private Date nextScheduledDate;

	// Setters and getters
	public Integer getFollowupID() {
		return followupID;
	}
	
	public void setFollowupID(Integer followupID) {
		this.followupID = followupID;
	}

	public Integer getInquiryID() {
		return inquiryID;
	}
	
	@RequiredFieldValidator(key="fieldErrors.required")
	public void setInquiryID(Integer inquiryID) {
		this.inquiryID = inquiryID;
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

}
