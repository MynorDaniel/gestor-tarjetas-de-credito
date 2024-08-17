/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorcc.backend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author mynordma
 */
public class Solicitud {
    
    public void realizarSolicitud(int numero, String fecha, TipoTarjeta tipo, String nombre, double salario, String direccion){
        
        String numeroTarjeta = Tarjeta.generarTarjeta(tipo);        
  
        Conexion conexion = new Conexion();
        
        if(!solicitudRepetida(numero, conexion)){
            conexion.insert(salario, direccion, nombre); //tabla cliente
            conexion.insert(numeroTarjeta, fecha, Tarjeta.limite(salario), tipo.name(), Tarjeta.interes(tipo), Tarjeta.monto(0, Tarjeta.interes(tipo)), 0, "PENDIENTE"); //tabla tarjeta
            conexion.insert(numero, numeroTarjeta, "PENDIENTE", fecha, conexion.obtenerUltimoId("cliente")); //tabla solicitud
   
            conexion.cerrarConexion();
        }else{
            System.out.println("solicitud repetida");
        }
    }
    
    private boolean solicitudRepetida(int numero, Conexion conexion){
        String insert = "SELECT 1 FROM solicitud WHERE numero_solicitud = " + numero;
        return conexion.verificarClavePrimaria(insert);
    }
    
    
    
    
    
}
