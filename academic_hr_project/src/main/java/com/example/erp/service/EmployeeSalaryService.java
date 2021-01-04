package com.example.erp.service;

import com.example.erp.bean.Employee;
import com.example.erp.bean.Employee_Salary;
import com.example.erp.dao.EmployeeSalaryDao;
import com.example.erp.dao.implementation.EmployeeSalaryDaoimpl;

import java.util.List;

public class EmployeeSalaryService {
    EmployeeSalaryDao employeeSalaryDao=new EmployeeSalaryDaoimpl();
    public int disburse(List<Employee> employees)
    {
        return employeeSalaryDao.disburse(employees);
    }
    public List<Employee_Salary> getSalaries(Employee employee)
    {
        return employeeSalaryDao.getSalaries(employee);
    };
    public List<Employee_Salary> fetchAllSalaries()
    {
        return employeeSalaryDao.fetchAllSalaries();
    }
}
