/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trung.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import trung.dao.WordDAO;

/**
 *
 * @author Trung
 */
public class WordList extends LinkedHashMap<Integer, WordDTO> {

    public void loadAllWord() {
        WordDAO dao = new WordDAO();
        ArrayList<WordDTO> list = dao.getAllWord();

        Collections.sort(list);

        for (WordDTO dto : list) {
            this.put(dto.getSEQ(), dto);
        }

        System.out.println("----Load all word----");
    }
}
