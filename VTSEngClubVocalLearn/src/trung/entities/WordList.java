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
import trung.dto.WordDTO;

/**
 *
 * @author Trung
 */
public class WordList extends LinkedHashMap<String, WordDTO> {

    public void loadAllWord() {
        WordDAO dao = new WordDAO();
        ArrayList<WordDTO> list = dao.getAllWord();

        Collections.sort(list);

        for (WordDTO dto : list) {
            this.put(dto.getName(), dto);
        }

        System.out.println("----Load all word----");
    }
}
