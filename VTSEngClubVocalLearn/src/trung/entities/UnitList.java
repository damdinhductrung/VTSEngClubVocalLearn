/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trung.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import trung.dao.UnitDAO;
import trung.dto.UnitDTO;

/**
 *
 * @author Trung
 */
public class UnitList extends ArrayList<UnitDTO> {
    public void loadAllUnit() {
        UnitDAO dao = new UnitDAO();
        ArrayList<UnitDTO> list = dao.getAllUnit();
        for (UnitDTO dto : list) {
            this.add(dto);
        }
        System.out.println("----Load all unit----");
    }
    
    public ArrayList<UnitDTO> getUnitsByGradeSEQ(int seq) {
        ArrayList<UnitDTO> result = new ArrayList<>();
        
        for (UnitDTO dto : this) {
            if (dto.getGradeSEQ() == seq) {
                result.add(dto);
            }
        }
        
        return result;
    }
    
    public UnitDTO getUnitBySEQ(int seq) {
        UnitDTO result = null;
        
        for (UnitDTO dto : this) {
            if (dto.getSEQ() == seq) {
                result = dto;
                break;
            }
        }
        
        return result;
    }
}
