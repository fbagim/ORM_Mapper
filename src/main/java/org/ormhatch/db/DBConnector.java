package org.ormhatch.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
    public static Connection getConnection() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db_emp","root","");
            return con;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
}
