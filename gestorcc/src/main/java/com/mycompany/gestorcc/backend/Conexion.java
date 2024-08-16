/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorcc.backend;

import java.sql.Connection;
import java.sql.DriverManager;
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
    
    public void insertarDatos(String insert){
        try {
            Statement statement = connection.createStatement();
            int columnasAfectadas = statement.executeUpdate(insert);
            System.out.println("Columnas afectadas: " + columnasAfectadas);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
