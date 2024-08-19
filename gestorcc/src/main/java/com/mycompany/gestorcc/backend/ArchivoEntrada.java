/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorcc.backend;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Clase que maneja el archivo de entrada y devuelve las instrucciones ordenadas.
 * @author mynordma
 */
public class ArchivoEntrada {
    
    private final String pathEntrada;
    private final List<String> lineas = new ArrayList<>();
    private final List<String[]> instrucciones = new ArrayList<>();

    public List<String[]> getInstrucciones() {
        return instrucciones;
    }
    
    /**
     * Lee el contenido del archivo en el path especificado.
     * @throws java.lang.Exception
     */
    public void leerContenido() throws Exception{
        try (BufferedReader br = new BufferedReader(new FileReader(pathEntrada))) {
            int index = 0;
            String tmp;
            while((tmp = br.readLine()) != null){
                lineas.add(tmp);
                instrucciones.add((separarLinea(lineas.get(index))));
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * Toma una frase y la separa en instruccion y parametros.
     * @param frase
     * @return String[]
     */
    private static String[] separarLinea  (String frase) {
        int indiceParentesis = frase.indexOf('(');
        
        String instruccion = frase.substring(0, indiceParentesis);
        String[] parametros = frase.substring(indiceParentesis + 1, frase.length() - 2).split(",", -1);
        
        
        for (int i = 0; i < parametros.length; i++) {
            if(parametros[i].equals("")){
                parametros[i] = "NULO";
            }else{
                if(parametros[i].charAt(0) == '\"'){
                parametros[i] = parametros[i].substring(1, parametros[i].length()-1);
            }
            }
            
        }
        
        for (String parametro : parametros) {
            if(parametro.charAt(0) == '\"'){
                parametro = parametro.substring(1, parametro.length()-1);
            }
        }
        
        String[] resultado = new String[parametros.length + 1];
        resultado[0] = instruccion;
        
        System.arraycopy(parametros, 0, resultado, 1, parametros.length);
        
        return resultado;
    }
    
    public boolean verificarInstrucciones(){
        for (int i = 0; i < instrucciones.size(); i++) {
            String[] parametros = instrucciones.get(i);
                switch(parametros[0]){
                        case "SOLICITUD" -> {
                            return esEntero(parametros[1]) && esFecha(parametros[2]) && esTipo(parametros[3]) && esDouble(parametros[5]);
                    }
                        case "LISTADO_SOLICITUDES" -> {
                            return true;
                    }
                        case "MOVIMIENTO" -> {
                            return esTarjeta(parametros[1]) && esFecha(parametros[2]) && esMovimiento(parametros[3]) && esDouble(parametros[6]);
                    }
                        case "CONSULTAR_TARJETA" -> {
                            return esTarjeta(parametros[1]);
                    }
                        case "AUTORIZACION_TARJETA" -> {
                            return esEntero(parametros[1]);
                    }
                        case "CANCELACION_TARJETA" -> {
                            return esTarjeta(parametros[1]);
                    }
                        case "ESTADO_CUENTA" -> {
                            return true;
                    }
                        case "LISTADO_TARJETAS" -> {
                            return true;
                    }
                        default -> {
                            return false;
                    }
                }
            }
        return false;
    }
    
    public static boolean esTarjeta(String parametro){
        // Expresión regular para el formato xxxx xxxx xxxx xxxx
        String regex = "\\d{4} \\d{4} \\d{4} \\d{4}";

        if (!parametro.matches(regex)) {
            return false;
        }

        for (int i = 0; i < parametro.length(); i++) {
            char c = parametro.charAt(i);
            if (!Character.isDigit(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }
    
    public static boolean esEntero(String parametro) {
    try {
        Integer.valueOf(parametro);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}

    
    public static boolean esFecha(String parametro) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    try {
        LocalDate.parse(parametro, formatter);
        return true;
    } catch (DateTimeParseException e) {
        return false;
    }
}
    
    public static boolean esTipo(String parametro) {
    return "NACIONAL".equals(parametro) || 
           "INTERNACIONAL".equals(parametro) || 
           "REGIONAL".equals(parametro);
}

    
    public static boolean esDouble(String parametro) {
    // Expresión regular para números con hasta 2 decimales
    String regex = "^[0-9]+(\\.[0-9]{1,2})?$";
    
    if (!parametro.matches(regex)) {
        return false;
    }

    try {
        Double.valueOf(parametro);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}

    
    public static boolean esMovimiento(String parametro){
        return "CARGO".equals(parametro) || 
           "ABONO".equals(parametro);
    }

    
    public ArchivoEntrada(String pathEntrada){
        this.pathEntrada = pathEntrada;
    }
    
}
