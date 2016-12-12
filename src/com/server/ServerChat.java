/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerChat implements Runnable{
    
    private Socket klijent;
    
    public ServerChat(Socket klijent) {
        this.klijent = klijent;
    }

    public void startChat(){
        try {
            BufferedReader IN = new BufferedReader(
                    new InputStreamReader(
                        klijent.getInputStream()));
            
            PrintStream OUT = new PrintStream(klijent.getOutputStream());
            
            OUT.println("Ostvarena konekcija!");
            String s;
            while (true) {
                while ((s = IN.readLine()) != null)
                    System.out.println(s);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    @Override
    public void run() {
        startChat();
    }
    
    
}
