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
            
            KontrolerServer.dodeliUDP_PORT(noviKlijent);
            KontrolerServer.dodajKorisnika(noviKlijent);
            KontrolerServer.osveziListuKorisnikaGUI();
            noviKlijent.getOUT().println(
                    Integer.toString(
                            noviKlijent.getUDP_PORT()));
            Thread.sleep(2000);
            KontrolerServer.posaljiListuKorisnikaUDP(noviKlijent);
            
            String s;

            while (true) {
                while ((s = IN.readLine()) != null) {
//                    Odraditi stampanje u file, umesto na System.out!
                    System.out.println(noviKlijent.getIme() + ": " + s);
                    String klijentiKojimaSeSalje = IN.readLine();
                    KontrolerServer.posalji(s, klijentiKojimaSeSalje, noviKlijent);
                    KontrolerServer.ispisiNaServerChat(s, noviKlijent);
                }
            }
            
        } catch (IOException ex) {
            KontrolerServer.odjaviKorisnika(noviKlijent);
            System.err.println("Prekinuta veza sa korisnikom: " + noviKlijent.getIme());
            KontrolerServer.osveziListuKorisnikaGUI();
            KontrolerServer.posaljiListuKorisnikaUDP(noviKlijent);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
    }
    
    @Override
    public void run() {
        startChat();
    }
    
    
}
