package com.royaltechnosoft.inquiry.model;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

@Document(collection = "inquiries")
public class Inquiry implements Model {
	public static final char STATUS_FRESH = 'f';
	public static final char STATUS_OPEN = 'o';
	public static final char STATUS_CLOSED = 'c';
	
	@Id
	private String inquiryID;
	@Indexed
	private Date dateCreated;
	@Indexed
	private String studentName;
	private String studentMobile;
	private String studentTelephone;
	private String studentAddress;
	private String studentEmail;
	private String institutionName;
	private String studentLevel;
	private String subjects;
	@Indexed
	private Character status;
	private String preferredTiming;
	private String referredBy;
	private String inquiryHandledBy;
	
	@DBRef
	private Course course;
	@DBRef(lazy=true)
	List<Followup> followups;
	
	public List<Followup> getFollowups() {
		return followups;
	}

	public void setFollowups(List<Followup> followups) {
		this.followups = followups;
	}

	// Setters and getters
	public String getInquiryID() {
		return inquiryID;
	}

	public void setInquiryID(String inquiryID) {
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

	public String getPreferredTiming() {
		return preferredTiming;
	}

	public void setPreferredTiming(String preferredTiming) {
		this.preferredTiming = preferredTiming;
	}

	public String getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}

	public String getInquiryHandledBy() {
		return inquiryHandledBy;
	}

	@RequiredStringValidator(key="fieldErrors.requiredString")
	public void setInquiryHandledBy(String inquiryHandledBy) {
		this.inquiryHandledBy = inquiryHandledBy;
	}

	public String getSubjects() {
		return subjects;
	}

	@RequiredStringValidator(key="fieldErrors.requiredString")
	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
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
