/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.formationecole.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author zchen01
 */
public class MysqlConnector {

    public Connection con;

    public MysqlConnector() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/FormationEcole", "root", "");
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
            int rs=stmt.executeUpdate(query);
            System.out.println("RUN SQL SUCCESS");
        }
        catch(Exception e) {
            System.out.println("RUN SQL FAILED");
            e.printStackTrace();
        }
        
    }
}
