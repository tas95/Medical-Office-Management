/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.utilitaires;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Manel
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
       
        Connection connexion=SingletonConnexion.getConnexion();       
        
    }
    
}
