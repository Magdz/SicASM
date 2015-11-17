/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicasm;

/**
 *
 * @author Ahmed
 */
public class SicASM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Testing
        Hashtable H = new Hashtable(0);
        H.setHash("abc", "AF");
        System.out.println(H.getOpcode("abc"));
        System.out.println(H.getOpcode("wd"));
    }
    
}
