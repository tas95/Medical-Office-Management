/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.modeles;


import com.gestionmedicale.beans.Medicament;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Manel
 */
public class ModeleTableMedicaments extends AbstractTableModel{
private List<Medicament> lignes;
    private List<String> colonnes;

    public ModeleTableMedicaments(List<Medicament> lignes) {
        this.lignes = lignes;
        colonnes=Arrays.asList("Code Medicament","Nom","Prix","ID Ordonnance");
    }
    
     public String getColumnName(int column)
    {
        return colonnes.get(column);
    }
    @Override
    public int getRowCount() {
    return lignes.size();    
    }

    @Override
    public int getColumnCount() {
    return colonnes.size();    
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    
        Medicament c=lignes.get(rowIndex);
        switch(columnIndex)
        {
            case 0 : return c.getCode_med();
            case 1 : return c.getNomMed();
            case 2 : return c.getPrixMed();
            case 3 : return c.getOrdonnance().getNumOrd();
          
        }
        return null;
    }
     public Medicament getRow(int i)
    {
        return lignes.get(i);
    }
}
