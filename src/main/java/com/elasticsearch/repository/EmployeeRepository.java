package com.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.elasticsearch.document.Employee;

@Repository
public interface EmployeeRepository extends ElasticsearchRepository<Employee,String> {

	Employee findByName(String name);

	Employee findByDepartmentsDepartmentName(String name);

}
