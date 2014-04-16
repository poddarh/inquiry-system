package com.royaltechnosoft.inquiry.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

@Entity
@Table(name = "inquiries")
public class Inquiry implements Model {
	public static final char STATUS_FRESH = 'f';
	public static final char STATUS_OPEN = 'o';
	public static final char STATUS_CLOSED = 'c';
	
	@Id
	@GeneratedValue
	private Integer inquiryID;
	@Column(nullable = false)
	private Date dateCreated;
	@OneToOne(orphanRemoval=true,optional=false,cascade={CascadeType.ALL})
	private Student student;
	@Column(length = 75, nullable = false)
	private String subjects;
	@Column(nullable = false)
	private Character status;
	@ManyToOne(optional=false)
	private Course course;
	@Column(length = 30)
	private String preferredTiming;
	@Column(length = 45)
	private String referredBy;
	@Column(nullable = false)
	private String inquiryHandledBy;
	
	@OneToMany(mappedBy="inquiry",orphanRemoval=true)
	private List<Followup> followups;
	
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
	
	// Setters and getters
	public List<Followup> getFollowups() {
		return followups;
	}
	
	public void setFollowups(List<Followup> followups) {
		this.followups = followups;
	}
	
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

	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	
}
