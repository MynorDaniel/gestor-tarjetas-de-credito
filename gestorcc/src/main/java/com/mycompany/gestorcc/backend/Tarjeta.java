/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorcc.backend;

import static com.mycompany.gestorcc.backend.TipoTarjeta.INTERNACIONAL;
import static com.mycompany.gestorcc.backend.TipoTarjeta.NACIONAL;
import static com.mycompany.gestorcc.backend.TipoTarjeta.REGIONAL;
import java.util.Random;

/**
 *
 * @author mynordma
 */
public class Tarjeta {
    
    public void transaccion(String numeroTarjeta, String fecha, TipoMovimiento tipo, String descripcion, String establecimiento, double monto){
    }
    
    public void consultar(String numeroTarjeta){
    }
    
    public void autorizar(int numeroSolicitud){
        Conexion conexion = new Conexion();
        String salarioCliente = conexion.obtenerSalarioCliente(numeroSolicitud);
        String tipoTarjeta = conexion.obtenerTipoTarjeta(numeroSolicitud);
        if(superaLimite(salarioCliente, tipoTarjeta)){
            System.out.println("zmzmzmmz");
        }
    }
    
    public void cancelar(String numeroTarjeta){
    }
    
    public boolean superaLimite(String salario, String tipo){
        int[] limites = new int[]{5000, 10000, 20000};
        double limiteSalarial = Double.parseDouble(salario) * 0.6;
        return switch (tipo) {
            case "NACIONAL" -> limiteSalarial >= limites[0];
            case "REGIONAL" -> limiteSalarial >= limites[1];
            case "INTERNACIONAL" -> limiteSalarial >= limites[2];
            default -> false;
        };
    }
    
    public static String generarTarjeta(TipoTarjeta tipo){
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
    
    public static double limite(double numero) {
        return numero * 0.6;
    }
    
    public static double interes(TipoTarjeta tipo) {
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
    
    public static double monto(double saldo, double interesPorcentaje) {
        double interes = saldo * (interesPorcentaje / 100);
        return saldo + interes;
    }
}
