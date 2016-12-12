package com.klijent;


import com.server.ServerStrana;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nepoznat
 */
public class Klijent  implements Runnable{
    
    public static void main(String[] args) {
        Klijent k = new Klijent();
        k.run();
    }
    
    public static final int PORT = 444;
    private String ime;
    private String pol;
    private Socket soc; 
    private static boolean porukaSpremna = false;
    private static boolean porukaPrimljena = false;
    private static String porukaZaSlanje = null;
    
    public Klijent(){}

    public Klijent(String ime, String pol) {
        this.ime = ime;
        this.pol = pol;
    }
    
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public Socket getSoc() {
        return soc;
    }

    public void setSoc(Socket soc) {
        this.soc = soc;
    }

    public static boolean isPorukaSpremna() {
        return porukaSpremna;
    }

    public static void setPorukaSpremna(boolean porukaSpremna) {
        Klijent.porukaSpremna = porukaSpremna;
    }
    
    public static boolean isPorukaPrimljena() {
        return porukaPrimljena;
    }

    public static void setPorukaPrimljena(boolean porukaPrimljena) {
        Klijent.porukaPrimljena = porukaPrimljena;
    }

    public static String getPorukaZaSlanje() {
        return porukaZaSlanje;
    }

    public static void setPorukaZaSlanje(String porukaZaSlanje) {
        Klijent.porukaZaSlanje = porukaZaSlanje;
    }
    
    
    public synchronized void startKlijent(){
        
        try {
            soc = new Socket("localhost", ServerStrana.PORT);
            
            PrintStream OUT = new PrintStream(
                    soc.getOutputStream());
            
            OUT.println("Desi serveru, legendo?");
            
            BufferedReader IN = new BufferedReader(
                    new InputStreamReader(
                        soc.getInputStream()));
            
            String s = IN.readLine();
            System.out.println(s);
            
            while (true) {
                if (porukaSpremna) {
                    OUT.println(porukaZaSlanje);
                    System.out.println("Poslata poruka!");
                    porukaSpremna = false;
                } else if (porukaPrimljena) {
                    while ((s = IN.readLine()) != null && !s.equals(""))
                        System.out.println(s);
                    porukaPrimljena = false;
                }
                Thread.sleep(500);
            } 
            
        } catch (IOException ex) {
            Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
        }  
    
    }

    @Override
    public void run() {
        startKlijent();
    }
    
    public static void posalji(String poruka) {
        setPorukaZaSlanje(poruka);
        System.out.println("Namestena poruka za slanje: " + getPorukaZaSlanje());
        porukaSpremna = true;
        System.out.println("Poruka spremna: " + porukaSpremna);
    }
    
}
