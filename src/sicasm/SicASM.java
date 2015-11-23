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
public class SicASM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ReadWriteFile rw = new ReadWriteFile();
        rw.setFileName("love.txt");
        /*String s = rw.readFile();
        System.out.println(s);*/
        rw.writeFile("Basma Kadry Osman/n96/ncollege engineering/nHAPPY");
    }
    
}
