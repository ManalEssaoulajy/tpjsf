/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;

import javax.persistence.EntityManager;
import ma.projet.beans.Service;
/**
 *
 * @author Admin
 */
public class ServiceService extends AbstractFacade<Service>{

    

  
     @Override
    protected Class<Service> getEntityClass() {
        return Service.class;
    }
}
