/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.gestorcc.frontend;

import com.mycompany.gestorcc.frontend.gestiones.*;
import com.mycompany.gestorcc.frontend.reportes.*;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author mynordma
 */
public class FramePrincipal extends javax.swing.JFrame {
    private String destinoPath = System.getProperty("user.dir");
    /**
     * Creates new form FramePrincipal
     */
    public FramePrincipal() {
        initComponents();
        this.setSize(1100, 500);
        this.setLocationRelativeTo(null);
        this.setTitle("Gestion Tarjetas");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        cargarBtn = new javax.swing.JMenu();
        destinoBtn = new javax.swing.JMenu();
        solicitudBtn = new javax.swing.JMenu();
        movimientoBtn = new javax.swing.JMenu();
        consultaBtn = new javax.swing.JMenu();
        autorizacionBtn = new javax.swing.JMenu();
        cancelacionBtn = new javax.swing.JMenu();
        estadoBtn = new javax.swing.JMenu();
        listadoTBtn = new javax.swing.JMenu();
        listadoSBtn = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jDesktopPane1.setBackground(new java.awt.Color(85, 124, 162));
        jDesktopPane1.setPreferredSize(new java.awt.Dimension(1100, 552));

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 552, Short.MAX_VALUE)
        );

        cargarBtn.setText("Cargar Archivo");
        cargarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cargarBtnMouseClicked(evt);
            }
        });
        jMenuBar1.add(cargarBtn);

        destinoBtn.setText("Carpeta Destino");
        destinoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                destinoBtnMouseClicked(evt);
            }
        });
        jMenuBar1.add(destinoBtn);

        solicitudBtn.setText("Solicitud");
        solicitudBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                solicitudBtnMouseClicked(evt);
            }
        });
        jMenuBar1.add(solicitudBtn);

        movimientoBtn.setText("Movimiento");
        movimientoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                movimientoBtnMouseClicked(evt);
            }
        });
        jMenuBar1.add(movimientoBtn);

        consultaBtn.setText("Consulta");
        consultaBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                consultaBtnMouseClicked(evt);
            }
        });
        jMenuBar1.add(consultaBtn);

        autorizacionBtn.setText("Autorizacion");
        autorizacionBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                autorizacionBtnMouseClicked(evt);
            }
        });
        jMenuBar1.add(autorizacionBtn);

        cancelacionBtn.setText("Cancelacion");
        cancelacionBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelacionBtnMouseClicked(evt);
            }
        });
        jMenuBar1.add(cancelacionBtn);

        estadoBtn.setText("Estado De Cuenta");
        estadoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                estadoBtnMouseClicked(evt);
            }
        });
        jMenuBar1.add(estadoBtn);

        listadoTBtn.setText("Ver Tarjetas");
        listadoTBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listadoTBtnMouseClicked(evt);
            }
        });
        jMenuBar1.add(listadoTBtn);

        listadoSBtn.setText("Ver Solicitudes");
        listadoSBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listadoSBtnMouseClicked(evt);
            }
        });
        jMenuBar1.add(listadoSBtn);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cargarBtnMouseClicked
        FrameCargarArchivo frameArchivo = new FrameCargarArchivo(destinoPath);
        jDesktopPane1.add(frameArchivo);
        frameArchivo.setVisible(true);
    }//GEN-LAST:event_cargarBtnMouseClicked

    private void solicitudBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_solicitudBtnMouseClicked
        FrameSolicitud frame = new FrameSolicitud();
        jDesktopPane1.add(frame);
        frame.setVisible(true);
    }//GEN-LAST:event_solicitudBtnMouseClicked

    private void movimientoBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movimientoBtnMouseClicked
        FrameMovimiento frame = new FrameMovimiento();
        jDesktopPane1.add(frame);
        frame.setVisible(true);
    }//GEN-LAST:event_movimientoBtnMouseClicked

    private void consultaBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consultaBtnMouseClicked
        FrameConsulta frame = new FrameConsulta(jDesktopPane1);
        jDesktopPane1.add(frame);
        frame.setVisible(true);
    }//GEN-LAST:event_consultaBtnMouseClicked

    private void autorizacionBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_autorizacionBtnMouseClicked
        FrameAutorizacion frame = new FrameAutorizacion();
        jDesktopPane1.add(frame);
        frame.setVisible(true);
    }//GEN-LAST:event_autorizacionBtnMouseClicked

    private void cancelacionBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelacionBtnMouseClicked
        FrameCancelacion frame = new FrameCancelacion();
        jDesktopPane1.add(frame);
        frame.setVisible(true);
    }//GEN-LAST:event_cancelacionBtnMouseClicked

    private void estadoBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_estadoBtnMouseClicked
        FrameEstadoCuenta frame = new FrameEstadoCuenta(jDesktopPane1);
        jDesktopPane1.add(frame);
        frame.setVisible(true);
    }//GEN-LAST:event_estadoBtnMouseClicked

    private void listadoTBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listadoTBtnMouseClicked
        ListadoTarjetas frame = new ListadoTarjetas(jDesktopPane1);
        jDesktopPane1.add(frame);
        frame.setVisible(true);
    }//GEN-LAST:event_listadoTBtnMouseClicked

    private void listadoSBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listadoSBtnMouseClicked
        ListadoSolicitudes frame = new ListadoSolicitudes(jDesktopPane1);
        jDesktopPane1.add(frame);
        frame.setVisible(true);
    }//GEN-LAST:event_listadoSBtnMouseClicked

    private void destinoBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_destinoBtnMouseClicked
        //Guardando la ruta de la carpeta destino

        JFileChooser directorioChooser = new JFileChooser();
    
        directorioChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = directorioChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File directorio = directorioChooser.getSelectedFile();
            destinoPath = directorio.getAbsolutePath();
        }
    }//GEN-LAST:event_destinoBtnMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu autorizacionBtn;
    private javax.swing.JMenu cancelacionBtn;
    private javax.swing.JMenu cargarBtn;
    private javax.swing.JMenu consultaBtn;
    private javax.swing.JMenu destinoBtn;
    private javax.swing.JMenu estadoBtn;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu listadoSBtn;
    private javax.swing.JMenu listadoTBtn;
    private javax.swing.JMenu movimientoBtn;
    private javax.swing.JMenu solicitudBtn;
    // End of variables declaration//GEN-END:variables
}
