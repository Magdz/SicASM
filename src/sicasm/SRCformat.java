package sicasm;

import com.sun.org.apache.xpath.internal.compiler.OpCodes;
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
    private boolean duplicated;
    private boolean Exp;
    private boolean EQULabel;
    private boolean validOperand;
    
    public SRCformat (String Instruction){
        this.instruction = Instruction;
        splitInstruction();
        check = checkFormat();
        literal = checkLiteral();
        duplicated = false;
        Exp = true;
        EQULabel = false;
        validOperand = true;
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
    
    private void splitInstruction(){
        String label;
        String opcode;
        String operand;
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
    
    //Handling Errors
    private boolean checkFormat(){
        if (instruction.length() <= 15)
            return !(instruction.charAt(8) != ' ');
        else
            return !(instruction.charAt(8) != ' ' || instruction.charAt(15) != ' ' || instruction.charAt(16) != ' ' || instruction.charAt(16) != '=');
    }
    
    public boolean StartAddress(){
        if(getOPCode().equalsIgnoreCase("START")) return false;
        SicASM.setError("  *** INVALID STARTING ADDRESS");
        return true;
    }
    
    public boolean EndAddress(){
        if(getOPCode().equalsIgnoreCase("END"))return false;
        SicASM.setError("  *** NO ENDING ADDRESS SPECIFIED");
        return true;
    }
    
    public boolean ProgramName(String line){
        if(line.equalsIgnoreCase(getOperand())
                || line.equalsIgnoreCase(getOperand()))return false;
        SicASM.setError("  *** INVALID ENDING ADDRESS");
        return true;
    }
    
    public boolean Format(){
        if(checkFormat() || !isErrorFlag())return false;
        SicASM.setError("  *** INVALID INSTRUCTION FORMAT");
        return true;
    }
    
    public void DuplicatedLabel(){
        if (isDuplicated()) SicASM.setError("  *** INVALID INSTRUCTION FORMAT");
    }
    
    public boolean InvalidOpCodeInstruction(){
        if(OPCode != null)return false;
        if(OPCode.equalsIgnoreCase("START") || OPCode.equalsIgnoreCase("END") 
                || OPCode.equalsIgnoreCase("RESW") || OPCode.equalsIgnoreCase("RESB")
                || OPCode.equalsIgnoreCase("BYTE") || OPCode.equalsIgnoreCase("WORD") 
                || OPCode.equalsIgnoreCase("LTORG") || OPCode.equalsIgnoreCase("ORG") || OPCode.equalsIgnoreCase("EQU"))return false;
        SicASM.setError("  *** INVALID Opcode Instruction: ");
        return true;
    }
    
    public void validExpression (){
        if (!isExp()) SicASM.setError("  *** Invalid Expression");
    }
    
    public void validEQULabel (){
        if (isEQULabel()) SicASM.setError("  *** Invalid Instruction");
    }
    
    public void validOperand (){
        if(!isValidOperand()) SicASM.setError("  *** Invalid Operand");
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

    /**
     * @return the duplicated
     */
    public boolean isDuplicated() {
        return duplicated;
    }

    /**
     * @param duplicated the duplicated to set
     */
    public void setDuplicated(boolean duplicated) {
        this.duplicated = duplicated;
    }

    /**
     * @return the Exp
     */
    public boolean isExp() {
        return Exp;
    }

    /**
     * @param Exp the Exp to set
     */
    public void setExp(boolean Exp) {
        this.Exp = Exp;
    }

    /**
     * @return the EQULabel
     */
    public boolean isEQULabel() {
        return EQULabel;
    }

    /**
     * @param EQULabel the EQULabel to set
     */
    public void setEQULabel(boolean EQULabel) {
        this.EQULabel = EQULabel;
    }

    /**
     * @return the validOperand
     */
    public boolean isValidOperand() {
        return validOperand;
    }

    /**
     * @param validOperand the validOperand to set
     */
    public void setValidOperand(boolean validOperand) {
        this.validOperand = validOperand;
    }
    
    
}
