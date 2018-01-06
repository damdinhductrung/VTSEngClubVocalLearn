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
import trung.dto.GradeDTO;

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
    
    public ArrayList<GradeDTO> getAllGrade() {
        ArrayList<GradeDTO> result = new ArrayList<>();
        
        try {
            conn = MyConnection.getConnection();
            String sql = "Select SEQ, Number from Grade";
            pre = conn.prepareCall(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                GradeDTO dto = new GradeDTO();
                dto.setSEQ(rs.getInt("SEQ"));
                dto.setNumber(rs.getInt("Number"));
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        
        System.out.println("----GET ALL GRADE----");
        return result;
    }
    
    /**
     * Lấy info của Grade bằng số SEQ
     * @param gradeSEQ: Số SEQ của grade muốn xem info
     * @return GradeDTO
     */
    public GradeDTO getGradeInfoBySEQ(int gradeSEQ) {
        GradeDTO result = new GradeDTO();
        
        try {
            conn = MyConnection.getConnection();
            String sql = "Select Number from Grade where SEQ = ?";
            pre = conn.prepareCall(sql);
            pre.setInt(1, gradeSEQ);
            rs = pre.executeQuery();
            if (rs.next()) {
                result.setSEQ(gradeSEQ);
                result.setNumber(rs.getInt("Number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        
        System.out.println("----GET GRADE SEQ " + gradeSEQ + " INFO----");
        return result;
    }
    
    
}
