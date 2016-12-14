package com.server;


import com.klijent.Klijent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import kontroler.KontrolerServer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nepoznat
 */
public class ServerStrana {
    
    public static final int PORT = 444;
    
    public ServerStrana(){}
   
    public void startServer() throws IOException{
        
        ServerSocket serverSocket = new ServerSocket(PORT);
        
        try {
            while (true) {
                Socket klijentSocket = serverSocket.accept();
                Thread X = new Thread(new ServerChat(klijentSocket));
                X.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerStrana.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
