/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proba;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class UDPServer {
    
    public static final int UDP_PORT = 8000;
    public static byte buff[];
    public static DatagramSocket soc;
    public static DatagramPacket p;

    
    
    public static void main(String[] args) {
        
        try {
            
            soc = new DatagramSocket(UDP_PORT);
            buff = new byte[1024];
            
            while (true) {
                
                    p = new DatagramPacket(buff, buff.length);
                    soc.receive(p);
                    String poruka = new String(p.getData(), 0, p.getLength());
                    System.out.println(poruka);
                    
            }
            
        } catch (SocketException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
