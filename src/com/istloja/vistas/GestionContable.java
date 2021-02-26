/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.vistas;

import com.istloja.ModelPersona.ComunicacionVistasModelosTablas;
import com.istloja.ModelPersona.ModelTablaPersona;
import com.istloja.ModelPersona.ModelTableProveedores;
import com.istloja.controlador.Personabd;
import com.istloja.controlador.Proveedoresbd;
import com.istloja.modelo.Persona;
import com.istloja.modelo.Proveedores;
import com.istloja.utilidades.Utilidades;
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
    private GestionPersona gestionPersona;
    private ModelTablaPersona ModelTablePersona;
    private ModelTableProveedores modelTableProveedores;
    private Proveedoresbd controladorProveedor;

    /**
     * Creates new form GestionContable
     */
    
    public GestionContable() {
        
        controladorPersona = new Personabd();
        controladorProveedor = new Proveedoresbd();
        ModelTablePersona = new ModelTablaPersona(controladorPersona.obtenerPersonas());
        modelTableProveedores = new ModelTableProveedores(controladorProveedor.obtenerProveedores(), this);
        initComponents();
        utilidades = new Utilidades();
        gestionPersona = new GestionPersona(txtCedula, txtNombres, txtApellidos, txtDireccion, txtCorreo, txtTelefono, utilidades, this);
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
                personaEditar = null;
            } else {
                JOptionPane.showMessageDialog(rootPane, "No se puede editar la persona", "ERROR", JOptionPane.ERROR_MESSAGE);
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
                gestionPersona.limpiar();
                personaEditar = null;
            } else {
                JOptionPane.showMessageDialog(rootPane, "No se puede eliminar a la persona", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
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
        txtParametroBusquedaCliente = new javax.swing.JTextField();
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
        BotonBusquedaProveedor = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        comboParametroBusquedaProveedor = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
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

        comboParametroBusquedaCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Por Cédula", "Por Apellido" }));
        comboParametroBusquedaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboParametroBusquedaClienteActionPerformed(evt);
            }
        });

        jLabel3.setText("Buscador:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(apellidos)
                                    .addComponent(direccion)
                                    .addComponent(correo, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(cedula)
                                        .addComponent(nombres)))))
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
                                        .addComponent(txtParametroBusquedaCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
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
                    .addComponent(txtParametroBusquedaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(261, 261, 261)
                        .addComponent(jLabel5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel5)
                .addGap(26, 26, 26)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
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

        BotonEliminarProveedor.setText("Eliminar");

        modeltableProveedores.setModel(modelTableProveedores);
        jScrollPane2.setViewportView(modeltableProveedores);

        BotonBusquedaProveedor.setText("Buscar");

        jLabel2.setText("Buscador:");

        comboParametroBusquedaProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addComponent(txtParametroBusquedaProveedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BotonBusquedaProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
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
                                .addComponent(BotonGuardarProveedor, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboParametroBusquedaProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(BotonEliminarProveedor)
                .addGap(94, 94, 94))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(BotonBusquedaProveedor)
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
                .addGap(258, 258, 258)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel6)
                .addGap(45, 45, 45)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Proveedores", jPanel9);

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
                .addContainerGap(17, Short.MAX_VALUE))
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
        proveedores.setTelefono(txtTelefonoProveedor.getText());
        proveedores.setCorreo(txtCorreo.getText());
       
         if (controladorProveedor.registrarProveedor(proveedores)) {
            JOptionPane.showMessageDialog(rootPane, "Proveedor guardado con éxito del sitema.");
            limpiarCamposProveedor();
            modelTableProveedores.setProveedores(controladorProveedor.obtenerProveedores());
            modelTableProveedores.fireTableDataChanged();
        } else {
            JOptionPane.showMessageDialog(rootPane, "No se puede guardar el proveedor.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BotonGuardarProveedorActionPerformed
  void limpiarCamposProveedor() {
        txtRuc.setText("");
        txtRazonSocial.setText("");
        txtTipoActividad.setText("");
        txtNombreRepresentante.setText("");
        txtApellidoRepresentante.setText("");
        txtTelefonoProveedor.setText("");
        txtCorreoProveedor.setText("");
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
    private javax.swing.JButton BotonBusquedaProveedor;
    private javax.swing.JButton BotonEditar;
    private javax.swing.JButton BotonEditarProveedor;
    private javax.swing.JButton BotonEliminar;
    private javax.swing.JButton BotonEliminarProveedor;
    private javax.swing.JButton BotonGuardar;
    private javax.swing.JButton BotonGuardarProveedor;
    private javax.swing.JButton BotonLimpiar;
    private javax.swing.JButton BotonTraer;
    private javax.swing.JCheckBoxMenuItem BuscarCedula;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
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
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtCorreoProveedor;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombreRepresentante;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtParametroBusquedaCliente;
    private javax.swing.JTextField txtParametroBusquedaProveedor;
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
        txtTelefono.setText(p.getDireccion());
        txtCorreo.setText(p.getCorreo());
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
        txtTelefonoProveedor.setText(p.getTelefono());
        txtCorreoProveedor.setText(p.getCorreo());
        
    }
}
