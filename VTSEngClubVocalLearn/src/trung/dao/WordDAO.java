/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trung.dao;

import connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import trung.dto.WordDTO;

/**
 *
 * @author Trung
 */
public class WordDAO {
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
    
    public ArrayList<WordDTO> getAllWord() {
        ArrayList<WordDTO> result = new ArrayList<>();
        
        try {
            conn = MyConnection.getConnection();
//            String sql = "Select "
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public boolean saveNewWord(WordDTO dto) {
        boolean result = false;
        
        try {
            conn = MyConnection.getConnection();
            conn.setAutoCommit(false);
            String sql = "Insert into Word (Name, Spelling, SpellingSoundSrc, PartsOfSpeech, ImageSrc, Meaning) values (?,?,?,?,?,?)";
            pre = conn.prepareStatement(sql);
            pre.setString(1, dto.getName());
            pre.setString(2, dto.getSpelling());
            pre.setString(3, dto.getSpellingSrc());
            pre.setString(4, dto.getPartsOfSpeech());
            pre.setString(5, dto.getImageSrc());
            pre.setString(6, dto.getMeaning());

            String completeSql = pre.toString();
            String finalSql = completeSql.substring(completeSql.indexOf(" ") + 1);
            pre.executeUpdate(finalSql, Statement.RETURN_GENERATED_KEYS);
            rs = pre.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            
            
            sql = "Insert into WordByGrade (Example, ExampleSoundSrc, Unit_SEQ, Word_SEQ) values (?,?,?,?)";
            pre = conn.prepareCall(sql);
            pre.setString(1, dto.getExample());
            pre.setString(2, dto.getExSoundSrc());
            pre.setInt(3, dto.getUnitSEQ());
            pre.setInt(4, id);
            pre.executeUpdate();
            
            conn.commit();
            result = true;
            System.out.println("----Save new Word----");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public boolean isUniqueName(String name) {
        boolean result = true;
        
        try {
            conn = MyConnection.getConnection();
            String sql = "Select `SEQ` from `word` where `Name` = ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, name);
            rs = pre.executeQuery();
            result = !rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        
        return result;
    }
}
