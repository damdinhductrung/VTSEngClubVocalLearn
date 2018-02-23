/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trung.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import trung.dao.GradeDAO;
import trung.dto.GradeDTO;

/**
 *
 * @author Trung
 */
public final class GradeList extends HashMap<Integer, GradeDTO> {    
    
    public void loadGradeList() {
        GradeDAO dao = new GradeDAO();
        ArrayList<GradeDTO> list = dao.getAllGrade();
        for (GradeDTO dto : list) {
            this.put(dto.getSEQ(), dto);
        }
        System.out.println("----Load all grade----");
    }
    
    public ArrayList<GradeDTO> getAllGrades() {
        ArrayList<GradeDTO> result = new ArrayList<>();
        
        for (Map.Entry<Integer, GradeDTO> entry : this.entrySet()) {
            result.add(entry.getValue());
        }
        
        return result;
    }
}
