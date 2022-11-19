/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.modeles;


import com.gestionmedicale.beans.Ordonnance;
import com.gestionmedicale.dao.ImpDaoOrdonnance;
import java.util.List;

/**
 *
 * @author Manel
 */
public class ModeleOrdonnance {
    private Ordonnance ordonnance;
    private ImpDaoOrdonnance impDaoOrdonnance;
    
    public ModeleOrdonnance()
    {
        impDaoOrdonnance=new ImpDaoOrdonnance();
    }

    public Ordonnance getOrdonnance() {
        return ordonnance;
    }

    public void setOrdonnance(Ordonnance ordonnance) {
        this.ordonnance = ordonnance;
    }
    
    public void ajouter() throws Exception
    {
        impDaoOrdonnance.ajouterOrdonnance(ordonnance);
    }
    
    public void supprimer(int code)
    {
        impDaoOrdonnance.supprimerOrdonnance(code);
    }
    public List<Ordonnance> lister()
    {
        return impDaoOrdonnance.listerOrdonnances();
    }

    
}
