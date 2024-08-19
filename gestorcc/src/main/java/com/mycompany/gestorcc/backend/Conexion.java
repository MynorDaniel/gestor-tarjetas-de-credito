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
    
    public void insert(String numeroTarjeta, String descripcion, String fecha, double monto, String tipo, String establecimiento){

        String insertMovimiento = "INSERT INTO movimiento (numero_tarjeta, descripcion, fecha, monto, tipo, establecimiento) VALUES('" + numeroTarjeta + "', '" + descripcion + "', '" + fecha + "', '" + monto + "', '" + tipo + "', '" + establecimiento + "')";

        try {
            Statement statement = connection.createStatement();
            int columnasAfectadas = statement.executeUpdate(insertMovimiento);
            System.out.println("Columnas afectadas: " + columnasAfectadas);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void insert(String numeroTarjeta, int idMovimiento){

        String insertTarjeta_Movimiento = "INSERT INTO tarjeta_movimiento (numero_tarjeta, id_movimiento) VALUES('" + numeroTarjeta + "', '" + idMovimiento + "')";

        try {
            Statement statement = connection.createStatement();
            int columnasAfectadas = statement.executeUpdate(insertTarjeta_Movimiento);
            System.out.println("Columnas afectadas: " + columnasAfectadas);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public String[][] listadoSolicitudes(String[] titulos){
        String[][] datos = null;
        
        String query = "SELECT " +
                       "    s.numero_solicitud AS numero_solicitud, " +
                       "    s.fecha AS fecha_solicitud, " +
                       "    t.tipo AS tipo_tarjeta, " +
                       "    c.nombre AS nombre_cliente, " +
                       "    c.salario AS salario_cliente, " +
                       "    c.direccion AS direccion_cliente, " +
                       "    s.estado AS estado_solicitud " +
                       "FROM " +
                       "    tarjeta t " +
                       "JOIN " +
                       "    solicitud s ON t.numero = s.numero_tarjeta " +
                       "JOIN " +
                       "    cliente c ON s.id_cliente = c.id";
        
        
        try {
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);
            // Obtener el número de filas
            rs.last();
            int numRows = rs.getRow();
            rs.beforeFirst();

            // Número de columnas (12 columnas en el SELECT)
            int numCols = 7;

            // Inicializar la matriz con una fila adicional para los títulos
            datos = new String[numRows + 1][numCols];

            // Agregar los títulos en la primera fila (índice 0)
            datos[0] = titulos;

            int i = 1;  // Iniciar desde la fila 1 para insertar datos
            while (rs.next()) {
                datos[i][0] = rs.getString("numero_solicitud");
                datos[i][1] = rs.getString("fecha_solicitud");
                datos[i][2] = rs.getString("tipo_tarjeta");
                datos[i][3] = rs.getString("nombre_cliente");
                datos[i][4] = rs.getString("salario_cliente");
                datos[i][5] = rs.getString("direccion_cliente");
                datos[i][6] = rs.getString("estado_solicitud");
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datos;
    }
    
    public String[][] listadoTarjetas(String[] titulos){
        String[][] datos = null;
        
        String query = "SELECT " +
                       "    t.numero AS numero_tarjeta, " +
                       "    t.tipo AS tipo_tarjeta, " +
                       "    t.limite AS limite_tarjeta, " +
                       "    c.nombre AS nombre_cliente, " +
                       "    c.direccion AS direccion_cliente, " +
                       "    t.fecha AS fecha_tarjeta, " +
                       "    t.estado AS estado_tarjeta " +
                       "FROM " +
                       "    tarjeta t " +
                       "JOIN " +
                       "    solicitud s ON t.numero = s.numero_tarjeta " +
                       "JOIN " +
                       "    cliente c ON s.id_cliente = c.id";
        
        
        try {
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);
            // Obtener el número de filas
            rs.last();
            int numRows = rs.getRow();
            rs.beforeFirst();

            // Número de columnas (12 columnas en el SELECT)
            int numCols = 7;

            // Inicializar la matriz con una fila adicional para los títulos
            datos = new String[numRows + 1][numCols];

            // Agregar los títulos en la primera fila (índice 0)
            datos[0] = titulos;

            int i = 1;  // Iniciar desde la fila 1 para insertar datos
            while (rs.next()) {
                datos[i][0] = rs.getString("numero_tarjeta");
                datos[i][1] = rs.getString("tipo_tarjeta");
                datos[i][2] = rs.getString("limite_tarjeta");
                datos[i][3] = rs.getString("nombre_cliente");
                datos[i][4] = rs.getString("direccion_cliente");
                datos[i][5] = rs.getString("fecha_tarjeta");
                datos[i][6] = rs.getString("estado_tarjeta");
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datos;
    }
    
    public String[][] estadoDeCuenta(String[] titulos){
        String[][] datos = null;
        
        String query = "SELECT " +
                       "    t.numero AS numero_tarjeta, " +
                       "    t.tipo AS tipo_tarjeta, " +
                       "    c.nombre AS nombre_cliente, " +
                       "    c.direccion AS direccion_cliente, " +
                       "    m.fecha AS fecha_movimiento, " +
                       "    m.tipo AS tipo_movimiento, " +
                       "    m.descripcion AS descripcion_movimiento, " +
                       "    m.establecimiento AS establecimiento_movimiento, " +
                       "    m.monto AS monto_movimiento, " +
                       "    t.monto AS monto_tarjeta, " +
                       "    t.interes AS interes_tarjeta, " +
                       "    t.saldo AS saldo_tarjeta " +
                       "FROM " +
                       "    tarjeta t " +
                       "JOIN " +
                       "    solicitud s ON t.numero = s.numero_tarjeta " +
                       "JOIN " +
                       "    cliente c ON s.id_cliente = c.id " +
                       "JOIN " +
                       "    tarjeta_movimiento tm ON t.numero = tm.numero_tarjeta " +
                       "JOIN " +
                       "    movimiento m ON tm.id_movimiento = m.id";
        
        
        try {
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);
            // Obtener el número de filas
            rs.last();
            int numRows = rs.getRow();
            rs.beforeFirst();

            // Número de columnas (12 columnas en el SELECT)
            int numCols = 12;

            // Inicializar la matriz con una fila adicional para los títulos
            datos = new String[numRows + 1][numCols];

            // Agregar los títulos en la primera fila (índice 0)
            datos[0] = titulos;

            int i = 1;  // Iniciar desde la fila 1 para insertar datos
            while (rs.next()) {
                datos[i][0] = rs.getString("numero_tarjeta");
                datos[i][1] = rs.getString("tipo_tarjeta");
                datos[i][2] = rs.getString("nombre_cliente");
                datos[i][3] = rs.getString("direccion_cliente");
                datos[i][4] = rs.getString("fecha_movimiento");
                datos[i][5] = rs.getString("tipo_movimiento");
                datos[i][6] = rs.getString("descripcion_movimiento");
                datos[i][7] = rs.getString("establecimiento_movimiento");
                datos[i][8] = rs.getString("monto_movimiento");
                datos[i][9] = rs.getString("monto_tarjeta");
                datos[i][10] = rs.getString("interes_tarjeta");
                datos[i][11] = rs.getString("saldo_tarjeta");
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datos;
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
    
    public int obtenerUltimoId(String tabla) {
        int ultimoId = -1;

        String query = "SELECT id FROM " + tabla + " ORDER BY id DESC LIMIT 1";

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
