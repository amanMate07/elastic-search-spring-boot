package com.elasticsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elasticsearch.document.Employee;
import com.elasticsearch.service.SpringDataService;

@RestController
@RequestMapping("/api/springdata")
public class SpringDataController {

	@Autowired
	private SpringDataService springDataService;
	
	@PostMapping("add")
	public ResponseEntity<?> addPerson(@RequestBody Employee employee)
	{
		return new ResponseEntity<>(springDataService.addEmployee(employee),HttpStatus.OK);
	}
	
	@GetMapping("get-all-employee")
	public ResponseEntity<?> getAllEmployees()
	{
		return new ResponseEntity<>(springDataService.getAllEmployees(),HttpStatus.OK);
	}
	
	@GetMapping("get-employee-by-name/{name}")
	public ResponseEntity<?> getPersonByName(@PathVariable String name)
	{
		return new ResponseEntity<>(springDataService.getEmployeeByName(name),HttpStatus.OK);
	}
	
	@PutMapping("update-employee/{id}")
	public ResponseEntity<?> updatePerson(@PathVariable String id,@RequestBody Employee employee)
	{
		return new ResponseEntity<>(springDataService.updateEmployee(id,employee),HttpStatus.OK);
	}
	
	@DeleteMapping("delete-employee/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable String id)
	{
		return new ResponseEntity<>(springDataService.deleteEmployee(id),HttpStatus.OK);
	}
}
