package com.elasticsearch.document;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class Department {

	@Field(type = FieldType.Text)
	private String departmentName;

	@Field(type = FieldType.Text, index = false)
	private String departmentInfo;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentInfo() {
		return departmentInfo;
	}

	public void setDepartmentInfo(String departmentInfo) {
		this.departmentInfo = departmentInfo;
	}

}
