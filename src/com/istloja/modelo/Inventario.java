/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.modelo;

/**
 *
 * @author jssmontalvan
 */
public class Inventario {
    private String idInventario;
    private String codigoProducto;
    private String Descripcion;
    private String PrecioCompra;
    private String PrecioVenta;
    private String CantidadProducto;

    public Inventario() {
    }

    public Inventario(String idInventario, String codigoProducto, String Descripcion, String PrecioCompra, String PrecioVenta, String CantidadProducto) {
        this.idInventario = idInventario;
        this.codigoProducto = codigoProducto;
        this.Descripcion = Descripcion;
        this.PrecioCompra = PrecioCompra;
        this.PrecioVenta = PrecioVenta;
        this.CantidadProducto = CantidadProducto;
    }

    public String getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(String idInventario) {
        this.idInventario = idInventario;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getPrecioCompra() {
        return PrecioCompra;
    }

    public void setPrecioCompra(String PrecioCompra) {
        this.PrecioCompra = PrecioCompra;
    }

    public String getPrecioVenta() {
        return PrecioVenta;
    }

    public void setPrecioVenta(String PrecioVenta) {
        this.PrecioVenta = PrecioVenta;
    }

    public String getCantidadProducto() {
        return CantidadProducto;
    }

    public void setCantidadProducto(String CantidadProducto) {
        this.CantidadProducto = CantidadProducto;
    }

    @Override
    public String toString() {
        return "Inventario{" + "idInventario=" + idInventario + ", codigoProducto=" + codigoProducto + ", Descripcion=" + Descripcion + ", PrecioCompra=" + PrecioCompra + ", PrecioVenta=" + PrecioVenta + ", CantidadProducto=" + CantidadProducto + '}';
    }

   
   
    
}
