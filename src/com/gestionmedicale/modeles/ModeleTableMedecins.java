/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.modeles;


import com.gestionmedicale.beans.Medecin;
import com.gestionmedicale.beans.Patient;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Manel
 */
public class ModeleTableMedecins extends AbstractTableModel{
private List<Medecin> lignes;
    private List<String> colonnes;

    public ModeleTableMedecins(List<Medecin> lignes) {
        this.lignes = lignes;
        colonnes=Arrays.asList("Code Medecin","Nom","Adresse","Téléphone","ID Cabinet");
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
    
        Medecin c=lignes.get(rowIndex);
        switch(columnIndex)
        {
            case 0 : return c.getCode();
            case 1 : return c.getNom();
            case 2 : return c.getAdresse();
            case 3 : return c.getTel();
            case 4 : return c.getCabinet().getCode_cabinet();
          
        }
        return null;
    }
     public Medecin getRow(int i)
    {
        return lignes.get(i);
    }
}
