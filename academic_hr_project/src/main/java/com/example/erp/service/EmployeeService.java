package com.example.erp.service;

import com.example.erp.bean.Employee;
import com.example.erp.dao.EmployeeDao;
import com.example.erp.dao.implementation.EmployeeDaoimpl;

import java.util.List;

public class EmployeeService {
    EmployeeDao employeeDao = new EmployeeDaoimpl();
    public List<Employee> verifyEmail(Employee employee){
        return employeeDao.emailVerify(employee);
    }
    public int addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }
    public int deleteEmployee(List<Employee> employees) {
        return employeeDao.deleteEmployee(employees);
    }

    public int modifySalary(Employee employee) {
        return employeeDao.modifySalary(employee);
    }
    public List<Employee> getEmployees(Integer id)
    {
        return employeeDao.getEmployees(id);
    }
    public  List<Employee> getAllEmployees(Employee employee)
    {
        return employeeDao.getAllEmployees(employee);
    }


}
