/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trung.dao;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import trung.dto.WordByGradeDTO;

/**
 *
 * @author Trung
 */
public class WordByGradeDAO {
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
    
    public ArrayList<WordByGradeDTO> getAllWordByGrade() {
        ArrayList<WordByGradeDTO> result = new ArrayList<>();
        
        try {
            conn = MyConnection.getConnection();
            String sql = "Select SEQ, Example, ExampleSoundSrc, Unit_SEQ, Word_SEQ from WordByGrade";
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                WordByGradeDTO dto = new WordByGradeDTO();
                dto.setSEQ(rs.getInt("SEQ"));
                dto.setExample(rs.getString("Example"));
                dto.setExampleSrc(rs.getString("ExampleSoundSrc"));
                dto.setUnitSEQ(rs.getInt("Unit_SEQ"));
                dto.setWordSEQ(rs.getInt("Word_SEQ"));
                result.add(dto);
            }
            System.out.println("----Get all word by grade----");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        
        
        return result;
    }
}
