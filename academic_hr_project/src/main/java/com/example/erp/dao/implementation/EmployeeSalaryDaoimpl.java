package com.example.erp.dao.implementation;

import com.example.erp.bean.Employee;
import com.example.erp.bean.Employee_Salary;
import com.example.erp.dao.EmployeeSalaryDao;
import com.example.erp.utils.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSalaryDaoimpl implements EmployeeSalaryDao {
    @Override
    public int disburse(List<Employee> emplist) {
        Session session= SessionUtil.getSession();
        try {
            List<Integer> ids=new ArrayList<>();
            for(Employee employee:emplist)
            {
                ids.add(employee.getEmployee_id());
            }
            Query query = session.createQuery("from Employee as e where e.employee_id in(:idList)");
            query.setParameter("idList",ids);
            List<Employee> result=query.getResultList();
            System.out.println(query.getResultList().size());
            session.beginTransaction();
            for(Employee employee:result)
            {
                Employee_Salary es=new Employee_Salary();
                es.setAmount(employee.getSalary());
                es.setEmployee(employee);
                LocalDate currentdate = LocalDate.now();
                Date sqldate=Date.valueOf(currentdate);
                es.setPayment_date(sqldate);
                es.setDescription("salary transferred");
                session.save(es);
            }
            session.getTransaction().commit();
            session.close();
            if(result.size()!=0)
            {
                return 200;
            }
            else
            {
                return 201;
            }

        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return 203;
        }finally {
            session.close();
        }

    }

    @Override
    public List<Employee_Salary> getSalaries(Employee employee) {
        Session session=SessionUtil.getSession();
        List<Employee_Salary> salaries=new ArrayList<>();
        try {
            Query query=session.createQuery("from Employee_Salary as e where e.employee.employee_id=:id order by e.payment_date desc");
            query.setParameter("id",employee.getEmployee_id());
            for (final Object i : query.getResultList()) {
                salaries.add((Employee_Salary) i);
            }
            System.out.println(salaries.size());
            return salaries;
        }
        catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<Employee_Salary> fetchAllSalaries() {
        Session session=SessionUtil.getSession();
        try {
            Query query=session.createQuery("from Employee_Salary order by payment_date desc");
            System.out.println(query.getResultList().size());
            return query.getResultList();
        }
        catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }finally {
            session.close();
        }
    }
}
