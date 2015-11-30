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
    private static Hashtable OPTAB = new Hashtable(0);
    private static Hashtable SYMTAB = new Hashtable();
    private static int LOCCTR;
    private static Vector<String> Address = new Vector();
    
    //Main Running Controller Function.
    private static void Controller(){
        GUI = new GUI();
        GUI.setVisible(true);
    }

    public static void run(String SRCText){
        
    }
    
    private void Counter(String SRCText){
        String lines [] = SRCText.split("\n");
        for (String line: lines){
            if (line.startsWith(".")){
                Address.addElement("");
                continue;
            }
            SRCformat instruction = new SRCformat(line);
            if ((instruction.getOPCode()).equalsIgnoreCase("START")) {
                LOCCTR = Integer.parseInt(instruction.getOperand(), 16);
                Address.addElement(Integer.toHexString(LOCCTR));
                continue;
            }
            Address.addElement(Integer.toHexString(LOCCTR));
            if (!(instruction.getLabel()).equals(""))
                SYMTAB.setHash(instruction.getLabel(), Address.lastElement());            
            if ((instruction.getOPCode()).equalsIgnoreCase("BYTE"))
                LOCCTR ++;
            else if ((instruction.getOPCode()).equalsIgnoreCase("RESB"))
                LOCCTR += Integer.parseInt(instruction.getOperand());
            else if ((instruction.getOPCode()).equalsIgnoreCase("RESW"))
                LOCCTR += 3*Integer.parseInt(instruction.getOperand());
            else
                LOCCTR += 3;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Testing
        ReadWriteFile file = new ReadWriteFile();
        file.setFileName("Test.txt");
        new SicASM().Counter(file.readFile());
        for(String s: Address){
            System.out.println(s);
        }
        
        //Main Running Controller
        Controller();
    }
    
}
