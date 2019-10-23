
import entity.Course;
import entity.Location;
import entity.Users;
import static java.lang.System.in;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.Session;
import repository.HibernateCourseDao;
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
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("fr.utbm_FormationEcole_war_1.0-SNAPSHOTPU");
        EntityManager em=emf.createEntityManager();
        em.getTransaction().begin();
       
        Location l=new Location();
        l.setCity("shanghai");
      
        HibernateCourseDao cdao=new HibernateCourseDao(em);
       Collection<Course> cous=cdao.readAllCourse();
        for(Course c:cous){
                System.out.println(c.getId() + "  : "+c.getTitle()+"\n");
        }
        
                System.out.println("saduahdkwhakdjakwd");
//        session.save(u);
//        session.save(l);
//        session.getTransaction().commit();
em.close();
emf.close();
        session.close();
        System.exit(0);
    }
}
