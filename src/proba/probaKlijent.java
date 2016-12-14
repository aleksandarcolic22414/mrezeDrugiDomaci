/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
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
            
            ObjectOutputStream objectOUT = new ObjectOutputStream(
                    klijent.getOutputStream());
            
            
            
                Objekat noviObjekat = new Objekat("Aleksandar", 
                        "Colic", "Muski", new Socket("localhost", 555));
                objectOUT.writeObject(noviObjekat);
             
        } catch (IOException ex) {
            Logger.getLogger(probaKlijent.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
}
