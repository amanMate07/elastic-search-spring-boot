package com.elasticsearch.service;

import java.util.Map;

import com.elasticsearch.document.Employee;

public interface SpringDataService {

	Map<String,Object> addEmployee(Employee employee);

	Map<String,Object> getAllEmployees();

	Map<String,Object> getEmployeeByName(String name);

	Map<String,Object> updateEmployee(String id, Employee employee);

	Map<String,Object> deleteEmployee(String id);
}
