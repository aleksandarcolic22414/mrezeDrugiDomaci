/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class probaKlijent {
    
    public static void main(String[] args) {
        probaKlijent p = new probaKlijent();
        p.glavna();
        
    }
    
    public void glavna(){
        try {
            Socket klijent = new Socket("localhost", 555);
            
            PrintStream OUT = new PrintStream(
                    klijent.getOutputStream());
            
//            BufferedReader IN = new BufferedReader(
//                    new InputStreamReader(
//                        klijent.getInputStream()));
           
            OUT.println("Desi serveru, legendo?");
//            String s = IN.readLine();
//            System.out.println(s);
//            for (int i = 0; i < 10; i++) {
//                OUT.println("Pozdrav " + (i+1) + "-ti put");
//            }
            
            Scanner input = new Scanner(System.in);
            while (true) {
                String s = input.nextLine();
                OUT.println(s);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(probaKlijent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
