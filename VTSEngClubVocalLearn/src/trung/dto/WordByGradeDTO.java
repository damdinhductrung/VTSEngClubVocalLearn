/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trung.dto;

/**
 *
 * @author Trung
 */
public class WordByGradeDTO {
    int SEQ;
    String example;
    String exampleSrc;
    int UnitSEQ;
    int WordSEQ;

    public WordByGradeDTO() {
    }

    public WordByGradeDTO(int SEQ, String example, String exampleSrc, int UnitSEQ, int WordSEQ) {
        this.SEQ = SEQ;
        this.example = example;
        this.exampleSrc = exampleSrc;
        this.UnitSEQ = UnitSEQ;
        this.WordSEQ = WordSEQ;
    }

    public int getSEQ() {
        return SEQ;
    }

    public void setSEQ(int SEQ) {
        this.SEQ = SEQ;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getExampleSrc() {
        return exampleSrc;
    }

    public void setExampleSrc(String exampleSrc) {
        this.exampleSrc = exampleSrc;
    }

    public int getUnitSEQ() {
        return UnitSEQ;
    }

    public void setUnitSEQ(int UnitSEQ) {
        this.UnitSEQ = UnitSEQ;
    }

    public int getWordSEQ() {
        return WordSEQ;
    }

    public void setWordSEQ(int WordSEQ) {
        this.WordSEQ = WordSEQ;
    }

    @Override
    public String toString() {
        return "WordByGradeDTO{" + "SEQ=" + SEQ + ", example=" + example + ", exampleSrc=" + exampleSrc + ", UnitSEQ=" + UnitSEQ + ", WordSEQ=" + WordSEQ + '}';
    }
    
    
}
