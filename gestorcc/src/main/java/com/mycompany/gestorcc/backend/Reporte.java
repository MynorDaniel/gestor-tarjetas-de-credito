/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorcc.backend;

import java.io.*;

/**
 *
 * @author mynordma
 */
public class Reporte {
    private final String PATH_SALIDA;
    
    public Reporte(String path_salida){
        this.PATH_SALIDA = path_salida;
    }
    
    public String[][] generarReporte(String[] titulos, int tipoReporte) {
        String[][] datos = null;
        
        Conexion conexion = new Conexion();
        
        switch (tipoReporte){
            case 1 -> datos = conexion.estadoDeCuenta(titulos);
            case 2 -> datos = conexion.listadoTarjetas(titulos);
            case 3 -> datos = conexion.listadoSolicitudes(titulos);
        }
       
        conexion.cerrarConexion();
        return datos;
    }
    
     public void generarHTML(String[][] datos, String nombreArchivo) {
        // Verificar que haya datos suficientes para generar la tabla
        if (datos == null || datos.length == 0 || datos[0].length == 0) {
            System.err.println("No hay datos suficientes para generar la tabla.");
            return;
        }

        // Definir la ruta del archivo
        String userHome = System.getProperty("user.home");
        String directorio = userHome + File.separator + "Documents" + File.separator + "reportes";
        File directorioReportes = new File(directorio);

        // Crear el directorio si no existe
        if (!directorioReportes.exists()) {
            directorioReportes.mkdirs();
        }

        File archivoHTML = new File(directorioReportes, nombreArchivo);

        // Construir el contenido HTML
        StringBuilder contenidoHTML = new StringBuilder();
        contenidoHTML.append("<html>\n");
        contenidoHTML.append("<head>\n");
        contenidoHTML.append("<title>Reporte</title>\n");
        contenidoHTML.append("<style>\n");
        contenidoHTML.append("table {width: 100%; border-collapse: collapse;}\n");
        contenidoHTML.append("table, th, td {border: 1px solid black; padding: 10px; text-align: left;}\n");
        contenidoHTML.append("</style>\n");
        contenidoHTML.append("</head>\n");
        contenidoHTML.append("<body>\n");
        contenidoHTML.append("<h1>Reporte</h1>\n");
        contenidoHTML.append("<table>\n");
        contenidoHTML.append("<tr>\n");

        // Agregar los encabezados de la tabla usando la primera fila de datos
        for (String encabezado : datos[0]) {
            contenidoHTML.append("<th>").append(encabezado).append("</th>\n");
        }
        contenidoHTML.append("</tr>\n");

        // Agregar las filas subsecuentes de la tabla
        for (int i = 1; i < datos.length; i++) {
            contenidoHTML.append("<tr>\n");
            for (String valor : datos[i]) {
                contenidoHTML.append("<td>").append(valor).append("</td>\n");
            }
            contenidoHTML.append("</tr>\n");
        }

        contenidoHTML.append("</table>\n");
        contenidoHTML.append("</body>\n");
        contenidoHTML.append("</html>");

        // Escribir el contenido HTML en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoHTML))) {
            writer.write(contenidoHTML.toString());
            System.out.println("Archivo HTML creado en: " + archivoHTML.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo HTML: " + e.getMessage());
        }
    }
}
