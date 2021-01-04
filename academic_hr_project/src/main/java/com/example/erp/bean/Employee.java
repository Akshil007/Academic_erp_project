package com.example.erp.bean;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import javax.json.bind.annotation.JsonbTransient;
import java.util.Set;

@Entity
public class Employee {
    @Override
    public String toString() {
        return "{" +
                "employee_id=" + getEmployee_id() +
                ", first_name='" + getFirst_name() + '\'' +
                ", last_name='" + getLast_name() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", department='" + getDepartment() +
                '}';
    }

    public Employee(int employee_id, int salary) {
        this.employee_id = employee_id;
        this.salary = salary;
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int employee_id;

    @Column(nullable = false)
    private String first_name;

    public Employee(String first_name, String last_name, String email, String password, String department, int salary) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.department = department;
        this.salary = salary;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public Employee(int employee_id) {
        this.employee_id = employee_id;
    }

    @Column
    private String last_name;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column
    private String department;

    @Column(nullable = false)
    private int salary;

    @Column(nullable = false)
    private boolean loggedIn;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @OneToMany(mappedBy = "employee",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Set<Employee_Salary> employee_salaries;

    @JsonbTransient
    public Set<Employee_Salary> getEmployee_salaries() {
        return employee_salaries;
    }

    public void setEmployee_salaries(Set<Employee_Salary> employee_salaries) {
        this.employee_salaries = employee_salaries;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Employee() {
    }
}
