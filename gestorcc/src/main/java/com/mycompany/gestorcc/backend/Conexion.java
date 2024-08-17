/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorcc.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mynordma
 */
public class Conexion {
    
    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/REGISTROS_TARJETAS";
    private static final String USER = "root";
    private static final String PASSWORD = "mynordma";
    private Connection connection;
    
    public Conexion(){
        try {
            connection = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
        } catch (SQLException ex) {
            System.out.println("Error al conectar con la bd");
        }
    }
    
    public void insert(double salario, String direccion, String nombre){
        String insertCliente = "INSERT INTO cliente (salario, direccion, nombre) VALUES('" + salario + "', '" + direccion + "', '" + nombre + "')";
        
        try {
            Statement statement = connection.createStatement();
            int columnasAfectadas = statement.executeUpdate(insertCliente);
            System.out.println("Columnas afectadas: " + columnasAfectadas);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void insert(String numero, String fecha, double limite, String tipo, double interes, double monto, double saldo, String estado){

        String insertTarjeta = "INSERT INTO tarjeta (numero, fecha, limite, tipo, interes, monto, saldo, estado) VALUES('" + numero + "', '" + fecha + "', '" + limite + "', '" + tipo + "', '" + interes + "', '" + monto + "', '" + saldo + "', '" + estado + "')";

        try {
            Statement statement = connection.createStatement();
            int columnasAfectadas = statement.executeUpdate(insertTarjeta);
            System.out.println("Columnas afectadas: " + columnasAfectadas);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void insert(int numSolicitud, String numTarjeta, String estado, String fecha, int idCliente){
       
        String insertSolicitud = "INSERT INTO solicitud (numero_solicitud, numero_tarjeta, estado, fecha, id_cliente) VALUES('" + numSolicitud + "', '" + numTarjeta + "', '" + estado + "', '" + fecha + "', '" + idCliente + "')";
        
        try {
            Statement statement = connection.createStatement();
            int columnasAfectadas = statement.executeUpdate(insertSolicitud);
            System.out.println("Columnas afectadas: " + columnasAfectadas);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public String obtenerAtributo(String query, String atributo){
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            if(rs.next()){
                System.out.println(rs.getString(atributo));
                return rs.getString(atributo);
            }else{
                System.out.println("No existe el atributo");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }
    
    public void actualizarAtributo(String query) {

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean verificarClavePrimaria(String insert) {

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(insert);
            return rs.next(); 

        } catch (SQLException e) {
            System.out.println("Error en verificar la pk");
            e.printStackTrace();
        }

        return false; 
    }
    
    public int obtenerUltimoIdCliente() {
        int ultimoId = -1;

        String query = "SELECT id FROM cliente ORDER BY id DESC LIMIT 1";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                ultimoId = rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ultimoId;
    }
    
    public String obtenerSalarioCliente(int numeroSolicitud) {
        String query = "select c.salario from cliente c join solicitud s on c.id = s.id_cliente where s.numero_solicitud = " + numeroSolicitud;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getString("salario"); 
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener atributo.");
            e.printStackTrace();
        }

        return null;
    }
    
    public String obtenerTipoTarjeta(int numeroSolicitud) {
        String query = "select t.tipo from tarjeta t join solicitud s on t.numero = s.numero_tarjeta where s.numero_solicitud = " + numeroSolicitud;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getString("tipo"); 
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener atributo");
            e.printStackTrace();
        }

        return null;
    }

    public void cerrarConexion() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion");
            }
        }
    }

}
