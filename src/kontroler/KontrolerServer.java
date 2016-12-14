/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import com.klijent.Klijent;
import com.server.ServerStrana;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class KontrolerServer {
    
    public static ArrayList<Klijent> listaAktivnihKlijenataServer;
    
    public static void main(String[] args) {
        try {
            listaAktivnihKlijenataServer = new ArrayList<>();
            ServerStrana server = new ServerStrana();
            server.startServer();
        } catch (IOException ex) {
            Logger.getLogger(KontrolerServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
