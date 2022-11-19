/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.controlleurs;

import com.gestionmedicale.beans.Cabinet;
import com.gestionmedicale.beans.Medecin;
import com.gestionmedicale.beans.Medicament;
import com.gestionmedicale.beans.Ordonnance;
import com.gestionmedicale.beans.Patient;
import com.gestionmedicale.modeles.ModeleCabinet;
import com.gestionmedicale.modeles.ModeleMedecin;
import com.gestionmedicale.modeles.ModeleMedicament;
import com.gestionmedicale.modeles.ModeleOrdonnance;
import com.gestionmedicale.modeles.ModelePatient;
import com.gestionmedicale.modeles.ModeleTableMedecins;
import com.gestionmedicale.modeles.ModeleTableMedicaments;
import com.gestionmedicale.modeles.ModeleTablePatients;
import com.gestionmedicale.vues.GestionMedecin;
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
public class ControlleurMedecin implements ActionListener, ListSelectionListener, DocumentListener, KeyListener {

    private GestionMedecin vue;
    private ModeleMedecin modeleMedecin;
    private ModeleCabinet modeleCabinet;
    private ModeleTableMedecins modeleTableMedecins;

    public ControlleurMedecin(GestionMedecin vue) {
        this.vue = vue;
        modeleCabinet = new ModeleCabinet();
        modeleMedecin = new ModeleMedecin();
        ajouterListenerButtons();
        ajouterListenerTextFields();
        ajouterListenerCheckBox();
        ajouterListenerTables();
        initialiserComboBox();
        initialiserTableMedecins();
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
        vue.getjTextFieldTel().addActionListener(this);
        vue.getjTextFieldFiltreCabinet().addActionListener(this);
        vue.getjTextFieldFiltreCabinet().getDocument().addDocumentListener(this);
        vue.getjComboBoxCode_Cabinet().addActionListener(this);
    }

    private void ajouterListenerTables() {
        vue.getjTableMedecins().getSelectionModel().addListSelectionListener(this);
    }

    private void initialiserComboBox() {
        List<Cabinet> listeCabinets = new ArrayList<Cabinet>();
        listeCabinets = modeleCabinet.lister();
        for (int i = 0; i < listeCabinets.size(); i++) {
            vue.getjComboBoxCode_Cabinet().addItem(listeCabinets.get(i).getCode_cabinet());
        }
    }

