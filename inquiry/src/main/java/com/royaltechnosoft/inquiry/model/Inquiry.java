package com.royaltechnosoft.inquiry.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

@Entity
@Table(name = "inquiries")
public class Inquiry implements Model {

	@Id
	@GeneratedValue
	private Integer inquiryID;
	@Column(nullable = false)
	private Date dateCreated;
	@Column(length = 45, nullable = false)
	private String studentName;
	@Column(length = 11, nullable = false)
	private String studentMobile;
	@Column(length = 11)
	private String studentTelephone;
	@Column(length = 100)
	private String studentAddress;
	@Column(length = 45)
	private String studentEmail;
	@Column(length = 75, nullable = false)
	private String institutionName;
	@Column(length = 12, nullable = false)
	private String studentLevel;
	@Column(nullable = false)
	private Integer courseID;
	@Column(length = 75, nullable = false)
	private String subjects;
	@Column(length = 30)
	private String preferedTiming;
	@Column(length = 45)
	private String referredBy;
	@Column(nullable = false)
	private String inquiryHandeledBy;
	
	@Transient
	private List<Followup> followups;
	
	public List<Followup> getFollowups() {
		return followups;
	}

	public void setFollowups(List<Followup> followups) {
		this.followups = followups;
	}

	// Setters and getters
	public Integer getInquiryID() {
		return inquiryID;
	}

	public void setInquiryID(Integer inquiryID) {
		this.inquiryID = inquiryID;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getStudentName() {
		return studentName;
	}

	@RequiredStringValidator(key="fieldErrors.requiredString")
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentMobile() {
		return studentMobile;
	}

	@RequiredStringValidator(key="fieldErrors.requiredString")
	@RegexFieldValidator(trim=true,regex="^\\d{10}$",message="Student's Mobile can only be in digits. Please enter non-0, 10 digit mobile number.")
	public void setStudentMobile(String studentMobile) {
		this.studentMobile = studentMobile;
	}
	
	public String getStudentTelephone() {
		return studentTelephone;
	}

	@RegexFieldValidator(trim=true,regex="^(\\d{3}[- ]?\\d{8}$|\\d{4}[- ]?\\d{7}|\\d{5}[- ]?\\d{6}|[1-9]\\d{7})$",message="Please enter a valid Student's Telephone")
	public void setStudentTelephone(String studentTelephone) {
		this.studentTelephone = studentTelephone;
	}

	public String getStudentAddress() {
		return studentAddress;
	}
	
	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}
	
	public String getStudentEmail() {
		return studentEmail;
	}
	
	@EmailValidator(key="fieldErrors.email")
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getInstitutionName() {
		return institutionName;
	}
	
	@RequiredStringValidator(key="fieldErrors.requiredString")
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getStudentLevel() {
		return studentLevel;
	}

	@RequiredStringValidator(key="fieldErrors.requiredString")
	public void setStudentLevel(String studentLevel) {
		this.studentLevel = studentLevel;
	}

	public Integer getCourseID() {
		return courseID;
	}

	@RequiredFieldValidator(key="fieldErrors.required")
	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	public String getPreferedTiming() {
		return preferedTiming;
	}

	public void setPreferedTiming(String preferedTiming) {
		this.preferedTiming = preferedTiming;
	}

	public String getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}

	public String getInquiryHandeledBy() {
		return inquiryHandeledBy;
	}

	@RequiredStringValidator(key="fieldErrors.requiredString")
	public void setInquiryHandeledBy(String inquiryHandeledBy) {
		this.inquiryHandeledBy = inquiryHandeledBy;
	}

	public String getSubjects() {
		return subjects;
	}

	@RequiredStringValidator(key="fieldErrors.requiredString")
	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}

}
