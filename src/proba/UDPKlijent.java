/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proba;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class UDPKlijent {
    
    public static final int UDP_PORT = 8000;
    public static DatagramSocket soc;
    public static DatagramPacket paket;
    public static InetAddress address;
    
    public static void main(String[] args) {
        
        try {
            
            String s = "Aleksandar Colic\nMilos Petrovic\nJovana Mitrovic";
            soc = new DatagramSocket();
            
            byte buff[] = new byte[1024];
            buff = s.getBytes();
            address = InetAddress.getLocalHost();
            
            paket = new DatagramPacket(buff, buff.length, address, UDP_PORT);
            soc.send(paket);
            soc.close();
            
        } catch (IOException ex) {
            Logger.getLogger(UDPKlijent.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
    }
    
}
