/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.ModelPersona;

import com.istloja.modelo.Persona;
import java.util.List;
import javax.swing.event.EventListenerList;
import javax.swing.table.AbstractTableModel;
import com.istloja.vistas.GestionContable;


/**
 *
 * @author jssmontalvan
 */
public class ModelTablaPersona extends AbstractTableModel {
 public String[] m_colNames = {"CÉDULA", "NOMBRES", "APELLIDOS", "DIRECCIÓN", "CORREO", "TELÉFONO"};
    public List<Persona> personas;

    public ModelTablaPersona(List<Persona> personas) {
        this.personas = personas;
    }
    
    //Determina el número de filas que tengo en mi tabla
    @Override
    public int getRowCount() {
        return personas.size();
    }
    //Determina el número de columnas que tengo en mi tabla
    @Override
    public int getColumnCount() {
        return m_colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Persona persona = personas.get(rowIndex);
        switch(columnIndex){
            case 0:
                return persona.getCedula();
            case 1:
                return persona.getNombre();
            case 2:
                return persona.getApellidos();
            case 3:
                return persona.getDireccion();
            case 4:
                return persona.getCorreo();
            case 5:
                return persona.getTelefono();
          
        }
        return new String();
    }
    
    //Este método sirve para definir los nombres de las columnas
    @Override
    public String getColumnName(int column) {
        return m_colNames[column];
    }
         
         
}
