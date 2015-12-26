/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicasm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import javafx.util.Pair;

/**
 *
 * @author Ahmed
 */
public class SicASM {

    private static GUI GUI;
    private static Hashtable OPTAB = new Hashtable(0);
    private static Hashtable SYMTAB = new Hashtable(false, 2);
    private static Hashtable LITTAB = new Hashtable(true, 4);
    private static int LOCCTR;
    private static int TempLOCCTR;
    private static Vector<String> Address = new Vector();
    private static Vector<String> ObjCode = new Vector();
    private static String ListFile = "";
    private static String ObjProgram = "";
    private static ArrayList<String> names = new ArrayList();
    private static ArrayList<ArrayList<String>> allNames = new ArrayList();
    private static ArrayList<Hashtable> LITTABs = new ArrayList();
    private static Queue<Pair<String, Integer>> Errors;

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
    
    public static void intialize(){
        OPTAB = new Hashtable(0);
        SYMTAB = new Hashtable(false, 2);
        LITTAB = new Hashtable(true, 4);
        LOCCTR = 0;
        Address = new Vector();
        ObjCode = new Vector();
        ListFile = "";
        ObjProgram = "";
        names = new ArrayList();
        allNames = new ArrayList();
        LITTABs = new ArrayList();
        Errors = new LinkedList();
    }

    public static void run(String SRCText) {
        intialize();
        String lines[] = SRCText.split("\n");
        ErrorsHandler.Before(lines);
        try{
            Counter(lines);
            ObjFormat(lines);
            ListFile(lines);
            ObjProgram(lines);
        }catch(Exception e){
            while(!Errors.isEmpty()){
                ListFile += Errors.poll().getKey();
            }
        }
        GUI.setListText(ListFile);
        GUI.setObjText(ObjProgram);
    }

    public static void setError(String Error, int Place) {
        Errors.add(new Pair(Error+"\n", Place));
    }
    
