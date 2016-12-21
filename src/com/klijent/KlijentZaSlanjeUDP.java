/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klijent;

import java.io.Serializable;
import java.net.InetAddress;


public class KlijentZaSlanjeUDP implements Serializable{
    
    private String ime;
    private String pol;
    InetAddress address;

    public KlijentZaSlanjeUDP() {}
    
    public KlijentZaSlanjeUDP(String ime, String pol, InetAddress address) {
        this.ime = ime;
        this.pol = pol;
        this.address = address;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }
    
}
