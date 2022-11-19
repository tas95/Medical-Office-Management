/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.dao;


import com.gestionmedicale.beans.Cabinet;
import com.gestionmedicale.beans.Patient;
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
public class ImpDaoPatient{

    private Connection connexion=SingletonConnexion.getConnexion();

    
    public void ajouterPatient(Patient c) throws Exception {
        try
        {
            PreparedStatement ps=connexion.prepareStatement("select count(*) from patient where nomP=? and adresseP=?");
            ps.setString(1,c.getNomP());           
            ps.setString(2,c.getAdresseP());
            ResultSet rs=ps.executeQuery();
            rs.next();
            int n=rs.getInt(1);
            if(n==1)
            {
                throw new Exception("Patient existant !");
            }
            ps=connexion.prepareStatement("insert into patient (nomP,adresseP,code_cabinet) values(?,?,?)");
            ps.setString(1,c.getNomP());
            ps.setString(2,c.getAdresseP());            
            ps.setInt(3,c.getCabinet().getCode_cabinet());
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
       }

   
    public void modifierPatient(Patient c, boolean verification) throws Exception {
    try
        {
            if(verification==true)
            {
                PreparedStatement ps=connexion.prepareStatement("select count(*) from patient where code_patient=?");
            ps.setInt(1,c.getCode_patient());            
            ResultSet rs=ps.executeQuery();
            rs.next();
            int n=rs.getInt(1);
            if(n==0)
            {
                throw new Exception("Patient inexistant !");
            }
            }
            PreparedStatement ps=connexion.prepareStatement("update patient set nomP=?,adresseP=?,code_cabinet=? where code_patient=?");
            ps.setString(1,c.getNomP());
            ps.setString(2,c.getAdresseP());
            ps.setInt(3,c.getCabinet().getCode_cabinet());
            ps.setInt(4,c.getCode_patient());
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }   
    }

    
    public void supprimerPatient(int code) {
     try
        {
            PreparedStatement ps=connexion.prepareStatement("delete from patient where code_patient=?");
            ps.setInt(1,code);
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }   
    }

    
    public List<Patient> listerPatients() {
        
    List<Patient> listePatients=new ArrayList<>();
        try
        {
            PreparedStatement ps=connexion.prepareStatement("select * from patient order by code_patient");
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                Patient c=new Patient();
                c.setCode_patient(rs.getInt("code_patient"));
                c.setNomP(rs.getString("nomP"));
                c.setAdresseP(rs.getString("adresseP"));  
                c.setCabinet(new Cabinet());
                c.getCabinet().setCode_cabinet(rs.getInt("code_cabinet"));                  
                listePatients.add(c);
            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return listePatients;
    }
    
    public List<Patient> listerPatientsFiltres(int filtre) {
         List<Patient> listePatients=new ArrayList<>();
     try
        {
            PreparedStatement ps=connexion.prepareStatement("select * from patient where code_cabinet=?");
            ps.setInt(1,filtre);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                Patient v=new Patient();
                v.setCode_patient(rs.getInt("code_med"));
                v.setNomP(rs.getString("nomP"));
                v.setAdresseP(rs.getString("adresseP"));                
                v.setCabinet(new Cabinet());
                v.getCabinet().setCode_cabinet(rs.getInt("code_cabinet"));               
                listePatients.add(v);
            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return listePatients;    
    }
    public static void main(String [] args){
    
        ImpDaoPatient idm = new ImpDaoPatient();
        idm.listerPatients();
    }

}

  