package com.royaltechnosoft.inquiry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

@Entity
@Table(name = "students")
public class Student {
	@Id
	@GeneratedValue
	private Integer studentId;
	@Column(length = 32, nullable = false)
	private String name;
	@Column(length = 10, nullable = false)
	private String mobile;
	@Column(length = 12)
	private String telephone;
	@Column(length = 100)
	private String address;
	@Column(length = 45)
	private String email;
	@Column(length = 45, nullable = false)
	private String institutionName;
	@Column(length = 3, nullable = false)
	private String educationLevel;

	// Getters and setters

	public String getEducationLevelString() {
		if (educationLevel == null)
			return null;
		String educationLevelString;

		if (educationLevel.startsWith("g"))
			educationLevelString = "Grade ";
		else
			educationLevelString = "Semester ";

		educationLevelString += Integer.parseInt(educationLevel.substring(1));
		return educationLevelString;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	@RequiredStringValidator(key = "fieldErrors.requiredString")
	@StringLengthFieldValidator(key = "fieldErrors.stringMaxLength", trim = true, maxLength = "32")
	@RegexFieldValidator(trim = true, regex = "^[a-zA-Z ]*$", key = "fieldErrors.lettersAndSpaces")
	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	@RequiredStringValidator(key = "fieldErrors.requiredString")
	@RegexFieldValidator(trim = true, regex = "^\\d{10}$", 
			message = "Student's Mobile can only be in digits. Please enter non-0, 10 digit mobile number.")
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	@RegexFieldValidator(trim = true, regex = "^(\\d{3}[- ]?\\d{8}$|\\d{4}[- ]?\\d{7}|\\d{5}[- ]?\\d{6}|[1-9]\\d{7})$", 
			message = "Please enter a valid Student's Telephone")
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	@StringLengthFieldValidator(key = "fieldErrors.stringMaxLength", trim = true, maxLength = "100")
	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	@EmailValidator(key = "fieldErrors.email")
	@StringLengthFieldValidator(key = "fieldErrors.stringMaxLength", trim = true, maxLength = "45")
	public void setEmail(String email) {
		this.email = email;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	@RequiredStringValidator(key = "fieldErrors.requiredString")
	@StringLengthFieldValidator(key = "fieldErrors.stringMaxLength", trim = true, maxLength = "45")
	@RegexFieldValidator(trim = true, regex = "^[a-zA-Z ]*$", key = "fieldErrors.lettersAndSpaces")
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	@RequiredStringValidator(key = "fieldErrors.requiredString")
	@RegexFieldValidator(trim = true, regex = "^[gs]\\d{2}$", message = "Please choose a correct education level")
	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

}
