/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.controlleurs;

import com.gestionmedicale.beans.Ordonnance;
import com.gestionmedicale.modeles.ModeleOrdonnance;
import com.gestionmedicale.modeles.ModeleTableOrdonnance;
import com.gestionmedicale.vues.GestionOdonnances;
import com.gestionmedicale.vues.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Manel
 */
public class ControlleurOrdonnance implements ActionListener, ListSelectionListener {

    private GestionOdonnances vue;
    private ModeleOrdonnance modeleOrdonnance;
    private ModeleTableOrdonnance modeleTableOrdonnances;

    public ControlleurOrdonnance(GestionOdonnances vue) {
        this.vue = vue;
        modeleOrdonnance = new ModeleOrdonnance();
        ajouterListenerButtons();
        ajouterListenerTextFields();
        ajouterListenerTables();
        initialiserTableOrdonnances();

    }

    private void ajouterListenerButtons() {
        vue.getjButtonExit().addActionListener(this);
        vue.getjButtonAjouter().addActionListener(this);
        vue.getjButtonRetour().addActionListener(this);
        vue.getjButtonSupprimer().addActionListener(this);
    }

    private void ajouterListenerTextFields() {
        vue.getjTextFieldID().addActionListener(this);
    }

    private void ajouterListenerTables() {
        vue.getjTableOrdonnances().getSelectionModel().addListSelectionListener(this);
    }

    private void initialiserTableOrdonnances() {
        List<Ordonnance> listeOrdonnances = new ArrayList<Ordonnance>();
        listeOrdonnances = modeleOrdonnance.lister();
        modeleTableOrdonnances = new ModeleTableOrdonnance(listeOrdonnances);

        vue.getjTableOrdonnances().setModel(modeleTableOrdonnances);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        vue.getjTableOrdonnances().getTableHeader().setDefaultRenderer(centerRenderer);
        for (int i = 0; i < vue.getjTableOrdonnances().getColumnCount(); i++) {
            vue.getjTableOrdonnances().getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void initialiserFormulaire() {
        vue.getjTextFieldID().setText("");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Ajouter")) {
            if (vue.getjTextFieldID().getText().equals("")) {
                JOptionPane.showMessageDialog(vue, "Remplissez le champ ID !", "Ajout", JOptionPane.ERROR_MESSAGE);
            } else {
                Ordonnance o = new Ordonnance();
                o.setNumOrd(Integer.parseInt(vue.getjTextFieldID().getText()));
                modeleOrdonnance.setOrdonnance(o);
                try {
                    modeleOrdonnance.ajouter();
                    initialiserTableOrdonnances();
                    initialiserFormulaire();
                    JOptionPane.showMessageDialog(vue, "Ordonnance a été ajoutée !", "Ajout", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vue, ex.getMessage(), "Ajout", JOptionPane.ERROR_MESSAGE);
                }

            }
        } else if (e.getActionCommand().equals("Supprimer")) {
            if (JOptionPane.showConfirmDialog(vue, "Voulez-vous supprimer cette ordonnance ?", "Suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                int code = modeleTableOrdonnances.getRow(vue.getjTableOrdonnances().getSelectedRow()).getNumOrd();
                modeleOrdonnance.supprimer(code);
                initialiserTableOrdonnances();
                JOptionPane.showMessageDialog(vue, "Orodonnance a été supprimée !", "Suppression", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (e.getActionCommand().equals("Retour")) {
            Menu vueMenu = new Menu();
            ControlleurMenu controlleurMenu = new ControlleurMenu(vueMenu);
            vueMenu.setVisible(true);
            vue.setVisible(false);
        } else if (e.getActionCommand().equals("Exit")) {

            vue.setVisible(false);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        if (lse.getSource().equals(vue.getjTableOrdonnances().getSelectionModel())) {
            if (vue.getjTableOrdonnances().getSelectedRow() > -1) {
                Ordonnance or = modeleTableOrdonnances.getRow(vue.getjTableOrdonnances().getSelectedRow());
                vue.getjTextFieldID().setText(String.valueOf(or.getNumOrd()));
                vue.getjButtonAjouter().setEnabled(false);
                vue.getjButtonSupprimer().setEnabled(true);

            } else {
                vue.getjButtonAjouter().setEnabled(true);
                vue.getjButtonSupprimer().setEnabled(false);

                initialiserFormulaire();
            }

        }

    }

}
