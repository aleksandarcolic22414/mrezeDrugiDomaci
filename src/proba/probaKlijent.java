/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proba;

import java.io.IOException;
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
            PrintStream OUT = new PrintStream(klijent.getOutputStream());
            
            for (int i = 0; i < 10; i++) {
                OUT.println("Pozdrav " + (i+1) + "-ti put");
            }
            
            Scanner IN = new Scanner(System.in);
            
            while (true) {
                OUT.println(IN.nextLine());
            }
            
        } catch (IOException ex) {
            Logger.getLogger(probaKlijent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
