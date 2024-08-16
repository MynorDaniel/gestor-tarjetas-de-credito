/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorcc.backend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 *
 * @author mynordma
 */
public final class Gestion {
    private String instruccion;
    private String[] parametros;
    
    public Gestion(String[] instrucciones){
        instruccion = instrucciones[0];
        parametros = new String[instrucciones.length-1];
        for (int i = 0; i < parametros.length; i++) {
            parametros[i] = instrucciones[i+1];
        }
        
        switch(instruccion){
            case "SOLICITUD" -> solicitar(Integer.parseInt(parametros[0]), parametros[1], TipoTarjeta.valueOf(parametros[2]), parametros[3], Double.parseDouble(parametros[4]), parametros[5]);
            case "MOVIMIENTO" -> mover(parametros[0], parametros[1], TipoMovimiento.valueOf(parametros[2]), parametros[3], parametros[4], Double.parseDouble(parametros[5]));
            case "CONSULTAR_TARJETA" -> consultar(parametros[0]);
            case "AUTORIZACION_TARJETA" -> autorizar(Integer.parseInt(parametros[0]));
            case "CANCELACION_TARJETA" -> cancelar(parametros[0]);
        }
    }
    
    public void solicitar(int numero, String fecha, TipoTarjeta tipo, String nombre, double salario, String direccion){

        String numeroTarjeta = Tarjeta.generarTarjeta(tipo);
        String insertTarjeta = "INSERT INTO tarjeta (numero, fecha, limite, tipo, interes, monto, saldo, estado) VALUES('" + numeroTarjeta + "', '" + fechaActual() + "', '" + Tarjeta.limite(salario) + "', '" + tipo + "', '" + Tarjeta.interes(tipo) + "', '" + Tarjeta.monto(0, Tarjeta.interes(tipo)) + "', '" + 0 + "', 'PENDIENTE')";
        String insertSolicitud = "INSERT INTO solicitud (numero_solicitud, numero_tarjeta, estado, fecha) VALUES('" + numero + "', '" + numeroTarjeta + "', 'PENDIENTE', '" + fechaActual() + "')";
        String insertCliente = "INSERT INTO cliente (numero_tarjeta, salario, direccion, nombre) VALUES('" + numeroTarjeta + "', '" + salario + "', '" + direccion + "', '" + nombre + "')";
        
        Conexion conexion = new Conexion();
        
        if(!solicitudRepetida(numero, conexion)){
            conexion.insertarQuery(insertTarjeta);
            conexion.insertarQuery(insertSolicitud);
            conexion.insertarQuery(insertCliente);
            conexion.cerrarConexion();
        }else{
            System.out.println("solicitud repetida");
        }
        
        
        
    }
    
    public void mover(String numeroTarjeta, String fecha, TipoMovimiento tipo, String descripcion, String establecimiento, double monto){
    }
    
    public void consultar(String numeroTarjeta){
    }
    
    public void autorizar(int numeroSolicitud){
    }
    
    public void cancelar(String numeroTarjeta){
    }
    
    private String fechaActual() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return fechaActual.format(formato);
    }
    
    private boolean solicitudRepetida(int numero, Conexion conexion){
        String insert = "SELECT 1 FROM solicitud WHERE numero_solicitud = " + numero;
        return conexion.verificarClavePrimaria(insert);
    }
}
