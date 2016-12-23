/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import com.gui.ServerGUI;
import com.klijent.Klijent;
import com.server.ServerStrana;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class KontrolerServer {
    
//    public static final int UDP_PORT = 8091; -> ako se pokrece jedna aplikacija
//    sa jednog racunara.
    public static ArrayList<Klijent> listaAktivnihKlijenataServer;
    public static LinkedList<Klijent> kontrolnaLista;
    public static ServerGUI serverProzor;
    private static int UDPPorts[];
    private static int freeUDPPort;
    private static final String FILE_LOCATION = "ServerInfo.txt";
    private static PrintWriter OUT_FILE;
    private static File server_file;
//    private static final byte IP_SOBA[] = {24, (byte)135, 62, 56};
            
    public static void main(String[] args) {
        try {
            inicijalizacijeUDP();
            KontrolerServer.otvoriFile();
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
                    KontrolerServer.posaljiSaProverom(noviKlijent, serverKlijent, s);
                }
            }
        }
        KontrolerServer.verifikacijaSlanja(noviKlijent);
        KontrolerServer.obrisiKontrolnuListu();
    }

    private static void posaljiSaProverom
    (Klijent noviKlijent, Klijent serverKlijent, String s)
    {
        try {
            serverKlijent.getOUT().println(
                    noviKlijent.getIme() + ": " + s);
            
        } catch (Exception E) {
            if (kontrolnaLista == null)
                kontrolnaLista = new LinkedList<>();
            kontrolnaLista.addLast(serverKlijent);
        }
    }
    
    private static void verifikacijaSlanja(Klijent noviKlijent) {
        if (kontrolnaLista == null) {
            noviKlijent.getOUT().println("server:success");
        } else {
            String odgovorServera = "server:fail:";
            for (int i = 0; i < kontrolnaLista.size(); i++) {
                odgovorServera += kontrolnaLista.get(i).getIme();
                if (i != kontrolnaLista.size() - 1)
                    odgovorServera += ",";
            }
            noviKlijent.getOUT().println(odgovorServera);
        }
    }
    
    private static void obrisiKontrolnuListu() {
        if (kontrolnaLista != null)
            kontrolnaLista = null;
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
                Klijent k = listaAktivnihKlijenataServer.get(i);
                if (k.getSoc().isConnected()) {
                    k.getOUT().println("server:turnoff");
                    k.getSoc().close();
                }
            }
            listaAktivnihKlijenataServer.clear();
            serverProzor.dispose();
            KontrolerServer.stampajUFileGasenje();
            OUT_FILE.close();
        } catch (IOException ex) {
                Logger.getLogger(KontrolerServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static void posaljiListuKorisnikaUDP(Klijent noviKlijent) {
        
        try {
            byte buff[];
            DatagramSocket soket = new DatagramSocket();
//            InetAddress address = noviKlijent.getSoc().getRemoteSocketAddress();
            String listaKorisnikaZaSlanje = "";
            for (int i = 0; i < listaAktivnihKlijenataServer.size(); i++) {
                listaKorisnikaZaSlanje += listaAktivnihKlijenataServer.get(i).getIme();
                listaKorisnikaZaSlanje += " " + listaAktivnihKlijenataServer.get(i).getPol();
                if (i != listaAktivnihKlijenataServer.size() - 1)
                    listaKorisnikaZaSlanje += "\n";
            }
            
            buff = listaKorisnikaZaSlanje.getBytes();
            
            for (int i = 0; i < listaAktivnihKlijenataServer.size(); i++) {
                Klijent k = listaAktivnihKlijenataServer.get(i);
//                Uzimamo lokalnu adresu, s'obzirom da su svi klijenti na ovom racunaru
//                Promeniti ako se pravi normalan cet!
//                InetAddress address = InetAddress.getByName("192.168.1.13");
                InetAddress address = InetAddress.getLocalHost();
                DatagramPacket paket = new DatagramPacket(
                        buff, buff.length, address, k.getUDP_PORT());
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

    public static void stampajUFile(String s, Klijent k) {
        GregorianCalendar vreme = new GregorianCalendar();
        int dan = vreme.get(GregorianCalendar.DAY_OF_MONTH);
        int mesec = vreme.get(GregorianCalendar.MONTH);
        ++mesec;
        int godina = vreme.get(GregorianCalendar.YEAR);
        int sat = vreme.get(GregorianCalendar.HOUR_OF_DAY);
        int minut = vreme.get(GregorianCalendar.MINUTE);
        int sekund = vreme.get(GregorianCalendar.SECOND);
        OUT_FILE.print(dan + "." + mesec + "." + godina + " "
            + sat + ":" + minut + ":" + sekund + " "); 
        OUT_FILE.println(k.getIme() + ": " + s); 
    }

    private static void otvoriFile() {
        try {
            server_file = new File(FILE_LOCATION);
            if (!server_file.exists())
                server_file.createNewFile();
            
            OUT_FILE = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter(server_file, true)));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KontrolerServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(KontrolerServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void stampajUFileOdjava(String s, Klijent noviKlijent) {
        GregorianCalendar vreme = new GregorianCalendar();
        int dan = vreme.get(GregorianCalendar.DAY_OF_MONTH);
        int mesec = vreme.get(GregorianCalendar.MONTH);
        ++mesec;
        int godina = vreme.get(GregorianCalendar.YEAR);
        int sat = vreme.get(GregorianCalendar.HOUR_OF_DAY);
        int minut = vreme.get(GregorianCalendar.MINUTE);
        int sekund = vreme.get(GregorianCalendar.SECOND);
        OUT_FILE.print(dan + "." + mesec + "." + godina + " "
            + sat + ":" + minut + ":" + sekund + " "); 
        OUT_FILE.println("Prekinuta veza sa korisnikom: " + noviKlijent.getIme());
    }

    private static void stampajUFileGasenje() {
        GregorianCalendar vreme = new GregorianCalendar();
        int dan = vreme.get(GregorianCalendar.DAY_OF_MONTH);
        int mesec = vreme.get(GregorianCalendar.MONTH);
        ++mesec;
        int godina = vreme.get(GregorianCalendar.YEAR);
        int sat = vreme.get(GregorianCalendar.HOUR_OF_DAY);
        int minut = vreme.get(GregorianCalendar.MINUTE);
        int sekund = vreme.get(GregorianCalendar.SECOND);
        OUT_FILE.print(dan + "." + mesec + "." + godina + " "
            + sat + ":" + minut + ":" + sekund + " ");
        OUT_FILE.println("**Gasenje servera!");
    }

    public static void stampajUFileObicno(String s) {
        GregorianCalendar vreme = new GregorianCalendar();
        int dan = vreme.get(GregorianCalendar.DAY_OF_MONTH);
        int mesec = vreme.get(GregorianCalendar.MONTH);
        ++mesec;
        int godina = vreme.get(GregorianCalendar.YEAR);
        int sat = vreme.get(GregorianCalendar.HOUR_OF_DAY);
        int minut = vreme.get(GregorianCalendar.MINUTE);
        int sekund = vreme.get(GregorianCalendar.SECOND);
        OUT_FILE.print(dan + "." + mesec + "." + godina + " "
            + sat + ":" + minut + ":" + sekund + " ");
        OUT_FILE.println(s);
    }

       
}
