/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.dao;


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
public class ImpDaoOrdonnance{
    
    private Connection connexion=SingletonConnexion.getConnexion();

   
    public void ajouterOrdonnance(Ordonnance o) throws Exception {
        try
        {
            PreparedStatement ps=connexion.prepareStatement("select count(*) from ordonnance where numOrd=?");
            ps.setInt(1,o.getNumOrd());            
            ResultSet rs=ps.executeQuery();
            rs.next();
            int n=rs.getInt(1);
            if(n==1)
            {
                throw new Exception("Ordonnance existe déja!");
            }
            ps=connexion.prepareStatement("insert into ordonnance (numOrd) values(?)");
            ps.setInt(1,o.getNumOrd());           
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
       }   
    

    public void supprimerOrdonnance(int code) {
     try
        {

           PreparedStatement ps=connexion.prepareStatement("delete from ordonnance where numOrd=?");
            ps.setInt(1,code);
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }   
    }
    
    public List<Ordonnance> listerOrdonnances() {
        
    List<Ordonnance> listeOrdonnances=new ArrayList<>();
        try
        {
            PreparedStatement ps=connexion.prepareStatement("select * from ordonnance order by numOrd");
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                Ordonnance o=new Ordonnance();
                o.setNumOrd(rs.getInt("numOrd"));               
                listeOrdonnances.add(o);
            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return listeOrdonnances;
    }
    
    public static void main(String[] args){
    
        ImpDaoOrdonnance ido = new ImpDaoOrdonnance();
                 List<Ordonnance> l = ido.listerOrdonnances();
                 for(int i = 0 ; i<l.size();i++){
                     System.out.println(l.get(i).getNumOrd());
                 }
    }
    
}
