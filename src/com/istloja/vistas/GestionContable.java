/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.vistas;

import com.istloja.ModelPersona.ComunicacionVistasModelosTablas;
import com.istloja.ModelPersona.ModelTablaInventario;
import com.istloja.ModelPersona.ModelTablaPersona;
import com.istloja.ModelPersona.ModelTableProveedores;
import com.istloja.conexion.Conexion;
import com.istloja.controlador.Inventariobd;
import com.istloja.controlador.Personabd;
import com.istloja.controlador.Proveedoresbd;
import com.istloja.modelo.Inventario;
import com.istloja.modelo.Persona;
import com.istloja.modelo.Proveedores;
import com.istloja.utilidades.Utilidades;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author jssmontalvan
 */
public class GestionContable extends javax.swing.JFrame implements ComunicacionVistasModelosTablas{
     private Utilidades utilidades;
    private Personabd controladorPersona;
    private Persona personaEditar;
    private Proveedores EditarProveedor;
    private Inventario EditarInventario;
    private GestionPersona gestionPersona;
    private GestionProveedor gestionProveedor;
    private GestionInventario gestionInventario;
    private ModelTablaPersona ModelTablePersona;
    private ModelTableProveedores modelTableProveedores;
    private ModelTablaInventario modelTableInventario;
    private Inventariobd controladorInventario;
    private Proveedoresbd controladorProveedor;

    /**
     * Creates new form GestionContable
     */
    
