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
public class WordByGradeList extends ArrayList<WordByGradeDTO> {
    public void loadAllWordByGrade() {
        WordByGradeDAO dao = new WordByGradeDAO();
        ArrayList<WordByGradeDTO> list = dao.getAllWordByGrade();
        for (WordByGradeDTO dto : list) {
            this.add(dto);
        }
        System.out.println("----Load all word by grade----");
    }
    
    public WordByGradeDTO getWBGByWordSEQ(int seq) {
        WordByGradeDTO result = null;
        
        for (WordByGradeDTO dto : this) {
            if (dto.getWordSEQ() == seq) {
                result = dto;
                break;
            }
        }
        
        return result;
    }
}
