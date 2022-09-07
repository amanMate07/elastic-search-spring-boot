package com.elasticsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elasticsearch.service.QueryDSLService;

@RestController
@RequestMapping("/api/querydsl")
public class QueryDSLController {

	@Autowired
	private QueryDSLService queryDSLService;
	
	@GetMapping("get-employee/{address}")
	public ResponseEntity<?> getPersonByAddress(@PathVariable String address)
	{
		return new ResponseEntity<>(queryDSLService.getPersonByAddress(address),HttpStatus.OK);
	}
	
	@PutMapping("update-employee/{departmentName}")
	public ResponseEntity<?> updateEmployee(@PathVariable String departmentName)
	{
		return new ResponseEntity<>(queryDSLService.updateEmployee(departmentName),HttpStatus.OK);
	}
}
