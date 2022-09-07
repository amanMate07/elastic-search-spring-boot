package com.elasticsearch.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elasticsearch.document.Employee;
import com.elasticsearch.repository.EmployeeRepository;

@Service
public class SpringDataServiceImpl implements SpringDataService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	private static Logger logger=LoggerFactory.getLogger(SpringDataServiceImpl.class);

	@Override
	public Map<String, Object> addEmployee(Employee employee) {
		Map<String,Object> map=new HashMap<>();
		employeeRepository.save(employee);
		map.put("status","success");
		return map;
	}

	@Override
	public Map<String, Object> getAllEmployees() {
		Map<String,Object> map=new HashMap<>();
		map.put("data", employeeRepository.findAll());
		return map;
	}

	@Override
	public Map<String, Object> getEmployeeByName(String name) {
		Map<String,Object> map=new HashMap<>();
		map.put("data", employeeRepository.findByName(name));
		map.put("newData", employeeRepository.findByDepartmentsDepartmentName(name));
		return map;
	}

	@Override
	public Map<String, Object> updateEmployee(String id, Employee employee) {
		Map<String,Object> map=new HashMap<>();
		employeeRepository.save(employee);
		return map;
	}

	@Override
	public Map<String, Object> deleteEmployee(String id) {
		Map<String,Object> map=new HashMap<>();
		employeeRepository.save(employeeRepository.findById(id).get());
		return map;
	}
	

}
