/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicasm;

import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author TOSHIBA
 */
public class ReadWriteFile {
    
    private String fileName;
    public ReadWriteFile (){
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    
    public String readFile() {
        try{
            BufferedReader buff = new BufferedReader(new FileReader(getFileName()));
            StringBuilder sb = new StringBuilder();
            String line = buff.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = buff.readLine();
            }
            buff.close();
            return sb.toString();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + getFileName() + "'");   
            return null;
        } catch (IOException ex) {
            System.out.println("Error reading file '" + getFileName() + "'");
            return null;
        }
    }
    public void writeFile(String text) {
        try{
            BufferedWriter buff = new BufferedWriter(new FileWriter(getFileName()));
            String [] str = text.split("/n");
            for(String s: str){
                buff.write(s);
                buff.newLine();
            }
            buff.close();
        } catch (IOException ex) {
            System.out.println("Error reading file '" + getFileName() + "'");
        }
    }
}
