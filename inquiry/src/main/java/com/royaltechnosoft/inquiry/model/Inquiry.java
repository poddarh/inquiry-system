package com.royaltechnosoft.inquiry.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

@Entity
@Table(name = "inquiries")
public class Inquiry implements Model {
	public static final char STATUS_FRESH = 'f';
	public static final char STATUS_OPEN = 'o';
	public static final char STATUS_CLOSED = 'c';
	
	@Id @GeneratedValue
	private Integer inquiryId;
	
	@Column(nullable = false)
	private Date dateCreated;
	
	@OneToOne(orphanRemoval=true,optional=false,cascade={CascadeType.ALL})
	@JoinColumn(name="studentId")
	private Student student;
	
	@Column(length = 45, nullable = false)
	private String subjects;
	
	@Column(nullable = false)
	private Character status;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="courseId")
	private Course course;
	
	@Column(length = 32)
	private String preferredTiming;
	
	@Column(length = 32)
	private String referredBy;
	
	@Column(nullable = false, length = 32)
	private String inquiryHandledBy;
	
	@OneToMany(mappedBy="inquiry",orphanRemoval=true)
	private List<Followup> followups;
	
	@Column
	private Date scheduledFollowupDate;
	
	
	public String getStatusString(){
		if(status!=null){
			switch (status) {
			case 'f':
				return "Fresh";
			case 'o':
				return "Open";
			case 'c':
				return "Closed";
			default:
				return null;
			}
		}else{
			return null;
		}
	}
	
	// Getters and setters
	public Date getScheduledFollowupDate() {
		return scheduledFollowupDate;
	}
	
	public void setScheduledFollowupDate(Date scheduledFollowupDate) {
		this.scheduledFollowupDate = scheduledFollowupDate;
	}
	
	public List<Followup> getFollowups() {
		return followups;
	}
	
	public void setFollowups(List<Followup> followups) {
		this.followups = followups;
	}
	
	public Integer getInquiryId() {
		return inquiryId;
	}

	public void setInquiryId(Integer inquiryId) {
		this.inquiryId = inquiryId;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getPreferredTiming() {
		return preferredTiming;
	}

	@StringLengthFieldValidator(key = "fieldErrors.stringMaxLength", trim = true, maxLength = "32")
	public void setPreferredTiming(String preferredTiming) {
		this.preferredTiming = preferredTiming;
	}

	public String getReferredBy() {
		return referredBy;
	}

	@StringLengthFieldValidator(key = "fieldErrors.stringMaxLength", trim = true, maxLength = "32")
	@RegexFieldValidator(trim=true,regex="^[a-zA-Z ]*$",key="fieldErrors.lettersAndSpaces")
	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}

	public String getInquiryHandledBy() {
		return inquiryHandledBy;
	}

	@RequiredStringValidator(key="fieldErrors.requiredString")
	@StringLengthFieldValidator(key = "fieldErrors.stringMaxLength", trim = true, maxLength = "32")
	@RegexFieldValidator(trim=true,regex="^[a-zA-Z ]*$",key="fieldErrors.lettersAndSpaces")
	public void setInquiryHandledBy(String inquiryHandledBy) {
		this.inquiryHandledBy = inquiryHandledBy;
	}

	public String getSubjects() {
		return subjects;
	}

	@RequiredStringValidator(key="fieldErrors.requiredString")
	@StringLengthFieldValidator(key = "fieldErrors.stringMaxLength", trim = true, maxLength = "45")
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
	
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	
}
