/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestionmedicale.beans;

/**
 *
 * @author Manel
 */
public class Cabinet {
    
    private int code_cabinet;
    private String designation;

    public int getCode_cabinet() {
        return code_cabinet;
    }

    public String getDesignation() {
        return designation;
    }

    public void setCode_cabinet(int code_cabinet) {
        this.code_cabinet = code_cabinet;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    
}
