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
        SicASM.setError(" **** No Code", 0);
        return true;
    }
    
    private static boolean StartAddress(String line){
        SRCformat instruction = new SRCformat(line);
        if(instruction.getOPCode().equalsIgnoreCase("START"))return false;
        SicASM.setError(" **** INVALID STARTING ADDRESS", 0);
        return true;
    }
    
    private static boolean EndAddress(String line){
        SRCformat instruction = new SRCformat(line);
        if(instruction.getOPCode().equalsIgnoreCase("END"))return false;
        SicASM.setError(" **** NO ENDING ADDRESS SPECIFIED", 0);
        return true;
    }
    
    private static boolean ProgramName(String line1, String line2){
        SRCformat instruction1 = new SRCformat(line1);
        SRCformat instruction2 = new SRCformat(line2);
        if(instruction1.getLabel().equalsIgnoreCase(instruction2.getOperand())
                || instruction1.getOperand().equalsIgnoreCase(instruction2.getOperand()))return false;
        SicASM.setError(" **** INVALID ENDING ADDRESS", 0);
        return true;
    }
    
    public static boolean Format(SRCformat instruction, int place){
        if(instruction.isCheck() && !instruction.isErrorFlag())return false;
        SicASM.setError(" **** INVALID INSTRUCTION FORMAT", place);
        return true;
    }
    
    public static boolean DuplicatedLabel(String Address, String Label, int place){
        if(Address == null)return false;
        SicASM.setError(" **** Duplicated Label: "+Label, place);
        return true;
    }
    
    public static boolean InvaledOpCodeInstruction(String Opcode, String inst, int place){
        if(Opcode != null)return false;
        if(inst.equalsIgnoreCase("START") || inst.equalsIgnoreCase("END") || inst.equalsIgnoreCase("RESW") || inst.equalsIgnoreCase("RESB")
                || inst.equalsIgnoreCase("BYTE") || inst.equalsIgnoreCase("WORD")
                || inst.equalsIgnoreCase("ORG") || inst.equalsIgnoreCase("LTORG")
                || inst.equalsIgnoreCase("EQU"))return false;
        SicASM.setError(" **** INVALID Opcode Instruction: " + inst, place);
        return true;
    }
    
    public static void UnDefinedLabel(String inst, String Operand, int place){
        if(inst != null)return;
        SicASM.setError(" **** UnDefined Label: " + Operand, place);
    }
    
    public static void InvaledExpresssion(int place){
        SicASM.setError(" **** UnDefined Label or invalied Expression", place);
    }
    
    public static void MissingOperand(int place){
        SicASM.setError(" **** Missing Operand or Label", place);
    }
}
