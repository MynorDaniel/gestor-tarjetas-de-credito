/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestorcc.backend;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author mynordma
 */
public class ArchivoEntrada {
    
    private final String pathEntrada;
    private final String[] lineas = new String[10];
    List<String[]> instrucciones = new ArrayList<>();
    
    public ArchivoEntrada(String pathEntrada){
        this.pathEntrada = pathEntrada;
    }
    
    public void leerContenido() {
        try (BufferedReader br = new BufferedReader(new FileReader(pathEntrada))) {
            int index = 0;
            while ((lineas[index] = br.readLine()) != null){
                instrucciones.add(separarLinea(lineas[index]));
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        imprimirElementos(instrucciones);
    }
    
   
    
    private String[] separarLinea(String frase) {
        int indiceParentesis = frase.indexOf('(');
        
        String instruccion = frase.substring(0, indiceParentesis).trim();
        String[] parametros = frase.substring(indiceParentesis + 1, frase.length() - 2).trim().split(",");
        
        for (int i = 0; i < parametros.length; i++) {
            if(parametros[i].charAt(0) == '\"'){
                parametros[i] = parametros[i].substring(1, parametros[i].length()-1);
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

    public static void imprimirElementos(List<String[]> listaDeArreglos) {
        for (int i = 0; i < listaDeArreglos.size(); i++) {
            System.out.println("Arreglo " + i + ":");
            String[] arreglo = listaDeArreglos.get(i);
            for (String elemento : arreglo) {
                System.out.println("  " + elemento);
            }
        }
    }
    
}
