/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import com.gui.KlijentGUIp;
import com.gui.logInKorisnikGUI;
import com.klijent.Klijent;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class KontrolerKlijent {

    private static ArrayList<Klijent> listaAktivnihKorisnika;
    private static KlijentGUIp glavniProzor;
    private static logInKorisnikGUI logInProzor;
    private static Klijent aktivniKlijent;

    private boolean porukaPrimljenaServer  = false;
    private boolean porukaSpremnaServer    = false;
    private boolean porukaSpremnaKlijent   = false;
    private boolean porukaPrimljenaKlijent = false;
    
    public static void main(String[] args) {
        listaAktivnihKorisnika = new ArrayList<>();
        logInProzor = new logInKorisnikGUI();
        logInProzor.setVisible(true);
    }
    
    public static void pokreniKlijentGUI(Klijent k) {
        
        aktivniKlijent = k;
        aktivniKlijent.startKlijent();
        listaAktivnihKorisnika.add(k);
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(logInKorisnikGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(logInKorisnikGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(logInKorisnikGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(logInKorisnikGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    glavniProzor = new KlijentGUIp(k);
                    glavniProzor.setVisible(true);
                }
            });
        new Thread(k).start();
    }
    
    public static void pokreniLogInKorisnikGUI() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(logInKorisnikGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(logInKorisnikGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(logInKorisnikGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(logInKorisnikGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new logInKorisnikGUI().setVisible(true);
            }
        });
    }
    
    public static void posalji() {
        
        String s = glavniProzor.getTxtNovaPoruka().getText();
        if (s == null || s.equals("")) {
            return;
        } else {
            aktivniKlijent.posalji(s);
            glavniProzor.getTxtPoruka().append("Me: " + s + "\n");
        }
        
    }
    
    public static void ispisiPoruku(String porukaPrimljenaPoruka) {
        if (glavniProzor == null) 
            System.out.println("Nije inicijalizovan glavni prozor!");
        else
            glavniProzor.getTxtPoruka().append(porukaPrimljenaPoruka + "\n");
    }
    
    public static void zatvaranje() {
        try {
            listaAktivnihKorisnika.remove(aktivniKlijent);
            Socket soc = aktivniKlijent.getSoc();
            if (soc.isConnected())
                soc.close();
        } catch (IOException ex) {
            Logger.getLogger(KontrolerKlijent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void logInAkcijaKon(String ime, boolean muskiPol) {
        if (ime == null || ime.equals("")) {
            JOptionPane.showMessageDialog(null, "Unesite vase ime!");
        } else if (ime.length() > 25) {
            JOptionPane.showMessageDialog(null, "Unesite nadimak, cisto da ne pukne program!");
        } else {
            try {
                Klijent noviKlijent;
                noviKlijent = new Klijent(ime, muskiPol ? "Muski" : "Zenski");
                KontrolerKlijent.pokreniKlijentGUI(noviKlijent);
                logInProzor.dispose();
            } catch (IOException ex) {
                Logger.getLogger(logInKorisnikGUI.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Doslo je do problema prilikom povezivanja sa serverom!",
                    "Neuspesno povezivanje!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static String getAktivniKlijent() {
        return aktivniKlijent.getIme();
    }
    
}
