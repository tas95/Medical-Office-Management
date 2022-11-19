/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.dao;


import com.gestionmedicale.beans.Medicament;
import com.gestionmedicale.beans.Ordonnance;
import com.gestionmedicale.utilitaires.SingletonConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Manel
 */
public class ImpDaoMedicament{

    private Connection connexion=SingletonConnexion.getConnexion();


    public void ajouterMedicament(Medicament v) throws Exception {
    try
        {
            PreparedStatement ps=connexion.prepareStatement("select count(*) from medicament where code_med=?");
            ps.setInt(1,v.getCode_med());
            ResultSet rs=ps.executeQuery();
            rs.next();
            int n=rs.getInt(1);
            if(n==1)
            {
                throw new Exception("Medicament existe !");
            }
            ps=connexion.prepareStatement("insert into medicament(nomMed,prixMed,numOrd) values(?,?,?)");
            ps.setString(1,v.getNomMed());
            ps.setFloat(2,v.getPrixMed());            
            ps.setInt(3,v.getOrdonnance().getNumOrd());
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }    
    }

  
    public void modifierMedicament(Medicament v, boolean verification) throws Exception {
    
         try
        {
            if(verification==true)
            {
                PreparedStatement ps=connexion.prepareStatement("select count(*) from medicament where code_med=?");
            ps.setInt(1,v.getCode_med());
            ResultSet rs=ps.executeQuery();
            rs.next();
            int n=rs.getInt(1);
            if(n!=1)
            {
                throw new Exception("Medicament inexistant !");
            }
            }
            PreparedStatement ps=connexion.prepareStatement("update medicament set nomMed=?,prixMed=?,numOrd=? where code_med=?");
            ps.setString(1,v.getNomMed());
            ps.setFloat(2,v.getPrixMed());            
            ps.setInt(3,v.getOrdonnance().getNumOrd());
            ps.setInt(4,v.getCode_med());
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

   
    public void supprimerMedicament(int codeMed) {
       
    
      try
        {          
           PreparedStatement ps=connexion.prepareStatement("delete from medicament where code_med=?");
            ps.setInt(1,codeMed);
            ps.executeUpdate();
           
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Medicament> listerMedicaments() {
    List<Medicament> listeMedicaments=new ArrayList<>();
        try
        {
            PreparedStatement ps=connexion.prepareStatement("select * from medicament");
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                Medicament v=new Medicament();
                v.setCode_med(rs.getInt("code_med"));
                v.setNomMed(rs.getString("nomMed"));
                v.setPrixMed(rs.getFloat("prixMed"));                
                v.setOrdonnance(new Ordonnance());
                v.getOrdonnance().setNumOrd(rs.getInt("numOrd"));
                
                listeMedicaments.add(v);
            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return listeMedicaments;
    }    
    

    
    public List<Medicament> listerMedicamentsFiltres(int filtre) {
         List<Medicament> listeMedicaments=new ArrayList<>();
     try
        {
            PreparedStatement ps=connexion.prepareStatement("select * from medicament where numOrd=?");
            ps.setInt(1,filtre);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                Medicament v=new Medicament();
                v.setCode_med(rs.getInt("code_med"));
                v.setNomMed(rs.getString("nomMed"));
                v.setPrixMed(rs.getFloat("prixMed"));                
                v.setOrdonnance(new Ordonnance());
                v.getOrdonnance().setNumOrd(rs.getInt("numOrd"));               
                listeMedicaments.add(v);
            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return listeMedicaments;    
    }
    
    public static void main(String [] args){
    
        ImpDaoMedicament idm = new ImpDaoMedicament();
        idm.listerMedicaments();
        idm.listerMedicamentsFiltres(33);
    }

}

  