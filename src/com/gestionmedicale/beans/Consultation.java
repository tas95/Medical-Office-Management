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
public class Consultation {
    
    private int code_Consultation;
    private Date dateCons;
    private String horraire;
    private Patient patient;
    private Medecin medecin;

    public int getCode_Consultation() {
        return code_Consultation;
    }

    public Date getDateCons() {
        return dateCons;
    }

    public String getHorraire() {
        return horraire;
    }

    public Patient getPatient() {
        return patient;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setCode_Consultation(int code_Consultation) {
        this.code_Consultation = code_Consultation;
    }

    public void setDateCons(Date dateCons) {
        this.dateCons = dateCons;
    }

    public void setHorraire(String horraire) {
        this.horraire = horraire;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }
    
    
    
    
}
