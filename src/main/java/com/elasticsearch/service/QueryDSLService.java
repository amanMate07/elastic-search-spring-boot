package com.elasticsearch.service;

import java.util.Map;

public interface QueryDSLService {

	Map<String,Object> getPersonByAddress(String address);

	Map<String,Object> updateEmployee(String departmentName);

}
