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
public class Medicament {
        
    private int code_med;
    private String nomMed;
    private float prixMed;
    private Ordonnance ordonnance;

    public int getCode_med() {
        return code_med;
    }

    public String getNomMed() {
        return nomMed;
    }

    public float getPrixMed() {
        return prixMed;
    }

    public Ordonnance getOrdonnance() {
        return ordonnance;
    }

    public void setCode_med(int code_med) {
        this.code_med = code_med;
    }

    public void setNomMed(String nomMed) {
        this.nomMed = nomMed;
    }

    public void setPrixMed(float prixMed) {
        this.prixMed = prixMed;
    }

    public void setOrdonnance(Ordonnance ordonnance) {
        this.ordonnance = ordonnance;
    }
    
          
          
    

    
    
          
    
}
