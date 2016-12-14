/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proba;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author w7
 */
public class probaServerChat implements Runnable {
    
    private Socket klijentSocket;
    
    public probaServerChat(Socket klijentSocket) {
        this.klijentSocket = klijentSocket;
    }

    public void startChat(){
        try {
            
            Objekat obj;
            ObjectInputStream objectIN = new ObjectInputStream(
                    new BufferedInputStream(
                            klijentSocket.getInputStream()));
            while (true) {
                while ((obj = (Objekat)objectIN.readObject()) != null) {
                    System.out.println("Procitan objekat: " + obj);
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(probaServerChat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(probaServerChat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    @Override
    public void run() {
        startChat();
    }
}
