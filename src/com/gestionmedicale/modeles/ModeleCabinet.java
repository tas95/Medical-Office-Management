/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.modeles;


import com.gestionmedicale.beans.Cabinet;
import com.gestionmedicale.beans.Medicament;
import com.gestionmedicale.dao.ImpDaoCabinet;
import com.gestionmedicale.dao.ImpDaoMedicament;
import java.util.List;

/**
 *
 * @author Manel
 */
public class ModeleCabinet {
     private Cabinet cabinet;
    private ImpDaoCabinet impDaoCabinet;
    public ModeleCabinet()
    {
        impDaoCabinet=new ImpDaoCabinet();
    }

    public Cabinet getCabinet() {
        return cabinet;
    }

    public void setCabinet(Cabinet cabinet) {
        this.cabinet = cabinet;
    }
       
    public void ajouter() throws Exception
    {
        impDaoCabinet.ajouterCabinet(cabinet);
    }
    public void modifier(boolean verification) throws Exception
    {
        impDaoCabinet.modifierCabinet(cabinet,verification);
    }
    public void supprimer(int codeCab)
    {
        impDaoCabinet.supprimerCabinet(codeCab);
    }
    public List<Cabinet> lister()
    {
        return impDaoCabinet.listerCabinets();
    }     
}
