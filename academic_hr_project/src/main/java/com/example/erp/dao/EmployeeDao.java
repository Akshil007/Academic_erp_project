package com.example.erp.dao;

import com.example.erp.bean.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> emailVerify(Employee employee);
    int addEmployee(Employee employee);
    int deleteEmployee(List<Employee> employee);
    int modifySalary(Employee employee);
    List<Employee> getEmployees(Integer id);
    List<Employee> getAllEmployees(Employee employee);
}
