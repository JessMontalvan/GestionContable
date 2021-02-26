/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.ModelPersona;

import com.istloja.modelo.Inventario;
import com.istloja.modelo.Persona;
import com.istloja.modelo.Proveedores;

/**
 *
 * @author jssmontalvan
 */
public interface ComunicacionVistasModelosTablas {
     void clickPersona (Persona p);
     void clickProveedores (Proveedores p);
     void clickInventario (Inventario p);
}