    private void initialiserTableMedecins() {
        List<Medecin> listeMedecins = new ArrayList<Medecin>();
        listeMedecins = modeleMedecin.lister();
        modeleTableMedecins = new ModeleTableMedecins(listeMedecins);

        vue.getjTableMedecins().setModel(modeleTableMedecins);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        vue.getjTableMedecins().getTableHeader().setDefaultRenderer(centerRenderer);
        for (int i = 0; i < vue.getjTableMedecins().getColumnCount(); i++) {
            vue.getjTableMedecins().getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void initialiserTableMedecinsFiltrees(int code) {
        List<Medecin> listeMedecins = new ArrayList<Medecin>();
        listeMedecins = modeleMedecin.listerFiltres(code);
        modeleTableMedecins = new ModeleTableMedecins(listeMedecins);

        vue.getjTableMedecins().setModel(modeleTableMedecins);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        vue.getjTableMedecins().getTableHeader().setDefaultRenderer(centerRenderer);
        for (int i = 0; i < vue.getjTableMedecins().getColumnCount(); i++) {
            vue.getjTableMedecins().getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void initialiserFormulaire() {
        vue.getjTextFieldNom().setText("");
        vue.getjTextFieldAdresse().setText("");
        vue.getjTextFieldTel().setText("");
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
            } else if (vue.getjTextFieldTel().getText().equals("")) {
                JOptionPane.showMessageDialog(vue, "Remplissez le champ Téléphone !", "Ajout", JOptionPane.ERROR_MESSAGE);
            } else if (vue.getjComboBoxCode_Cabinet().getSelectedItem().toString().equals("Aucun")) {
                JOptionPane.showMessageDialog(vue, "Choisir le Code d'un cabinet !", "Ajout", JOptionPane.ERROR_MESSAGE);
            } else {
                Medecin c = new Medecin();
                c.setNom(vue.getjTextFieldNom().getText());
                c.setAdresse(vue.getjTextFieldAdresse().getText());
                c.setTel(vue.getjTextFieldTel().getText());
                c.setCabinet(new Cabinet());
                c.getCabinet().setCode_cabinet(Integer.parseInt(vue.getjComboBoxCode_Cabinet().getSelectedItem().toString()));
                modeleMedecin.setMedecin(c);
                try {
                    modeleMedecin.ajouter();
                    if (vue.getjCheckBoxFiltreCabinet().isSelected()) {
                        initialiserTableMedecinsFiltrees(Integer.parseInt(vue.getjTextFieldFiltreCabinet().getText()));
                    } else {
                        initialiserTableMedecins();
                    }
                    initialiserFormulaire();
                    JOptionPane.showMessageDialog(vue, "Medecin a été ajouté !", "Ajout", JOptionPane.INFORMATION_MESSAGE);
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
            } else if (vue.getjTextFieldTel().getText().equals("")) {
                JOptionPane.showMessageDialog(vue, "Remplissez le champ Téléphone !", "Modification", JOptionPane.ERROR_MESSAGE);
            } else if (vue.getjComboBoxCode_Cabinet().getSelectedItem().toString().equals("Aucun")) {
                JOptionPane.showMessageDialog(vue, "Choisir le Code d'un cabinet !", "Modification", JOptionPane.ERROR_MESSAGE);
            } else {

                Medecin c = new Medecin();
                c.setNom(vue.getjTextFieldNom().getText());
                c.setAdresse(vue.getjTextFieldAdresse().getText());
                c.setTel(vue.getjTextFieldTel().getText());
                c.setCabinet(new Cabinet());
                c.getCabinet().setCode_cabinet(Integer.parseInt(vue.getjComboBoxCode_Cabinet().getSelectedItem().toString()));
                c.setCode(modeleTableMedecins.getRow(vue.getjTableMedecins().getSelectedRow()).getCode());
                modeleMedecin.setMedecin(c);

                try {

                    if (vue.getjTextFieldNom().getText().equals(modeleTableMedecins.getRow(vue.getjTableMedecins().getSelectedRow()).getNom()) && vue.getjTextFieldAdresse().getText().equals(modeleTableMedecins.getRow(vue.getjTableMedecins().getSelectedRow()).getAdresse())) {

                        modeleMedecin.modifier(false);
//
                    } else {
                        modeleMedecin.modifier(true);

                    }
                    if (vue.getjCheckBoxFiltreCabinet().isSelected()) {
                        initialiserTableMedecinsFiltrees(Integer.parseInt(vue.getjTextFieldFiltreCabinet().getText()));
                    } else {
                        initialiserTableMedecins();

                    }
                    initialiserFormulaire();
                    JOptionPane.showMessageDialog(vue, "Médecin a été Modifié!", "Modification", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vue, ex.getMessage(), "Modification", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getActionCommand().equals("Supprimer")) {
            if (JOptionPane.showConfirmDialog(vue, "Voulez-vous supprimer ce Médecin ?", "Suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                int code = modeleTableMedecins.getRow(vue.getjTableMedecins().getSelectedRow()).getCode();
                modeleMedecin.supprimer(code);
                if (vue.getjCheckBoxFiltreCabinet().isSelected()) {
                    initialiserTableMedecinsFiltrees(Integer.parseInt(vue.getjTextFieldFiltreCabinet().getText()));
                } else {
                    initialiserTableMedecins();
                }
                JOptionPane.showMessageDialog(vue, "Médecin a été supprimé !", "Suppression", JOptionPane.INFORMATION_MESSAGE);
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
        if (lse.getSource().equals(vue.getjTableMedecins().getSelectionModel())) {
            if (vue.getjTableMedecins().getSelectedRow() > -1) {
                Medecin c = modeleTableMedecins.getRow(vue.getjTableMedecins().getSelectedRow());
                vue.getjTextFieldNom().setText(c.getNom());
                vue.getjComboBoxCode_Cabinet().setSelectedItem(c.getCabinet().getCode_cabinet());
                vue.getjTextFieldAdresse().setText(c.getAdresse());
                vue.getjTextFieldTel().setText(c.getTel());
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
            initialiserTableMedecinsFiltrees(Integer.parseInt(vue.getjTextFieldFiltreCabinet().getText()));
        }
    }

    @Override
    public void removeUpdate(DocumentEvent de) {
        if (de.getDocument().equals(vue.getjTextFieldFiltreCabinet().getDocument())) {
            initialiserTableMedecinsFiltrees(Integer.parseInt(vue.getjTextFieldFiltreCabinet().getText()));
        }
    }

    @Override
    public void changedUpdate(DocumentEvent de) {
        if (de.getDocument().equals(vue.getjTextFieldFiltreCabinet().getDocument())) {
            initialiserTableMedecinsFiltrees(Integer.parseInt(vue.getjTextFieldFiltreCabinet().getText()));
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
