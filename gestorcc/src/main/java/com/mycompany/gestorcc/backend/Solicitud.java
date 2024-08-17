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
            String insertCliente = "INSERT INTO cliente (salario, direccion, nombre) VALUES('" + salario + "', '" + direccion + "', '" + nombre + "')";
            conexion.insertarQuery(insertCliente);
            String insertTarjeta = "INSERT INTO tarjeta (numero, fecha, limite, tipo, interes, monto, saldo, estado) VALUES('" + numeroTarjeta + "', '" + fechaActual() + "', '" + Tarjeta.limite(salario) + "', '" + tipo + "', '" + Tarjeta.interes(tipo) + "', '" + Tarjeta.monto(0, Tarjeta.interes(tipo)) + "', '" + 0 + "', 'PENDIENTE')";
            conexion.insertarQuery(insertTarjeta);
            String insertSolicitud = "INSERT INTO solicitud (numero_solicitud, numero_tarjeta, estado, fecha, id_cliente) VALUES('" + numero + "', '" + numeroTarjeta + "', 'PENDIENTE', '" + fechaActual() + "', '" + conexion.obtenerUltimoIdCliente() + "')";
            conexion.insertarQuery(insertSolicitud);
   
            conexion.cerrarConexion();
        }else{
            System.out.println("solicitud repetida");
        }
    }
    
    private boolean solicitudRepetida(int numero, Conexion conexion){
        String insert = "SELECT 1 FROM solicitud WHERE numero_solicitud = " + numero;
        return conexion.verificarClavePrimaria(insert);
    }
    
    private String fechaActual() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return fechaActual.format(formato);
    }
    
    
    
}
