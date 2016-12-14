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
import sun.java2d.d3d.D3DRenderQueue;



public class ServerChat implements Runnable {
    
    private final Socket klijentSocket;
    private Klijent noviKlijent;
    
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
            
            noviKlijent = new Klijent(ime, pol, klijentSocket);
            System.out.println(input);

            PrintStream OUT = new PrintStream(
                    klijentSocket.getOutputStream());
            
            noviKlijent.setIN(IN);
            noviKlijent.setOUT(OUT);
             
            noviKlijent.getOUT().println("Ostvarena konekcija! " + "Korisnik: " + noviKlijent.getIme() 
                    + " Aktivni korisnici: " 
                        + KontrolerServer.listaAktivnihKlijenataServer);
            KontrolerServer.dodajKorisnika(noviKlijent);
            String s;

            while (true) {
                while ((s = IN.readLine()) != null) {
//                    Odraditi stampanje u file, umesto na System.out!
                    System.out.println(noviKlijent.getIme() + ": " + s);
                    KontrolerServer.posalji(s, noviKlijent);
                }
            }
            
        } catch (IOException ex) {
            KontrolerServer.odjaviKorisnika(noviKlijent);
            System.err.println("Prekinuta veza sa korisnikom: " + noviKlijent.getIme());
        }
        
        
    }
    
    @Override
    public void run() {
        startChat();
    }
    
    
}
