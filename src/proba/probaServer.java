/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class probaServer {
    
    public static void main(String[] args) {
        probaServer p = new probaServer();
        p.glavna();
    }
    
    public void glavna(){
        try {
            ServerSocket server = new ServerSocket(555);
            System.out.println("Napravljen server socket!");
            Socket klijent = server.accept();
            System.out.println("Prihvacen klijent");
            
            BufferedReader IN = new BufferedReader(
                    new InputStreamReader(
                        klijent.getInputStream()));
            
            String s;
            
            while (true) {
                while ((s = IN.readLine()) != null)
                    System.out.println(s);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(probaServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
