/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.modeles;


import com.gestionmedicale.beans.Patient;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Manel
 */
public class ModeleTablePatients extends AbstractTableModel{
private List<Patient> lignes;
    private List<String> colonnes;

    public ModeleTablePatients(List<Patient> lignes) {
        this.lignes = lignes;
        colonnes=Arrays.asList("Code Patient","Nom","Adresse","ID Cabinet");
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
    
        Patient c=lignes.get(rowIndex);
        switch(columnIndex)
        {
            case 0 : return c.getCode_patient();
            case 1 : return c.getNomP();
            case 2 : return c.getAdresseP();            
            case 3 : return c.getCabinet().getCode_cabinet();
          
        }
        return null;
    }
     public Patient getRow(int i)
    {
        return lignes.get(i);
    }
}
