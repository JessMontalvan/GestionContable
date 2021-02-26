/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.ModelPersona;

import com.istloja.modelo.Inventario;
import com.istloja.vistas.GestionContable;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jssmontalvan
 */
    public class ModelTablaInventario extends AbstractTableModel {
 public String[] m_colNames = {"CODIGO ", "DESCRIPCION", "PRECIO COMPRA", "PRECIO VENTA", "CANTIDAD"};
    public List<Inventario> inventario;
    private GestionContable gestionContable;

    public ModelTablaInventario(List<Inventario> inventario, GestionContable gestionContable) {
        this.inventario = inventario;
        this.gestionContable = gestionContable;
    }

   
    //Determina el número de filas que tengo en mi tabla
    @Override
    public int getRowCount() {
        return inventario.size();
    }
    //Determina el número de columnas que tengo en mi tabla
    @Override
    public int getColumnCount() {
        return m_colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Inventario persona = inventario.get(rowIndex);
        switch(columnIndex){
            case 0:
                return persona.getCodigoProducto();
            case 1:
                return persona.getDescripcion();
            case 2:
                return persona.getPrecioCompra();
            case 3:
                return persona.getPrecioVenta();
            case 4:
                return persona.getCantidadProducto();
          
          
        }
        return new String();
    }
    
    //Este método sirve para definir los nombres de las columnas
    @Override
    public String getColumnName(int column) {
        return m_colNames[column];
    }
         
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        gestionContable.clickInventario(inventario.get(rowIndex));
        return super.isCellEditable(rowIndex, columnIndex);//To change body of generated methods, choose Tools | Templates.
    }

    public List<Inventario> getInventario() {
        return inventario;
    }

    public void setInventario(List<Inventario> inventario) {
        this.inventario = inventario;
    }     
}
