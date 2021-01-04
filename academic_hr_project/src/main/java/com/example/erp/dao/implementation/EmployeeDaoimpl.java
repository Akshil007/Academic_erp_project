package com.example.erp.dao.implementation;

import com.example.erp.bean.Employee;
import com.example.erp.bean.Employee_Salary;
import com.example.erp.dao.EmployeeDao;
import com.example.erp.utils.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoimpl implements EmployeeDao {
    public void setLoginbit(Employee employee)
    {
        Session session=SessionUtil.getSession();
        session.beginTransaction();
        Query query=session.createQuery("update Employee set loggedIn=:Valid where email=:email");
        query.setParameter("email",employee.getEmail());
        query.setParameter("Valid",employee.isLoggedIn());
        int status=query.executeUpdate();
        System.out.println("number of rows updated="+status);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Employee> emailVerify(Employee employee) {
        Session session=SessionUtil.getSession();
        try {
           // System.out.println(id);
            Query query = session.createQuery("from Employee where email=:email and password=:password");
            query.setParameter("email",employee.getEmail());
            query.setParameter("password",employee.getPassword());
            return query.getResultList();
//            if(result.size()==1) {
//                if (((String) result.get(0)[0]).equals("account")) {
//                    System.out.println("account");
//                    employee.setLoggedIn(true);
//                    setLoginbit(employee);
//                    System.out.println("outside");
//                    return 200;
//                } else {
//                    System.out.println("normal");
//                    employee.setLoggedIn(true);
//                    setLoginbit(employee);
//                    return 201;
//                }

        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }finally {
            session.close();
        }


        //return -1;
    }



    @Override
    public int addEmployee(Employee employee) {
        Session session = SessionUtil.getSession();
        try {
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
            session.close();
            return 200;
        }
        catch (HibernateException exception) {
            System.out.println("email alert");
            System.out.print(exception.getLocalizedMessage());
            return 203;
        }finally {
            session.close();
        }

        //return 0;
    }

    @Override
    public int deleteEmployee(List<Employee> employees) {
        Session session = SessionUtil.getSession();
        try {
            session.beginTransaction();
            if(employees.size()==1) {
                Query query = session.createQuery("from Employee where employee_id=:id");
                query.setParameter("id",employees.get(0).getEmployee_id());
                if(query.getResultList().size()==0)
                {
                    return 201;
                }
            }
            for(Employee employee:employees) {
                Object persistentInstance = session.load(Employee.class, employee.getEmployee_id());
                if (persistentInstance != null) {
                    session.delete(persistentInstance);
                }
            }
            session.getTransaction().commit();
            session.close();
            return 200;
        }
        catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return 203;
        }finally {
            session.close();
        }

       // return 0;
    }

    @Override
    public int modifySalary(Employee employee) {
        Session session=SessionUtil.getSession();
        try {

            session.beginTransaction();
            Query query=session.createQuery("update Employee set salary=:salary where employee_id=:emp_id");
            query.setParameter("emp_id",employee.getEmployee_id());
            query.setParameter("salary",employee.getSalary());
            int status=query.executeUpdate();
            System.out.println("number of rows updated="+status);
            session.getTransaction().commit();
            session.close();
            if(status==1)
            {
                return 200;
            }
            else
            {
                return 201;
            }
        }
        catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return 203;
        }finally {
            session.close();
        }

        //return 0;
    }

    @Override
    public List<Employee> getEmployees(Integer id) {
        Session session=SessionUtil.getSession();
        try {
            LocalDate currentdate = LocalDate.now();
            int currentMonth= currentdate.getMonthValue();
            int currentYear=currentdate.getYear();
            Query query=session.createQuery("from Employee as e where e.employee_id not in (select s.employee.employee_id from Employee_Salary as s where Year(s.payment_date)=:year and Month(s.payment_date)=:month) and e.employee_id!=:emp_id");
            query.setParameter("year",currentYear);
            query.setParameter("month",currentMonth);
            query.setParameter("emp_id",id);
            System.out.println(query.getResultList().size());
            return query.getResultList();
//            for (Object i : query.getResultList()) {
//                employees.add((Employee) i);
//            }
//            System.out.println(employees.size());
//            return employees;
        }
        catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }finally {
            session.close();
        }
        //return null;
    }

    @Override
    public List<Employee> getAllEmployees(Employee employee) {
        Session session=SessionUtil.getSession();
        try {
            Query query=session.createQuery("from Employee where employee_id!=:id");
            query.setParameter("id",employee.getEmployee_id());
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
