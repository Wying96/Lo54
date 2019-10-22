/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.dvdstore.core.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author zchen01
 */
public class JdbcConnector {

    public Connection con;

    public JdbcConnector() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            con = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/video", "video", "video");
            System.out.println("CONNECTED");
        } catch (Exception e) {
            System.out.println("CONNECT FAILED");
            e.printStackTrace();
        }
    }

    public ResultSet runSelect(String query) {
        try{
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            System.out.println("RUN SQL SUCCESS");
            return rs;
        }
        catch(Exception e) {
            System.out.println("RUN SQL FAILED");
            e.printStackTrace();
        }
        return null;
    }
    
    public void runModify(String query) throws SQLException {
        try{
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            System.out.println("RUN SQL SUCCESS");
        }
        catch(Exception e) {
            System.out.println("RUN SQL FAILED");
            e.printStackTrace();
        }
        con.commit();
    }
}
