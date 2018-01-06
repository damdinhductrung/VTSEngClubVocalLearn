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
public class GradeDTO {
    private int SEQ;
    private int number;

    public GradeDTO() {
    }

    public GradeDTO(int SEQ, int number) {
        this.SEQ = SEQ;
        this.number = number;
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

    
    
}
