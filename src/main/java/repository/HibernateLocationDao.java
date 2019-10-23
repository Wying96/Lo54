/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entity.Location;
import entity.Users;
import org.hibernate.Session;
import tools.HibernateUtil;

/**
 *
 * @author wuying
 */
public class HibernateLocationDao {
    public void save(Location l){
     Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
//        session.save(u);
        session.save(l);
        session.getTransaction().commit();
        session.close();
        System.exit(0);
    }
}
