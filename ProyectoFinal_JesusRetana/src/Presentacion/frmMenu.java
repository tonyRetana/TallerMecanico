/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class frmMenu extends javax.swing.JFrame {

    /**
     * Creates new form frmMenu
     */
    
    public frmMenu() {
        initComponents();
        this.setExtendedState(frmMenu.MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        mnuBarra = new javax.swing.JMenuBar();
        mnuTaller = new javax.swing.JMenu();
        mnuVendedores = new javax.swing.JMenuItem();
        mnuInventario = new javax.swing.JMenuItem();
        mnuServi = new javax.swing.JMenuItem();
        mnuLibro = new javax.swing.JMenuItem();
        mnuFacturacion = new javax.swing.JMenu();
        mnuClientes = new javax.swing.JMenuItem();
        mnuFactura = new javax.swing.JMenuItem();
        mnuSalir = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2742, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 834, Short.MAX_VALUE)
        );

        mnuBarra.setPreferredSize(new java.awt.Dimension(100, 23));

        mnuTaller.setText("Taller");

        mnuVendedores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuVendedores.setText("Vendedores");
        mnuVendedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuVendedoresActionPerformed(evt);
            }
        });
        mnuTaller.add(mnuVendedores);

        mnuInventario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuInventario.setText("Inventario");
        mnuInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuInventarioActionPerformed(evt);
            }
        });
        mnuTaller.add(mnuInventario);

        mnuServi.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuServi.setText("Servicios");
        mnuServi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuServiActionPerformed(evt);
            }
        });
        mnuTaller.add(mnuServi);

        mnuLibro.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuLibro.setText("Libro de Cuentas");
        mnuLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLibroActionPerformed(evt);
            }
        });
        mnuTaller.add(mnuLibro);

        mnuBarra.add(mnuTaller);

        mnuFacturacion.setText("Facturacion");

        mnuClientes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuClientes.setText("Clientes");
        mnuClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuClientesActionPerformed(evt);
            }
        });
        mnuFacturacion.add(mnuClientes);

        mnuFactura.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnuFactura.setText("Sistema de Ventas");
        mnuFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuFacturaActionPerformed(evt);
            }
        });
        mnuFacturacion.add(mnuFactura);

        mnuBarra.add(mnuFacturacion);

        mnuSalir.setText("Salir");
        mnuBarra.add(mnuSalir);

        setJMenuBar(mnuBarra);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(escritorio)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(escritorio)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuVendedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuVendedoresActionPerformed
        // TODO add your handling code here:
        frmVendedores frVende;
        try {
            frVende = new frmVendedores();
            escritorio.add(frVende);
        
            Dimension size = escritorio.getSize();

            Dimension sizeInter = frVende.getSize();

            frVende.setLocation(((int)size.getWidth() - (int)sizeInter.getWidth())/2,
                    ((int)size.getHeight()-(int)sizeInter.getHeight())/2);

            frVende.show();
        } catch (Exception ex) {
            Logger.getLogger(frmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mnuVendedoresActionPerformed

    private void mnuInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuInventarioActionPerformed
        frmInventario frInve;
        try {
            frInve = new frmInventario();
            escritorio.add(frInve);
        
            Dimension size = escritorio.getSize();

            Dimension sizeInter = frInve.getSize();

            frInve.setLocation(((int)size.getWidth() - (int)sizeInter.getWidth())/2,
                    ((int)size.getHeight()-(int)sizeInter.getHeight())/2);

            frInve.show();
        } catch (Exception ex) {
            Logger.getLogger(frmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mnuInventarioActionPerformed

    private void mnuServiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuServiActionPerformed
        frmServicios frSer;
        try {
            frSer = new frmServicios();
            escritorio.add(frSer);
        
            Dimension size = escritorio.getSize();

            Dimension sizeInter = frSer.getSize();

            frSer.setLocation(((int)size.getWidth() - (int)sizeInter.getWidth())/2,
                    ((int)size.getHeight()-(int)sizeInter.getHeight())/2);

            frSer.show();
        } catch (Exception ex) {
            Logger.getLogger(frmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mnuServiActionPerformed

    private void mnuClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuClientesActionPerformed
        // TODO add your handling code here:
        frmClientes frtCl;
        try {
            frtCl = new frmClientes();
            escritorio.add(frtCl);
        
            Dimension size = escritorio.getSize();

            Dimension sizeInter = frtCl.getSize();

            frtCl.setLocation(((int)size.getWidth() - (int)sizeInter.getWidth())/2,
                    ((int)size.getHeight()-(int)sizeInter.getHeight())/2);

            frtCl.show();
        } catch (Exception ex) {
            Logger.getLogger(frmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mnuClientesActionPerformed

    private void mnuFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuFacturaActionPerformed
        // TODO add your handling code here:
        frmFactura frFac;
        try {
            frFac = new frmFactura();
            escritorio.add(frFac);
        
            Dimension size = escritorio.getSize();

            Dimension sizeInter = frFac.getSize();

            frFac.setLocation(((int)size.getWidth() - (int)sizeInter.getWidth())/2,
                    ((int)size.getHeight()-(int)sizeInter.getHeight())/2);

            frFac.show();
        } catch (Exception ex) {
            Logger.getLogger(frmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mnuFacturaActionPerformed

    private void mnuLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLibroActionPerformed
        // TODO add your handling code here:
        frmComprasVentas fr;
        try {
            fr = new frmComprasVentas();
            escritorio.add(fr);
        
            Dimension size = escritorio.getSize();

            Dimension sizeInter = fr.getSize();

            fr.setLocation(((int)size.getWidth() - (int)sizeInter.getWidth())/2,
                    ((int)size.getHeight()-(int)sizeInter.getHeight())/2);

            fr.show();
        } catch (Exception ex) {
            Logger.getLogger(frmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mnuLibroActionPerformed
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenuBar mnuBarra;
    private javax.swing.JMenuItem mnuClientes;
    private javax.swing.JMenuItem mnuFactura;
    private javax.swing.JMenu mnuFacturacion;
    private javax.swing.JMenuItem mnuInventario;
    private javax.swing.JMenuItem mnuLibro;
    private javax.swing.JMenu mnuSalir;
    private javax.swing.JMenuItem mnuServi;
    private javax.swing.JMenu mnuTaller;
    private javax.swing.JMenuItem mnuVendedores;
    // End of variables declaration//GEN-END:variables
}
