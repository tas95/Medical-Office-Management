/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.modeles;


import com.gestionmedicale.beans.Medecin;
import com.gestionmedicale.beans.Patient;
import com.gestionmedicale.dao.ImpDaoMedecin;
import com.gestionmedicale.dao.ImpDaoPatient;
import java.util.List;

/**
 *
 * @author Manel
 */
public class ModeleMedecin {
     private Medecin medecin;
    private ImpDaoMedecin impDaoMedecin;
    public ModeleMedecin()
    {
        impDaoMedecin=new ImpDaoMedecin();
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }   
   
    public void ajouter() throws Exception
    {
        impDaoMedecin.ajouterMedecin(medecin);
    }
    public void modifier(boolean verification) throws Exception
    {
        impDaoMedecin.modifierMedecin(medecin,verification);
    }
    public void supprimer(int code)
    {
        impDaoMedecin.supprimerMedecin(code);
    }
    public List<Medecin> lister()
    {
        return impDaoMedecin.listerMedecins();
    }
    public List<Medecin> listerFiltres(int filtre)
    {
        return impDaoMedecin.listerMedecinsFiltres(filtre);
    }
    
}
