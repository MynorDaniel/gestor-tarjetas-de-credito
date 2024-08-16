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
        System.out.println(numero + fecha + tipo + nombre + salario + direccion);
        String numeroTarjeta = generarTarjeta(tipo);
        String insertTarjeta = "INSERT INTO tarjeta (numero, fecha, limite, tipo, interes, monto, saldo, estado) VALUES('" + numeroTarjeta + "', '" + fechaActual() + "', '" + limite(salario) + "', '" + tipo + "', '" + interes(tipo) + "', '" + monto(0, interes(tipo)) + "', '" + 0 + "', 'PENDIENTE')";
        String insertSolicitud = "INSERT INTO solicitud (numero_solicitud, numero_tarjeta, estado, fecha) VALUES('" + numero + "', '" + numeroTarjeta + "', 'PENDIENTE', '" + fechaActual() + "')";
        String insertCliente = "INSERT INTO cliente (numero_tarjeta, salario, direccion, nombre) VALUES('" + numeroTarjeta + "', '" + salario + "', '" + direccion + "', '" + nombre + "')";
        
        Conexion conexion = new Conexion();
        conexion.insertarDatos(insertTarjeta);
        conexion.insertarDatos(insertSolicitud);
        conexion.insertarDatos(insertCliente);
        
    }
    
    public void mover(String numeroTarjeta, String fecha, TipoMovimiento tipo, String descripcion, String establecimiento, double monto){
    }
    
    public void consultar(String numeroTarjeta){
    }
    
    public void autorizar(int numeroSolicitud){
    }
    
    public void cancelar(String numeroTarjeta){
    }
    
    private String generarTarjeta(TipoTarjeta tipo){
        String prefijo = "4256 3102 ";

        switch (tipo) {
            case NACIONAL -> prefijo += "654";
            case REGIONAL -> prefijo += "656";
            case INTERNACIONAL -> prefijo += "658";
        }
        
        Random random = new Random();
        int digitoAleatorio = random.nextInt(9);
        int digitosAleatorios = random.nextInt(9000) + 1000;  
        
        return (prefijo + digitoAleatorio + " " + digitosAleatorios);
    }
    
    private String fechaActual() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return fechaActual.format(formato);
    }
    
    private double limite(double numero) {
        return numero * 0.6;
    }
    
    private double interes(TipoTarjeta tipo) {
        switch (tipo) {
            case NACIONAL -> {
                return 1.2;
            }
            case REGIONAL -> {
                return 2.3;
            }
            case INTERNACIONAL -> {
                return 3.75;
            }
        }
        return 0;
    }
    
    public double monto(double saldo, double interesPorcentaje) {
        double interes = saldo * (interesPorcentaje / 100);
        return saldo + interes;
    }
}
