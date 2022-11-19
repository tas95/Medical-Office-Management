/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.dao;


import com.gestionmedicale.beans.Cabinet;
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
public class ImpDaoCabinet{
    
    private Connection connexion=SingletonConnexion.getConnexion();

   
    public void ajouterCabinet(Cabinet o) throws Exception {
        try
        {
            PreparedStatement ps=connexion.prepareStatement("select count(*) from cabinet where code_cabinet=?");
            ps.setInt(1,o.getCode_cabinet());            
            ResultSet rs=ps.executeQuery();
            rs.next();
            int n=rs.getInt(1);
            if(n==1)
            {
                throw new Exception("Cabinet existe déja!");
            }
            ps=connexion.prepareStatement("insert into cabinet (designation) values(?)");
            ps.setString(1,o.getDesignation());           
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
       }   
    

        public void modifierCabinet(Cabinet v, boolean verification) throws Exception {
    
         try
        {
            if(verification==true)
            {
                PreparedStatement ps=connexion.prepareStatement("select count(*) from cabinet where code_cabinet=?");
            ps.setInt(1,v.getCode_cabinet());
            ResultSet rs=ps.executeQuery();
            rs.next();
            int n=rs.getInt(1);
            if(n!=1)
            {
                throw new Exception("Cabinet inexistante !");
            }
            }
            PreparedStatement ps=connexion.prepareStatement("update cabinet set designation=? where code_cabinet=?");
            ps.setString(1,v.getDesignation());            
            ps.setInt(2,v.getCode_cabinet());
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
        
    public void supprimerCabinet(int code) {
     try
        {

           PreparedStatement ps=connexion.prepareStatement("delete from cabinet where code_cabinet=?");
            ps.setInt(1,code);
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }   
    }
    
    public List<Cabinet> listerCabinets() {
        
    List<Cabinet> listeCabinets=new ArrayList<>();
        try
        {
            PreparedStatement ps=connexion.prepareStatement("select * from cabinet order by code_cabinet");
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                Cabinet o=new Cabinet();
                o.setCode_cabinet(rs.getInt("code_cabinet"));   
                o.setDesignation(rs.getString("designation"));      

                listeCabinets.add(o);
            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return listeCabinets;
    }
    
    public static void main(String[] args){
    
        ImpDaoCabinet ido = new ImpDaoCabinet();
                 List<Cabinet> l = ido.listerCabinets();
                 for(int i = 0 ; i<l.size();i++){
                     System.out.println(l.get(i).getCode_cabinet());
                 }
    }
    
}