    public GestionContable() {
        
        controladorPersona = new Personabd();
        controladorProveedor = new Proveedoresbd();
        controladorInventario = new Inventariobd();
        ModelTablePersona = new ModelTablaPersona(controladorPersona.obtenerPersonas(), this);
        modelTableProveedores = new ModelTableProveedores(controladorProveedor.obtenerProveedores(), this);
        modelTableInventario = new ModelTablaInventario(controladorInventario.obtenerProductos(), this);
        initComponents();
        utilidades = new Utilidades();
        gestionPersona = new GestionPersona(txtCedula, txtNombres, txtApellidos, txtDireccion, txtCorreo, txtTelefono, utilidades, this);
        gestionProveedor = new GestionProveedor(txtRuc, txtRazonSocial, txtTipoActividad, txtNombreRepresentante, txtApellidoRepresentante, txtTelefonoProveedor, txtCorreoProveedor, utilidades, this);
        gestionInventario = new GestionInventario(txtCodigoProducto, txtDescripcion, txtPrecioCompra, txtPrecioVenta, txtCantidadProducto, utilidades, this);
    }
public void guardarPersona() {
        if (controladorPersona.PersonaCedula(txtCedula.getText()) != null) {
            JOptionPane.showMessageDialog(rootPane, "La persona ya se encuentra registrada en el sistema.");
        } else {
            Persona personaGuardar = gestionPersona.guardarEditar();
            if (personaGuardar != null) {
                if (controladorPersona.registrarPersona(personaGuardar)) {
                    JOptionPane.showMessageDialog(rootPane, "Persona registrada en el sistema.");
                    gestionPersona.limpiar();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "No se puede guardar la persona", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void ultimaPersona() {
        List<Persona> obtper = controladorPersona.obtenerPersonas();
        Persona persona = obtper.get(obtper.size() - 1);
        System.out.println(persona);
        txtCedula.setText(persona.getCedula());
        txtNombres.setText(persona.getNombre());
        txtApellidos.setText(persona.getApellidos());
        txtDireccion.setText(persona.getDireccion());
        txtCorreo.setText(persona.getCorreo());
        txtTelefono.setText(persona.getTelefono());
    }

    public void editarPersona() {
        personaEditar = controladorPersona.PersonaCedula(txtCedula.getText());
        personaEditar = controladorPersona.BuscarTelefono(txtTelefono.getText());
        if (personaEditar == null) {
            JOptionPane.showMessageDialog(rootPane, "No hay una persona seleccionada para editar", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Persona personaEditarLocal = gestionPersona.guardarEditar();
        if (personaEditarLocal != null) {
            personaEditarLocal.setIdPersona(personaEditar.getIdPersona());
            if (controladorPersona.actualizar(personaEditarLocal)) {
                JOptionPane.showMessageDialog(rootPane, "Persona editada con exito del sitema.");
                gestionPersona.limpiar();
                ModelTablePersona.fireTableDataChanged();
                personaEditar = null;
            } else {
                JOptionPane.showMessageDialog(rootPane, "No se puede editar la persona", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public void editarProveedor() {
       if (EditarProveedor == null) {
            JOptionPane.showMessageDialog(rootPane, "No hay un Proveedor seleccionado para editar", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Proveedores proveedores = gestionProveedor.guardarEditarProveedor();
        if (proveedores != null) {
            proveedores.setIdProveedores(EditarProveedor.getIdProveedores());
            if (controladorProveedor.actualizarProveedores(proveedores)) {
                JOptionPane.showMessageDialog(rootPane, "Proveedor editado con éxito.");
                gestionProveedor.limpiarProveedor();
                List<Proveedores> pro = controladorProveedor.obtenerProveedores();
                modelTableProveedores.setProveedores(pro);
                modelTableProveedores.fireTableDataChanged();
                EditarProveedor = null;
            } else {
                JOptionPane.showMessageDialog(rootPane, "No se puede editar el Proveedor", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public void editarInventario() {
        if (EditarInventario == null) {
            JOptionPane.showMessageDialog(rootPane, "No hay un Producto seleccionado para editar", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Inventario inventario = gestionInventario.guardarEditarProducto();
        if (inventario != null) {
            inventario.setIdInventario(EditarInventario.getIdInventario());
            if (controladorInventario.actualizarProducto(inventario)) {
                JOptionPane.showMessageDialog(rootPane, "Producto editado con éxito.");
                gestionInventario.limpiarInventario();
                List<Inventario> pro = controladorInventario.obtenerProductos();
                modelTableInventario.setInventario(pro);
                modelTableInventario.fireTableDataChanged();
                EditarInventario = null;
            } else {
                JOptionPane.showMessageDialog(rootPane, "No se puede editar el Producto", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
  
    public void buscarCedulaPersona() {
        if (txtCedula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "No hay un número de cédula colocado para buscar", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtCedula.requestFocus();
        } else {
            Persona persona = controladorPersona.PersonaCedula(txtCedula.getText());
            if (persona != null) {
                txtCedula.setText(persona.getCedula());
                txtNombres.setText(persona.getNombre());
                txtApellidos.setText(persona.getApellidos());
                txtDireccion.setText(persona.getDireccion());
                txtCorreo.setText(persona.getCorreo());
                txtTelefono.setText(persona.getTelefono());

            } else {
                JOptionPane.showMessageDialog(rootPane, "No se encontró la persona con ese número de cédula", "ERROR", JOptionPane.ERROR_MESSAGE);
                txtCedula.setText("");
                txtCedula.requestFocus();

            }
        }
    }
    public void buscarTelefonoPersona() {

        if (txtTelefono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "No hay un número de teléfono colocado para buscar", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtTelefono.requestFocus();
        } else {
            Persona persona = controladorPersona.BuscarTelefono(txtTelefono.getText());
            if (persona != null) {
                txtCedula.setText(persona.getCedula());
                txtNombres.setText(persona.getNombre());
                txtApellidos.setText(persona.getApellidos());
                txtDireccion.setText(persona.getDireccion());
                txtCorreo.setText(persona.getCorreo());
                txtTelefono.setText(persona.getTelefono());

            } else {
                JOptionPane.showMessageDialog(rootPane, "No se encontró la persona con ese número de teléfono", "ERROR", JOptionPane.ERROR_MESSAGE);
                txtTelefono.setText("");
                txtTelefono.requestFocus();
            }
        }
    }
    
     public void eliminarPersona() {
        personaEditar = controladorPersona.PersonaCedula(txtCedula.getText());

        if (personaEditar == null) {
            JOptionPane.showMessageDialog(rootPane, "No hay una persona seleccionada para eliminar", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (personaEditar != null) {
            personaEditar.setIdPersona(personaEditar.getIdPersona());
            if (controladorPersona.eliminar(personaEditar)) {
                JOptionPane.showMessageDialog(rootPane, "Persona eliminada con exito del sitema.");
                ModelTablePersona.fireTableDataChanged();
                gestionPersona.limpiar();
                
                personaEditar = null;
            } else {
                JOptionPane.showMessageDialog(rootPane, "No se puede eliminar a la persona", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        }
     public void BuscarCliente() {

        if (comboParametroBusquedaCliente.getSelectedItem().equals("Por Cédula")) {
            List<Persona> personaCedula = controladorPersona.getPersonaCedula(txtBusquedaCliente.getText());
            ModelTablePersona.setPersonas(personaCedula);
            ModelTablePersona.fireTableDataChanged();
        } else if (comboParametroBusquedaCliente.getSelectedItem().equals("Por Nombre")) {
            List<Persona> personaNombre = controladorPersona.getPersonaNombre(txtBusquedaCliente.getText());
            ModelTablePersona.setPersonas(personaNombre);
            ModelTablePersona.fireTableDataChanged();
        } else if (comboParametroBusquedaCliente.getSelectedItem().equals("Por Apellido")) {
            List<Persona> personaApellido = controladorPersona.getPersonaApellido(txtBusquedaCliente.getText());
            ModelTablePersona.setPersonas(personaApellido);
            ModelTablePersona.fireTableDataChanged();
        } else if (comboParametroBusquedaCliente.getSelectedItem().equals("Por Direccion")) {
            List<Persona> personaDireccion = controladorPersona.getPersonaDireccion(txtBusquedaCliente.getText());
            ModelTablePersona.setPersonas(personaDireccion);
            ModelTablePersona.fireTableDataChanged();
        } else if (comboParametroBusquedaCliente.getSelectedItem().equals("Por Correo")) {
            List<Persona> personaNombre = controladorPersona.getPersonaCorreo(txtBusquedaCliente.getText());
            ModelTablePersona.setPersonas(personaNombre);
            ModelTablePersona.fireTableDataChanged();
        } else if (comboParametroBusquedaCliente.getSelectedItem().equals("Por Teléfono")) {
            List<Persona> personaNombre = controladorPersona.getPersonaTelefono(txtBusquedaCliente.getText());
            ModelTablePersona.setPersonas(personaNombre);
            ModelTablePersona.fireTableDataChanged();
        }
    }
     public void BuscarProveedor() {

        if (comboParametroBusquedaProveedor.getSelectedItem().equals("RUC")) {
            List<Proveedores> ruc = controladorProveedor.getProveedorRuc(txtParametroBusquedaProveedor.getText());
            modelTableProveedores.setProveedores(ruc);
            modelTableProveedores.fireTableDataChanged();
        } else if (comboParametroBusquedaProveedor.getSelectedItem().equals("Razon Social")) {
            List<Proveedores> RazonSocial = controladorProveedor.getProveedorRazonSocial(txtParametroBusquedaProveedor.getText());
            modelTableProveedores.setProveedores(RazonSocial);
            modelTableProveedores.fireTableDataChanged();
        } else if (comboParametroBusquedaProveedor.getSelectedItem().equals("Tipo de Actividad")) {
            List<Proveedores> TipoActividad = controladorProveedor.getTipoActividad(txtParametroBusquedaProveedor.getText());
            modelTableProveedores.setProveedores(TipoActividad);
            modelTableProveedores.fireTableDataChanged();
        } else if (comboParametroBusquedaProveedor.getSelectedItem().equals("Nombre Representante")) {
            List<Proveedores> NombreRepresentante = controladorProveedor.getNombreRepresentante(txtParametroBusquedaProveedor.getText());
            modelTableProveedores.setProveedores(NombreRepresentante);
            modelTableProveedores.fireTableDataChanged();
        } else if (comboParametroBusquedaProveedor.getSelectedItem().equals("Apellido Representante")) {
            List<Proveedores> ApellidoRepresentante = controladorProveedor.getApellidoRepresentante(txtParametroBusquedaProveedor.getText());
            modelTableProveedores.setProveedores(ApellidoRepresentante);
            modelTableProveedores.fireTableDataChanged();
            } else if (comboParametroBusquedaProveedor.getSelectedItem().equals("Telefono")) {
            List<Proveedores> Telefono = controladorProveedor.getTelefonoProveedor(txtParametroBusquedaProveedor.getText());
            modelTableProveedores.setProveedores(Telefono);
            modelTableProveedores.fireTableDataChanged();
        } else if (comboParametroBusquedaProveedor.getSelectedItem().equals("Correo")) {
            List<Proveedores> Correo = controladorProveedor.getCorreoProveedor(txtParametroBusquedaProveedor.getText());
            modelTableProveedores.setProveedores(Correo);
            modelTableProveedores.fireTableDataChanged();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        cedula = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        nombres = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        apellidos = new javax.swing.JLabel();
        direccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        correo = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        BotonGuardar = new javax.swing.JButton();
        BotonEliminar = new javax.swing.JButton();
        BotonTraer = new javax.swing.JButton();
        BotonEditar = new javax.swing.JButton();
        BotonLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        comboParametroBusquedaCliente = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtBusquedaCliente = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jlbRuc = new javax.swing.JLabel();
        jlbRazonSocial = new javax.swing.JLabel();
        jlbActividad = new javax.swing.JLabel();
        jlbNombreRepresentante = new javax.swing.JLabel();
        jlbApellidoRepresentante = new javax.swing.JLabel();
        jlbCorreoProveedor = new javax.swing.JLabel();
        jlbTelefonoProveedor = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        txtRazonSocial = new javax.swing.JTextField();
        txtTipoActividad = new javax.swing.JTextField();
        txtNombreRepresentante = new javax.swing.JTextField();
        txtApellidoRepresentante = new javax.swing.JTextField();
        txtTelefonoProveedor = new javax.swing.JTextField();
        txtCorreoProveedor = new javax.swing.JTextField();
        BotonGuardarProveedor = new javax.swing.JButton();
        BotonEditarProveedor = new javax.swing.JButton();
        BotonEliminarProveedor = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        modeltableProveedores = new javax.swing.JTable();
        txtParametroBusquedaProveedor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        comboParametroBusquedaProveedor = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCodigoProducto = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        txtPrecioCompra = new javax.swing.JTextField();
        txtPrecioVenta = new javax.swing.JTextField();
        txtCantidadProducto = new javax.swing.JTextField();
        BotonGuardarInventario = new javax.swing.JButton();
        BotonEditarInventario = new javax.swing.JButton();
        BotonEliminarInventario = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaInventario = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        menuSalir = new javax.swing.JMenuItem();
        accionPersona = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        buscarTelefono = new javax.swing.JCheckBoxMenuItem();
        BuscarCedula = new javax.swing.JCheckBoxMenuItem();
        accionPersonaGuardar = new javax.swing.JCheckBoxMenuItem();
        accionPersonaEditar = new javax.swing.JRadioButtonMenuItem();
        accionPersonaEliminar = new javax.swing.JRadioButtonMenuItem();
        accionPersonaUltimoRegistro = new javax.swing.JRadioButtonMenuItem();
        accionPersonaLimpiar = new javax.swing.JRadioButtonMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        jCheckBox1.setText("jCheckBox1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 659, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 739, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Proveedores", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 659, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 739, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Inventario", jPanel4);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 659, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 739, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Venta", jPanel6);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel5.setFont(new java.awt.Font("Adobe Garamond Pro", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 102));
        jLabel5.setText("REGISTRO DE CLIENTES");

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Bright", 1, 14), new java.awt.Color(153, 0, 102))); // NOI18N

        cedula.setBackground(new java.awt.Color(255, 204, 255));
        cedula.setText("Cédula");

        nombres.setText("Nombres");

        apellidos.setText("Apellidos");

        direccion.setText("Dirección");

        correo.setText("Correo");

        jLabel1.setText("Teléfono");

        BotonGuardar.setText("Guardar");
        BotonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonGuardarActionPerformed(evt);
            }
        });

        BotonEliminar.setText("Eliminar");
        BotonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEliminarActionPerformed(evt);
            }
        });

        BotonTraer.setText("Ultimo Registro");
        BotonTraer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonTraerActionPerformed(evt);
            }
        });

        BotonEditar.setText("Editar");
        BotonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEditarActionPerformed(evt);
            }
        });

        BotonLimpiar.setText("Limpiar");
        BotonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonLimpiarActionPerformed(evt);
            }
        });

        jTable1.setModel(ModelTablePersona);
        jScrollPane1.setViewportView(jTable1);

        comboParametroBusquedaCliente.setForeground(new java.awt.Color(0, 0, 102));
        comboParametroBusquedaCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Por Cédula", "Por Nombre", "Por Apellido", "Por Dirección", "Por Correo", "Por Teléfono" }));
        comboParametroBusquedaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboParametroBusquedaClienteActionPerformed(evt);
            }
        });

        jLabel3.setText("Buscador:");

        txtBusquedaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusquedaClienteKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(apellidos)
                                .addComponent(direccion)
                                .addComponent(correo, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cedula)
                                    .addComponent(nombres))))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCorreo)
                            .addComponent(txtDireccion)
                            .addComponent(txtApellidos)
                            .addComponent(txtNombres)
                            .addComponent(txtCedula)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(102, 102, 102))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(comboParametroBusquedaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtBusquedaCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(BotonGuardar)
                                        .addGap(18, 18, 18)
                                        .addComponent(BotonEliminar)
                                        .addGap(28, 28, 28)
                                        .addComponent(BotonTraer)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(BotonLimpiar)
                                    .addComponent(BotonEditar))))))
                .addGap(65, 65, 65))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cedula)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombres))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(apellidos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(direccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(correo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(37, 37, 37)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotonGuardar)
                    .addComponent(BotonEliminar)
                    .addComponent(BotonTraer)
                    .addComponent(BotonEditar))
                .addGap(26, 26, 26)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboParametroBusquedaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonLimpiar)
                    .addComponent(jLabel3)
                    .addComponent(txtBusquedaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(274, 274, 274))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Clientes", jPanel2);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Proveedores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Sans", 1, 14), new java.awt.Color(153, 0, 102))); // NOI18N

        jlbRuc.setText("RUC;");

        jlbRazonSocial.setText("Razon Social:");

        jlbActividad.setText("Actividad:");

        jlbNombreRepresentante.setText("Nombre Representante:");

        jlbApellidoRepresentante.setText("Apellido Representante:");

        jlbCorreoProveedor.setText("Correo:");

        jlbTelefonoProveedor.setText("Teléfono:");

        txtTelefonoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoProveedorActionPerformed(evt);
            }
        });

        BotonGuardarProveedor.setText("Guardar");
        BotonGuardarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonGuardarProveedorActionPerformed(evt);
            }
        });

        BotonEditarProveedor.setText("Editar");
        BotonEditarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEditarProveedorActionPerformed(evt);
            }
        });

        BotonEliminarProveedor.setText("Eliminar");

        modeltableProveedores.setModel(modelTableProveedores);
        jScrollPane2.setViewportView(modeltableProveedores);

        txtParametroBusquedaProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtParametroBusquedaProveedorKeyTyped(evt);
            }
        });

        jLabel2.setText("Buscador:");

        comboParametroBusquedaProveedor.setForeground(new java.awt.Color(0, 51, 153));
        comboParametroBusquedaProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "RUC", "Razon Social", "Tipo Actividad", "Nombre Representante", "Apellido Representante ", "Telefono ", "Correo" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNombreRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jlbRuc)
                                .addComponent(jlbRazonSocial)
                                .addComponent(jlbActividad)
                                .addComponent(jlbNombreRepresentante))
                            .addGap(36, 36, 36)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTipoActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jlbTelefonoProveedor)
                                .addComponent(jlbApellidoRepresentante)
                                .addComponent(jlbCorreoProveedor))
                            .addGap(36, 36, 36)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(BotonEditarProveedor)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtApellidoRepresentante)
                                    .addComponent(txtTelefonoProveedor)
                                    .addComponent(txtCorreoProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))))
                        .addComponent(BotonGuardarProveedor, javax.swing.GroupLayout.Alignment.LEADING)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(BotonEliminarProveedor)
                .addGap(94, 94, 94))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(comboParametroBusquedaProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(txtParametroBusquedaProveedor)
                .addGap(114, 114, 114))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbRuc)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbRazonSocial)
                    .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbActividad)
                    .addComponent(txtTipoActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbNombreRepresentante))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidoRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbApellidoRepresentante))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbTelefonoProveedor)
                    .addComponent(txtTelefonoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbCorreoProveedor)
                    .addComponent(txtCorreoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotonGuardarProveedor)
                    .addComponent(BotonEditarProveedor)
                    .addComponent(BotonEliminarProveedor))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtParametroBusquedaProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(comboParametroBusquedaProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Adobe Garamond Pro", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 102));
        jLabel6.setText("REGISTRO DE PROVEEDORES");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(258, 258, 258))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Proveedores", jPanel9);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Bright", 1, 14), new java.awt.Color(153, 0, 102))); // NOI18N

        jLabel4.setText("Codigo Producto:");

        jLabel8.setText("Precio Compra;");

        jLabel9.setText("Precio Venta:");

        jLabel10.setText("Cantidad Productos:");

        jLabel11.setText("Descripcion Producto:");

        BotonGuardarInventario.setText("Guardar");
        BotonGuardarInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonGuardarInventarioActionPerformed(evt);
            }
        });

        BotonEditarInventario.setText("Editar");
        BotonEditarInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEditarInventarioActionPerformed(evt);
            }
        });

        BotonEliminarInventario.setText("Eliminar");

        TablaInventario.setModel(modelTableInventario);
        jScrollPane3.setViewportView(TablaInventario);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(54, 54, 54)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCodigoProducto)
                    .addComponent(txtDescripcion)
                    .addComponent(txtPrecioCompra)
                    .addComponent(txtPrecioVenta)
                    .addComponent(txtCantidadProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(BotonGuardarInventario)
                        .addGap(87, 87, 87)
                        .addComponent(BotonEditarInventario)
                        .addGap(100, 100, 100)
                        .addComponent(BotonEliminarInventario)
                        .addGap(115, 115, 115))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(44, 44, 44)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotonGuardarInventario)
                    .addComponent(BotonEditarInventario)
                    .addComponent(BotonEliminarInventario))
                .addGap(87, 87, 87)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(222, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Adobe Garamond Pro", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 102));
        jLabel7.setText("REGISTRO DE PRODUCTOS");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addComponent(jLabel7))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel7)
                .addGap(36, 36, 36)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Inventario", jPanel5);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 715, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 866, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab("Venta", jPanel7);

        jMenuBar1.setBackground(new java.awt.Color(0, 255, 255));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 255, 255), new java.awt.Color(0, 255, 255), new java.awt.Color(0, 255, 255), new java.awt.Color(0, 255, 255)));
        jMenuBar1.setBorderPainted(false);
        jMenuBar1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        menuArchivo.setBackground(new java.awt.Color(0, 255, 255));
        menuArchivo.setText("Archivo");

        menuSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuSalir.setText("Salir");
        menuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSalirActionPerformed(evt);
            }
        });
        menuArchivo.add(menuSalir);

        accionPersona.setText("Acciones Persona");

        jMenu6.setText("Buscar");

        buscarTelefono.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.SHIFT_MASK));
        buscarTelefono.setSelected(true);
        buscarTelefono.setText("Buscar Por Apellido");
        buscarTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarTelefonoActionPerformed(evt);
            }
        });
        jMenu6.add(buscarTelefono);

        BuscarCedula.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.SHIFT_MASK));
        BuscarCedula.setSelected(true);
        BuscarCedula.setText("Buscar Por Cédula");
        BuscarCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarCedulaActionPerformed(evt);
            }
        });
        jMenu6.add(BuscarCedula);

        accionPersona.add(jMenu6);

        accionPersonaGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        accionPersonaGuardar.setSelected(true);
        accionPersonaGuardar.setText("Guardar");
        accionPersonaGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionPersonaGuardarActionPerformed(evt);
            }
        });
        accionPersona.add(accionPersonaGuardar);

        accionPersonaEditar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        accionPersonaEditar.setSelected(true);
        accionPersonaEditar.setText("Editar");
        accionPersonaEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionPersonaEditarActionPerformed(evt);
            }
        });
        accionPersona.add(accionPersonaEditar);

        accionPersonaEliminar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        accionPersonaEliminar.setSelected(true);
        accionPersonaEliminar.setText("Eliminar");
        accionPersonaEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionPersonaEliminarActionPerformed(evt);
            }
        });
        accionPersona.add(accionPersonaEliminar);

        accionPersonaUltimoRegistro.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        accionPersonaUltimoRegistro.setSelected(true);
        accionPersonaUltimoRegistro.setText("Último Registro");
        accionPersonaUltimoRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionPersonaUltimoRegistroActionPerformed(evt);
            }
        });
        accionPersona.add(accionPersonaUltimoRegistro);

        accionPersonaLimpiar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        accionPersonaLimpiar.setSelected(true);
        accionPersonaLimpiar.setText("Limpiar Campos");
        accionPersonaLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionPersonaLimpiarActionPerformed(evt);
            }
        });
        accionPersona.add(accionPersonaLimpiar);

        menuArchivo.add(accionPersona);

        jMenuBar1.add(menuArchivo);

        jMenu2.setBackground(new java.awt.Color(0, 255, 255));
        jMenu2.setText("Editar");
        jMenuBar1.add(jMenu2);

        jMenu5.setBackground(new java.awt.Color(0, 255, 255));
        jMenu5.setText("Acerca de");
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuSalirActionPerformed

    private void buscarTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarTelefonoActionPerformed

    }//GEN-LAST:event_buscarTelefonoActionPerformed

    private void BuscarCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarCedulaActionPerformed
        buscarCedulaPersona();
    }//GEN-LAST:event_BuscarCedulaActionPerformed

    private void accionPersonaGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionPersonaGuardarActionPerformed
        guardarPersona();
    }//GEN-LAST:event_accionPersonaGuardarActionPerformed

    private void accionPersonaEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionPersonaEditarActionPerformed
         editarPersona();
    }//GEN-LAST:event_accionPersonaEditarActionPerformed

    private void accionPersonaEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionPersonaEliminarActionPerformed
        eliminarPersona();
    }//GEN-LAST:event_accionPersonaEliminarActionPerformed

    private void accionPersonaUltimoRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionPersonaUltimoRegistroActionPerformed
        ultimaPersona();
    }//GEN-LAST:event_accionPersonaUltimoRegistroActionPerformed

    private void accionPersonaLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionPersonaLimpiarActionPerformed
        gestionPersona.limpiar();
    }//GEN-LAST:event_accionPersonaLimpiarActionPerformed

    private void BotonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonLimpiarActionPerformed
        // TODO add your handling code here:
        gestionPersona.limpiar();
    }//GEN-LAST:event_BotonLimpiarActionPerformed

    private void BotonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEditarActionPerformed
        // TODO add your handling code here:
        editarPersona();
    }//GEN-LAST:event_BotonEditarActionPerformed

    private void BotonTraerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonTraerActionPerformed
        // TODO add your handling code here:
        ultimaPersona();
    }//GEN-LAST:event_BotonTraerActionPerformed

    private void BotonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEliminarActionPerformed
        // TODO add your handling code here:
        eliminarPersona();
    }//GEN-LAST:event_BotonEliminarActionPerformed

    private void BotonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonGuardarActionPerformed
        // TODO add your handling code here:
        guardarPersona();
    }//GEN-LAST:event_BotonGuardarActionPerformed

    private void comboParametroBusquedaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboParametroBusquedaClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboParametroBusquedaClienteActionPerformed

    private void txtTelefonoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoProveedorActionPerformed

    private void BotonGuardarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonGuardarProveedorActionPerformed
        // TODO add your handling code here:
        Proveedores proveedores = new Proveedores();
        proveedores.setRuc(txtRuc.getText());
        proveedores.setRazonSocial(txtRazonSocial.getText());
        proveedores.setTipoActividad(txtTipoActividad.getText());
        proveedores.setNombreRepresentanteLegal(txtNombreRepresentante.getText());
        proveedores.setApellidoRepresentanteLegal(txtApellidoRepresentante.getText());
        proveedores.setTelefonoProveedor(txtTelefonoProveedor.getText());
        proveedores.setCorreoProveedor(txtCorreo.getText());
       
         if (controladorProveedor.registrarProveedor(proveedores)) {
            JOptionPane.showMessageDialog(rootPane, "Proveedor guardado con éxito del sitema.");
            limpiarCamposProveedor();
            modelTableProveedores.setProveedores(controladorProveedor.obtenerProveedores());
            modelTableProveedores.fireTableDataChanged();
        } else {
            JOptionPane.showMessageDialog(rootPane, "No se puede guardar el proveedor.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BotonGuardarProveedorActionPerformed

    private void BotonEditarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEditarProveedorActionPerformed
        // TODO add your handling code here:
        editarProveedor();
    }//GEN-LAST:event_BotonEditarProveedorActionPerformed

    private void txtBusquedaClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaClienteKeyTyped
        // TODO add your handling code here:
        BuscarCliente();
    }//GEN-LAST:event_txtBusquedaClienteKeyTyped

    private void txtParametroBusquedaProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtParametroBusquedaProveedorKeyTyped
        // TODO add your handling code here:
        BuscarProveedor();
    }//GEN-LAST:event_txtParametroBusquedaProveedorKeyTyped

    private void BotonEditarInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEditarInventarioActionPerformed
        // TODO add your handling code here
        editarInventario();
    }//GEN-LAST:event_BotonEditarInventarioActionPerformed

    private void BotonGuardarInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonGuardarInventarioActionPerformed
        // TODO add your handling code here:
        Inventario inventario = new Inventario();
        inventario.setCodigoProducto(txtCodigoProducto.getText());
        inventario.setDescripcion(txtDescripcion.getText());
        inventario.setPrecioCompra(txtTipoActividad.getText());
        inventario.setPrecioVenta(txtNombreRepresentante.getText());
        inventario.setCantidadProducto(txtCantidadProducto.getText());
       
         if (controladorInventario.crearProducto(inventario)) {
            JOptionPane.showMessageDialog(rootPane, "Producto guardado con éxito del sitema.");
            limpiarInventario();
            modelTableInventario.setInventario(controladorInventario.obtenerProductos());
            modelTableInventario.fireTableDataChanged();
        } else {
            JOptionPane.showMessageDialog(rootPane, "No se puede guardar el Producto.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        
    }//GEN-LAST:event_BotonGuardarInventarioActionPerformed
  void limpiarCamposProveedor() {
        txtRuc.setText("");
        txtRazonSocial.setText("");
        txtTipoActividad.setText("");
        txtNombreRepresentante.setText("");
        txtApellidoRepresentante.setText("");
        txtTelefonoProveedor.setText("");
        txtCorreoProveedor.setText("");
    }
  public void limpiarInventario() {
        txtCodigoProducto.setText("");
        txtDescripcion.setText("");
        txtPrecioCompra.setText("");
        txtPrecioVenta.setText("");
        txtCantidadProducto.setText("");
        
        txtCodigoProducto.requestFocus();
    }
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
            java.util.logging.Logger.getLogger(GestionContable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionContable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionContable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionContable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionContable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonEditar;
    private javax.swing.JButton BotonEditarInventario;
    private javax.swing.JButton BotonEditarProveedor;
    private javax.swing.JButton BotonEliminar;
    private javax.swing.JButton BotonEliminarInventario;
    private javax.swing.JButton BotonEliminarProveedor;
    private javax.swing.JButton BotonGuardar;
    private javax.swing.JButton BotonGuardarInventario;
    private javax.swing.JButton BotonGuardarProveedor;
    private javax.swing.JButton BotonLimpiar;
    private javax.swing.JButton BotonTraer;
    private javax.swing.JCheckBoxMenuItem BuscarCedula;
    private javax.swing.JTable TablaInventario;
    private javax.swing.JMenu accionPersona;
    private javax.swing.JRadioButtonMenuItem accionPersonaEditar;
    private javax.swing.JRadioButtonMenuItem accionPersonaEliminar;
    private javax.swing.JCheckBoxMenuItem accionPersonaGuardar;
    private javax.swing.JRadioButtonMenuItem accionPersonaLimpiar;
    private javax.swing.JRadioButtonMenuItem accionPersonaUltimoRegistro;
    private javax.swing.JLabel apellidos;
    private javax.swing.JCheckBoxMenuItem buscarTelefono;
    private javax.swing.JLabel cedula;
    private javax.swing.JComboBox<String> comboParametroBusquedaCliente;
    private javax.swing.JComboBox<String> comboParametroBusquedaProveedor;
    private javax.swing.JLabel correo;
    private javax.swing.JLabel direccion;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jlbActividad;
    private javax.swing.JLabel jlbApellidoRepresentante;
    private javax.swing.JLabel jlbCorreoProveedor;
    private javax.swing.JLabel jlbNombreRepresentante;
    private javax.swing.JLabel jlbRazonSocial;
    private javax.swing.JLabel jlbRuc;
    private javax.swing.JLabel jlbTelefonoProveedor;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenuItem menuSalir;
    private javax.swing.JTable modeltableProveedores;
    private javax.swing.JLabel nombres;
    private javax.swing.JTextField txtApellidoRepresentante;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBusquedaCliente;
    private javax.swing.JTextField txtCantidadProducto;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCodigoProducto;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtCorreoProveedor;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombreRepresentante;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtParametroBusquedaProveedor;
    private javax.swing.JTextField txtPrecioCompra;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefonoProveedor;
    private javax.swing.JTextField txtTipoActividad;
    // End of variables declaration//GEN-END:variables

     @Override
    public void clickPersona(Persona p) {
        txtCedula.setText(p.getCedula());
        txtNombres.setText(p.getNombre());
        txtApellidos.setText(p.getApellidos());
        txtDireccion.setText(p.getDireccion());
        txtTelefono.setText(p.getTelefono());
        txtCorreo.setText(p.getCorreo());
        personaEditar = p;
    }

    private void buscarCliente(String text) {
        switch (comboParametroBusquedaCliente.getItemCount()) {

        }
    }

    @Override
    public void clickProveedores(Proveedores p) {
        txtRuc.setText(p.getRuc());
        txtRazonSocial.setText(p.getRazonSocial());
        txtTipoActividad.setText(p.getTipoActividad());
        txtNombreRepresentante.setText(p.getNombreRepresentanteLegal());
        txtApellidoRepresentante.setText(p.getApellidoRepresentanteLegal());
        txtTelefonoProveedor.setText(p.getTelefonoProveedor());
        txtCorreoProveedor.setText(p.getCorreoProveedor());
        EditarProveedor = p;
        
    }
     @Override
    public void clickInventario(Inventario p) {
        txtCodigoProducto.setText(p.getCodigoProducto());
        txtDescripcion.setText(p.getDescripcion());
        txtPrecioCompra.setText(p.getPrecioCompra());
        txtPrecioVenta.setText(p.getPrecioVenta());
        txtCantidadProducto.setText(p.getCantidadProducto());
        EditarInventario = p;
}
}