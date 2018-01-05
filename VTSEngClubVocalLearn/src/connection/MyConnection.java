/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Trung
 */
public class MyConnection {
    public static Connection getConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=Tecknic", "sa", "password");
        } catch (Exception e) {
        }
        return null;
    }
}
