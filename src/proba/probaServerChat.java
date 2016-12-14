/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            BufferedReader IN = new BufferedReader(
                    new InputStreamReader(
                        klijentSocket.getInputStream()));
            String input = IN.readLine();
            System.out.println(input);

            PrintStream OUT = new PrintStream(klijentSocket.getOutputStream());
            
            OUT.println("Ostvarena konekcija!");
            String s;
            while (true) {
                while ((s = IN.readLine()) != null)
                    System.out.println(s);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(probaServerChat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    @Override
    public void run() {
        startChat();
    }
}
