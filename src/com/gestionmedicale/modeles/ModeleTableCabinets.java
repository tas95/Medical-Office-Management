/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.modeles;


import com.gestionmedicale.beans.Cabinet;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Manel
 */
public class ModeleTableCabinets extends AbstractTableModel{
private List<Cabinet> lignes;
    private List<String> colonnes;

    public ModeleTableCabinets(List<Cabinet> lignes) {
        this.lignes = lignes;
        colonnes=Arrays.asList("Code Cabinet","Designation");
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
    
        Cabinet c=lignes.get(rowIndex);
        switch(columnIndex)
        {
            case 0 : return c.getCode_cabinet();
            case 1 : return c.getDesignation();           
          
        }
        return null;
    }
     public Cabinet getRow(int i)
    {
        return lignes.get(i);
    }
}
