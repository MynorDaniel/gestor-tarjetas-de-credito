/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorcc.backend;

/**
 *
 * @author mynordma
 */
public enum TipoMovimiento {
    CARGO{

        @Override
        public double modificarSaldo(String numeroTarjeta, double monto) {
            Conexion conexion = new Conexion();
            Double saldoActual = Double.valueOf(conexion.obtenerAtributo("select saldo from tarjeta where numero = '" + numeroTarjeta + "'", "saldo"));
            Double saldoResultante = saldoActual + monto;
            conexion.actualizarAtributo("update tarjeta set saldo = '" + saldoResultante + "' where numero = '" + numeroTarjeta + "'");
            return saldoResultante;
        }
        
    }, ABONO{

        @Override
        public double modificarSaldo(String numeroTarjeta, double monto) {
            Conexion conexion = new Conexion();
            Double saldoActual = Double.valueOf(conexion.obtenerAtributo("select saldo from tarjeta where numero = '" + numeroTarjeta + "'", "saldo"));
            Double saldoResultante = saldoActual - monto;
            conexion.actualizarAtributo("update tarjeta set saldo = '" + saldoResultante + "' where numero = '" + numeroTarjeta + "'");
            return saldoResultante;
        }
        
    };
    
    public abstract double modificarSaldo(String numeroTarjeta, double monto);
}
