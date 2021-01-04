package com.example.erp.dao;

import com.example.erp.bean.Employee;
import com.example.erp.bean.Employee_Salary;

import java.util.List;

public interface EmployeeSalaryDao {
    int disburse(List<Employee> employees);
    List<Employee_Salary> getSalaries(Employee employee);
    List<Employee_Salary> fetchAllSalaries();

}
