package com.example.erp.controller;

import com.example.erp.bean.Employee_Salary;
import com.example.erp.service.EmployeeService;
import com.example.erp.bean.Employee;
import com.mysql.cj.xdevapi.JsonArray;
//import jdk.nashorn.internal.parser.JSONParser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

@Path("employee")
public class EmployeeController {
    EmployeeService employeeService=new EmployeeService();
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginEmployee(Employee employee) throws URISyntaxException
    {
        System.out.println("in");
        List<Employee> emplist = employeeService.verifyEmail(employee);
        if(emplist.size()==1) {
            if (emplist.get(0).getDepartment().equals("account")) {
                System.out.println("out");
                return Response.ok().entity(emplist.get(0)).build();
            } else {
                return Response.status(201).entity(emplist.get(0)).build();
            }
        }
        else {
            System.out.println("out2");
            return Response.status(202).build();
        }
    }

    @POST
    @Path("/add")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEmployee(Employee employee) throws URISyntaxException
    {
        if(employeeService.addEmployee(employee)==200) {
            return Response.ok().build();
        }
        else {
            System.out.println(203);
            return Response.status(203).build();
        }
    }

    @POST
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(List<Employee> employees) throws URISyntaxException
    {
       return Response.status(employeeService.deleteEmployee(employees)).build();
    }

    @POST
    @Path("/modify")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifySalary(Employee employee) throws URISyntaxException
    {
        if(employeeService.modifySalary(employee)==200) {
            return Response.ok().build();
        }
        else if(employeeService.modifySalary(employee)==201)
        {
            return Response.status(201).build();
        }
        else {
            return Response.status(203).build();
        }
    }

    @POST
    @Path("/getEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getEmployees(Employee employee) {
        //List<Employee> emplist = employeeService.getEmployees(Integer.parseInt(String.valueOf(employee.getEmployee_id())));
        List<Employee> employees = employeeService.getEmployees(Integer.parseInt(String.valueOf(employee.getEmployee_id())));;
        System.out.println(employees.size());
        return Response.ok().entity(employees).build();
    }


    @POST
    @Path("/getAllEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllEmployees(Employee employee) {
        //List<Employee> emplist = employeeService.getEmployees(Integer.parseInt(String.valueOf(employee.getEmployee_id())));
        List<Employee> employees = employeeService.getAllEmployees(employee);
        System.out.println(employees.size());
        return Response.ok().entity(employees).build();
    }




}
