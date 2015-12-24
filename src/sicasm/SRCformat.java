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
    private final String instruction;
    private String Label;
    private String OPCode;
    private String Operand;
    private boolean errorFlag;
    private final boolean check;
    private boolean literal;
    
    public SRCformat (String Instruction){
        this.instruction = Instruction;
        splitInstruction();
        check = checkFormat();
        literal = checkLiteral();
    }
    
    private boolean checkLiteral(){
        if (instruction.length() > 15) {
            if (instruction.charAt(16) == '=')
                return true;
            else
                return false;
        }
        return false;
    }
    
    private boolean checkFormat(){
        if (instruction.length() <= 15)
            return !(instruction.charAt(8) != ' ');
        else
            return !(instruction.charAt(8) != ' ' || instruction.charAt(15) != ' ' || instruction.charAt(16) != ' ' || instruction.charAt(16) != '=');

    }
    private void splitInstruction(){
        String label = null;
        String opcode = null;
        String operand = null;
        label = getInstruction().substring(0, 8);
        if (getInstruction().length() <= 15){
            opcode = getInstruction().substring(9, getInstruction().length());
            if ((label.startsWith(" ") && !label.equals("        ")) || opcode.startsWith(" ") )
                errorFlag = true;
            else{
                Label = trim(label);
                OPCode = trim (opcode);
                errorFlag = false;
            }
        }
        else{
            opcode = getInstruction().substring(9, 15);
            if (getInstruction().length() <= 35)
                operand = getInstruction().substring(17, getInstruction().length());
            else
                operand = getInstruction().substring(17, 35);
            if ((label.startsWith(" ") && !label.equals("        ")) || opcode.startsWith(" ") || operand.startsWith(" ") && !operand.equals("                  "))
                errorFlag = true;
            else{
                Label = trim(label);
                OPCode = trim (opcode);
                Operand = trim (operand);
                errorFlag = false;
            }
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

    public boolean isCheck() {
        return check;
    }

    /**
     * @return the instruction
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * @return the literal
     */
    public boolean isLiteral() {
        return literal;
    }
    
    
}
