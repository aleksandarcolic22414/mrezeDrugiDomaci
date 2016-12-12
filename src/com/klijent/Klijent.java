package com.klijent;


import com.server.ServerStrana;
import interfaces.klijentInterfejs;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
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
public class Klijent  implements klijentInterfejs, Runnable{
    
    public static void main(String[] args) {
        Klijent k = new Klijent();
        k.run();
    }
    
    public static final int PORT = 444;
    private String ime;
    private String pol;
    private Socket soc; 
    private boolean porukaSpremna = false;
    private boolean porukaPrimljena = false;
    
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

    public boolean isPorukaSpremna() {
        return porukaSpremna;
    }

    public void setPorukaSpremna(boolean porukaSpremna) {
        this.porukaSpremna = porukaSpremna;
    }
    
    public boolean isPorukaPrimljena() {
        return porukaPrimljena;
    }

    public void setPorukaPrimljena(boolean porukaPrimljena) {
        this.porukaPrimljena = porukaPrimljena;
    }
    
    @Override
    public void startKlijent(){
        
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
            
            Scanner ulazZaKlijenta = new Scanner(System.in);
            porukaSpremna = true;
            porukaPrimljena = false;
            
            while (true) {
                if (porukaPrimljena)
                    while ((s = IN.readLine()) != null && !s.equals(""))
                        System.out.println(s);
                if (porukaSpremna)
                    while ((s = ulazZaKlijenta.nextLine()) != null) 
                        OUT.println(s);
            } 
            
        } catch (IOException ex) {
            Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
        } 
    
    }

    @Override
    public void run() {
        startKlijent();
    }
    
    
    
    
}
