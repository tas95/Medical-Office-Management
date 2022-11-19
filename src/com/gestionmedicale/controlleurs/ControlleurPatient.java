/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.controlleurs;

import com.gestionmedicale.beans.Cabinet;
import com.gestionmedicale.beans.Medicament;
import com.gestionmedicale.beans.Ordonnance;
import com.gestionmedicale.beans.Patient;
import com.gestionmedicale.modeles.ModeleCabinet;
import com.gestionmedicale.modeles.ModeleMedicament;
import com.gestionmedicale.modeles.ModeleOrdonnance;
import com.gestionmedicale.modeles.ModelePatient;
import com.gestionmedicale.modeles.ModeleTableMedicaments;
import com.gestionmedicale.modeles.ModeleTablePatients;
import com.gestionmedicale.vues.GestionMedicament;
import com.gestionmedicale.vues.GestionPatient;
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
public class ControlleurPatient implements ActionListener, ListSelectionListener, DocumentListener, KeyListener {

    private GestionPatient vue;
    private ModelePatient modelePatient;
    private ModeleCabinet modeleCabinet;
    private ModeleTablePatients modeleTablePatients;

    public ControlleurPatient(GestionPatient vue) {
        this.vue = vue;
        modeleCabinet = new ModeleCabinet();
        modelePatient = new ModelePatient();
        ajouterListenerButtons();
        ajouterListenerTextFields();
        ajouterListenerCheckBox();
        ajouterListenerTables();
        initialiserComboBox();
        initialiserTablePatients();
    }

    public void ajouterListenerCheckBox() {
        vue.getjCheckBoxFiltreCabinet().addActionListener(this);
    }

    private void ajouterListenerButtons() {
        vue.getjButtonExit().addActionListener(this);
        vue.getjButtonModifier().addActionListener(this);
        vue.getjButtonAjouter().addActionListener(this);
        vue.getjButtonRetour().addActionListener(this);
        vue.getjButtonSupprimer().addActionListener(this);
    }

    private void ajouterListenerTextFields() {
        vue.getjTextFieldNom().addActionListener(this);
        vue.getjTextFieldAdresse().addActionListener(this);
        vue.getjTextFieldFiltreCabinet().addActionListener(this);
        vue.getjTextFieldFiltreCabinet().getDocument().addDocumentListener(this);
        vue.getjComboBoxCode_Cabinet().addActionListener(this);
    }

    private void ajouterListenerTables() {
        vue.getjTablePatients().getSelectionModel().addListSelectionListener(this);
    }

    private void initialiserComboBox() {
        List<Cabinet> listeCabinets = new ArrayList<Cabinet>();
        listeCabinets = modeleCabinet.lister();
        for (int i = 0; i < listeCabinets.size(); i++) {
            vue.getjComboBoxCode_Cabinet().addItem(listeCabinets.get(i).getCode_cabinet());
        }
    }

