/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.utilitaires;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Manel
 */
public class SingletonConnexion {
    private static Connection connexion;
    

    static{
    try{
        
        connexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/health_care?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
        
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    }
    
    public static Connection getConnexion(){
        return connexion;
    }
    
}
