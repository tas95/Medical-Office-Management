/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.controlleurs;


import com.gestionmedicale.beans.Cabinet;
import com.gestionmedicale.beans.Medicament;
import com.gestionmedicale.beans.Ordonnance;
import com.gestionmedicale.modeles.ModeleCabinet;
import com.gestionmedicale.modeles.ModeleMedicament;
import com.gestionmedicale.modeles.ModeleOrdonnance;
import com.gestionmedicale.modeles.ModeleTableCabinets;
import com.gestionmedicale.modeles.ModeleTableMedicaments;
import com.gestionmedicale.vues.GestionCabinets;
import com.gestionmedicale.vues.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;


/**
 *
 * @author Manel
 */
public class ControlleurCabinet implements ActionListener,ListSelectionListener{
     private GestionCabinets vue;
     private ModeleCabinet modeleCabinet;    
    private ModeleTableCabinets modeleTableCabinets;

    public ControlleurCabinet(GestionCabinets vue) {
        this.vue = vue;
        modeleCabinet = new ModeleCabinet();       
        ajouterListenerButtons();
        ajouterListenerTextFields();
        ajouterListenerTables();       
        initialiserTableCabinets();
    }
    
     private void ajouterListenerButtons() {
          vue.getjButtonExit().addActionListener(this);
         vue.getjButtonModifier().addActionListener(this);
          vue.getjButtonAjouter().addActionListener(this);
           vue.getjButtonRetour().addActionListener(this);
            vue.getjButtonSupprimer().addActionListener(this);
       }
      private void ajouterListenerTextFields() {          
           vue.getjTextFielDesignation().addActionListener(this);            
       }

    private void ajouterListenerTables() {
    vue.getjTableCabinets().getSelectionModel().addListSelectionListener(this);   
    }  

    private void initialiserTableCabinets() {
     List<Cabinet> listeCabinets=new ArrayList<Cabinet>();
        listeCabinets=modeleCabinet.lister();
        modeleTableCabinets=new ModeleTableCabinets(listeCabinets);
        
        vue.getjTableCabinets().setModel(modeleTableCabinets);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        vue.getjTableCabinets().getTableHeader().setDefaultRenderer(centerRenderer);
        for(int i=0;i<vue.getjTableCabinets().getColumnCount();i++)
        {
            vue.getjTableCabinets().getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }   
    }    
    
    public void initialiserFormulaire()
    {
        vue.getjTextFielDesignation().setText("");    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
     if(e.getActionCommand().equals("Ajouter"))
        {
            
            if(vue.getjTextFielDesignation().getText().equals(""))
            {
                JOptionPane.showMessageDialog(vue,"Remplissez le champ Désignation !","Ajout",JOptionPane.ERROR_MESSAGE);
            }       
            
            else
            {
                Cabinet c=new Cabinet();
                c.setDesignation(vue.getjTextFielDesignation().getText());
                modeleCabinet.setCabinet(c);
                try
                {
                    modeleCabinet.ajouter();
                    initialiserTableCabinets();                   
                    initialiserFormulaire();
                    JOptionPane.showMessageDialog(vue,"Cabinet a été ajouté !","Ajout",JOptionPane.INFORMATION_MESSAGE);
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(vue,ex.getMessage(),"Ajout",JOptionPane.ERROR_MESSAGE);
                }
            }
        }   
      if(e.getActionCommand().equals("Modifier"))
        {
           if(vue.getjTextFielDesignation().getText().equals(""))
            {
                JOptionPane.showMessageDialog(vue,"Remplissez le champ Désignation !","Modification",JOptionPane.ERROR_MESSAGE);
            }           
            
            else
            {
                Cabinet c=new Cabinet();
                c.setDesignation(vue.getjTextFielDesignation().getText());                
                c.setCode_cabinet(modeleTableCabinets.getRow(vue.getjTableCabinets().getSelectedRow()).getCode_cabinet());
                modeleCabinet.setCabinet(c);
                
                try
                {
                   
                    if(vue.getjTextFielDesignation().getText().equals(modeleTableCabinets.getRow(vue.getjTableCabinets().getSelectedRow()).getDesignation()))
                    {

                     modeleCabinet.modifier(true);
//
                    }
                    else
                    {
                        modeleCabinet.modifier(false);
                        
                    }
                    initialiserTableCabinets();
                    initialiserFormulaire();
                    JOptionPane.showMessageDialog(vue,"Cabinet a été Modifié !","Modification",JOptionPane.INFORMATION_MESSAGE);
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(vue,ex.getMessage(),"Modification",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
      else if(e.getActionCommand().equals("Supprimer"))
        {
            if(JOptionPane.showConfirmDialog(vue,"Voulez-vous supprimer ce cabinet ?","Suppression",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
            {
                int code=modeleTableCabinets.getRow(vue.getjTableCabinets().getSelectedRow()).getCode_cabinet();
                modeleCabinet.supprimer(code);
                initialiserTableCabinets();
                JOptionPane.showMessageDialog(vue,"Cabinet a été supprimé !","Suppression",JOptionPane.INFORMATION_MESSAGE);
            }
        }
       else if(e.getActionCommand().equals("Retour"))
        {
        Menu vueMenu=new Menu();
                ControlleurMenu controlleurMenu=new ControlleurMenu(vueMenu);
                vueMenu.setVisible(true);
                vue.setVisible(false);
        }
      else if(e.getActionCommand().equals("Exit"))
        {
        
                vue.setVisible(false);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
    if(lse.getSource().equals(vue.getjTableCabinets().getSelectionModel()))
        {
            if(vue.getjTableCabinets().getSelectedRow()>-1)
            {
                Cabinet c=modeleTableCabinets.getRow(vue.getjTableCabinets().getSelectedRow());               
                vue.getjTextFielDesignation().setText(c.getDesignation());
                vue.getjButtonAjouter().setEnabled(false);
                vue.getjButtonModifier().setEnabled(true);
                vue.getjButtonSupprimer().setEnabled(true);
                
            }
            else
            {
                vue.getjButtonAjouter().setEnabled(true);
                vue.getjButtonModifier().setEnabled(false);
                vue.getjButtonSupprimer().setEnabled(false);
               
                initialiserFormulaire();
            }
            
        }   
    }

    
     
}
