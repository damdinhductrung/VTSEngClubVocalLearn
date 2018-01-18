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
public class WordDTO implements Comparable<WordDTO> {
    int SEQ;
    String name;
    String spelling;
    String spellingSrc;
    String partsOfSpeech;
    String imageSrc;
    String meaning;

    public WordDTO() {
    }

    public WordDTO(int SEQ, String name, String spelling, String spellingSrc, String partsOfSpeech, String imageSrc, String meaning) {
        this.SEQ = SEQ;
        this.name = name;
        this.spelling = spelling;
        this.spellingSrc = spellingSrc;
        this.partsOfSpeech = partsOfSpeech;
        this.imageSrc = imageSrc;
        this.meaning = meaning;
    }

    public int getSEQ() {
        return SEQ;
    }

    public void setSEQ(int SEQ) {
        this.SEQ = SEQ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    public String getSpellingSrc() {
        return spellingSrc;
    }

    public void setSpellingSrc(String spellingSrc) {
        this.spellingSrc = spellingSrc;
    }

    public String getPartsOfSpeech() {
        return partsOfSpeech;
    }

    public void setPartsOfSpeech(String partsOfSpeech) {
        this.partsOfSpeech = partsOfSpeech;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public String toString() {
        return "WordDTO{" + "SEQ=" + SEQ + ", name=" + name + ", spelling=" + spelling + ", spellingSrc=" + spellingSrc + ", partsOfSpeech=" + partsOfSpeech + ", imageSrc=" + imageSrc + ", meaning=" + meaning + '}';
    }

    @Override
    public int compareTo(WordDTO compareWord) {
         return this.getName().compareTo(compareWord.getName());
    }

      
    
}
