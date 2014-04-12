package com.royaltechnosoft.inquiry.model;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
public class Course implements Model {

	@Id
	private String courseId;
	private String name;

	// Setters and getters
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
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
