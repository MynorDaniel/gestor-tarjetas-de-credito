/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorcc.backend;

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
            case "SOLICITUD" -> {
                Solicitud solicitud = new Solicitud();
                solicitud.realizarSolicitud(Integer.parseInt(parametros[0]), parametros[1], TipoTarjeta.valueOf(parametros[2]), parametros[3], Double.parseDouble(parametros[4]), parametros[5]);
            }
            case "MOVIMIENTO" -> {
                Tarjeta tarjeta = new Tarjeta();
                tarjeta.transaccion(parametros[0], parametros[1], TipoMovimiento.valueOf(parametros[2]), parametros[3], parametros[4], Double.parseDouble(parametros[5]));
            }
            case "CONSULTAR_TARJETA" -> {
                Tarjeta tarjeta = new Tarjeta();
                tarjeta.consultar(parametros[0]);
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
}
