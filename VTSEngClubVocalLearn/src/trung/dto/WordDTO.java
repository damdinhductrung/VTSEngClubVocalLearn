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
public class WordDTO {
    int SEQ;
    String name;
    String spelling;
    String spellingSrc;
    String partsOfSpeech;
    String imageSrc;
    String meaning;
    String example;
    String exSoundSrc;
    int unitSEQ;

    public WordDTO() {
    }

    public WordDTO(int SEQ, String name, String spelling, String spellingSrc, String partsOfSpeech, String imageSrc, String meaning, String example, String exSoundSrc, int unitSEQ) {
        this.SEQ = SEQ;
        this.name = name;
        this.spelling = spelling;
        this.spellingSrc = spellingSrc;
        this.partsOfSpeech = partsOfSpeech;
        this.imageSrc = imageSrc;
        this.meaning = meaning;
        this.example = example;
        this.exSoundSrc = exSoundSrc;
        this.unitSEQ = unitSEQ;
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

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getExSoundSrc() {
        return exSoundSrc;
    }

    public void setExSoundSrc(String exSoundSrc) {
        this.exSoundSrc = exSoundSrc;
    }

    public int getUnitSEQ() {
        return unitSEQ;
    }

    public void setUnitSEQ(int unitSEQ) {
        this.unitSEQ = unitSEQ;
    }

    @Override
    public String toString() {
        return "WordDTO{" + "SEQ=" + SEQ + ", name=" + name + ", spelling=" + spelling + ", spellingSrc=" + spellingSrc + ", partsOfSpeech=" + partsOfSpeech + ", imageSrc=" + imageSrc + ", meaning=" + meaning + ", example=" + example + ", exSoundSrc=" + exSoundSrc + ", unitSEQ=" + unitSEQ + '}';
    }

    
    
    
}
