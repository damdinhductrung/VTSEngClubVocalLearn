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
public class UnitDTO {
    private int SEQ;
    private int number;
    private String name;
    private int gradeSEQ;

    public UnitDTO() {
    }

    public UnitDTO(int SEQ, int number, String name, int gradeSEQ) {
        this.SEQ = SEQ;
        this.number = number;
        this.name = name;
        this.gradeSEQ = gradeSEQ;
    }

    public int getSEQ() {
        return SEQ;
    }

    public void setSEQ(int SEQ) {
        this.SEQ = SEQ;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGradeSEQ() {
        return gradeSEQ;
    }

    public void setGradeSEQ(int gradeSEQ) {
        this.gradeSEQ = gradeSEQ;
    }
    
    
}
