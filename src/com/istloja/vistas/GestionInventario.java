/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.vistas;

import com.istloja.modelo.Inventario;
import com.istloja.utilidades.Utilidades;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author jssmontalvan
 */
public class GestionInventario {
    private JTextField txtCodigoProducto;
    private JTextField txtDescripcion;
    private JTextField txtPrecioCompra;
    private JTextField txtPrecioVenta;
    private JTextField txtCantidadProducto;
    private Utilidades utilidades;
    private JFrame frameGestionContable;

    public GestionInventario(JTextField txtCodigoProducto, JTextField txtDescripcion, JTextField txtPrecioCompra, JTextField txtPrecioVenta, JTextField txtCantidad, Utilidades utilidades, JFrame frameGestionContable) {
        this.txtCodigoProducto = txtCodigoProducto;
        this.txtDescripcion = txtDescripcion;
        this.txtPrecioCompra = txtPrecioCompra;
        this.txtPrecioVenta = txtPrecioVenta;
        this.txtCantidadProducto = txtCantidad;
        this.utilidades = utilidades;
        this.frameGestionContable = frameGestionContable;
    }

    public JTextField getTxtCodigoProducto() {
        return txtCodigoProducto;
    }

    public void setTxtCodigoProducto(JTextField txtCodigoProducto) {
        this.txtCodigoProducto = txtCodigoProducto;
    }

    public JTextField getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(JTextField txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public JTextField getTxtPrecioCompra() {
        return txtPrecioCompra;
    }

    public void setTxtPrecioCompra(JTextField txtPrecioCompra) {
        this.txtPrecioCompra = txtPrecioCompra;
    }

    public JTextField getTxtPrecioVenta() {
        return txtPrecioVenta;
    }

    public void setTxtPrecioVenta(JTextField txtPrecioVenta) {
        this.txtPrecioVenta = txtPrecioVenta;
    }

    public JTextField getTxtCantidad() {
        return txtCantidadProducto;
    }

    public void setTxtCantidad(JTextField txtCantidad) {
        this.txtCantidadProducto = txtCantidad;
    }

    public Utilidades getUtilidades() {
        return utilidades;
    }

    public void setUtilidades(Utilidades utilidades) {
        this.utilidades = utilidades;
    }

    public JFrame getFrameGestionContable() {
        return frameGestionContable;
    }

    public void setFrameGestionContable(JFrame frameGestionContable) {
        this.frameGestionContable = frameGestionContable;
    }
    
    public void limpiarInventario() {
        txtCodigoProducto.setText("");
        txtDescripcion.setText("");
        txtPrecioCompra.setText("");
        txtPrecioVenta.setText("");
        txtCantidadProducto.setText("");
        
        txtCodigoProducto.requestFocus();
    }
    
     public Inventario guardarEditarProducto() {
        if (txtCodigoProducto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo Codigo no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtCodigoProducto.requestFocus();// Sirve para ubicar el cursor en un campo vacio.
            return null;
        }
        if (!utilidades.validadorDeCedula(txtCodigoProducto.getText())) {
            JOptionPane.showMessageDialog(frameGestionContable, "El Código ingresado no es valido", "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (txtDescripcion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo Descripcion no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtDescripcion.requestFocus();
            return null;
        }
        if (txtPrecioCompra.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo Precio Compra no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtPrecioCompra.requestFocus();
            return null;
        }
        if (txtPrecioVenta.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo Precio Venta no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtPrecioVenta.requestFocus();
            return null;
        }
        if (txtCantidadProducto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "El campo Cantidad no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtCantidadProducto.requestFocus();
            return null;
        }
        
        Inventario inventario = new Inventario();
        inventario.setCodigoProducto(txtCodigoProducto.getText());
        inventario.setDescripcion(txtDescripcion.getText());
        inventario.setPrecioCompra(txtPrecioCompra.getText());
        inventario.setPrecioVenta(txtPrecioVenta.getText());
        inventario.setCantidadProducto(txtCantidadProducto.getText());
        
        return inventario;
    }
}
