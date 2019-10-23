/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entity.Course;
import entity.Location;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.hibernate.Session;
import tools.HibernateUtil;

/**
 *
 * @author wuying
 */
public class HibernateCourseDao {
    private EntityManager em;
    
    public void save(Course course){
     Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
//        session.save(u);
        session.save(course);
        session.getTransaction().commit();
        session.close();
        System.exit(0);
    }
    
    
    public Collection<Course> readAllCourse(){
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        List courses=session.createQuery("select course.id,course.title from Course course").list();
//        session.getTransaction().commit();
//        session.close();
//        System.exit(0);
            
            String sql="select course.id,course.title from Course course";
            Query query=em.createQuery(sql);
            
        return (Collection<Course>) query.getResultList();
    }
    
   public HibernateCourseDao(EntityManager em){
   this.em=em;
   }
           
    }
    
    
    //U
    //d
    //

