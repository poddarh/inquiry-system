package com.royaltechnosoft.inquiry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

@Entity
@Table(name = "courses")
public class Course implements Model {

	@Id
	@GeneratedValue
	private Integer courseId;
	@Column(nullable = false, length = 32)
	private String name;

	// Setters and getters
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
