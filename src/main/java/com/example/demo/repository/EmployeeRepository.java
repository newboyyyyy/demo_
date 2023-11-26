package com.example.demo.repository;

import com.example.demo.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    @Query(value = "call get_all_employees", nativeQuery = true)
    public List<Employee> getAllEmployees();


}
