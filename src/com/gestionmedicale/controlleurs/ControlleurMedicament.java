/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.controlleurs;

import com.gestionmedicale.beans.Medicament;
import com.gestionmedicale.beans.Ordonnance;
import com.gestionmedicale.modeles.ModeleMedicament;
import com.gestionmedicale.modeles.ModeleOrdonnance;
import com.gestionmedicale.modeles.ModeleTableMedicaments;
import com.gestionmedicale.vues.GestionMedicament;
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
public class ControlleurMedicament implements ActionListener, ListSelectionListener, DocumentListener, KeyListener {

    private GestionMedicament vue;
    private ModeleMedicament modeleMedicament;
    private ModeleOrdonnance modeleOrdonnance;
    private ModeleTableMedicaments modeleTableMedicaments;

    public ControlleurMedicament(GestionMedicament vue) {
        this.vue = vue;
        modeleOrdonnance = new ModeleOrdonnance();
        modeleMedicament = new ModeleMedicament();
        ajouterListenerButtons();
        ajouterListenerTextFields();
        ajouterListenerCheckBox();
        ajouterListenerTables();
        initialiserComboBox();
        initialiserTableMedicaments();
    }

    public void ajouterListenerCheckBox() {
        vue.getjCheckBoxFiltreOrdonnance().addActionListener(this);
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
        vue.getjTextFieldPrix().addActionListener(this);
        vue.getjTextFieldFiltreOrdonnance().addActionListener(this);
        vue.getjTextFieldFiltreOrdonnance().getDocument().addDocumentListener(this);
        vue.getjComboBoxCode_Ordonnance().addActionListener(this);
    }

    private void ajouterListenerTables() {
        vue.getjTableMedicaments().getSelectionModel().addListSelectionListener(this);
    }

    private void initialiserComboBox() {
        List<Ordonnance> listeOrdonnances = new ArrayList<Ordonnance>();
        listeOrdonnances = modeleOrdonnance.lister();
        for (int i = 0; i < listeOrdonnances.size(); i++) {
            vue.getjComboBoxCode_Ordonnance().addItem(listeOrdonnances.get(i).getNumOrd());
        }
    }

