/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vtsengclubvocallearn;

import connection.MyConnection;

/**
 *
 * @author Trung
 */
public class VTSEngClubVocalLearn {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(MyConnection.getConnection().toString());
    }
    
}
