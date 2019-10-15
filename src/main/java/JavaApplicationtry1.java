/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

/**
 *
 * @author wuying
 */
public class JavaApplicationtry1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    try{ 
        Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        
        try{
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/FormationEcole", "root", "");
        Statement stmt = con.createStatement();
        
        ResultSet rs = stmt.executeQuery("SELECT * FROM FormationEcole.LOCATION");
        
        while (rs.next()) {
        String s = rs.getString("ID");
        String n = rs.getString("CITY");
        System.out.println(s + "   " + n);
    }
        }catch(SQLException e){
            System.err.println(e);
        }                 
     
      }
        
    }
    
