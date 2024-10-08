/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.gestorcc.frontend.gestiones;

import com.mycompany.gestorcc.backend.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author mynordma
 */
public class FrameCargarArchivo extends javax.swing.JInternalFrame {
    
    private String filePath = "";
    private String destinoPath = "";
    private final JInternalFrame componente = this;

    /**
     * Creates new form FrameCargarArchivo
     * @param destinoPath
     */
    public FrameCargarArchivo(String destinoPath) {
        initComponents();
        this.destinoPath = destinoPath;
        this.setTitle("Cargar Archivo");
        this.setSize(500, 300);
        this.setClosable(true);
        this.setIconifiable(true);
        this.setMaximizable(true);
        this.setResizable(true);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        seleccionarBtn = new javax.swing.JButton();
        confirmarBtn = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        seleccionarBtn.setText("Seleccionar Archivo");
        seleccionarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarBtnActionPerformed(evt);
            }
        });

        confirmarBtn.setText("Confirmar");
        confirmarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Velocidad Procesamiento (ms)");

        jLabel2.setText("Logs");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(seleccionarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(129, 129, 129))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(confirmarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(46, 46, 46))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(seleccionarBtn)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(confirmarBtn)
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void seleccionarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarBtnActionPerformed
        //Guardando la ruta del archivo de entrada
        
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            filePath = archivoSeleccionado.getAbsolutePath();
            System.out.println("Path archivo: " + filePath);
        }
        
    }//GEN-LAST:event_seleccionarBtnActionPerformed

    private void confirmarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarBtnActionPerformed
        
        String text = jTextField1.getText();

        try {
            int milisegundos = Integer.parseInt(text);

            jProgressBar1.setMaximum(milisegundos);
            
            ArchivoEntrada archivo = new ArchivoEntrada(filePath);
            try {
                archivo.leerContenido();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Hay un error de sintaxis en tu archivo de entrada", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if(!archivo.verificarInstrucciones()){
                JOptionPane.showMessageDialog(this, "Hay un error de sintaxis en tu archivo de entrada", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Gestion gestion;
            
            seleccionarBtn.setEnabled(false);
            confirmarBtn.setEnabled(false);
            
            for (int i = 0; i < archivo.getInstrucciones().size(); i++){
                gestion = new Gestion(archivo.getInstrucciones().get(i), destinoPath, false);
            }
            
        
            Timer timer = new Timer(1, new ActionListener() {
                private int progreso = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (progreso < milisegundos) {
                        progreso++;
                        jProgressBar1.setValue(progreso);
                    } else {
                        ((Timer)e.getSource()).stop(); 
                        seleccionarBtn.setEnabled(true);
                        confirmarBtn.setEnabled(true);
                        JOptionPane.showMessageDialog(componente, "Completado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });
            timer.start();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "No se pudo completar la accion, revise sus entradas", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_confirmarBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton confirmarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton seleccionarBtn;
    // End of variables declaration//GEN-END:variables
}
