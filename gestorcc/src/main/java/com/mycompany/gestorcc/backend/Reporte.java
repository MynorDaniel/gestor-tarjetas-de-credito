/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorcc.backend;

/**
 *
 * @author mynordma
 */
public class Reporte {
    private final String PATH_SALIDA;
    
    public Reporte(String path_salida){
        this.PATH_SALIDA = path_salida;
    }
    
    public void generarHTML(String[] datos){
        System.out.println(PATH_SALIDA);
        //codigo para generar el html
    }
}
