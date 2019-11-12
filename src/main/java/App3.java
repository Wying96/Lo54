
import fr.utbm.formationecole.entity.Course;
import fr.utbm.formationecole.entity.Location;
import fr.utbm.formationecole.entity.Users;
import fr.utbm.formationecole.repository.LocationDaoImp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.utbm.formationecole.service.LocationService;
//import org.hibernate.Query;
//
//import org.hibernate.Session;
//import repository.HibernateCourseDao;
//import tools.HibernateUtil;

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
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//
//        Users u=new Users();
        
//        Query query = session.getNamedQuery("Location.findAll");
//        
//        List<Location> list= query.list();
        
//        int i=10;
       //u.setIdUser(i);
//        u.setName("dqwdq");
//        u.setToto("yeyey");
//        Location l=new Location();
//        l.setCity("sajhzgdkjashg");
////        HibernateCourseDao my=new HibernateCourseDao();
////        Course cou=new Course("test","test3");
////        my.ajouterCourse(cou);
//       
//     List<Course> stringList=my.findAll();
//EntityManagerFactory emf=Persistence.createEntityManagerFactory("my_persistence_unit");
//Course course=new Course();
//course.setId("testsb");
//course.setTitle("test33");
//CourseJpaController cctrl=new CourseJpaController(emf);
//        try {
//            cctrl.create(course);
//            
//            
//            
//////my.ajouterCourse(course);
//////cou.setTitle("changer");
//////my.updateCourse(cou);
////// List<Course> list=my.findByUncompletId("ew");
//////
////for(Course l:stringList){
////System.out.println(l.toString()+" , title :"+l.getTitle());
////}
//////Course course=new Course();
//////course.setId("test33");
//////course.setTitle("changer");
//////my.deleteCourse(cou);
//////        session.save(u);
//////        session.save(l);
//////        session.getTransaction().commit();
//////        session.close();
////        System.exit(0);
//        } catch (Exception ex) {
//            Logger.getLogger(App3.class.getName()).log(Level.SEVERE, null, ex);
//        }
Location l=new Location();

l.setCity("paris");
LocationDaoImp lp=new LocationDaoImp();
lp.save(l);
    }
}
