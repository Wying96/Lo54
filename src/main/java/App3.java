
import entity.Course;
import entity.Location;
import entity.Users;
import java.util.List;
import org.hibernate.Query;

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
        HibernateCourseDao my=new HibernateCourseDao();
//        Course cou=new Course("test","test3");
//        my.ajouterCourse(cou);
       
     List<Course> stringList=my.findAll();
//Course course=new Course();
//course.setId("test33");
//course.setTitle("test33");
//my.ajouterCourse(course);
//cou.setTitle("changer");
//my.updateCourse(cou);
// List<Course> list=my.findByUncompletId("ew");
//
for(Course l:stringList){
System.out.println(l.toString()+" , title :"+l.getTitle());
}
//Course course=new Course();
//course.setId("test33");
//course.setTitle("changer");
//my.deleteCourse(cou);
//        session.save(u);
//        session.save(l);
//        session.getTransaction().commit();
//        session.close();
        System.exit(0);
    }
}
