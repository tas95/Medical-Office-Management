/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.controlleurs;

import com.gestionmedicale.vues.GestionCabinets;
import com.gestionmedicale.vues.GestionMedecin;
import com.gestionmedicale.vues.GestionMedicament;
import com.gestionmedicale.vues.GestionOdonnances;
import com.gestionmedicale.vues.GestionPatient;
import com.gestionmedicale.vues.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Manel
 */
public class ControlleurMenu implements ActionListener{
    private Menu menu;

    public ControlleurMenu(Menu menu) {
        this.menu = menu;
        ajouterListenerButtons();
    }

    private void ajouterListenerButtons() {
        menu.getjButtonGestionCabinets().addActionListener(this);
        menu.getjButtonGestionMedecins().addActionListener(this);
        menu.getjButtonGestionMedicaments().addActionListener(this);
        menu.getjButtonGestionOrdonnances().addActionListener(this);
        menu.getjButtonGestionPatients().addActionListener(this);
       }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("Gestion Odonnances"))
        {
             GestionOdonnances vue = new GestionOdonnances();
                ControlleurOrdonnance c = new ControlleurOrdonnance(vue);
                vue.setVisible(true);
        }
        else if(ae.getActionCommand().equals("Gestion Médicaments"))
        {
             GestionMedicament vue = new GestionMedicament();
                ControlleurMedicament c = new ControlleurMedicament(vue);
                vue.setVisible(true);
            
        }
        else if(ae.getActionCommand().equals("Gestion Patients"))
        {
            GestionPatient vueGestionPatients = new GestionPatient();
            ControlleurPatient controlleurGestionPatients=new ControlleurPatient(vueGestionPatients);
            vueGestionPatients.setVisible(true);
        }
        else if(ae.getActionCommand().equals("Gestion Médecins"))
        {
            GestionMedecin vue = new GestionMedecin();
                ControlleurMedecin c = new ControlleurMedecin(vue);
                vue.setVisible(true);
        }
        else if(ae.getActionCommand().equals("Gestion Cabinets"))
        {
            
            GestionCabinets vue = new GestionCabinets();
                ControlleurCabinet c = new ControlleurCabinet(vue);
                vue.setVisible(true);
            
           
        }
      
    }
    
    
    
}
