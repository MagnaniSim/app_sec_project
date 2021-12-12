/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainlibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author bikash
 */
public class DB {

    public static String user = "user";
    public static String connection = "jdbc:mysql://localhost:3306/library";
    
    public static Connection getConnection() {
        Connection con = null;
        try {
            Properties props = new Properties();
//            Dotenv dotenv = Dotenv.load();
//            String userName = dotenv.get(“DB_USERNAME”);
//            String userPwd = dotenv.get(“DB_PASSWORD”);
//            props.put("user", userName);
//            props.put("password", userPwd);

            props.put("user", System.getProperty("dbUsername"));
            props.put("password", System.getProperty("dbPassword"));
            
            props.put("useUnicode", "true");
            props.put("useServerPrepStmts", "false"); // use client-side prepared statement
            props.put("characterEncoding", "UTF-8"); // ensure charset is utf8 here

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(connection, props);
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

}
