/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import com.gui.ServerGUI;
import com.klijent.Klijent;
import com.server.ServerStrana;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class KontrolerServer {
    
    public static ArrayList<Klijent> listaAktivnihKlijenataServer;
    public static ServerGUI serverProzor;
    
    public static void main(String[] args) {
        try {
            listaAktivnihKlijenataServer = new ArrayList<>();
            serverProzor = new ServerGUI();
            serverProzor.setVisible(true);
            ServerStrana server = new ServerStrana();
            server.startServer();
        } catch (IOException ex) {
            Logger.getLogger(KontrolerServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void posalji(String s, Klijent k) {
        for (Klijent klijent : listaAktivnihKlijenataServer) {
            if (klijent != k)
                klijent.getOUT().println(k.getIme() + ": " + s);
        }
    }

    public static void dodajKorisnika(Klijent noviKlijent) {
        listaAktivnihKlijenataServer.add(noviKlijent);
    }

    public static void odjaviKorisnika(Klijent noviKlijent) {
        try {
            listaAktivnihKlijenataServer.remove(noviKlijent);
            Socket soc = noviKlijent.getSoc();
            if (soc.isConnected())
                soc.close();
        } catch (IOException ex) {
            Logger.getLogger(KontrolerServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setListaAktivnihKlijenataServer(ArrayList<Klijent> listaAktivnihKlijenataServer) {
        KontrolerServer.listaAktivnihKlijenataServer = listaAktivnihKlijenataServer;
    }
    
    public static ArrayList<Klijent> getlistaAktivnihKlijenataServer() {
        return listaAktivnihKlijenataServer;
    }

    public static void osveziListuKorisnikaGUI() {
        javax.swing.JTextArea prozorSaKlijentima = 
                serverProzor.getTxtAktivniKlijenti();
        prozorSaKlijentima.setText("");
        for (Klijent k : listaAktivnihKlijenataServer) {
            prozorSaKlijentima.append(k.getIme() + "\n");
        }
    }

    public static void ispisiNaServerChat(String s, Klijent noviKlijent) {
        serverProzor.getTxtPoruke().append(noviKlijent.getIme() + ": " + s + "\n");
    }

    public static void ugasiServer() {
        try {
            for (Klijent k : listaAktivnihKlijenataServer) {
                Socket soc = k.getSoc();
                if (soc.isConnected())
                    soc.close();
                listaAktivnihKlijenataServer.remove(k);
            }
        } catch (IOException ex) {
                Logger.getLogger(KontrolerServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
 
    
}
