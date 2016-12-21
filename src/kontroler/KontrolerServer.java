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
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class KontrolerServer {
    
//    public static final int UDP_PORT = 8091;
    public static ArrayList<Klijent> listaAktivnihKlijenataServer;
    public static ServerGUI serverProzor;
    private static int UDPPorts[];
    private static int freeUDPPort;
    
    public static void main(String[] args) {
        try {
            inicijalizacijeUDP();
            listaAktivnihKlijenataServer = new ArrayList<>();
            serverProzor = new ServerGUI();
            serverProzor.setVisible(true);
            ServerStrana server = new ServerStrana();
            server.startServer();
        } catch (IOException ex) {
            Logger.getLogger(KontrolerServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void posalji
    (String s, String klijentiKojimaSeSalje, Klijent noviKlijent) 
    {
        String nizKlijenata[] = klijentiKojimaSeSalje.split(",");
        
        for (String klijent : nizKlijenata) {
            for (Klijent serverKlijent : listaAktivnihKlijenataServer) {
                if (klijent.equals(serverKlijent.getIme())) {
                    serverKlijent.getOUT().println(
                            noviKlijent.getIme() + ": " + s);
                }
            }
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
            for (int i = 0; i < listaAktivnihKlijenataServer.size(); i++) {
                Socket soc = listaAktivnihKlijenataServer.get(i).getSoc();
                if (soc.isConnected())
                    soc.close();
                listaAktivnihKlijenataServer.remove(i);
            }
            serverProzor.dispose();
        } catch (IOException ex) {
                Logger.getLogger(KontrolerServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static void posaljiListuKorisnikaUDP(Klijent noviKlijent) {
        
        try {
            byte buff[];
            DatagramSocket soket = new DatagramSocket();
            InetAddress address = noviKlijent.getSoc().getInetAddress();
            
            String listaKorisnikaZaSlanje = "";
            for (int i = 0; i < listaAktivnihKlijenataServer.size(); i++) {
                listaKorisnikaZaSlanje += listaAktivnihKlijenataServer.get(i).getIme();
                listaKorisnikaZaSlanje += " " + listaAktivnihKlijenataServer.get(i).getPol();
                if (i != listaAktivnihKlijenataServer.size() - 1)
                    listaKorisnikaZaSlanje += "\n";
            }
            
            buff = listaKorisnikaZaSlanje.getBytes();
            
            for (int i = 0; i < listaAktivnihKlijenataServer.size(); i++) {
                DatagramPacket paket = new DatagramPacket(
                        buff, buff.length, address,
                            listaAktivnihKlijenataServer.get(i).getUDP_PORT());
                soket.send(paket);
            }
            
            soket.close();
            
        } catch (SocketException ex) {
            Logger.getLogger(KontrolerServer.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Greska prilikom otvaranja UDP soketa na serveru!");
        } catch (IOException ex) {
            Logger.getLogger(KontrolerServer.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Greska prilikom slanja liste klijenata sa servera!");
        }
        
    }

    public static void dodeliUDP_PORT(Klijent noviKlijent) {
        noviKlijent.setUDP_PORT(UDPPorts[freeUDPPort++]);
    }

    private static void inicijalizacijeUDP() {
        freeUDPPort = 0;
        UDPPorts = new int[512];
        
        for (int i = 0; i < 512; i++) {
            UDPPorts[i] = 8000 + i;
        }
    }
 
    
}
