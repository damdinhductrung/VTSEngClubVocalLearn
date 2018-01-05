/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trung.dao;

import connection.MyConnection;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Trung
 */
public class GradeDAO {
    private Connection conn;
    private PreparedStatement pre;
    private ResultSet rs;
    
    private void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
        }
    }
    
    public ArrayList getAllGrade() {
        ArrayList grade = new ArrayList();
        
        try {
            conn = MyConnection.getConnection();
            String sql = "Select SEQ from Grade";
            pre = conn.prepareCall(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                grade.add(rs.getString("SEQ"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        
        return grade;
    }
}
