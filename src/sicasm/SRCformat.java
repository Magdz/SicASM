package sicasm;

import static jdk.nashorn.internal.objects.NativeString.trim;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TOSHIBA
 */
public class SRCformat {
    private final String Instruction;
    private String Label;
    private String OPCode;
    private String Operand;
    private boolean errorFlag;
    private boolean check;
    
    public SRCformat (String Instruction){
        this.Instruction = Instruction;
        splitInstruction();
        check = checkFormat();
    }
    private boolean checkFormat(){
        return !(Instruction.charAt(8) != ' ' || Instruction.charAt(15) != ' ' || Instruction.charAt(16) != ' ');
    }
    private void splitInstruction(){
        String label = Instruction.substring(0, 8);
        String opcode = Instruction.substring(9, 15);
        String operand;
        if (Instruction.length() <= 35)
            operand = Instruction.substring(17, Instruction.length());
        else
            operand = Instruction.substring(17, 35);
        
        if ((label.startsWith(" ") && !label.equals("        ")) || opcode.startsWith(" ") || operand.startsWith(" ") && !operand.equals("                  "))
            errorFlag = true;
        else{
            Label = trim(label);
            OPCode = trim (opcode);
            Operand = trim (operand);
            errorFlag = false;
        }
    }

    /**
     * @return the Label
     */
    public String getLabel() {
        return Label;
    }

    /**
     * @return the OPCode
     */
    public String getOPCode() {
        return OPCode;
    }

    /**
     * @return the Operand
     */
    public String getOperand() {
        return Operand;
    }

    /**
     * @return the errorFlag
     */
    public boolean isErrorFlag() {
        return errorFlag;
    }
    
    
}