    private static void Counter(String[] lines) {
        int place = -1;
        for (String line : lines) {
            place++;
            if (line.startsWith(".")) {
                Address.addElement("");
                continue;
            }
            SRCformat instruction = new SRCformat(line);
            ErrorsHandler.Format(instruction, place);
            ErrorsHandler.DuplicatedLabel(SYMTAB.getValue(instruction.getLabel(), 0), instruction.getLabel(), place);
            if ((instruction.getOPCode()).equalsIgnoreCase("START")) {
                LOCCTR = Integer.parseInt(instruction.getOperand(), 16);
                TempLOCCTR = LOCCTR;
                Address.addElement(Integer.toHexString(LOCCTR));
                continue;
            }
            if((instruction.getOPCode()).equalsIgnoreCase("ORG")){
                if(instruction.getOperand() == null){
                    LOCCTR = TempLOCCTR;
                }else{
                    String newLOCCTR = SYMTAB.getValue(instruction.getOperand(), 0);
                    if(newLOCCTR != null){
                        TempLOCCTR = LOCCTR;
                        LOCCTR = Integer.parseInt(newLOCCTR, 16);
                    }else{
                        ErrorsHandler.UnDefinedLabel(null, instruction.getOperand(), place);
                    }
                }
                Address.addElement("");
                continue;
            }
            if((instruction.getOPCode()).equalsIgnoreCase("EQU")){
                if(instruction.getLabel().equalsIgnoreCase("")){
                    ErrorsHandler.MissingOperand(place);
                }else if(instruction.getOperand().equalsIgnoreCase("*")){
                    SYMTAB.setHash(instruction.getLabel(), Integer.toHexString(LOCCTR), "Relative");
                }else{
                    try{
                        SYMTAB.setHash(instruction.getLabel(), ZeroFormat(Integer.parseInt(instruction.getOperand()),4), "Relative");
                    }catch(Exception e){
                        String Expression = instruction.getOperand();
                        String value = SYMTAB.getValue(Expression, 0);
                        if(value != null){
                            SYMTAB.setHash(instruction.getLabel(), value, "Relative");
                        }else{
                            try{
                                CalcExpression(Expression, instruction);
                            }catch(Exception ex){
                                ErrorsHandler.InvaledExpresssion(place);
                            }
                        }
                    }
                }
                Address.addElement("");
                continue;
            }
            Address.addElement(Integer.toHexString(LOCCTR));
            if (instruction.isLiteral()) {
                String name = null;
                boolean found = false;
                if ((instruction.getOperand().startsWith("C'") || instruction.getOperand().startsWith("c'") || instruction.getOperand().startsWith("X'") || instruction.getOperand().startsWith("x'")) && instruction.getOperand().endsWith("'")) {
                    name = instruction.getOperand().substring(2, instruction.getOperand().length() - 1);
                    if (LITTABs.size() > 0) {
                        for (Hashtable LITTAB1 : LITTABs) {
                            if (LITTAB1.getValue(name, 0) != null) {
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
                    try{
                        name = instruction.getOperand();
                        Integer.parseInt(name);
                        if (LITTABs.size() > 0) {
                            for (Hashtable LITTAB1 : LITTABs) {
                                if (LITTAB1.getValue(instruction.getOperand(), 0) != null) {
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
                    }catch(Exception e){
                        System.out.println(place);
                        ErrorsHandler.UnDefinedLabel(null, name, place);
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
                    LITTAB = new Hashtable(true, 4);
                    names = new ArrayList();
                }
            }
            if (!(instruction.getLabel()).equals("")) {
                SYMTAB.setHash(instruction.getLabel(), Address.lastElement(), "Relative");
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
        int place = -1;
        for (String line : lines) {
            place++;
            if (line.startsWith(".")) {
                ObjCode.addElement("");
                continue;
            }
            SRCformat instruction = new SRCformat(line);
            ErrorsHandler.InvaledOpCodeInstruction(OPTAB.getValue(instruction.getOPCode(), 0), instruction.getOPCode(), place);
            if ((instruction.getOPCode()).equalsIgnoreCase("START")
                    || (instruction.getOPCode()).equalsIgnoreCase("RESW")
                    || (instruction.getOPCode()).equalsIgnoreCase("RESB")
                    || (instruction.getOPCode()).equalsIgnoreCase("ORG")
                    || (instruction.getOPCode()).equalsIgnoreCase("EQU")) {
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
                ErrorsHandler.UnDefinedLabel(SYMTAB.getValue(instruction.getOperand(), 0), instruction.getOperand(),  place);
                ObjCode.addElement(OPTAB.getValue(instruction.getOPCode(), 0) + SYMTAB.getValue(instruction.getOperand(), 0));
            }
        }
    }

    private static void ListFile(String[] lines) {
        int counter = 0;
        int count = 0;
        String name;
        for (int i = 0; i < lines.length; ++i) {
            if(!Errors.isEmpty()){
                if(i == Errors.peek().getValue()){
                    ListFile += Errors.poll().getKey();
                }
            }
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
        boolean flag = false;
        int recordLength;
        String record;
        int i = 1;
        int counter = 0;
        int jcount = 0;
        int icount = 1;
        while (loop) {
            recordLength = 0;
            int startAddIndex = i;
            record = "";
            jcount = icount;
            for (int j = i; j < lines.length - 1; j++) {
                SRCformat ins = new SRCformat(lines[j]);
                if (ins.getOPCode().equalsIgnoreCase("RESB") || ins.getOPCode().equalsIgnoreCase("RESW") || ins.getOPCode().equalsIgnoreCase("ORG") || ins.getOPCode().equalsIgnoreCase("EQU") || ins.getOPCode().equalsIgnoreCase("LTORG")) {
                    if (ins.getOPCode().equalsIgnoreCase("LTORG")) {
                        flag = true;
                        counter++;
                    }
                    i = j + 1;
                    icount = jcount + 1;
                    loop = true;
                    break;
                } else if (recordLength == 60) {
                    i = j;
                    icount = jcount + 1;
                    loop = true;
                    break;
                } else {
                    if (flag) {
                        if (allNames.size() > 0) {
                            for (int k = 0; k < allNames.get(counter - 1).size(); k++) {
                                record += "^" + ObjCode.elementAt(jcount);
                                recordLength += ObjCode.elementAt(jcount).length();
                                jcount++;
                            }
                        }
                        flag = false;
                    }
                    record += "^" + ObjCode.elementAt(jcount);
                    recordLength += ObjCode.elementAt(jcount).length();
                    loop = false;
                }
                jcount++;
            }
            if (!record.equals("")) {
                while(Address.elementAt(startAddIndex).equals("")){
                    if(startAddIndex == 0) break;
                    startAddIndex--;
                }
                ObjProgram += "T^" + ZeroFormat(Integer.parseInt(Address.elementAt(startAddIndex), 16), 6)
                        + '^' + ZeroFormat(recordLength / 2, 2) + record + '\n';
            }
        }
        record = "";
        recordLength = 0;
        if (allNames.size() > 0) {
            jcount++;
            for (int k = 0; k < allNames.get(counter).size(); k++) {
                record += "^" + ObjCode.elementAt(jcount);
                recordLength += ObjCode.elementAt(jcount).length();
                jcount++;
            }
            ObjProgram += "T^" + ZeroFormat(Integer.parseInt(Address.lastElement(), 16), 6)
                        + '^' + ZeroFormat(recordLength / 2, 2) + record + '\n';
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

    private static void CalcExpression(String Expression, SRCformat instruction) throws Exception{
        String parameter = "";
        char operation = 0;
        int result = 0;
        String Type = "";
        for(int i = 0; i <= Expression.length() ; ++i){
            if(i == Expression.length() || Expression.charAt(i) == '+' || Expression.charAt(i) == '-'){
                if(i == Expression.length() && SYMTAB.getValue(parameter, 1) == "Relative"){
                    if((Type == "Relative" && operation == '+') 
                            || (Type == "Absolute" && operation == '-')){
                        throw new Exception();
                    }
                }
                try{
                    switch(operation){
                        case '+':
                            result += Integer.parseInt(parameter);
                            break;
                        case '-':
                            result -= Integer.parseInt(parameter);
                            break;
                        default:
                            Type = "Absolute";
                            result = Integer.parseInt(parameter);
                            break;
                    }
                }catch(Exception ex){
                    switch(operation){
                        case '+':
                            result += Integer.parseInt(SYMTAB.getValue(parameter, 0), 16);
                            break;
                        case '-':
                            result -= Integer.parseInt(SYMTAB.getValue(parameter, 0), 16);
                            break;
                        default:
                            Type = "Relative";
                            result = Integer.parseInt(SYMTAB.getValue(parameter, 0), 16);
                            break;
                    }
                }
                if(i < Expression.length())operation = Expression.charAt(i);
                parameter = "";
            }else{
                parameter += Expression.charAt(i);
            }
        }
        SYMTAB.setHash(instruction.getLabel(), ZeroFormat(result, 4), "Absolute");
    }
    
}
