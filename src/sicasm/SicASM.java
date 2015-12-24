/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicasm;

import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Ahmed
 */
public class SicASM {

    private static GUI GUI;
    private static final Hashtable OPTAB = new Hashtable(0);
    private static final Hashtable SYMTAB = new Hashtable();
    private static Hashtable LITTAB = new Hashtable(true);
    private static int LOCCTR;
    private static final Vector<String> Address = new Vector();
    private static final Vector<String> ObjCode = new Vector();
    private static String ListFile = "";
    private static String ObjProgram = "";
    private static ArrayList<String> names = new ArrayList();
    private static ArrayList<ArrayList<String>> allNames = new ArrayList();
    private static ArrayList<Hashtable> LITTABs = new ArrayList();

    //Main Running Controller Function.
    private static void Controller() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI = new GUI();
                GUI.setVisible(true);
            }
        });
    }

    public static void run(String SRCText) {
        String lines[] = SRCText.split("\n");
        ListFile = "";
        ObjProgram = "";
        /*if (ErrorsHandler.Before(lines)) {
                return;
            }*/
        Counter(lines);
        ObjFormat(lines);
        ListFile(lines);
        ObjProgram(lines);
        GUI.setListText(ListFile);
        GUI.setObjText(ObjProgram);
    }

    /*public static void setError(String Error) {
        ListFile = "";
        GUI.setListText(Error);
    }*/
    private static void Counter(String[] lines) {
        for (String line : lines) {
            if (line.startsWith(".")) {
                Address.addElement("");
                continue;
            }
            SRCformat instruction = new SRCformat(line);
            /*if (ErrorsHandler.Format(instruction)) {
                return;
            }*/
            if ((instruction.getOPCode()).equalsIgnoreCase("START")) {
                LOCCTR = Integer.parseInt(instruction.getOperand(), 16);
                Address.addElement(Integer.toHexString(LOCCTR));
                continue;
            }
            Address.addElement(Integer.toHexString(LOCCTR));

            if (instruction.isLiteral()) {
                String name;
                boolean found = false;
                if (instruction.getOperand().startsWith("C'") || instruction.getOperand().startsWith("c'") || instruction.getOperand().startsWith("X'") || instruction.getOperand().startsWith("x'")) {
                    name = instruction.getOperand().substring(2, instruction.getOperand().length() - 1);
                    if (LITTABs.size() > 0) {
                        for (int i = 0; i < LITTABs.size(); i++) {
                            if (LITTABs.get(i).getValue(name, 0) != null) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {

                            names.add(name);
                            LITTAB.setHash(name, instruction.getOperand());
                        }
                    } else {
                        names.add(name);
                        LITTAB.setHash(name, instruction.getOperand());
                    }
                } else {
                    name = instruction.getOperand();
                    if (LITTABs.size() > 0) {
                        for (int i = 0; i < LITTABs.size(); i++) {
                            if (LITTABs.get(i).getValue(instruction.getOperand(), 0) != null) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {

                            names.add(name);
                            LITTAB.setHash(name, instruction.getOperand());
                        }
                    } else {
                        names.add(name);
                        LITTAB.setHash(name, name);
                    }
                }
            } else if (instruction.getOPCode().equalsIgnoreCase("LTORG") || instruction.getOPCode().equals("END")) {
                if (names.size() > 0) {
                    Address.addElement(Integer.toHexString(LOCCTR));
                    LITTAB.setAddress(names.get(0), Integer.toHexString(LOCCTR));
                    int Length;
                    for (int i = 1; i < names.size(); i++) {
                        Length = Integer.parseInt(LITTAB.getValue(names.get(i - 1), 2));
                        LOCCTR += Length;
                        Address.addElement(Integer.toHexString(LOCCTR));
                        LITTAB.setAddress(names.get(i), Integer.toHexString(LOCCTR));
                    }
                    LITTABs.add(LITTAB);
                    allNames.add(names);
                    LITTAB = new Hashtable(true);
                    names = new ArrayList();
                }
            }
            if (!(instruction.getLabel()).equals("")) {
                /*if (ErrorsHandler.DuplicatedLabel(SYMTAB.getValue(instruction.getLabel(), 0), instruction.getLabel())) {
                    return;
                }*/
                SYMTAB.setHash(instruction.getLabel(), Address.lastElement());
            }
            if ((instruction.getOPCode()).equalsIgnoreCase("BYTE")) {
                LOCCTR++;
            } else if ((instruction.getOPCode()).equalsIgnoreCase("RESB")) {
                LOCCTR += Integer.parseInt(instruction.getOperand());
            } else if ((instruction.getOPCode()).equalsIgnoreCase("RESW")) {
                LOCCTR += 3 * Integer.parseInt(instruction.getOperand());
            } else {
                LOCCTR += 3;
            }
        }
    }

    private static void ObjFormat(String[] lines) {
        int counter = 0;
        for (String line : lines) {
            if (line.startsWith(".")) {
                ObjCode.addElement("");
                continue;
            }
            SRCformat instruction = new SRCformat(line);
            /*if (ErrorsHandler.InvaledOpCodeInstruction(OPTAB.getValue(instruction.getOPCode(), 0), instruction.getOPCode())) {
                throw new Exception();
            }*/
            if ((instruction.getOPCode()).equalsIgnoreCase("START")
                    || (instruction.getOPCode()).equalsIgnoreCase("RESW")
                    || (instruction.getOPCode()).equalsIgnoreCase("RESB")) {
                ObjCode.addElement("");
            } else if (instruction.getOPCode().equalsIgnoreCase("WORD")
                    || instruction.getOPCode().equalsIgnoreCase("BYTE")) {
                ObjCode.addElement(ZeroFormat(Integer.parseInt(instruction.getOperand()), 6));
            } else if (instruction.getOPCode().equalsIgnoreCase("RSUB")) {
                ObjCode.addElement(OPTAB.getValue(instruction.getOPCode(), 0) + "0000");
            } else if (instruction.getOPCode().equalsIgnoreCase("LTORG") || instruction.getOPCode().equalsIgnoreCase("END")) {
                ObjCode.addElement("");
                if (allNames.size() > 0) {
                    for (int i = 0; i < allNames.get(counter).size(); i++) {
                        ObjCode.addElement(ZeroFormat(Integer.parseInt(LITTABs.get(counter).getValue(allNames.get(counter).get(i), 1), 16), 6));
                    }
                }
                counter++;
            } else if (instruction.getOperand().endsWith(",X") || instruction.getOperand().endsWith(",x")) {
                String Modifed = Integer.toHexString(Integer.parseInt(SYMTAB.getValue(instruction.getOperand().substring(0, instruction.getOperand().length() - 2), 0), 16) | (int) Math.pow(2, 15));
                ObjCode.addElement(OPTAB.getValue(instruction.getOPCode(), 0) + Modifed);
            } else if (instruction.isLiteral()) {
                for (int count = 0; count < allNames.size(); count++) {
                    for (int i = 0; i < allNames.get(count).size(); i++) {
                        if (instruction.getOperand().startsWith("C'") || instruction.getOperand().startsWith("c'") || instruction.getOperand().startsWith("X'") || instruction.getOperand().startsWith("x'")) {
                            if (instruction.getOperand().substring(2, instruction.getOperand().length() - 1).equalsIgnoreCase(allNames.get(count).get(i))) {
                                ObjCode.addElement(OPTAB.getValue(instruction.getOPCode(), 0) + LITTABs.get(count).getValue(instruction.getOperand().substring(2, instruction.getOperand().length() - 1), 3));
                            }
                        } else if (instruction.getOperand().equalsIgnoreCase(allNames.get(count).get(i))) {
                            ObjCode.addElement(OPTAB.getValue(instruction.getOPCode(), 0) + LITTABs.get(count).getValue(instruction.getOperand(), 3));
                        }
                    }
                }

            } else {
                ObjCode.addElement(OPTAB.getValue(instruction.getOPCode(), 0) + SYMTAB.getValue(instruction.getOperand(), 0));
            }
        }
    }

    private static void ListFile(String[] lines) {
        int counter = 0;
        int count = 0;
        String name;
        for (int i = 0; i < lines.length; ++i) {
            SRCformat instruction = new SRCformat(lines[i]);
            if (instruction.getOPCode().equalsIgnoreCase("LTORG") || instruction.getOPCode().equalsIgnoreCase("END")) {
                if (allNames.size() > 0) {
                    ListFile += Address.get(count).toUpperCase() + '\t' + ObjCode.get(count).toUpperCase() + '\t' + lines[i] + '\n';
                    count++;
                    for (int j = 0; j < allNames.get(counter).size(); j++) {
                        name = allNames.get(counter).get(j);
                        ListFile += Address.get(count).toUpperCase() + '\t' + ObjCode.get(count).toUpperCase() + "\t*\t=" + LITTABs.get(counter).getValue(name, 0) + '\n';
                        count++;
                    }
                }
                counter++;
            } else {
                ListFile += Address.get(count).toUpperCase() + '\t' + ObjCode.get(count).toUpperCase() + '\t' + lines[i] + '\n';
                count++;
            }

        }
    }

    private static void ObjProgram(String[] lines) {
        SRCformat firstIns = new SRCformat(lines[0]);
        String programName;
        if ((firstIns.getLabel()).length() > 6) {
            programName = (firstIns.getLabel()).substring(0, 7);
        } else {
            String spaces = "";
            for (int i = 6; i > firstIns.getLabel().length(); --i) {
                spaces += " ";
            }
            programName = firstIns.getLabel() + spaces;
        }
        int Length = Integer.parseInt(Address.lastElement(), 16) - Integer.parseInt(Address.firstElement(), 16);
        String programLength = ZeroFormat(Length, 6);
        String startAddress = ZeroFormat(Integer.parseInt(Address.firstElement(), 16), 6);
        ObjProgram += "H^" + programName + '^' + startAddress + '^' + programLength + '\n';
        boolean loop = true;
        int i = 1;
        while (loop) {
            int recordLength = 0;
            int startAddIndex = i;
            String record = "";
            for (int j = i; j < lines.length - 1; j++) {
                SRCformat ins = new SRCformat(lines[j]);
                if (ins.getOPCode().equalsIgnoreCase("RESB") || ins.getOPCode().equalsIgnoreCase("RESW")) {
                    i = j + 1;
                    loop = true;
                    break;
                } else if (recordLength == 60) {
                    i = j;
                    loop = true;
                    break;
                } else {
                    if(!ObjCode.elementAt(j).equals("")){
                        record += "^" + ObjCode.elementAt(j);
                        recordLength += ObjCode.elementAt(j).length();
                        loop = false;
                    }
                }
            }
            if (!record.equals("")) {
                ObjProgram += "T^" + ZeroFormat(Integer.parseInt(Address.elementAt(startAddIndex), 16), 6)
                        + '^' + ZeroFormat(recordLength / 2, 2) + record + '\n';
            }
        }
        ObjProgram = ObjProgram + "E^" + ZeroFormat(Integer.parseInt(Address.firstElement(), 16), 6);
        ObjProgram = ObjProgram.toUpperCase();
    }

    private static String ZeroFormat(int x, int length) {
        String zeros = "";
        for (int i = length; i > Integer.toHexString(x).length(); --i) {
            zeros += "0";
        }
        return zeros + Integer.toHexString(x);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Testing

        //Main Running Controller
        Controller();
    }

}
