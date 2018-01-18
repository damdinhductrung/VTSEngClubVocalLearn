/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trung.dto;

import java.util.ArrayList;
import java.util.HashMap;
import trung.dao.GradeDAO;

/**
 *
 * @author Trung
 */
public class GradeList extends HashMap<Integer, GradeDTO> {
    public void loadGradeList() {
        GradeDAO dao = new GradeDAO();
        ArrayList<GradeDTO> list = dao.getAllGrade();
        for (GradeDTO dto : list) {
            this.put(dto.getSEQ(), dto);
        }
        System.out.println("----Load all grade----");
    }
}
