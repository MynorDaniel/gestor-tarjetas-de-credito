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
     */
    public void leerContenido() {
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
        
        //imprimirElementos(instrucciones);
    }
    
    /**
     * Toma una frase y la separa en instruccion y parametros.
     * @param frase
     * @return String[]
     */
    private static String[] separarLinea(String frase) {
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
    
    public ArchivoEntrada(String pathEntrada){
        this.pathEntrada = pathEntrada;
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
