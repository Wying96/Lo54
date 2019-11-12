/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.formationecole.repository;

import fr.utbm.formationecole.entity.Course;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author c
 */
@Repository(value = "CourseDao")  
public class CourseDaoImp extends BaseDaoImp<Course> implements CourseDao {
//筛选的解决方案 1.直接写HQL进行删选 或者  2.先选出全部再在service中进行筛选
    @Override
    public List<Course> findByTitle(String title) {
        String hql = "from Course c where c.title like ?";
        return this.findByHQL(hql, title);
    }

    @Override
    public List<Course> findBy2SessionTime(Object... params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Course> findBySessionLocation(Object... params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    
}
