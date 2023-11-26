/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;

import java.util.List;
import ma.projet.beans.UserLogin;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author DELL
 */
public class UserLoginService extends AbstractFacade<UserLogin>{
   @Override
    protected Class<UserLogin> getEntityClass() {
        return UserLogin.class;
    }
   public int nbrUsers(String username,String password){
           List<UserLogin> users;
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            users = session.createQuery("FROM User WHERE username = :username AND password = :password").setParameter("username", username).setParameter("password", password).list();
            session.getTransaction().commit();
            if (users==null){
                return 0;
            }else
            return users.size();
}  
}
