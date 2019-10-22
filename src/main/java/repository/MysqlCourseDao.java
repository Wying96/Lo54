/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entity.Course;
import java.sql.SQLException;

/**
 *
 * @author wuying
 */
public class MysqlCourseDao {
    
    
    
    public void ajouterCourse(Course c) throws SQLException{
    
    MysqlConnector con =new MysqlConnector();
    String id=c.getId();
    String titre=c.getTitle();
    String query="insert into COURSE (ID,TITLE) VALUES ('"+id+"','"+titre+"')";
    con.runModify(query);
  
    }
}
