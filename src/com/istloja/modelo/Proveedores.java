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
public class Proveedores {
    private int idProveedores;
    private String ruc;
    private String RazonSocial;
    private String TipoActividad;
    private String NombreRepresentanteLegal;
    private String ApellidoRepresentanteLegal;
    private String telefono;
    private String correo;

    public Proveedores() {
    }

    public Proveedores(int idProveedores, String ruc, String RazonSocial, String TipoActividad, String NombreRepresentanteLegal, String ApellidoRepresentanteLegal, String telefono, String correo) {
        this.idProveedores = idProveedores;
        this.ruc = ruc;
        this.RazonSocial = RazonSocial;
        this.TipoActividad = TipoActividad;
        this.NombreRepresentanteLegal = NombreRepresentanteLegal;
        this.ApellidoRepresentanteLegal = ApellidoRepresentanteLegal;
        this.telefono = telefono;
        this.correo = correo;
    }

   
    
    

    public int getIdProveedores() {
        return idProveedores;
    }

    public void setIdProveedores(int idProveedores) {
        this.idProveedores = idProveedores;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String RazonSocial) {
        this.RazonSocial = RazonSocial;
    }

    public String getTipoActividad() {
        return TipoActividad;
    }

    public void setTipoActividad(String TipoActividad) {
        this.TipoActividad = TipoActividad;
    }

    public String getNombreRepresentanteLegal() {
        return NombreRepresentanteLegal;
    }

    public void setNombreRepresentanteLegal(String NombreRepresentanteLegal) {
        this.NombreRepresentanteLegal = NombreRepresentanteLegal;
    }

    public String getApellidoRepresentanteLegal() {
        return ApellidoRepresentanteLegal;
    }

    public void setApellidoRepresentanteLegal(String ApellidoRepresentanteLegal) {
        this.ApellidoRepresentanteLegal = ApellidoRepresentanteLegal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Proveedores{" + "idProveedores=" + idProveedores + ", ruc=" + ruc + ", RazonSocial=" + RazonSocial + ", TipoActividad=" + TipoActividad + ", NombreRepresentanteLegal=" + NombreRepresentanteLegal + ", ApellidoRepresentanteLegal=" + ApellidoRepresentanteLegal + ", telefono=" + telefono + ", correo=" + correo + '}';
    }
    
    
    
}
