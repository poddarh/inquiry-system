package com.royaltechnosoft.inquiry.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

@Entity
@Table(name = "followups")
public class Followup implements Model, Comparable<Followup> {

	@Id
	@GeneratedValue
	private Integer followupId;
	@Column(nullable = false)
	private Date time;
	@Column(length = 200, nullable = false)
	private String remark;
	@Column(nullable = false)
	private Date nextScheduledDate;
	@Column(nullable = false)
	private Boolean isNextPending;
	@ManyToOne
	private Inquiry inquiry;
	
	// Setters and getters
	public Boolean getIsNextPending() {
		return isNextPending;
	}
	
	public void setIsNextPending(Boolean isNextPending) {
		this.isNextPending = isNextPending;
	}

	public Integer getFollowupId() {
		return followupId;
	}
	
	public void setFollowupId(Integer followupId) {
		this.followupId = followupId;
	}
	
	@Transient
	public Integer getInquiryId() {
		if(inquiry!=null)
			return inquiry.getInquiryId();
		else
			return null;
	}
	
	@RequiredFieldValidator(key="fieldErrors.required")
	public void setInquiryId(Integer inquiryId) {
		inquiry = new Inquiry();
		inquiry.setInquiryId(inquiryId);
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

	public Inquiry getInquiry() {
		return inquiry;
	}

	public void setInquiry(Inquiry inquiry) {
		this.inquiry = inquiry;
	}
	
	public String toString() {
		try {
			return JSONUtil.serialize(this);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "Error serializing the object!";
	}

	public int compareTo(Followup o) {
		return -time.compareTo(o.getTime());
	}
}
