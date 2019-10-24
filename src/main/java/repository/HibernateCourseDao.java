/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entity.Course;
import entity.Location;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import tools.HibernateUtil;

/**
 *
 * @author wuying
 */
public class HibernateCourseDao {
    
    private Session session;
    
    public void ajouterCourse(Course c) {
    
   session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
    
    session.save(c);
  session.getTransaction().commit();
        session.close();
    }
    public List<Course> findAll(){
         session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
         Query query = session.getNamedQuery("Course.findAll");
        
        List<Course> list= query.list();
        session.getTransaction().commit();
        session.close();
        return list;
        
    }
    public List<Course> findByUncompletId(String id){
    session =HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Query query=session.getNamedQuery("Course.findByUncompletId");
    query.setParameter("id", "%"+id+"%");
    List<Course> result=query.list();
     session.getTransaction().commit();
        session.close();
    return result;
    }
     public List<Course> findByUncompletTitle(String title){
    session =HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Query query=session.getNamedQuery("Course.findByUncompletId");
    query.setParameter("id", "%"+title+"%");
    List<Course> result=query.list();
     session.getTransaction().commit();
        session.close();
    return result;
    }
    
    public void updateCourse(Course course){
    session =HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    session.update(course);
     session.getTransaction().commit();
        session.close();
    }
    public void deleteCourse(Course course){
    session =HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    session.delete(course);
        session.getTransaction().commit();
        session.close();
    }
}
