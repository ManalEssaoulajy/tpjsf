/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import ma.projet.beans.Employee;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class EmployeeService extends AbstractFacade<Employee>{

    

  
     @Override
    protected Class<Employee> getEntityClass() {
        return Employee.class;
    }
         public List<Object[]> nbemployes(){
        List<Object[]> employee = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employee  = session.createQuery("select count(e.service), e.service from Employee e group by e.service").list();
        session.getTransaction().commit();
        return employee;
    }
    
   
    
    
}
