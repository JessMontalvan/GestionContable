/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.ModelPersona;

import com.istloja.modelo.Proveedores;
import com.istloja.vistas.GestionContable;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jssmontalvan
 */
public class ModelTableProveedores extends AbstractTableModel {
    
     private String[] m_colNames = {"RUC", "RAZON SOCIAL", "TIPO ACTIVIDAD", "NOMBRE REPRESENTANTE LEGAL", "APELLIDOS REPRESENTANTE LEGAL", "TELEFONO", "CORREO"};
    private List<Proveedores> proveedores;
    private GestionContable gContable;

    public ModelTableProveedores(List<Proveedores> proveedores, GestionContable gContable) {
        this.proveedores = proveedores;
        this.gContable = gContable;
    }

    //Determina el número de filas que tengo en mi tabla.
    @Override
    public int getRowCount() {
        return proveedores.size();
    }

    //Determina el número de columnas que tengo en mi tabla.
    @Override
    public int getColumnCount() {
        return m_colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Proveedores proveedor = proveedores.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return proveedor.getRuc();
            case 1:
                return proveedor.getRazonSocial();
            case 2:
                return proveedor.getTipoActividad();
            case 3:
                return proveedor.getNombreRepresentanteLegal();
            case 4:
                return proveedor.getApellidoRepresentanteLegal();
            case 5:
                return proveedor.getTelefono();
            case 6:
                return proveedor.getCorreo();
        }
        return new String();
    }

    @Override
    public String getColumnName(int column) {
        return m_colNames[column]; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        gContable.clickProveedores(proveedores.get(rowIndex));
        return super.isCellEditable(rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Proveedores> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedores> proveedores) {
        this.proveedores = proveedores;
    }

}
