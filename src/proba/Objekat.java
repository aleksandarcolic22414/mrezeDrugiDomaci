/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proba;

import java.io.Serializable;
import java.net.Socket;

/**
 *
 * @author w7
 */
public class Objekat implements Serializable {
    
    private static final long serialVersionUID = 123123123L;
    public String ime;
    public String prezime;
    public String pol;
    public Socket soc;

    public Objekat() {}

    public Objekat(String ime, String prezime, String pol, Socket soc) {
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.soc = soc;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public Socket getSoc() {
        return soc;
    }

    public void setSoc(Socket soc) {
        this.soc = soc;
    }

    @Override
    public String toString() {
        return "Objekat{" + "ime=" + ime + ", prezime=" + prezime + ", pol=" + pol + ", soc=" + soc + '}';
    }

}
