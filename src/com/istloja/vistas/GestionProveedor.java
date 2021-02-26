/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.vistas;

import com.istloja.modelo.Proveedores;
import com.istloja.utilidades.Utilidades;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author jssmontalvan
 */
public class GestionProveedor {
    private JTextField txtRuc;
    private JTextField txtRazonSocial;
    private JTextField txtTipoActividad;
    private JTextField txtNombreRepresentante;
    private JTextField txtApellidoRepresentante;
    private JTextField txtTelefonoProveedor;
    private JTextField txtCorreoProveedor;
    private Utilidades utilidades;

    private JFrame frameGestionContable;

    public GestionProveedor(JTextField txtRuc, JTextField txtRazonSocial, JTextField txtTipoActividad, JTextField txtNombreRepresentante, JTextField txtApellidoRepresentante, JTextField txtTelefonoProveedor, JTextField txtCorreoProveedor, Utilidades utilidades, JFrame frameGestionContable) {
        this.txtRuc = txtRuc;
        this.txtRazonSocial = txtRazonSocial;
        this.txtTipoActividad = txtTipoActividad;
        this.txtNombreRepresentante = txtNombreRepresentante;
        this.txtApellidoRepresentante = txtApellidoRepresentante;
        this.txtTelefonoProveedor = txtTelefonoProveedor;
        this.txtCorreoProveedor = txtCorreoProveedor;
        this.utilidades = utilidades;
        this.frameGestionContable = frameGestionContable;
        
    }

    

    public JTextField getTxtRuc() {
        return txtRuc;
    }

    public void setTxtRuc(JTextField txtRuc) {
        this.txtRuc = txtRuc;
    }

    public JTextField getTxtRazonSocial() {
        return txtRazonSocial;
    }

    public void setTxtRazonSocial(JTextField txtRazonSocial) {
        this.txtRazonSocial = txtRazonSocial;
    }

    public JTextField getTxtTipoActividad() {
        return txtTipoActividad;
    }

    public void setTxtTipoActividad(JTextField txtTipoActividad) {
        this.txtTipoActividad = txtTipoActividad;
    }

    public JTextField getTxtNombreReperentante() {
        return txtNombreRepresentante;
    }

    public void setTxtNombreReperentante(JTextField txtNombreReperentante) {
        this.txtNombreRepresentante = txtNombreReperentante;
    }

    public JTextField getTxtApellidoRepresentante() {
        return txtApellidoRepresentante;
    }

    public void setTxtApellidoRepresentante(JTextField txtApellidoRepresentante) {
        this.txtApellidoRepresentante = txtApellidoRepresentante;
    }

    public JTextField getTxtTelefonoProveedor() {
        return txtTelefonoProveedor;
    }

    public void setTxtTelefonoProveedor(JTextField txtTelefonoProveedor) {
        this.txtTelefonoProveedor = txtTelefonoProveedor;
    }

    public JTextField getTxtCorreoProveedor() {
        return txtCorreoProveedor;
    }

    public void setTxtCorreoProveedor(JTextField txtCorreoProveedor) {
        this.txtCorreoProveedor = txtCorreoProveedor;
    }

    public JFrame getFrameGestionContable() {
        return frameGestionContable;
    }

    public void setFrameGestionContable(JFrame frameGestionContable) {
        this.frameGestionContable = frameGestionContable;
    }

  public void limpiarProveedor() {
        txtRuc.setText("");
        txtRazonSocial.setText("");
        txtTipoActividad.setText("");
        txtNombreRepresentante.setText("");
        txtApellidoRepresentante.setText("");
        txtTelefonoProveedor.setText("");
        txtCorreoProveedor.setText("");
        txtRuc.requestFocus();
    }
    
     public Proveedores guardarEditarProveedor() {
        if (txtRuc.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo Ruc no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtRuc.requestFocus();// Sirve para ubicar el cursor en un campo vacio.
            return null;
        }
        if (!utilidades.validadorDeCedula(txtRuc.getText())) {
            JOptionPane.showMessageDialog(frameGestionContable, "El Ruc ingresado no es valido", "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
          if (txtRazonSocial.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo direccion no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtRazonSocial.requestFocus();
            return null;
        }
        if (txtTipoActividad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo telefono no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtTipoActividad.requestFocus();
            return null;
        }
        if (txtNombreRepresentante.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo Nombre Representante no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtNombreRepresentante.requestFocus();
            return null;
        }
        if (txtApellidoRepresentante.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo Apellido Representante no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtApellidoRepresentante.requestFocus();
            return null;
        }
      
        if (!utilidades.validarNumeros(txtTelefonoProveedor.getText())) {
            JOptionPane.showMessageDialog(frameGestionContable, "Los datos ingresados en el telefono no son validos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtTelefonoProveedor.requestFocus();
            return null;
        }
        if (txtCorreoProveedor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo correo no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtCorreoProveedor.requestFocus();
            return null;
        }
        if (!utilidades.validarcorreo(txtCorreoProveedor.getText())) {
            JOptionPane.showMessageDialog(frameGestionContable, "El correo ingresado no es valido.", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtCorreoProveedor.requestFocus();
            return null;
        }
        Proveedores proveedores = new Proveedores();
        proveedores.setRuc(txtRuc.getText());
        proveedores.setRazonSocial(txtRazonSocial.getText());
        proveedores.setNombreRepresentanteLegal(txtNombreRepresentante.getText());
        proveedores.setApellidoRepresentanteLegal(txtApellidoRepresentante.getText());
        proveedores.setTelefonoProveedor(txtTelefonoProveedor.getText());
        proveedores.setCorreoProveedor(txtCorreoProveedor.getText());
        return proveedores;
        }

  
    
}
