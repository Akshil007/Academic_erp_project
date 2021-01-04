package com.example.erp.bean;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Employee_Salary {
    @Override
    public String toString() {
        return "{" +
                "employee_id=" + getEmployee().getEmployee_id() +
                ", Amount='" + getAmount() + '\'' +
                ", Date='" + getPayment_date() + '\'' +
                ", desc='" + getDescription() + '\'' +
                '}';
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @Column(nullable = false)
    private Date payment_date;
    @Column(nullable = false)
    private int amount;
    @Column
    private String description;

    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "employee_id",unique = false)
    private Employee employee;

    public Employee_Salary() {
    }

    public Employee_Salary(Date payment_date, int amount, String description, Employee employee) {
        this.payment_date = payment_date;
        this.amount = amount;
        this.description = description;
        this.employee = employee;
    }

    public Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
