/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicasm;

import java.util.Vector;

/**
 *
 * @author Ahmed
 */
public class SicASM {
    
    private static GUI GUI;
    private static Hashtable Optable;
    private static Hashtable Symtable;
    private static Vector<String> LOCCTR;
    
    //Main Running Controller Function.
    private static void Controller(){
        Optable = new Hashtable(0);
        Symtable = new Hashtable();
        GUI = new GUI();
        GUI.setVisible(true);
    }

    public static void run(String SRCText){
        
    }
    
    private void LocatorCounter(String SRCText){
        /*
        * it is required to 
        * 1- fill the LOCCTR with a list of the address of the lines
        * 2- fill the Symtable with the labels and there addresses
        */
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Testing
        String Hex = Integer.toHexString(15);
        System.out.println(Hex);
        LOCCTR.add(Hex);
        
        //Main Running Controller
        Controller();
    }
    
}
