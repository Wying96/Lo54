/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;
import entity.Course;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repository.HibernateCourseDao;

/**
 *
 * @author wuying
 */
public class TableServlet extends HttpServlet {
     public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
     HibernateCourseDao dao=new HibernateCourseDao();
     List<Course> stringList=dao.findAll();
     request.setAttribute("stringList", stringList);
     request.getRequestDispatcher("/register.jsp").forward(request,response);
     
     }
}
