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
import java.util.ArrayList;
import trung.dto.UnitDTO;

/**
 *
 * @author Trung
 */
public class UnitDAO {
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
    
    /**
     * Lấy tất cả các Unit bằng Grade SEQ
     * @param gradeSEQ: SEQ của grade
     * @return Danh sách các Unit
     */
    public ArrayList<UnitDTO> getAllUnitByGradeSEQ(int gradeSEQ) {
        ArrayList<UnitDTO> result = new ArrayList<>();
        
        try {
            conn = MyConnection.getConnection();
            String sql = "Select SEQ, Number, Name from Unit where Grade_SEQ = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, gradeSEQ);
            rs = pre.executeQuery();
            while (rs.next()) {
                UnitDTO dto = new UnitDTO();
                dto.setSEQ(rs.getInt("SEQ"));
                dto.setNumber(rs.getInt("Number"));
                dto.setName(rs.getString("Name"));
                dto.setGradeSEQ(gradeSEQ);
                result.add(dto);
            }
            System.out.println("----GET UNITS GRADE SEQ " + gradeSEQ + "----");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        
        
        return result;
    }
    
    public UnitDTO getUnitInfoBySEQ(int unitSEQ) {
        UnitDTO result = new UnitDTO();
        
        try {
            conn = MyConnection.getConnection();
            String sql = "Select Number, Name from Unit where SEQ = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, unitSEQ);
            rs = pre.executeQuery();
            if (rs.next()) {
                result.setSEQ(unitSEQ);
                result.setNumber(rs.getInt("Number"));
                result.setName(rs.getString("Name"));
            }
            System.out.println("----GET UNIT INFO SEQ " + unitSEQ + "----");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        
        
        return result;
    }
    
    public boolean checkUnitNumberByGradeSEQ(int number, int gradeSEQ) {
        boolean result = false;
        
        try {
            conn = MyConnection.getConnection();
            String sql = "Select Name from Unit where Number = ? and Grade_SEQ = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, number);
            pre.setInt(2, gradeSEQ);
            if (pre.execute()) {
                result = true;
            }
            System.out.println("----Check Unit Number num: " + number + " gradeSEQ: " + gradeSEQ + "----");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public boolean createUnitByGradeSEQ(UnitDTO dto, int gradeSEQ) {
        boolean result = false;
        
        try {
            conn = MyConnection.getConnection();
            String sql = "INSERT INTO Unit (`Number`, `Name`, `Grade_SEQ`) VALUES (?, ?, ?)";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, dto.getNumber());
            pre.setString(2, dto.getName());
            pre.setInt(3, gradeSEQ);
            
            if (pre.executeUpdate() > 0) {
                result = true;
            }
            System.out.println("----Create Unit by gradeSEQ: " + gradeSEQ + "----");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public boolean updateUnitByUnitSEQ(UnitDTO dto) {
        boolean result = false;
        
        try {
            conn = MyConnection.getConnection();
            String sql = "update Unit Set Number = ?, Name = ? where Grade_SEQ = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, dto.getNumber());
            pre.setString(2, dto.getName());
            pre.setInt(3, dto.getSEQ());
            
            if (pre.executeUpdate() > 0) {
                result = true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        
        return result;
    }
    
    public boolean isHaveNumber(int number, int gradeSEQ) {
        boolean result = false;
        
        try {
            conn = MyConnection.getConnection();
            String sql = "Select Name from Unit where Number = ? and Grade_SEQ = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, number);
            pre.setInt(2, gradeSEQ);
            
            rs = pre.executeQuery();
            result = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return result;
    }
}
