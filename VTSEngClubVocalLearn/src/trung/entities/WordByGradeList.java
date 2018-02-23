/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trung.entities;

import java.util.ArrayList;
import java.util.HashMap;
import trung.dao.WordByGradeDAO;
import trung.dto.WordByGradeDTO;

/**
 *
 * @author Trung
 */
public class WordByGradeList extends HashMap<Integer, WordByGradeDTO> {
    public void loadAllWordByGrade() {
        WordByGradeDAO dao = new WordByGradeDAO();
        ArrayList<WordByGradeDTO> list = dao.getAllWordByGrade();
        for (WordByGradeDTO dto : list) {
            this.put(dto.getWordSEQ(), dto);
        }
        System.out.println("----Load all word by grade----");
    }
}
