/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.modeles;


import com.gestionmedicale.beans.Medicament;
import com.gestionmedicale.dao.ImpDaoMedicament;
import java.util.List;

/**
 *
 * @author Manel
 */
public class ModeleMedicament {
     private Medicament medicament;
    private ImpDaoMedicament impDaoMedicament;
    public ModeleMedicament()
    {
        impDaoMedicament=new ImpDaoMedicament();
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }
   
    public void ajouter() throws Exception
    {
        impDaoMedicament.ajouterMedicament(medicament);
    }
    public void modifier(boolean verification) throws Exception
    {
        impDaoMedicament.modifierMedicament(medicament,verification);
    }
    public void supprimer(int codeMed)
    {
        impDaoMedicament.supprimerMedicament(codeMed);
    }
    public List<Medicament> lister()
    {
        return impDaoMedicament.listerMedicaments();
    }
    public List<Medicament> listerFiltres(int filtre)
    {
        return impDaoMedicament.listerMedicamentsFiltres(filtre);
    }
    
}
