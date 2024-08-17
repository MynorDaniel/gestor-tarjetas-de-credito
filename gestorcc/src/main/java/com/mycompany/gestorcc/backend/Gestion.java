/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorcc.backend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author mynordma
 */
public final class Gestion {
    private String instruccion;
    private String[] parametros;
    
    public Gestion(String[] instrucciones, String path_salida){
        instruccion = instrucciones[0];
        parametros = new String[instrucciones.length-1];
        for (int i = 0; i < parametros.length; i++) {
            parametros[i] = instrucciones[i+1];
        }
        
        switch(instruccion){
            case "SOLICITUD" -> {
                Solicitud solicitud = new Solicitud();
                solicitud.realizarSolicitud(Integer.parseInt(parametros[0]), cambiarFormatoFecha(parametros[1]), TipoTarjeta.valueOf(parametros[2]), parametros[3], Double.parseDouble(parametros[4]), parametros[5]);
            }
            case "MOVIMIENTO" -> {
                Tarjeta tarjeta = new Tarjeta();
                tarjeta.transaccion(parametros[0], cambiarFormatoFecha(parametros[1]), TipoMovimiento.valueOf(parametros[2]), parametros[3], parametros[4], Double.parseDouble(parametros[5]));
            }
            case "CONSULTAR_TARJETA" -> {
                Tarjeta tarjeta = new Tarjeta();
                tarjeta.consultar(parametros[0], path_salida);
            }
            case "AUTORIZACION_TARJETA" -> {
                Tarjeta tarjeta = new Tarjeta();
                tarjeta.autorizar(Integer.parseInt(parametros[0]));
            }
            case "CANCELACION_TARJETA" -> {
                Tarjeta tarjeta = new Tarjeta();
                tarjeta.cancelar(parametros[0]);
            }
        }
    } 
    
    public String cambiarFormatoFecha(String fecha) {
        try {
            DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaLocalDate = LocalDate.parse(fecha, formatoEntrada);
            DateTimeFormatter formatoSalida = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return fechaLocalDate.format(formatoSalida);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return null; 
        }
    }
    
    public static String fechaActual() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return fechaActual.format(formato);
    }
}
