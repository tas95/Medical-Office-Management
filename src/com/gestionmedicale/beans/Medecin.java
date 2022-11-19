/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.beans;

/**
 *
 * @author Manel
 */
public class Medecin {
    
    private int code;
    private String nom;
    private String adresse;
    private String tel;
    private Cabinet cabinet;

    public int getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTel() {
        return tel;
    }

    public Cabinet getCabinet() {
        return cabinet;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setCabinet(Cabinet cabinet) {
        this.cabinet = cabinet;
    }
    
    
          
    
}
