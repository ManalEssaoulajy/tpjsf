/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import ma.projet.beans.UserLogin;
import ma.projet.service.UserLoginService;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author DELL
 */
@ManagedBean(name = "AuthBean")
@SessionScoped

public class AuthBean {
 


    
private UserLoginService userLoginService;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    // Getters and setters
    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        if (isValidCredentials(username, password)) {
            System.out.println("============yes");
            return "serviceForm.xhtml"; 
        } else {
            return null;
        }
    }

    private boolean isValidCredentials(String username, String password) {
          List<UserLogin> users;
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            users = session.createQuery("FROM UserLogin WHERE username = :username AND password = :password").setParameter("username", username).setParameter("password", password).list();
            session.getTransaction().commit();
            if(users.size()==0){
                return false;
            }else
            return true;
    }
}   

