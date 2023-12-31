/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Presentacion;

import Entidades.EntidadVendedor;
import Logica.LogicaVendedor;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class frmVendedoresActivar extends javax.swing.JDialog {

    /**
     * Creates new form frmVendedoresActivar
     */
    DefaultTableModel modelo;
    String nombre;
    String apellido1;
    String apellido2;
    String telefono;
    String correo;
    
    public frmVendedoresActivar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        	
        this.setLocationRelativeTo(parent);
        try {
            cargarVendedores(" ACTIVO = 0 and IDENTIFICACION > 1");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: "+e.getMessage());
        }
    }
    public void cargarVendedores(String condicion)throws Exception{
        try {
            LogicaVendedor logica = new LogicaVendedor();
            List<EntidadVendedor> arreglo;
            LimpiarTabla();
            Object[] fila = new Object[6];
            arreglo= logica.ListarVendedores(condicion);
            for(EntidadVendedor vend : arreglo){
                fila[0] = vend.getIdentificacion();
                fila[1] = vend.getNombre();
                fila[2] = vend.getPrimerApellido();
                fila[3] = vend.getSegundoApellido();
                fila[4] = vend.getTelefono();
                fila[5] = vend.getCorreoElectronico();
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    public void LimpiarTabla(){
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int colum){ return false; }
        };
        tblVendedoreInactivos.setModel(modelo);
        modelo.addColumn("Cedula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Primer");
        modelo.addColumn("Segundo");
        modelo.addColumn("Telefono");
        modelo.addColumn("Correo");
    }
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVendedoreInactivos = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnActivar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Activar Vendedores");

        tblVendedoreInactivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblVendedoreInactivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVendedoreInactivosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblVendedoreInactivos);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel2.setText("Buscar por Nombre:");

        jLabel3.setText("Cedula:");

        txtCedula.setEnabled(false);

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnActivar.setText("Activar");
        btnActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(78, 78, 78))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnActivar))
                            .addComponent(txtNombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(btnActivar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        try {
            String nombre = txtNombre.getText();
            cargarVendedores(" ACTIVO = 0 and IDENTIFICACION > 1 and NOMBRE LIKE '%"+txtNombre.getText()+"%'");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: "+e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tblVendedoreInactivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVendedoreInactivosMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
            int filaSeleccionada = tblVendedoreInactivos.getSelectedRow();
            if (filaSeleccionada != -1) {
                txtCedula.setText(tblVendedoreInactivos.getValueAt(filaSeleccionada, 0).toString());
                nombre =(tblVendedoreInactivos.getValueAt(filaSeleccionada, 1).toString());
                apellido1 = (tblVendedoreInactivos.getValueAt(filaSeleccionada, 2).toString());
                apellido2 = (tblVendedoreInactivos.getValueAt(filaSeleccionada, 3).toString());
                correo = (tblVendedoreInactivos.getValueAt(filaSeleccionada,5).toString());
                telefono = (tblVendedoreInactivos.getValueAt(filaSeleccionada, 4).toString());
            }
        }
    }//GEN-LAST:event_tblVendedoreInactivosMouseClicked

    private void btnActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarActionPerformed
        // TODO add your handling code here:
        EntidadVendedor vendedor = new EntidadVendedor();
        vendedor.setActivo(true);
        vendedor.setCorreoElectronico(correo);
        vendedor.setIdentificacion(Integer.parseInt(txtCedula.getText()));
        vendedor.setNombre(nombre);
        vendedor.setPrimerApellido(apellido1);
        vendedor.setSegundoApellido(apellido2);
        vendedor.setTelefono(telefono);
        
        try {
            LogicaVendedor logica = new LogicaVendedor();
            int resultado = logica.ActualizarVendedor(vendedor);
            JOptionPane.showMessageDialog(this, "Se activo el Vendedor");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        this.dispose();
    }//GEN-LAST:event_btnActivarActionPerformed

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
            java.util.logging.Logger.getLogger(frmVendedoresActivar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmVendedoresActivar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmVendedoresActivar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmVendedoresActivar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmVendedoresActivar dialog = new frmVendedoresActivar(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblVendedoreInactivos;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
