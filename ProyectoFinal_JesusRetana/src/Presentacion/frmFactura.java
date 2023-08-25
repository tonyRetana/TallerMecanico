/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Presentacion;

import Entidades.*;
import Logica.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author PC
 */
public class frmFactura extends javax.swing.JInternalFrame {
    
    private int idCliente;
    private int id;
    private double precio;
    private int cantidad;
    private int diferencia;
    private double precioNuevo;
    private int cantidadNueva;
    private double total;
    private String tipo;
    public void setId(int id) {
        this.id = id;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void setDiferecia(int diferencia) {
        this.cantidad = diferencia;
    }
    public void setPrecioNuevo(double precioNuevo) {
        this.precioNuevo = precioNuevo;
    }

    public void setCantidadNueva(int cantidadNueva) {
        this.cantidadNueva = cantidadNueva;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public int getCantidad() {
        return cantidad;
    }
    public double getTotal() {
        return total;
    }
       
    DefaultTableModel modelo;
    public frmFactura() {
        initComponents();
        try {
            CrearFactura();
            cargarServipro("");
            ListarDF(" NUMFACTURA="+txtNumFactura.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: "+e.getMessage());
        }
    }

    
    public void CrearFactura()throws Exception{
        int numFactura = 0;
        try {
            LogicaFactura logica = new LogicaFactura();
            numFactura = logica.InsertarProductoServicio();
            txtNumFactura.setText(numFactura+"");
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void LimpiarTabla(){
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int colum){ return false; }
        };
        tblListaServicios.setModel(modelo);
        modelo.addColumn("Codigo Barras");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Tipo");
    }
    
    public void cargarServipro(String condicion)throws Exception{
        try {
            LogicaServiPro logica = new LogicaServiPro();
            List<EntidadServiciosProductos> arreglo;
            LimpiarTabla();
            Object[] fila = new Object[5];
            arreglo= logica.ListarServiPro(condicion);
            for(EntidadServiciosProductos pro : arreglo){
                fila[0] = pro.getCodigoBarras();
                fila[1] = pro.getNombre();
                fila[2] = pro.getPrecio();
                fila[3] = pro.getDescripcion();
                fila[4] = pro.getTipo();
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    public void CrearStringConDatos(){
        double totalFinal=0;
        int maximo = tblListaFactura.getRowCount();
        if (maximo != -1) {
            for (int i = 0; i < maximo; i++) {
                
                totalFinal+=Double.parseDouble(tblListaFactura.getValueAt(i, 3).toString());
            }
        }
        totalFinal=(totalFinal*0.13)+totalFinal;
        txtTotal.setText(Double.toString(totalFinal));
    }
    public void InsertarDetalleFactura(){
        try {
            LogicaDetalle logica= new LogicaDetalle();
            EntidadDetalleFactura detalle = new EntidadDetalleFactura();
            
            detalle.setCantidad(Integer.parseInt(cboCantidadIngresar.getSelectedItem().toString()));
            detalle.setCodBarra(Integer.parseInt(txtCodigoBarrasAgregar.getText()));
            detalle.setCodFactura(Integer.parseInt(txtNumFactura.getText()));
            detalle.setTotal(detalle.getCantidad() * precio);
            
            int resultado = logica.InsertarDetalleFactura(detalle);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    public void LimparTabla(){
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int colum){ return false; }
        };
        tblListaFactura.setModel(modelo);
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Total");
    }
    public void ListarDF(String condicion)throws Exception{
        try {
            LogicaDetalle logica = new LogicaDetalle();
            ResultSet lista;
            LimparTabla();
            Object[] fila = new Object[4];
            lista= logica.ListarDF(condicion);
            
            while (lista.next()) {                
                fila[0] = lista.getInt("ID_DETALLE");
                fila[1] = lista.getString("Nombre");
                fila[2] = lista.getString("Cantidad");
                fila[3] = lista.getString("Total");
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void ActualizarInventario(int id, int cantidad){
        try {
            LogicaInventario logica = new LogicaInventario();
            int resultado = logica.ActualizarInventario(id, cantidad);
            String mensaje = logica.getMensaje();
            if(mensaje.equals("Transacción exitosa")){
                InsertarDetalleFactura();
            }
            else{
                JOptionPane.showMessageDialog(this, logica.getMensaje());
            }
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

        txtCedulaVendedor1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnVendedor1 = new javax.swing.JButton();
        cboCantidad = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtCedulaVendedor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCedulaCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNumFactura = new javax.swing.JTextField();
        btnVendedor = new javax.swing.JButton();
        btnCliente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListaFactura = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtDetalle = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtResumen = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnDetalle = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblListaServicios = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        txtNombreServiPro = new javax.swing.JTextField();
        Buscar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtCodigoBarrasAgregar = new javax.swing.JTextField();
        cboCantidad1 = new javax.swing.JComboBox<>();
        cboCantidadIngresar = new javax.swing.JComboBox<>();
        btnBorrar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();

        jLabel3.setText("Id.Vendedor");

        btnVendedor1.setText("Seleccionar");

        cboCantidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100" }));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Sistema de Facturas");

        txtCedulaVendedor.setEnabled(false);

        jLabel2.setText("Id.Vendedor:");

        txtCedulaCliente.setEnabled(false);

        jLabel4.setText("Id.Cliente:");

        jLabel5.setText("Factura N.");

        txtNumFactura.setEnabled(false);

        btnVendedor.setText("Seleccionar");
        btnVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendedorActionPerformed(evt);
            }
        });

        btnCliente.setText("Seleccionar");
        btnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteActionPerformed(evt);
            }
        });

        tblListaFactura.setModel(new javax.swing.table.DefaultTableModel(
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
        tblListaFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListaFacturaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListaFactura);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Lista:");

        txtDetalle.setEnabled(false);

        jLabel7.setText("Id. Detalle:");

        jLabel8.setText("Cantidad:");

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        txtResumen.setColumns(20);
        txtResumen.setRows(5);
        jScrollPane2.setViewportView(txtResumen);

        jLabel9.setText("Otros Detalles:");

        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnDetalle.setText("Agregar Detalle");
        btnDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalleActionPerformed(evt);
            }
        });

        tblListaServicios.setModel(new javax.swing.table.DefaultTableModel(
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
        tblListaServicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListaServiciosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblListaServicios);

        jLabel10.setText("Buscar por nombre:");

        Buscar.setText("Buscar");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });

        jLabel11.setText("Codigo de Barras:");

        txtCodigoBarrasAgregar.setEnabled(false);

        cboCantidad1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100" }));

        cboCantidadIngresar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100" }));

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        jLabel12.setText("Total con IVA:");

        txtTotal.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreServiPro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Buscar))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCodigoBarrasAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cboCantidadIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDetalle)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnImprimir)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCancelar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cboCantidad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnBorrar))
                                    .addComponent(jScrollPane2))
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(237, 237, 237))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCedulaVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(btnVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCedulaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(btnCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNumFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(263, 263, 263))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(txtNumFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCedulaVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtCedulaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVendedor)
                    .addComponent(btnCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtNombreServiPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Buscar)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(btnActualizar)
                            .addComponent(cboCantidad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBorrar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnCancelar)
                                .addComponent(btnImprimir))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)))
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtCodigoBarrasAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDetalle)
                            .addComponent(cboCantidadIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(15, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        try {
            int maximo = tblListaFactura.getRowCount();
            if (maximo != -1) {
                for (int i = 0; i < maximo; i++) {
                    int codigo =Integer.parseInt(tblListaFactura.getValueAt(i, 0).toString());
                    int cant = Integer.parseInt(tblListaFactura.getValueAt(i, 2).toString());
                    cant = cant-cant-cant;
                    ActualizarDetalle(codigo, 0, cant, 0);
                    LogicaDetalle logica = new LogicaDetalle();
                    int resultado = logica.EliminarDetalle(codigo);
                }
            }
            ListarDF(" NUMFACTURA="+txtNumFactura.getText());
            LogicaFactura logic = new LogicaFactura();
            int resultado = logic.EliminarFactura(Integer.parseInt(txtNumFactura.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tblListaServiciosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListaServiciosMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
            LogicaServiPro logica = new LogicaServiPro();
            EntidadServiciosProductos vend = new EntidadServiciosProductos();
            int filaSeleccionada = tblListaServicios.getSelectedRow();
            if (filaSeleccionada != -1) {
                txtCodigoBarrasAgregar.setText(tblListaServicios.getValueAt(filaSeleccionada, 0).toString());
                precio = Double.parseDouble(tblListaServicios.getValueAt(filaSeleccionada, 2).toString());
                tipo = tblListaServicios.getValueAt(filaSeleccionada,4).toString();
            }
        }
    }//GEN-LAST:event_tblListaServiciosMouseClicked

    private void btnDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleActionPerformed
        // TODO add your handling code here:
        if (tipo.equals("PRODUCTO")){
            int cantidad = Integer.parseInt(cboCantidadIngresar.getSelectedItem().toString());
            int id = Integer.parseInt(txtCodigoBarrasAgregar.getText());
            ActualizarInventario(id, cantidad);
            try {
                ListarDF(" NUMFACTURA="+txtNumFactura.getText());
                CrearStringConDatos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
        else if(tipo.equals("SERVICIO")){
            try {
                InsertarDetalleFactura();
                ListarDF(" NUMFACTURA="+txtNumFactura.getText());
                CrearStringConDatos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Seleccione Producto o Servicio");
            }
        }        
    }//GEN-LAST:event_btnDetalleActionPerformed

    private void btnVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendedorActionPerformed
        // TODO add your handling code here:
        frmListarEmpleados frm;
        try {
            frm = new frmListarEmpleados(null, true);
            frm.setVisible(true);
            setId(frm.getId());
            txtCedulaVendedor.setText(Integer.toString(id));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
        
    }//GEN-LAST:event_btnVendedorActionPerformed

    private void btnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteActionPerformed
        // TODO add your handling code here:
        frmListarClientes frm;
        try {
            frm = new frmListarClientes(null, true);
            frm.setVisible(true);
            setIdCliente(frm.getId());
            txtCedulaCliente.setText(Integer.toString(idCliente));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnClienteActionPerformed

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        // TODO add your handling code here:
        try {
            String nombre = txtNombreServiPro.getText();
            cargarServipro(" NOMBRE LIKE '%"+txtNombreServiPro.getText()+"%'");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: "+e.getMessage());
        }
    }//GEN-LAST:event_BuscarActionPerformed

    private void tblListaFacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListaFacturaMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
            LogicaServiPro logica = new LogicaServiPro();
            EntidadServiciosProductos vend = new EntidadServiciosProductos();
            int filaSeleccionada = tblListaFactura.getSelectedRow();
            if (filaSeleccionada != -1) {
                txtDetalle.setText(tblListaFactura.getValueAt(filaSeleccionada, 0).toString());
                setCantidad(Integer.parseInt(tblListaFactura.getValueAt(filaSeleccionada, 2).toString()));
                
                setTotal(Double.parseDouble(tblListaFactura.getValueAt(filaSeleccionada, 3).toString()));
            }
        }
    }//GEN-LAST:event_tblListaFacturaMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        int cant = Integer.parseInt(cboCantidad1.getSelectedItem().toString());
        int diferencia = ((Integer.parseInt(cboCantidad1.getSelectedItem().toString()))-cantidad);
        int id_Detalle = Integer.parseInt(txtDetalle.getText());
        
        double price = (cant*getTotal())/getCantidad();
        try {
            ActualizarDetalle(id_Detalle, price, diferencia, cant);
            cargarServipro("");
            ListarDF(" NUMFACTURA="+txtNumFactura.getText());
            CrearStringConDatos();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(this, "Seleccione de la lista un producto o servicio");
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
        try {
            int codigo = Integer.parseInt(txtDetalle.getText());
            int cant = getCantidad();
            cant = cant-cant-cant;
            ActualizarDetalle(codigo, 0, cant, 0);
            LogicaDetalle logica = new LogicaDetalle();
            int resultado = logica.EliminarDetalle(codigo);
            ListarDF(" NUMFACTURA="+txtNumFactura.getText());
            CrearStringConDatos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Seleccione de la lista un producto o servicio");
        }
        
    }//GEN-LAST:event_btnBorrarActionPerformed

    public void ImprimirFactura() {
        String lista = "";
        double totalFinal = 0;
        int maximo = tblListaFactura.getRowCount();

        // Tu código existente aquí

        // Agregar detalles adicionales
        String nombreEmpresa = "Servicios Automotrices Retana";
        String fechaFactura = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String idVendedor = txtCedulaVendedor.getText();
        String idCliente = txtCedulaCliente.getText();
        String numeroFactura = txtNumFactura.getText();
        String resumen = txtResumen.getText();

        lista = "Productos y Servicios Taller Mecanico Retana\n"+
                "     Factura N° " + numeroFactura + "\n"
                + "     Fecha: " + fechaFactura + "\n"
                + "     Vendedor: " + idVendedor + "\n"
                + "     Cliente: " + idCliente + "\n"
                + "     Resumen: \n" + resumen + "\n"
                + "=============================================\n"
                + lista;

        // Resto de tu código aquí

        try {
            if (maximo != -1) {
                for (int i = 0; i < maximo; i++) {
                    lista=lista+tblListaFactura.getValueAt(i, 1).toString() + "       " + tblListaFactura.getValueAt(i, 2).toString() + "       " + tblListaFactura.getValueAt(i, 3).toString()+ "\n";
                    totalFinal+=Double.parseDouble(tblListaFactura.getValueAt(i, 3).toString());
                }
            }
            lista=lista+"\n=============================================\nTotal: "+Double.toString(totalFinal);
            LogicaVenta logica = new LogicaVenta();
            Ventas venta = new Ventas();
            venta.setNumeroFactura(Integer.parseInt(txtNumFactura.getText()));
            venta.setTotalVenta(totalFinal);

            int resultado = logica.InsertarProductoServicio(venta);
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            PDFont font = PDType1Font.HELVETICA;

            float fontSize = 12;
            float leading = 1.5f * fontSize;

            PDRectangle mediabox = page.getMediaBox();
            float margin = 50;
            float width = mediabox.getWidth() - 2 * margin;
            float startX = mediabox.getLowerLeftX() + margin;
            float startY = mediabox.getUpperRightY() - margin;

            contentStream.beginText();
            contentStream.setFont(font, fontSize);
            contentStream.newLineAtOffset(startX, startY);

            String[] lines = lista.split("\n");
            for (String line : lines) {
                contentStream.showText(line);
                contentStream.newLineAtOffset(0, -leading);
            }

            contentStream.endText();
            contentStream.close();

            String userHome = System.getProperty("user.home");
            document.save(userHome + "\\" + txtNumFactura.getText() + " factura.pdf");
            document.close();

            JOptionPane.showMessageDialog(null, "PDF Guardado! Ruta: " + userHome + "\\" + txtNumFactura.getText() + " factura.pdf");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
        }
        this.dispose();
    }
    
    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
        double totalF = Double.parseDouble(txtTotal.getText());
        totalF = (totalF*0.13)+totalF;
        try {
            EntidadFactura factura = new EntidadFactura();
            factura.setIdCliente(Integer.parseInt(txtCedulaCliente.getText()));
            factura.setIdVendedor(Integer.parseInt(txtCedulaVendedor.getText()));
            factura.setTotal(totalF);
            factura.setResumen(txtResumen.getText());
            factura.setNumeroFactura(Integer.parseInt(txtNumFactura.getText()));
            LogicaFactura logica = new LogicaFactura();
            
            int resultado = logica.ActualizarFactura(factura);
            ImprimirFactura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Llene todos los campos");
        }
    }//GEN-LAST:event_btnImprimirActionPerformed
    public int ActualizarDetalle(int id, double total, int cantidad, int cantidadDe) throws Exception{
        int resultado = -1;
        try {
            LogicaDetalle logica = new LogicaDetalle();
            resultado = logica.ActualizarDetalle(id, total, cantidad, cantidadDe);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCliente;
    private javax.swing.JButton btnDetalle;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnVendedor;
    private javax.swing.JButton btnVendedor1;
    private javax.swing.JComboBox<String> cboCantidad;
    private javax.swing.JComboBox<String> cboCantidad1;
    private javax.swing.JComboBox<String> cboCantidadIngresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblListaFactura;
    private javax.swing.JTable tblListaServicios;
    private javax.swing.JTextField txtCedulaCliente;
    private javax.swing.JTextField txtCedulaVendedor;
    private javax.swing.JTextField txtCedulaVendedor1;
    private javax.swing.JTextField txtCodigoBarrasAgregar;
    private javax.swing.JTextField txtDetalle;
    private javax.swing.JTextField txtNombreServiPro;
    private javax.swing.JTextField txtNumFactura;
    private javax.swing.JTextArea txtResumen;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
