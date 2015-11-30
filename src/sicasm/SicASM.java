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
        GUI = new GUI();
        GUI.setVisible(true);
    }

    public static void run(String SRCText){
        String lines [] = SRCText.split("\n");
        Counter(lines);
        ObjFormat(lines);
        ListFile(lines);
        ObjProgram(lines);
    }
    
    private static void Counter(String[] lines){
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
    
    private static void ObjFormat(String[] lines){
        for(String line: lines){
            if(line.startsWith(".")){
                ObjCode.addElement("");
                continue;
            }
            SRCformat instruction = new SRCformat(line);
            if((instruction.getOPCode()).equalsIgnoreCase("START") || 
                    (instruction.getOPCode()).equalsIgnoreCase("RESW") ||
                    (instruction.getOPCode()).equalsIgnoreCase("RESB") ||
                    (instruction.getOPCode()).equalsIgnoreCase("END")){
                ObjCode.addElement("");
            }else{
                if(instruction.getOPCode().equalsIgnoreCase("WORD") || 
                        instruction.getOPCode().equalsIgnoreCase("BYTE")){
                    String zeros = "";
                    for(int i=6; i>Integer.toHexString(Integer.parseInt(instruction.getOperand())).length(); --i)
                        zeros+="0";
                    ObjCode.addElement(zeros+Integer.toHexString(Integer.parseInt(instruction.getOperand())));
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
        /* Create Object Program */
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Testing
        ReadWriteFile file = new ReadWriteFile();
        file.setFileName("Test.txt");
        run(file.readFile());
        System.out.println(ListFile);
        
        //Main Running Controller
        Controller();
    }
    
}