    private void initialiserTableMedicaments() {
        List<Medicament> listeMedicaments = new ArrayList<Medicament>();
        listeMedicaments = modeleMedicament.lister();
        modeleTableMedicaments = new ModeleTableMedicaments(listeMedicaments);

        vue.getjTableMedicaments().setModel(modeleTableMedicaments);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        vue.getjTableMedicaments().getTableHeader().setDefaultRenderer(centerRenderer);
        for (int i = 0; i < vue.getjTableMedicaments().getColumnCount(); i++) {
            vue.getjTableMedicaments().getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void initialiserTableMedicamentsFiltrees(int code) {
        List<Medicament> listeMedicaments = new ArrayList<Medicament>();
        listeMedicaments = modeleMedicament.listerFiltres(code);
        modeleTableMedicaments = new ModeleTableMedicaments(listeMedicaments);

        vue.getjTableMedicaments().setModel(modeleTableMedicaments);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        vue.getjTableMedicaments().getTableHeader().setDefaultRenderer(centerRenderer);
        for (int i = 0; i < vue.getjTableMedicaments().getColumnCount(); i++) {
            vue.getjTableMedicaments().getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void initialiserFormulaire() {
        vue.getjTextFieldNom().setText("");
        vue.getjTextFieldPrix().setText("");
        vue.getjComboBoxCode_Ordonnance().setSelectedIndex(0);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Ajouter")) {

            if (vue.getjTextFieldNom().getText().equals("")) {
                JOptionPane.showMessageDialog(vue, "Remplissez le champ Nom !", "Ajout", JOptionPane.ERROR_MESSAGE);
            } else if (vue.getjTextFieldPrix().getText().equals("")) {
                JOptionPane.showMessageDialog(vue, "Remplissez le champ Prix !", "Ajout", JOptionPane.ERROR_MESSAGE);
            } else if (vue.getjComboBoxCode_Ordonnance().getSelectedItem().toString().equals("Aucun")) {
                JOptionPane.showMessageDialog(vue, "Choisir le Code d'une ordonnance !", "Ajout", JOptionPane.ERROR_MESSAGE);
            } else {
                Medicament c = new Medicament();
                c.setNomMed(vue.getjTextFieldNom().getText());
                c.setPrixMed(Float.parseFloat(vue.getjTextFieldPrix().getText()));
                c.setOrdonnance(new Ordonnance());
                c.getOrdonnance().setNumOrd(Integer.parseInt(vue.getjComboBoxCode_Ordonnance().getSelectedItem().toString()));
                modeleMedicament.setMedicament(c);
                try {
                    modeleMedicament.ajouter();
                    if (vue.getjCheckBoxFiltreOrdonnance().isSelected()) {
                        initialiserTableMedicamentsFiltrees(Integer.parseInt(vue.getjTextFieldFiltreOrdonnance().getText()));
                    } else {
                        initialiserTableMedicaments();
                    }
                    initialiserFormulaire();
                    JOptionPane.showMessageDialog(vue, "Médicament a été ajouté !", "Ajout", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vue, ex.getMessage(), "Ajout", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (e.getActionCommand().equals("Modifier")) {
            if (vue.getjTextFieldNom().getText().equals("")) {
                JOptionPane.showMessageDialog(vue, "Remplissez le champ Nom !", "Modification", JOptionPane.ERROR_MESSAGE);
            } else if (vue.getjTextFieldPrix().getText().equals("")) {
                JOptionPane.showMessageDialog(vue, "Remplissez le champ Prix !", "Modification", JOptionPane.ERROR_MESSAGE);
            } else if (vue.getjComboBoxCode_Ordonnance().getSelectedItem().toString().equals("Aucun")) {
                JOptionPane.showMessageDialog(vue, "Choisir le Code d'une ordonnance !", "Modification", JOptionPane.ERROR_MESSAGE);
            } else {
                Medicament c = new Medicament();
                c.setNomMed(vue.getjTextFieldNom().getText());
                c.setPrixMed(Float.parseFloat(vue.getjTextFieldPrix().getText()));
                c.setOrdonnance(new Ordonnance());
                c.getOrdonnance().setNumOrd(Integer.parseInt(vue.getjComboBoxCode_Ordonnance().getSelectedItem().toString()));
                c.setCode_med(modeleTableMedicaments.getRow(vue.getjTableMedicaments().getSelectedRow()).getCode_med());
                modeleMedicament.setMedicament(c);

                try {

                    if (vue.getjTextFieldNom().getText().equals(modeleTableMedicaments.getRow(vue.getjTableMedicaments().getSelectedRow()).getNomMed()) && vue.getjTextFieldPrix().getText().equals(modeleTableMedicaments.getRow(vue.getjTableMedicaments().getSelectedRow()).getPrixMed())) {

                        modeleMedicament.modifier(true);
//
                    } else {
                        modeleMedicament.modifier(false);

                    }
                    if (vue.getjCheckBoxFiltreOrdonnance().isSelected()) {
                        initialiserTableMedicamentsFiltrees(Integer.parseInt(vue.getjTextFieldFiltreOrdonnance().getText()));
                    } else {
                        initialiserTableMedicaments();

                    }
                    initialiserFormulaire();
                    JOptionPane.showMessageDialog(vue, "Médicament a été Modifié!", "Modification", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vue, ex.getMessage(), "Modification", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getActionCommand().equals("Supprimer")) {
            if (JOptionPane.showConfirmDialog(vue, "Voulez-vous supprimer ce Médicament ?", "Suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                int code = modeleTableMedicaments.getRow(vue.getjTableMedicaments().getSelectedRow()).getCode_med();
                modeleMedicament.supprimer(code);
                if (vue.getjCheckBoxFiltreOrdonnance().isSelected()) {
                    initialiserTableMedicamentsFiltrees(Integer.parseInt(vue.getjTextFieldFiltreOrdonnance().getText()));
                } else {
                    initialiserTableMedicaments();
                }
                JOptionPane.showMessageDialog(vue, "Médicament a été supprimé !", "Suppression", JOptionPane.INFORMATION_MESSAGE);
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
        if (lse.getSource().equals(vue.getjTableMedicaments().getSelectionModel())) {
            if (vue.getjTableMedicaments().getSelectedRow() > -1) {
                Medicament c = modeleTableMedicaments.getRow(vue.getjTableMedicaments().getSelectedRow());
                vue.getjTextFieldNom().setText(c.getNomMed());
                vue.getjComboBoxCode_Ordonnance().setSelectedItem(c.getOrdonnance().getNumOrd());
                vue.getjTextFieldPrix().setText(String.valueOf(c.getPrixMed()));
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
        if (de.getDocument().equals(vue.getjTextFieldFiltreOrdonnance().getDocument())) {
            initialiserTableMedicamentsFiltrees(Integer.parseInt(vue.getjTextFieldFiltreOrdonnance().getText()));
        }
    }

    @Override
    public void removeUpdate(DocumentEvent de) {
        if (de.getDocument().equals(vue.getjTextFieldFiltreOrdonnance().getDocument())) {
            initialiserTableMedicamentsFiltrees(Integer.parseInt(vue.getjTextFieldFiltreOrdonnance().getText()));
        }
    }

    @Override
    public void changedUpdate(DocumentEvent de) {
        if (de.getDocument().equals(vue.getjTextFieldFiltreOrdonnance().getDocument())) {
            initialiserTableMedicamentsFiltrees(Integer.parseInt(vue.getjTextFieldFiltreOrdonnance().getText()));
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
