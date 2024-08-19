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
        Conexion conexion = new Conexion();
        if(puedeHacerMovimiento(numeroTarjeta, monto, conexion)){
            double saldoResultante = tipo.modificarSaldo(numeroTarjeta, monto);
            System.out.println(saldoResultante);
            conexion.insert(numeroTarjeta, descripcion, fecha, monto, tipo.name(), establecimiento);
            conexion.insert(numeroTarjeta, conexion.obtenerUltimoId("movimiento"));
        }else{
            System.out.println("Haz llegado al limite de credito");
        }
        
        conexion.cerrarConexion();
        
    }
    
    public String[][] consultar(String numeroTarjeta, String path_salida, boolean desdeInterfazGrafica){
        String[] datos = new String[6];
        String[][] matrizDatos = new String[2][6];
        String[] titulos = new String[]{"Numero", "TipoTarjeta", "Limite", "Nombre", "Direccion", "Estado", };
        
        Conexion conexion = new Conexion();
        
        datos[0] = conexion.obtenerAtributo("select numero from tarjeta where numero = '" + numeroTarjeta + "'", "numero");
        datos[1] = conexion.obtenerAtributo("select tipo from tarjeta where numero = '" + numeroTarjeta + "'", "tipo");
        datos[2] = conexion.obtenerAtributo("select limite from tarjeta where numero = '" + numeroTarjeta + "'", "limite");
        datos[3] = conexion.obtenerAtributo("select c.nombre from cliente c join solicitud s on c.id = s.id_cliente where s.numero_tarjeta = '" + numeroTarjeta + "'", "nombre");
        datos[4] = conexion.obtenerAtributo("select c.direccion from cliente c join solicitud s on c.id = s.id_cliente where s.numero_tarjeta = '" + numeroTarjeta + "'", "direccion");
        datos[5] = conexion.obtenerAtributo("select estado from tarjeta where numero = '" + numeroTarjeta + "'", "estado");
        
        matrizDatos[0] = titulos;
        matrizDatos[1] = datos;
        
        conexion.cerrarConexion();
        if(desdeInterfazGrafica){
            return matrizDatos;
        }else{
            Reporte reporte = new Reporte(path_salida);
            reporte.generarHTML(matrizDatos, "Consulta: '" + numeroTarjeta + "'", path_salida);
        }
        
        return null;
    }
    
    public void autorizar(int numeroSolicitud){
        Conexion conexion = new Conexion();
        String salarioCliente = conexion.obtenerSalarioCliente(numeroSolicitud);
        String tipoTarjeta = conexion.obtenerTipoTarjeta(numeroSolicitud);
        if(superaLimite(salarioCliente, tipoTarjeta)){
            conexion.actualizarAtributo("update solicitud set estado = 'APROBADA' where numero_solicitud = " + numeroSolicitud);
            conexion.actualizarAtributo("update tarjeta t join solicitud s on t.numero = s.numero_tarjeta set t.estado = 'AUTORIZADA' where s.numero_solicitud = " + numeroSolicitud);
            conexion.actualizarAtributo("update solicitud set fecha = '" + Gestion.fechaActual() + "' where numero_solicitud = " + numeroSolicitud);
            conexion.actualizarAtributo("update tarjeta t join solicitud s on t.numero = s.numero_tarjeta set t.fecha = '" + Gestion.fechaActual() + "' where s.numero_solicitud = " + numeroSolicitud);
        }else{
            conexion.actualizarAtributo("update solicitud set estado = 'RECHAZADA' where numero_solicitud = " + numeroSolicitud);
            conexion.actualizarAtributo("update tarjeta t join solicitud s on t.numero = s.numero_tarjeta set t.estado = 'RECHAZADA' where s.numero_solicitud = " + numeroSolicitud);
            conexion.actualizarAtributo("update solicitud set fecha = '" + Gestion.fechaActual() + "' where numero_solicitud = " + numeroSolicitud);
            conexion.actualizarAtributo("update tarjeta t join solicitud s on t.numero = s.numero_tarjeta set t.fecha = '" + Gestion.fechaActual() + "' where s.numero_solicitud = " + numeroSolicitud);
        }
        conexion.cerrarConexion();
    }
    
    public void cancelar(String numeroTarjeta){
        if(sePuedeCancelar(numeroTarjeta)){
            Conexion conexion = new Conexion();
            conexion.actualizarAtributo("update tarjeta set estado = 'CANCELADA' where numero = '" + numeroTarjeta + "'");
            conexion.actualizarAtributo("update tarjeta set fecha = '" + Gestion.fechaActual() + "' where numero = '" + numeroTarjeta + "'");
            conexion.cerrarConexion();
        }
    }
    
    public boolean sePuedeCancelar(String numeroTarjeta){
        Conexion conexion = new Conexion();
        String estado = conexion.obtenerAtributo("select estado from tarjeta where numero = '" + numeroTarjeta + "'", "estado");
        String saldo = conexion.obtenerAtributo("select saldo from tarjeta where numero = '" + numeroTarjeta + "'", "saldo");
        conexion.cerrarConexion();
        return (estado.equals("AUTORIZADA") && Double.parseDouble(saldo)<=0);
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
    
    private boolean puedeHacerMovimiento(String numeroTarjeta, double monto, Conexion conexion){
        String saldo = conexion.obtenerAtributo("select saldo from tarjeta where numero = '" + numeroTarjeta + "'", "saldo");
        String limite = conexion.obtenerAtributo("select limite from tarjeta where numero = '" + numeroTarjeta + "'", "limite");
        return ((Double.parseDouble(saldo) + monto) <= Double.parseDouble(limite));
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
