/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trung.dto;

import java.util.ArrayList;
import java.util.HashMap;
import trung.dao.UnitDAO;

/**
 *
 * @author Trung
 */
public class UnitList extends HashMap<Integer, UnitDTO> {
    public void loadAllUnit() {
        UnitDAO dao = new UnitDAO();
        ArrayList<UnitDTO> list = dao.getAllUnit();
        for (UnitDTO dto : list) {
            this.put(dto.getSEQ(), dto);
        }
        System.out.println("----Load all unit----");
    }
}
