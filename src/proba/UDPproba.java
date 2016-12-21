/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proba;

import java.net.DatagramPacket;

/**
 *
 * @author Korisnik
 */
public class UDPproba {
    
    public static void main(String[] args) {
        
        String s = "Novi string";
        byte buff[] = s.getBytes();
        
        for (int i = 0; i < buff.length; i++)
            System.out.printf("%d%s", buff[i], i == buff.length - 1 ?  "\n" : " ");
        
    }
    
}
