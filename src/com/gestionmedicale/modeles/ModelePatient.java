/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.modeles;


import com.gestionmedicale.beans.Patient;
import com.gestionmedicale.dao.ImpDaoPatient;
import java.util.List;

/**
 *
 * @author Manel
 */
public class ModelePatient {
     private Patient patient;
    private ImpDaoPatient impDaoPatient;
    public ModelePatient()
    {
        impDaoPatient=new ImpDaoPatient();
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
   
    public void ajouter() throws Exception
    {
        impDaoPatient.ajouterPatient(patient);
    }
    public void modifier(boolean verification) throws Exception
    {
        impDaoPatient.modifierPatient(patient,verification);
    }
    public void supprimer(int codeMed)
    {
        impDaoPatient.supprimerPatient(codeMed);
    }
    public List<Patient> lister()
    {
        return impDaoPatient.listerPatients();
    }
    public List<Patient> listerFiltres(int filtre)
    {
        return impDaoPatient.listerPatientsFiltres(filtre);
    }
    
}
