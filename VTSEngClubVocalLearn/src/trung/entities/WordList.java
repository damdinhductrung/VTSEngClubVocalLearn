/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trung.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import trung.dao.WordDAO;
import trung.dto.WordByGradeDTO;
import trung.dto.WordDTO;

/**
 *
 * @author Trung
 */
public class WordList extends ArrayList<WordDTO> {

    public void loadAllWord() {
        this.clear();
        
        WordDAO dao = new WordDAO();
        ArrayList<WordDTO> list = dao.getAllWord();

        //Sắp xếp theo alphabel
        Collections.sort(list);

        for (WordDTO dto : list) {
            this.add(dto);
        }

        System.out.println("----Load all word----");
    }
    
    public boolean saveNewWord(WordDTO wDto, WordByGradeDTO wbgDto) {
        boolean result = false;
        WordDAO dao = new WordDAO();
        
        if (dao.saveNewWord(wDto, wbgDto)) 
            result = true;
        
        return result;
    }
    
    public ArrayList<WordDTO> getWordsByUnitSEQ(int seq, WordByGradeList wbgList) {
        ArrayList<WordDTO> result = new ArrayList<>();
        
        for (WordByGradeDTO wbgDto : wbgList) {
            if (wbgDto.getUnitSEQ() == seq) {
                for (WordDTO wDto : this) {
                    if (wDto.getSEQ() == wbgDto.getWordSEQ()) {
                        result.add(wDto);
                    }
                }
            }
        }
        
        return result;
    }
}
