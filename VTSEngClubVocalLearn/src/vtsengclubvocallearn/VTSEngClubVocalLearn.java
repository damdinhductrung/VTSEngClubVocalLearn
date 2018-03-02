/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vtsengclubvocallearn;

import connection.MyConnection;
import javax.swing.JFrame;
import trung.dao.WordDAO;
import trung.entities.GradeList;
import trung.entities.UnitList;
import trung.entities.WordByGradeList;
import trung.entities.WordList;

/**
 *
 * @author Trung
 */
public final class VTSEngClubVocalLearn {

    public static final GradeList GRADE_LIST = new GradeList();
    public static final UnitList  UNIT_LIST  = new UnitList();
    public static final WordByGradeList WBG_LIST = new WordByGradeList();
    public static final WordList  WORD_LIST = new WordList();
    
    public static void main(String[] args) {
        loadData();
//        
//        TestMenu testMenu = new TestMenu();
//        testMenu.setVisible(true);

        Dictionary dic = new Dictionary();
        dic.setVisible(true);
    }
    
    public static void loadData() {
        GRADE_LIST.loadGradeList();
        UNIT_LIST.loadAllUnit();
        WBG_LIST.loadAllWordByGrade();
        WORD_LIST.loadAllWord();
        System.out.println("----Data all set----");
    }
    
}
