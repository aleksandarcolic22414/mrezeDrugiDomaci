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
public class Klijent {
    
    public static final int PORT = 444;
    public static final String ADDRESS = "localhost";
    private String ime;
    private String pol;
    private Socket soc; 
    private static boolean porukaSpremna = false;
    private static boolean porukaPrimljena = false;
    private static String porukaZaSlanje = null;
    private static String porukaPrimljenaPoruka = null;
    private PrintStream OUT;
    private BufferedReader IN;
    
    public Klijent(){}

    public Klijent(String ime, String pol) throws IOException {
        this.ime = ime;
        this.pol = pol;
        soc = new Socket(ADDRESS, PORT);
    }
    
    public Klijent(String ime, String pol, Socket soc) {
        this.ime = ime;
        this.pol = pol;
        this.soc = soc;
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
    
    
    public void startKlijent(){
        
        try {
            OUT = new PrintStream(
                    soc.getOutputStream());
            IN = new BufferedReader(
                    new InputStreamReader(
                        soc.getInputStream()));
           
            OUT.println(this);
            String s = IN.readLine();
            System.out.println(s);
            
        } catch (IOException ex) {
            Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
        } 
    
    }
    
    public void posalji(String poruka) {
        setPorukaZaSlanje(poruka);
        porukaSpremna = true;
        OUT.println(poruka);
        porukaSpremna = false;
    }

    @Override
    public String toString() {
        return this.ime + " " + this.pol;
    }
    
    public void slusaj() {
        while (true) {
            try {
                if (isPorukaSpremna()) {
                    OUT.println(getPorukaZaSlanje());
                    setPorukaSpremna(false);
                }
                if (isPorukaPrimljena()) {
                    porukaPrimljenaPoruka = IN.readLine();
                    setPorukaPrimljena(false);
                }
            } catch (IOException ex) {
                Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
    
    
}
