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
            case "SOLICITUD" -> solicitar(Integer.parseInt(parametros[0]), parametros[1], TipoTarjeta.valueOf(parametros[2]), parametros[3], Double.parseDouble(parametros[4]), parametros[5]);
            case "MOVIMIENTO" -> mover(parametros[0], parametros[1], TipoMovimiento.valueOf(parametros[2]), parametros[3], parametros[4], Double.parseDouble(parametros[5]));
            case "CONSULTAR_TARJETA" -> consultar(parametros[0]);
            case "AUTORIZACION_TARJETA" -> autorizar(Integer.parseInt(parametros[0]));
            case "CANCELACION_TARJETA" -> cancelar(parametros[0]);
        }
    }
    
    public void solicitar(int numero, String fecha, TipoTarjeta tipo, String nombre, double salario, String direccion){
        System.out.println(numero + fecha + tipo + nombre + salario + direccion);
    }
    
    public void mover(String numeroTarjeta, String fecha, TipoMovimiento tipo, String descripcion, String establecimiento, double monto){
        System.out.println(numeroTarjeta + fecha + tipo + descripcion + establecimiento + monto);
    }
    
    public void consultar(String numeroTarjeta){
        System.out.println(numeroTarjeta);
    }
    
    public void autorizar(int numeroSolicitud){
        System.out.println(numeroSolicitud);
    }
    
    public void cancelar(String numeroTarjeta){
        System.out.println(numeroTarjeta);
    }
}
