/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entity.Client;
import entity.Location;
import org.hibernate.Session;
import tools.HibernateUtil;

/**
 *
 * @author wuying
 */
public class HibernateClientDao {
    public void save(Client cli){
     Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
//        session.save(u);
        session.save(cli);
        session.getTransaction().commit();
        session.close();
        System.exit(0);
    }
    
}
