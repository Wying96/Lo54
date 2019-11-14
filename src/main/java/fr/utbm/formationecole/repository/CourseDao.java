/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.formationecole.repository;

import fr.utbm.formationecole.entity.Course;
import java.util.List;

/**
 *
 * @author wuying
 */
public interface CourseDao extends BaseDao<Course> {
    public List<Course> findByTitle(String title);
    
    public List<Course> findBy2Time(Object... params);
    
    public List<Course> findBySessionLocation(Object... params);
    

}
