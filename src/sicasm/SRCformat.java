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
    private final boolean check;
    
    public SRCformat (String Instruction){
        this.Instruction = Instruction;
        splitInstruction();
        check = checkFormat();
    }
    private boolean checkFormat(){
        if (Instruction.length() <= 15)
            return !(Instruction.charAt(8) != ' ');
        else
            return !(Instruction.charAt(8) != ' ' || Instruction.charAt(15) != ' ' || Instruction.charAt(16) != ' ');

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
     * @return the Instruction
     */
    public String getInstruction() {
        return Instruction;
    }
    
    
}
