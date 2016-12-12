/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import com.gui.KlijentGUIp;
import com.klijent.Klijent;
import java.net.Socket;
import java.util.ArrayList;



public class KontrolerKlijent {

    private static ArrayList<Klijent> listaAktivnihKorisnika;
    private static KlijentGUIp glavniProzor;
    
    private boolean porukaPrimljenaServer  = false;
    private boolean porukaSpremnaServer    = false;
    private boolean porukaSpremnaKlijent   = false;
    private boolean porukaPrimljenaKlijent = false;
    
    public static void main(String[] args) {
        listaAktivnihKorisnika = new ArrayList<>();
        glavniProzor = new KlijentGUIp();
        glavniProzor.setVisible(true);
        Klijent k = new Klijent();
        k.run();
    }
    
    
    public static void posalji() {
        
        String s = glavniProzor.getTxtNovaPoruka().getText();
        System.out.println(s);
        if (s == null || s.equals("")) {
            return;
        } else {
            Klijent.posalji(s);
            glavniProzor.getTxtPoruka().append(s + "\n");
        }
        
    }
    
    public static void pokreniGlavniProzor(){
        java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new KlijentGUIp().setVisible(true);
                }
            });
    }

}
