/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Trung
 */
public class MyUtil {
    
    public static boolean isNumber(String input) {
        boolean result = false;
        
        try {
            Integer.parseInt(input.trim());
            result = true;
        } catch (Exception e) {
        }
        
        return result;
    }
}
