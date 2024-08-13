/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorcc.backend;

/**
 *
 * @author mynordma
 */
public class Gestion {
    private String instruccion;
    private String[] parametros;
    
    public Gestion(String[] instrucciones){
        instruccion = instrucciones[0];
        parametros = new String[instrucciones.length-1];
        for (int i = 0; i < parametros.length; i++) {
            parametros[i] = instrucciones[i+1];
        }
    }
    
    public void solicitar(){
        
    }
    
    public void mover(){
        
    }
    
    public void consultar(){
        
    }
    
    public void autorizar(){
        
    }
    
    public void cancelar(){
        
    }
}
