/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.beans;

import java.util.Date;

/**
 *
 * @author Manel
 */
public class Patient {
    
    private int code_patient;
    private String NomP;
    private String adresseP;
    private Date datenaiss;
    private Cabinet cabinet;

    public int getCode_patient() {
        return code_patient;
    }

    public String getNomP() {
        return NomP;
    }

    public String getAdresseP() {
        return adresseP;
    }

    public Date getDatenaiss() {
        return datenaiss;
    }

    public Cabinet getCabinet() {
        return cabinet;
    }

    public void setCode_patient(int code_patient) {
        this.code_patient = code_patient;
    }

    public void setNomP(String NomP) {
        this.NomP = NomP;
    }

    public void setAdresseP(String adresseP) {
        this.adresseP = adresseP;
    }

    public void setDatenaiss(Date datenaiss) {
        this.datenaiss = datenaiss;
    }

    public void setCabinet(Cabinet cabinet) {
        this.cabinet = cabinet;
    }
    
    
            
}
