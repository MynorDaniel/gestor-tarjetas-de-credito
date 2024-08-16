/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorcc.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
    
    public void insertarValores(){
        
    }

    // Método para cerrar la conexión
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                // Manejo de excepciones personalizado
            }
        }
    }

}
