package com.example.erp.controller;

import com.example.erp.bean.Employee;
import com.example.erp.bean.Employee_Salary;
import com.example.erp.service.EmployeeSalaryService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.List;

@Path("salary")
public class EmployeeSalaryController {
    EmployeeSalaryService employeeSalaryService=new EmployeeSalaryService();
    @POST
    @Path("/disburse")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response disburse(List<Employee> emplist) throws URISyntaxException
    {
        System.out.println("reached");
        return Response.status(employeeSalaryService.disburse(emplist)).build();
    }

    @POST
    @Path("/getSalaries")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getSalaries(Employee employee) throws URISyntaxException
    {
        System.out.println("hi");
        List<Employee_Salary> salaries = employeeSalaryService.getSalaries(employee);
        System.out.println(salaries.size());
        return Response.ok().entity(salaries).build();
    }

    @GET
    @Path("/disbursed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchAllSalaries() throws URISyntaxException
    {
        System.out.println("hi");
        List<Employee_Salary> salaries = employeeSalaryService.fetchAllSalaries();
        for(Employee_Salary employee_salary:salaries)
        {
            System.out.println(employee_salary.toString());
        }
        return Response.ok().entity(salaries).build();
    }




}
