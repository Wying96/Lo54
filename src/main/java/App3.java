
import entity.Location;
import entity.Users;
import java.util.List;
import org.hibernate.Query;

import org.hibernate.Session;
import tools.HibernateUtil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wuying
 */
public class App3 {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Users u=new Users();
        
        Query query = session.getNamedQuery("Location.findAll");
        
        List<Location> list= query.list();
        
        int i=10;
       //u.setIdUser(i);
//        u.setName("dqwdq");
//        u.setToto("yeyey");
//        Location l=new Location();
//        l.setCity("sajhzgdkjashg");
for(Location l:list){
System.out.println(l.toString());
}
//        session.save(u);
//        session.save(l);
        session.getTransaction().commit();
        session.close();
        System.exit(0);
    }
}
