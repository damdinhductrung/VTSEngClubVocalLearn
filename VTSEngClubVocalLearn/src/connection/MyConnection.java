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
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/VTSEngClubVocalLearn?useUnicode=yes&characterEncoding=UTF-8", "root", "password");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
