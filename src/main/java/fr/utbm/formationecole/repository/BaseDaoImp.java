/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.formationecole.repository;


import fr.utbm.formationecole.tools.HibernateUtil;
import java.io.Serializable;  
import java.lang.reflect.ParameterizedType;  
import java.util.List;  
  
import javax.annotation.Resource;  
  
import org.hibernate.Query;  
import org.hibernate.Session;  
import org.hibernate.SessionFactory; 
/**
 *
 * @author wuying
 */

public class BaseDaoImp<T> implements BaseDao <T> {
    private Class<T> clazz;  
  /** 
     * 向DAO层注入SessionFactory 
     */  
    @Resource  
    private SessionFactory sessionFactory;  
    
    /** 
     * 通过构造方法指定DAO的具体实现类 
     */  
    public BaseDaoImp() {  
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();  
        clazz = (Class<T>) type.getActualTypeArguments()[0];  
        System.out.println("DAO的真实实现类是：" + this.clazz.getName());  
        sessionFactory = HibernateUtil.getSessionFactory();
    }  
  
  
    /** 
     * 获取当前工作的Session 
     */  
    protected Session getSession() {  
        return this.sessionFactory.openSession();  
    }  
  
    public void save(T entity) {  
        this.getSession().save(entity);  
    }  
  
    public void update(T entity) {  
        this.getSession().update(entity);  
    }  
  
    public void delete(Serializable id) {  
        this.getSession().delete(this.findById(id));  
    }  
  
    public T findById(Serializable id) {  
        return (T) this.getSession().get(this.clazz, id);  
    }  
  
    public List<T> findByHQL(String hql, Object... params) {  
        Query query = this.getSession().createQuery(hql);  
        for (int i = 0; params != null && i < params.length; i++) {  
            query.setParameter(i, params);  
        }  
        return query.list();  
    }  

   
    
}
