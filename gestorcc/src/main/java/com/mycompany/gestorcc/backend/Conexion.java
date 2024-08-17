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
    
    public void insertarQuery(String insert){
        try {
            Statement statement = connection.createStatement();
            int columnasAfectadas = statement.executeUpdate(insert);
            System.out.println("Columnas afectadas: " + columnasAfectadas);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public Object[] obtenerAtributos(String tabla, String llavePrimaria){
        String insert = "select * from " + tabla + " where numero_tarjeta = " + llavePrimaria;
        
        Object[] atributos = null;
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(insert);
            
            int columnas = rs.getMetaData().getColumnCount();
            
            if(rs.next()){
                atributos = new Object[columnas];
                for (int i = 0; i < columnas; i++) {
                    atributos[i] = rs.getObject(i+1);
                }
            }else{
                System.out.println("No hay tupla con: " + llavePrimaria);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return atributos;
    }
    
    public String obtenerAtributoCliente(String atributo, String numTarjeta){
        String query = "select c." + atributo + " from cliente c join solicitud s on c.id = s.id_cliente where s.numero_tarjeta = '" + numTarjeta + "'";
        String atributoReturn = "";
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            
            if(rs.next()){
                atributoReturn = rs.getString(atributo);
            }else{
                System.out.println("No existe el atributo");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return atributoReturn;
    }
    
    public String obtenerAtributoTarjeta(String atributo, String numTarjeta){
        String query = "select " + atributo + " from tarjeta where numero = '" + numTarjeta + "'";
        String atributoReturn = "";
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            
            if(rs.next()){
                atributoReturn = rs.getString(atributo);
            }else{
                System.out.println("No existe el atributo");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return atributoReturn;
    }
    
    public void actualizarAtributo(int numeroSolicitud, String estado, String tabla, boolean join, String tablaJoin) {
        String query = "UPDATE " + tabla + " SET estado = '" + estado + "' WHERE numero_solicitud = " + numeroSolicitud;
        String queryJoin = "UPDATE " + tabla + " t "
                     + "JOIN " + tablaJoin+ " s ON t.numero = s.numero_tarjeta "
                     + "SET t.estado = '" + estado + "' "
                     + "WHERE s.numero_solicitud = " + numeroSolicitud;

        try {
            Statement stmt = connection.createStatement();
            if(join){
                stmt.executeUpdate(queryJoin);
            }else{
                stmt.executeUpdate(query);
            }
            
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
            System.out.println("Error al obtener atributo");
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