    private void initialiserTablePatients() {
        List<Patient> listePatients = new ArrayList<Patient>();
        listePatients = modelePatient.lister();
        modeleTablePatients = new ModeleTablePatients(listePatients);

        vue.getjTablePatients().setModel(modeleTablePatients);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        vue.getjTablePatients().getTableHeader().setDefaultRenderer(centerRenderer);
        for (int i = 0; i < vue.getjTablePatients().getColumnCount(); i++) {
            vue.getjTablePatients().getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void initialiserTablePatientsFiltrees(int code) {
        List<Patient> listePatients = new ArrayList<Patient>();
        listePatients = modelePatient.listerFiltres(code);
        modeleTablePatients = new ModeleTablePatients(listePatients);

        vue.getjTablePatients().setModel(modeleTablePatients);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        vue.getjTablePatients().getTableHeader().setDefaultRenderer(centerRenderer);
        for (int i = 0; i < vue.getjTablePatients().getColumnCount(); i++) {
            vue.getjTablePatients().getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void initialiserFormulaire() {
        vue.getjTextFieldNom().setText("");
        vue.getjTextFieldAdresse().setText("");
        vue.getjTextFieldFiltreCabinet().setText("");
        vue.getjComboBoxCode_Cabinet().setSelectedIndex(0);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Ajouter")) {

            if (vue.getjTextFieldNom().getText().equals("")) {
                JOptionPane.showMessageDialog(vue, "Remplissez le champ Nom !", "Ajout", JOptionPane.ERROR_MESSAGE);
            } else if (vue.getjTextFieldAdresse().getText().equals("")) {
                JOptionPane.showMessageDialog(vue, "Remplissez le champ Adresse !", "Ajout", JOptionPane.ERROR_MESSAGE);
            } else if (vue.getjComboBoxCode_Cabinet().getSelectedItem().toString().equals("Aucun")) {
                JOptionPane.showMessageDialog(vue, "Choisir le Code d'un cabinet !", "Ajout", JOptionPane.ERROR_MESSAGE);
            } else {
                Patient c = new Patient();
                c.setNomP(vue.getjTextFieldNom().getText());
                c.setAdresseP(vue.getjTextFieldAdresse().getText());
                c.setCabinet(new Cabinet());
                c.getCabinet().setCode_cabinet(Integer.parseInt(vue.getjComboBoxCode_Cabinet().getSelectedItem().toString()));
                modelePatient.setPatient(c);
                try {
                    modelePatient.ajouter();
                    if (vue.getjCheckBoxFiltreCabinet().isSelected()) {
                        initialiserTablePatientsFiltrees(Integer.parseInt(vue.getjTextFieldFiltreCabinet().getText()));
                    } else {
                        initialiserTablePatients();
                    }
                    initialiserFormulaire();
                    JOptionPane.showMessageDialog(vue, "Patient a été ajouté !", "Ajout", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vue, ex.getMessage(), "Ajout", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (e.getActionCommand().equals("Modifier")) {
            if (vue.getjTextFieldNom().getText().equals("")) {
                JOptionPane.showMessageDialog(vue, "Remplissez le champ Nom !", "Modification", JOptionPane.ERROR_MESSAGE);
            } else if (vue.getjTextFieldAdresse().getText().equals("")) {
                JOptionPane.showMessageDialog(vue, "Remplissez le champ Adresse !", "Modification", JOptionPane.ERROR_MESSAGE);
            } else if (vue.getjComboBoxCode_Cabinet().getSelectedItem().toString().equals("Aucun")) {
                JOptionPane.showMessageDialog(vue, "Choisir le Code d'un cabinet !", "Modification", JOptionPane.ERROR_MESSAGE);
            } else {

                Patient c = new Patient();
                c.setNomP(vue.getjTextFieldNom().getText());
                c.setAdresseP(vue.getjTextFieldAdresse().getText());
                c.setCabinet(new Cabinet());
                c.getCabinet().setCode_cabinet(Integer.parseInt(vue.getjComboBoxCode_Cabinet().getSelectedItem().toString()));
                c.setCode_patient(modeleTablePatients.getRow(vue.getjTablePatients().getSelectedRow()).getCode_patient());
                modelePatient.setPatient(c);

                try {

                    if (vue.getjTextFieldNom().getText().equals(modeleTablePatients.getRow(vue.getjTablePatients().getSelectedRow()).getNomP()) && vue.getjTextFieldAdresse().getText().equals(modeleTablePatients.getRow(vue.getjTablePatients().getSelectedRow()).getAdresseP())) {

                        modelePatient.modifier(true);
//
                    } else {
                        modelePatient.modifier(false);

                    }
                    if (vue.getjCheckBoxFiltreCabinet().isSelected()) {
                        initialiserTablePatientsFiltrees(Integer.parseInt(vue.getjTextFieldFiltreCabinet().getText()));
                    } else {
                        initialiserTablePatients();

                    }
                    initialiserFormulaire();
                    JOptionPane.showMessageDialog(vue, "Patient a été Modifié!", "Modification", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vue, ex.getMessage(), "Modification", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getActionCommand().equals("Supprimer")) {
            if (JOptionPane.showConfirmDialog(vue, "Voulez-vous supprimer ce Patient ?", "Suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                int code = modeleTablePatients.getRow(vue.getjTablePatients().getSelectedRow()).getCode_patient();
                modelePatient.supprimer(code);
                if (vue.getjCheckBoxFiltreCabinet().isSelected()) {
                    initialiserTablePatientsFiltrees(Integer.parseInt(vue.getjTextFieldFiltreCabinet().getText()));
                } else {
                    initialiserTablePatients();
                }
                JOptionPane.showMessageDialog(vue, "Patient a été supprimé !", "Suppression", JOptionPane.INFORMATION_MESSAGE);
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
        if (lse.getSource().equals(vue.getjTablePatients().getSelectionModel())) {
            if (vue.getjTablePatients().getSelectedRow() > -1) {
                Patient c = modeleTablePatients.getRow(vue.getjTablePatients().getSelectedRow());
                vue.getjTextFieldNom().setText(c.getNomP());
                vue.getjComboBoxCode_Cabinet().setSelectedItem(c.getCabinet().getCode_cabinet());
                vue.getjTextFieldAdresse().setText(c.getAdresseP());
                vue.getjButtonAjouter().setEnabled(false);
                vue.getjButtonModifier().setEnabled(true);
                vue.getjButtonSupprimer().setEnabled(true);

            } else {
                vue.getjButtonAjouter().setEnabled(true);
                vue.getjButtonModifier().setEnabled(false);
                vue.getjButtonSupprimer().setEnabled(false);

                initialiserFormulaire();
            }

        }
    }

    @Override
    public void insertUpdate(DocumentEvent de) {
        if (de.getDocument().equals(vue.getjTextFieldFiltreCabinet().getDocument())) {
            initialiserTablePatientsFiltrees(Integer.parseInt(vue.getjTextFieldFiltreCabinet().getText()));
        }
    }

    @Override
    public void removeUpdate(DocumentEvent de) {
        if (de.getDocument().equals(vue.getjTextFieldFiltreCabinet().getDocument())) {
            initialiserTablePatientsFiltrees(Integer.parseInt(vue.getjTextFieldFiltreCabinet().getText()));
        }
    }

    @Override
    public void changedUpdate(DocumentEvent de) {
        if (de.getDocument().equals(vue.getjTextFieldFiltreCabinet().getDocument())) {
            initialiserTablePatientsFiltrees(Integer.parseInt(vue.getjTextFieldFiltreCabinet().getText()));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
