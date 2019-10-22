
import entity.User;
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
        User u=new User();
        int i=1;
        Long n=Long.valueOf(i);
        u.setId(i);
        u.setName("dqwdq");
        u.setToto("yeyey");
        session.save(u);
        session.getTransaction().commit();
        session.close();
        System.exit(0);
    }
}
