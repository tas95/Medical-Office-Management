/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.controlleurs;

import com.gestionmedicale.utilitaires.SingletonConnexion;
import com.gestionmedicale.vues.Menu;
import com.gestionmedicale.vues.authentification;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author Manel
 */
public class ControlleurAuthentification implements ActionListener{
     private authentification v;
       

    public ControlleurAuthentification(authentification v) {
        this.v = v;
         ajouterListenerButtons();
    }

    private void ajouterListenerButtons() {
        v.getjButtonCnx().addActionListener(this);
        v.getjTextFieldLogin().addActionListener(this);
        v.getjPasswordField().addActionListener(this);
         }

    @Override
    public void actionPerformed(ActionEvent ae) {
     if(ae.getActionCommand().equals("Connexion"))
        {
            if(v.getjTextFieldLogin().getText().equals("admin") && v.getjPasswordField().getText().equals("admin")) {
               Menu vueMenu=new Menu();
                ControlleurMenu controlleurMenu=new ControlleurMenu(vueMenu);
                vueMenu.setVisible(true);
                v.setVisible(false);
                
           }
           else{
               JOptionPane.showMessageDialog(v,"Verifier votre Login et Password ","Authentification",JOptionPane.ERROR_MESSAGE);
           } 
          
        }  
    }
     
}
