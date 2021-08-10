package org.ormhatch.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
    public static Connection getConnection() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://ormhatch.cfbhhfrm5dqk.us-east-2.rds.amazonaws.com:3306/Employee","admin","qwerty123456");
            return con;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
}
