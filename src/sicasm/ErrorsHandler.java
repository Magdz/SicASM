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
        if(isEmpty(lines))return true;
        if(StartAddress(lines[0]))return true;
        if(EndAddress(lines[lines.length-1]))return true;
        if(ProgramName(lines[0],lines[lines.length-1]))return true;
        return false;
    }
    
    private static boolean isEmpty(String[] lines){
        if(lines.length>1)return false;
        SicASM.setError("No Code");
        return true;
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
        SicASM.setError("NO ENDING ADDRESS SPECIFIED");
        return true;
    }
    
    private static boolean ProgramName(String line1, String line2){
        SRCformat instruction1 = new SRCformat(line1);
        SRCformat instruction2 = new SRCformat(line2);
        if(instruction1.getLabel().equalsIgnoreCase(instruction2.getOperand())
                || instruction1.getOperand().equalsIgnoreCase(instruction2.getOperand()))return false;
        SicASM.setError("INVALID ENDING ADDRESS");
        return true;
    }
    
    public static boolean Format(SRCformat instruction){
        if(instruction.isCheck() && !instruction.isErrorFlag())return false;
        SicASM.setError("INVALID INSTRUCTION FORMAT");
        return true;
    }
    
    public static boolean DuplicatedLabel(String Address, String Label){
        if(Address == null)return false;
        SicASM.setError("Duplicated Label: "+Label);
        return true;
    }
}
