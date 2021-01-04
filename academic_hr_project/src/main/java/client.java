import com.example.erp.bean.Employee;
import com.example.erp.bean.Employee_Salary;
import com.example.erp.dao.EmployeeDao;
import com.example.erp.dao.EmployeeSalaryDao;
import com.example.erp.dao.implementation.EmployeeDaoimpl;
import com.example.erp.dao.implementation.EmployeeSalaryDaoimpl;
import com.example.erp.utils.SessionUtil;
//import com.mysql.cj.Session;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.example.erp.bean.Employee;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class client {
    public static void main(String []args)
    {
        Session session= SessionUtil.getSession();
        session.beginTransaction();
//        //Employee emp1=new Employee("Alex","kanpariya","alex@gmail.com","abc");
//        Employee emp2=new Employee("Red","Power","red@gmail.com","development");
////        Dept_Salary d=new Dept_Salary("abc",10000);
//        //session.save(emp1);
//        session.save(emp2);
//        //session.save(new Dept_Salary("HR",60000));
//        session.getTransaction().commit();
//        session.close();
//        Query query = session.createQuery("select department,email from Employee where email=:email1");
//        query.setParameter("email1", "akshilrg1999@gmail.com");
//        List <Object[]> l=query.getResultList();
//        for(Object[] i: l)
//        {
//            System.out.println((String)i[0]+" "+(String)i[1]);
//        }
//        EmployeeDao employeeDao=new EmployeeDaoimpl();
//        Employee emp=new Employee("steev","smith","steev@gmail.com","steev","xyz",40000);
//        session.save(emp);
//        session.getTransaction().commit();
//        session.close();

//        employeeDao.getEmployees();
        //EmployeeSalaryDao employeeSalaryDao=new EmployeeSalaryDaoimpl();
//        Employee_Salary employee_salary=new Employee_Salary();

        //System.out.println(list.size());
//        Employee employee=new Employee();
//        employee.setEmployee_id(53);
//        employee.setSalary(200000);
//        employeeDao.modifySalary(employee);
//        LocalDate currentdate = LocalDate.now();
//        System.out.println(currentdate);




//        EmployeeDao employeeDao=new EmployeeDaoimpl();
//////        //employeeSalaryDao.disburse();
//        List<Employee> list=employeeDao.getEmployees();
//        for(int i=0;i<list.size();i++)
//        {
//            System.out.println(list.get(i).toString());
//        }




     //   EmployeeSalaryDao employeeSalaryDao=new EmployeeSalaryDaoimpl();

//        List<Employee> employees=new ArrayList<>();
//        employees.add(new Employee(69));
//        employees.add(new Employee(70));
//        employees.add(new Employee(71));
//        employeeSalaryDao.disburse(employees);
//        Query query=session.createQuery("delete from Employee_Salary where employee.employee_id=85 or employee.employee_id=87");
//        query.executeUpdate();
//        session.getTransaction().commit();
//        session.close();


        /*code for inserting record into Employeee_salary*/
        LocalDate dt = LocalDate.parse("2020-10-01");
        Date sqldate=Date.valueOf(dt);
        Employee employee=new Employee(71);
        Employee_Salary employee_salary=new Employee_Salary(sqldate,878000,"Salary transfered",employee);
        session.save(employee_salary);
        session.getTransaction().commit();
        session.close();

        /*code for add an employee*/
//        EmployeeDao employeeDao=new EmployeeDaoimpl();
//        Employee emp=new Employee("steev","smith","akshilrg1999@gmail.com","steev","xyz",40000);
//        session.save(emp);
//        session.getTransaction().commit();
//        session.close();





    }
}
