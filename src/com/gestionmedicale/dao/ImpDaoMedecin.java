/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.dao;


import com.gestionmedicale.beans.Cabinet;
import com.gestionmedicale.beans.Medecin;
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
public class ImpDaoMedecin{

    private Connection connexion=SingletonConnexion.getConnexion();

    
    public void ajouterMedecin(Medecin c) throws Exception {
        try
        {
            PreparedStatement ps=connexion.prepareStatement("select count(*) from medecin where nom=? and Tel=?");
            ps.setString(1,c.getNom());           
            ps.setString(2,c.getTel());
            ResultSet rs=ps.executeQuery();
            rs.next();
            int n=rs.getInt(1);
            if(n==1)
            {
                throw new Exception("Medecin existant !");
            }
            ps=connexion.prepareStatement("insert into medecin (nom,adresse,Tel,id_cabinet) values(?,?,?,?)");
            ps.setString(1,c.getNom());
            ps.setString(2,c.getAdresse());
            ps.setString(3,c.getTel());            
            ps.setInt(4,c.getCabinet().getCode_cabinet());
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
       }

   
    public void modifierMedecin(Medecin c, boolean verification) throws Exception {
    try
        {
            if(verification==true)
            {
                PreparedStatement ps=connexion.prepareStatement("select count(*) from medecin where code=?");
            ps.setInt(1,c.getCode());            
            ResultSet rs=ps.executeQuery();
            rs.next();
            int n=rs.getInt(1);
            if(n==0)
            {
                throw new Exception("medecin inexistant !");
            }
            }
            PreparedStatement ps=connexion.prepareStatement("update medecin set nom=?,adresse=?,Tel=?,id_cabinet=? where code=?");
            ps.setString(1,c.getNom());
            ps.setString(2,c.getAdresse());
            ps.setString(3,c.getTel());            
            ps.setInt(4,c.getCabinet().getCode_cabinet());
            ps.setInt(5,c.getCode());

            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }   
    }

    
    public void supprimerMedecin(int code) {
     try
        {
            PreparedStatement ps=connexion.prepareStatement("delete from medecin where code=?");
            ps.setInt(1,code);
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }   
    }

    
    public List<Medecin> listerMedecins() {
        
    List<Medecin> listeMedecins=new ArrayList<>();
        try
        {
            PreparedStatement ps=connexion.prepareStatement("select * from medecin order by code");
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                Medecin c=new Medecin();
                c.setCode(rs.getInt("code"));
                c.setNom(rs.getString("nom"));
                c.setAdresse(rs.getString("adresse"));  
                c.setTel(rs.getString("Tel"));  
                c.setCabinet(new Cabinet());
                c.getCabinet().setCode_cabinet(rs.getInt("id_cabinet"));                  
                listeMedecins.add(c);
            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return listeMedecins;
    }
    
    public List<Medecin> listerMedecinsFiltres(int filtre) {
        List<Medecin> listeMedecins=new ArrayList<>();
     try
        {
            PreparedStatement ps=connexion.prepareStatement("select * from medecin where id_cabinet=?");
            ps.setInt(1,filtre);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                Medecin c=new Medecin();
                c.setCode(rs.getInt("code"));
                c.setNom(rs.getString("nom"));
                c.setAdresse(rs.getString("adresse"));  
                c.setTel(rs.getString("Tel"));  
                c.setCabinet(new Cabinet());
                c.getCabinet().setCode_cabinet(rs.getInt("id_cabinet"));                  
                listeMedecins.add(c);
            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return listeMedecins;    
    }
    public static void main(String [] args){
    
        ImpDaoMedecin idm = new ImpDaoMedecin();
        idm.listerMedecins();
    }

}

  