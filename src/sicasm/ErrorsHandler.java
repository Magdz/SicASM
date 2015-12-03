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
public class ErrorsHandler {
    
    public static boolean Before(String[] lines){
        if(StartAddress(lines[0]))return true;
        if(EndAddress(lines[lines.length-1]))return true;
        return false;
    }
    
    private static boolean StartAddress(String line){
        SRCformat instruction = new SRCformat(line);
        if(instruction.getOPCode().equalsIgnoreCase("START"))return false;
        SicASM.setError("INVALID STARTING ADDRESS");
        return true;
    }
    
    private static boolean EndAddress(String line){
        SRCformat instruction = new SRCformat(line);
        if(instruction.getOPCode().equalsIgnoreCase("END"))return false;
        SicASM.setError("INVALID ENDDING ADDRESS");
        return true;
    }
}
