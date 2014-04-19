package com.royaltechnosoft.inquiry.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

@Entity
@Table(name = "followups")
public class Followup implements Model, Comparable<Followup> {

	@Id	@GeneratedValue
	private Integer followupId;
	@Column(nullable = false)
	private Date time;
	@Column(length = 100, nullable = false)
	private String remark;
	@ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="inquiryId")
	private Inquiry inquiry;
	
	// Compares the objects based on time field. Used by java.util.Collections.sort()
	public int compareTo(Followup o) {
		return -time.compareTo(o.getTime());
	}
	
	// Getters and setters
	public Integer getFollowupId() {
		return followupId;
	}
	
	public void setFollowupId(Integer followupId) {
		this.followupId = followupId;
	}
	
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
	@StringLengthFieldValidator(key = "fieldErrors.stringMaxLength", trim = true, maxLength = "100")
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Inquiry getInquiry() {
		return inquiry;
	}

	public void setInquiry(Inquiry inquiry) {
		this.inquiry = inquiry;
	}
}
