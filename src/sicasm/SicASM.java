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
    private static final Hashtable OPTAB = new Hashtable(0);
    private static final Hashtable SYMTAB = new Hashtable();
    private static int LOCCTR;
    private static final Vector<String> Address = new Vector();
    private static final Vector<String> ObjCode = new Vector();
    private static String ListFile = "";
    private static String ObjProgram = "";
    
    //Main Running Controller Function.
    private static void Controller(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI = new GUI();
                GUI.setVisible(true);
            }
        });
    }

    public static void run(String SRCText){
        try{
            String lines [] = SRCText.split("\n");
            ListFile = "";
            ObjProgram = "";
            if(ErrorsHandler.Before(lines))return;
            Counter(lines);
            ObjFormat(lines);
            ListFile(lines);
            ObjProgram(lines);
            GUI.setListText(ListFile);
            GUI.setObjText(ObjProgram);
        }catch(Exception e){
            
        }
    }
    
    public static void setError(String Error){
        ListFile = "";
        GUI.setListText(Error);
    }
    
    private static void Counter(String[] lines){
        for (String line: lines){
            if (line.startsWith(".")){
                Address.addElement("");
                continue;
            }
            SRCformat instruction = new SRCformat(line);
            if(ErrorsHandler.Format(instruction))return;
            if ((instruction.getOPCode()).equalsIgnoreCase("START")) {
                LOCCTR = Integer.parseInt(instruction.getOperand(), 16);
                Address.addElement(Integer.toHexString(LOCCTR));
                continue;
            }
            Address.addElement(Integer.toHexString(LOCCTR));
            if (!(instruction.getLabel()).equals("")){
                if(ErrorsHandler.DuplicatedLabel(SYMTAB.getValue(instruction.getLabel()),instruction.getLabel()))return;
                SYMTAB.setHash(instruction.getLabel(), Address.lastElement());
            }
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
    
    private static void ObjFormat(String[] lines) throws Exception{
        for(String line: lines){
            if(line.startsWith(".")){
                ObjCode.addElement("");
                continue;
            }
            SRCformat instruction = new SRCformat(line);
            if(ErrorsHandler.InvaledOpCodeInstruction(OPTAB.getValue(instruction.getOPCode()), instruction.getOPCode()))throw new Exception();
            if((instruction.getOPCode()).equalsIgnoreCase("START") || 
                    (instruction.getOPCode()).equalsIgnoreCase("RESW") ||
                    (instruction.getOPCode()).equalsIgnoreCase("RESB") ||
                    (instruction.getOPCode()).equalsIgnoreCase("END")){
                ObjCode.addElement("");
            }else{
                if(instruction.getOPCode().equalsIgnoreCase("WORD") || 
                        instruction.getOPCode().equalsIgnoreCase("BYTE")){
                    ObjCode.addElement(ZeroFormat(Integer.parseInt(instruction.getOperand()), 6));
                }else if (instruction.getOperand() == null){
                    ObjCode.addElement(OPTAB.getValue(instruction.getOPCode()) + "0000");
                }else{
                    if(instruction.getOperand().endsWith(",X") || instruction.getOperand().endsWith(",x")){
                        String Modifed = Integer.toHexString(Integer.parseInt(SYMTAB.getValue(instruction.getOperand().substring(0, instruction.getOperand().length()-2)), 16) | (int)Math.pow(2, 15));
                        ObjCode.addElement(OPTAB.getValue(instruction.getOPCode())+ Modifed);
                    }else
                        ObjCode.addElement(OPTAB.getValue(instruction.getOPCode())+SYMTAB.getValue(instruction.getOperand()));
                }
            }
        }
    }
    
    private static void ListFile(String[] lines){
        for(int i=0; i<lines.length; ++i){
            ListFile+=Address.get(i).toUpperCase()+'\t'+ObjCode.get(i).toUpperCase()+'\t'+lines[i]+'\n';
        }
    }
    
    private static void ObjProgram(String[] lines){
        SRCformat firstIns = new SRCformat (lines[0]);
        String programName;
        if ((firstIns.getLabel()).length() > 6){
            programName = (firstIns.getLabel()).substring(0,7);
        }else{
            String spaces = "";
            for(int i=6; i > firstIns.getLabel().length() ; --i)
                spaces += " ";
            programName = firstIns.getLabel() + spaces;
        }
        int Length = Integer.parseInt(Address.lastElement(), 16) - Integer.parseInt(Address.firstElement(), 16);
        String programLength = ZeroFormat(Length, 6);
        String startAddress = ZeroFormat(Integer.parseInt(Address.firstElement(), 16), 6);
        ObjProgram += "H^" + programName + '^' + startAddress + '^' + programLength + '\n';
        boolean loop = true;
        int i = 1;
        while (loop){
            int recordLength = 0;
            int startAddIndex = i;
            String record = "";
            for(int j=i; j<lines.length-1 ; j++){
                if (ObjCode.elementAt(j).equals("")){
                    i = j + 1;
                    loop = true;
                    break;
                }else if ( recordLength == 60 ){
                    i = j;
                    loop = true;
                    break;
                }else{
                    record += "^" + ObjCode.elementAt(j);
                    recordLength += ObjCode.elementAt(j).length();
                    loop = false;
                }
            }
            if (!record.equals("")){
                ObjProgram += "T^" + ZeroFormat(Integer.parseInt(Address.elementAt(startAddIndex), 16), 6) 
                        + '^' + ZeroFormat(recordLength/2, 2) + record + '\n';
            }
        }
        ObjProgram = ObjProgram + "E^" + ZeroFormat(Integer.parseInt(Address.firstElement(), 16), 6);
        ObjProgram = ObjProgram.toUpperCase();
    }
    
    private static String ZeroFormat(int x, int length){
        String zeros = "";
        for(int i=length; i>Integer.toHexString(x).length(); --i)
            zeros+="0";
        return zeros + Integer.toHexString(x);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Testing
//        ReadWriteFile file = new ReadWriteFile();
//        file.setFileName("Test.txt");
//        run(file.readFile());
//        System.out.println(ListFile);
//        System.out.println(ObjProgram);

        //Main Running Controller
        Controller();
    }
    
}
