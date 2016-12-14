/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import com.klijent.Klijent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import kontroler.KontrolerServer;


public class ServerChat implements Runnable {
    
    private final Socket klijentSocket;
    
    public ServerChat(Socket klijentSocket) {
        this.klijentSocket = klijentSocket;
    }

    public void startChat(){
        try {
            BufferedReader IN = new BufferedReader(
                    new InputStreamReader(
                        klijentSocket.getInputStream()));
            
            String input = IN.readLine();
            int index;
            String ime = input.substring(0, index = input.lastIndexOf(" "));
            String pol = input.substring(index + 1);
            
            Klijent noviKlijent = new Klijent(ime, pol, klijentSocket);
            KontrolerServer.listaAktivnihKlijenataServer.add(noviKlijent);
            System.out.println(input);

            PrintStream OUT = new PrintStream(
                    klijentSocket.getOutputStream());
            
            noviKlijent.setIN(IN);
            noviKlijent.setOUT(OUT);
            
            noviKlijent.getOUT().println("Ostvarena konekcija! " + "Korisnik: " + noviKlijent.getIme() 
                    + " Aktivni korisnici: " 
                        + KontrolerServer.listaAktivnihKlijenataServer);
            String s;
//            Upravo dodato, proba
            Thread.sleep(500);
            noviKlijent.getOUT().println("Pozdrav od servera!");
            Thread.sleep(5000);
            noviKlijent.getOUT().println("Pozdrav od servera!");
            Thread.sleep(10000);
            noviKlijent.getOUT().println("Pozdrav od servera!");
//            proba
            while (true) {
                while ((s = IN.readLine()) != null) {
                    System.out.println(s);
//                    KontrolerServer.posalji(s);
                
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    @Override
    public void run() {
        startChat();
    }
    
    
}
