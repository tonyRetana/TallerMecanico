/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Presentacion;

import Entidades.*;
import Logica.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class frmVendedores extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmVendedores
     */
    DefaultTableModel modelo;
    public frmVendedores() {
        initComponents();
        try {
            cargarVendedores(" ACTIVO = 1 and IDENTIFICACION > 1");
            ListarFactura("where IDVENDEDOR=-1");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: "+e.getMessage());
        }
    }
    public void LimparTabla(){
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int colum){ return false; }
        };
        tblFacturas.setModel(modelo);
        modelo.addColumn("NUMFACTURA");
        modelo.addColumn("RESUMEN");
        modelo.addColumn("FECHAHORA");
        modelo.addColumn("TOTAL");
    }
    public void ListarFactura(String condicion)throws Exception{
        try {
            LogicaFactura logica = new LogicaFactura();
            ResultSet lista;
            LimparTabla();
            Object[] fila = new Object[4];
            lista= logica.ListarFactura(condicion);
            
            while (lista.next()) {                
                fila[0] = lista.getInt("NUMFACTURA");
                fila[1] = lista.getString("RESUMEN");
                fila[2] = lista.getDate("FECHAHORA");
                fila[3] = lista.getDouble("TOTAL");
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            throw e;
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
    public void InsertarVendedor(){
        try {
            LogicaVendedor logica = new LogicaVendedor();
            EntidadVendedor vendedor = new EntidadVendedor();
            vendedor.setActivo(true);
            vendedor.setIdentificacion(Integer.parseInt(txtID.getText()));
            vendedor.setNombre(txtNombre.getText());
            vendedor.setPrimerApellido(txtApellido1.getText());
            vendedor.setSegundoApellido(txtApellido2.getText());
            vendedor.setTelefono(txtTelefono.getText());
            vendedor.setCorreoElectronico(txtCorreo.getText());
            
            int resultado = logica.InsertarVendedor(vendedor);
            JOptionPane.showMessageDialog(this, logica.getMensaje()+"\n"+vendedor.MostrarPersona());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Llene todos los datos correctamente");
        }
    }
    public void LimpiarTabla(){
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int colum){ return false; }
        };
        tblVendedoresActivos.setModel(modelo);
        modelo.addColumn("Cedula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Primer");
        modelo.addColumn("Segundo");
        modelo.addColumn("Telefono");
        modelo.addColumn("Correo");
    }
    
    public void ActualizarVendedor(){
        EntidadVendedor vendedor = new EntidadVendedor();
        vendedor.setActivo(true);
        vendedor.setCorreoElectronico(txtCorreo.getText());
        vendedor.setIdentificacion(Integer.parseInt(txtID.getText()));
        vendedor.setNombre(txtNombre.getText());
        vendedor.setPrimerApellido(txtApellido1.getText());
        vendedor.setSegundoApellido(txtApellido2.getText());
        vendedor.setTelefono(txtTelefono.getText());
        
        try {
            LogicaVendedor logica = new LogicaVendedor();
            int resultado = logica.ActualizarVendedor(vendedor);
            JOptionPane.showMessageDialog(this, logica.getMensaje());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Llene todos los campos solicitados");
        }
    }
    public void BorrarVendedor(){
        EntidadVendedor vendedor = new EntidadVendedor();
        vendedor.setActivo(false);
        vendedor.setCorreoElectronico(txtCorreo.getText());
        vendedor.setIdentificacion(Integer.parseInt(txtID.getText()));
        vendedor.setNombre(txtNombre.getText());
        vendedor.setPrimerApellido(txtApellido1.getText());
        vendedor.setSegundoApellido(txtApellido2.getText());
        vendedor.setTelefono(txtTelefono.getText());
        
        try {
            LogicaVendedor logica = new LogicaVendedor();
            int resultado = logica.ActualizarVendedor(vendedor);
            JOptionPane.showMessageDialog(this, "Se Desactivo el vendedor");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtID = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellido1 = new javax.swing.JTextField();
        txtApellido2 = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVendedoresActivos = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btnBorrar = new javax.swing.JButton();
        btnActivar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFacturas = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        jLabel1.setText("Cedula");

        jLabel2.setText("Nombre");

        jLabel3.setText("1st Apellido");

        jLabel4.setText("2nd Apellido");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel5.setText("Telefono");

        jLabel6.setText("Correo Electronico");

        tblVendedoresActivos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblVendedoresActivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVendedoresActivosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblVendedoresActivos);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Lista de Vendedores Activos");

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnActivar.setText("Activar");
        btnActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAtualizar.setText("Actualizar");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        tblFacturas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblFacturas);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Facturas");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Vendedores");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(35, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnActivar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAtualizar)
                                .addGap(224, 224, 224)
                                .addComponent(btnLimpiar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(jLabel5)))
                                    .addGap(10, 10, 10)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnGuardar))
                                        .addComponent(jLabel6)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(84, 84, 84)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel1)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtApellido1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtApellido2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))))
                        .addGap(84, 84, 84)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(244, 244, 244)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(202, 202, 202))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActivar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnCancelar)
                    .addComponent(btnBorrar)
                    .addComponent(btnAtualizar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        InsertarVendedor();
        try {
            cargarVendedores(" ACTIVO = 1 and IDENTIFICACION > 1");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tblVendedoresActivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVendedoresActivosMouseClicked
        // TODO add your handling code here:
        try {
            if(evt.getClickCount() == 2){
            LogicaVendedor logica = new LogicaVendedor();
            EntidadVendedor vend = new EntidadVendedor();
            int filaSeleccionada = tblVendedoresActivos.getSelectedRow();
            if (filaSeleccionada != -1) {
                ListarFactura("where IDVENDEDOR="+tblVendedoresActivos.getValueAt(filaSeleccionada, 0).toString());
                txtID.setText(tblVendedoresActivos.getValueAt(filaSeleccionada, 0).toString());
                txtNombre.setText(tblVendedoresActivos.getValueAt(filaSeleccionada, 1).toString());
                txtApellido1.setText(tblVendedoresActivos.getValueAt(filaSeleccionada, 2).toString());
                txtApellido2.setText(tblVendedoresActivos.getValueAt(filaSeleccionada, 3).toString());
                txtCorreo.setText(tblVendedoresActivos.getValueAt(filaSeleccionada,5).toString());
                txtTelefono.setText(tblVendedoresActivos.getValueAt(filaSeleccionada, 4).toString());
            }
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
    }//GEN-LAST:event_tblVendedoresActivosMouseClicked

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        // TODO add your handling code here:
        ActualizarVendedor();
        try {
            cargarVendedores(" ACTIVO = 1 and IDENTIFICACION > 1");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Seleccione un vendedor");
        }
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
        BorrarVendedor();
        try {
            cargarVendedores(" ACTIVO = 1 and IDENTIFICACION > 1");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Seleccione un empleado");
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarActionPerformed
        // TODO add your handling code here:
        frmVendedoresActivar activar = new frmVendedoresActivar(null,true);
        activar.setVisible(true);
        try {
            cargarVendedores(" ACTIVO = 1 and IDENTIFICACION > 1");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnActivarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblFacturas;
    private javax.swing.JTable tblVendedoresActivos;
    private javax.swing.JTextField txtApellido1;
    private javax.swing.JTextField txtApellido2;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
