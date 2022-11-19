/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.modeles;

import com.gestionmedicale.beans.Ordonnance;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Manel
 */
public class ModeleTableOrdonnance extends AbstractTableModel{
     private List<Ordonnance> lignes;
    private List<String> colonnes;

    public ModeleTableOrdonnance(List<Ordonnance> lignes) {
        this.lignes = lignes;
        colonnes=Arrays.asList("Code Ordonnance");
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
     Ordonnance o=lignes.get(rowIndex);
        switch(columnIndex)
        {
            case 0 : return o.getNumOrd();         
        }
        return null;    
    }
    
     public Ordonnance getRow(int i)
    {
        return lignes.get(i);
    }
    
}
