/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klijent.udp;

import com.klijent.Klijent;
import com.klijent.KlijentZaSlanjeUDP;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import kontroler.KontrolerKlijent;

/**
 *
 * @author Korisnik
 */
public class KlijentUDP implements Runnable {
    
    private int UDP_PORT;
    byte[] odgovor;
    DatagramSocket datagramSoket;
    DatagramPacket paket;
    
    
    public KlijentUDP() {}

    public KlijentUDP(int udp_port) {
        this.UDP_PORT = udp_port;
    }

    @Override
    public void run() {
        try {
            datagramSoket = new DatagramSocket(UDP_PORT);
            odgovor = new byte[1024];
            paket = new DatagramPacket(odgovor, odgovor.length);
            
            while (true) {
                datagramSoket.receive(paket);
                String odgovorOdServera = new String(paket.getData(), 0, paket.getLength());
                System.out.println("Primljena lista aktivnih klijenata!");
                System.out.println(odgovorOdServera);
                KontrolerKlijent.osveziListu(odgovorOdServera, paket.getAddress());
            }
            
        } catch (SocketException ex) {
            Logger.getLogger(KlijentUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(KlijentUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
