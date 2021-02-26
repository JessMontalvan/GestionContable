/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.controlador;

import com.istloja.conexion.Conexion;
import com.istloja.modelo.Proveedores;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jssmontalvan
 */
public class Proveedoresbd {
    
    public boolean registrarProveedor(Proveedores proveedor) {
        boolean registrar = false;
        //Interfaz de acceso a la base de datos
        Statement stm = null;
        //Conexion con la base de datos.
        Connection con = null;
        //INSERT INTO `ejercicio`.`persona` (`idpersona`, `cedula`, `nombres`, `apellidos`, `direccion`, `correo`, `telefono`) VALUES ('1', '1104268899', 'John', 'Solano', 'Loja', 'jpsolanoc@gmail.com', '072587392');
        String sql = "INSERT INTO `ejercicio`.`proveedores` (`ruc`, `razon_social`, `tipo_actividad`, `nombre_representante_legal`, `apellido_representante_legal`, `telefono`, `correo`) VALUES ('"+proveedor.getRuc()+"', '"+proveedor.getRazonSocial()+"', '"+proveedor.getTipoActividad()+"', '"+proveedor.getNombreRepresentanteLegal()+"', '"+proveedor.getApellidoRepresentanteLegal()+"', '"+proveedor.getTelefonoProveedor()+"', '"+proveedor.getCorreoProveedor()+"');";
        try {
            //Es una instancia de la conexion previamente creada.
            Conexion conexion = new Conexion();
            con = conexion.conectarBaseDatos();
            stm = con.createStatement();
            stm.execute(sql);
            registrar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return registrar;
    }
    //ACtualizar la persona e la base de datos.
    public boolean actualizarProveedores(Proveedores proveedor) {
        // Conexion con la base de datos.
        Connection connect = null;
        //Interfaz de acceso a la base de datos.
        Statement stm = null;
        // retorno del metodo cuando se realice la actualizacion
        boolean actualizar = false;
        //Contatenando la opcion de actualizacion
        String sql = "UPDATE `ejercicio`.`proveedores` SET `ruc` = '"+proveedor.getRuc()+"', `razon_social` = '"+proveedor.getRazonSocial()+"', `tipo_actividad` = '"+proveedor.getTipoActividad()+"', `nombre_representante_legal` = '"+proveedor.getNombreRepresentanteLegal()+"', `apellido_representante_legal` = '"+proveedor.getApellidoRepresentanteLegal()+"', `telefono_proveedor` = '"+proveedor.getTelefonoProveedor()+"', `correo_proveedor` = '"+proveedor.getCorreoProveedor()+"' WHERE (`idproveedores` = '"+proveedor.getIdProveedores()+"');";
        try {
            Conexion con = new Conexion();
            connect = con.conectarBaseDatos();
            //Puente entre la conexion y el codigo
            stm = connect.createStatement();
            stm.execute(sql);
            actualizar = true;
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
        return actualizar;
    }
    //Eliminar una persona en base al ID de la persona seleccionada de la base de datos.
    public boolean eliminarProveedores(Proveedores proveedor) {
        Connection connect = null;
        Statement stm = null;
        boolean eliminar = false;
        String sql = "DELETE FROM `ejercicio`.`proveedores` WHERE (`idproveedores` = '"+proveedor.getIdProveedores()+"');";
        try {
            connect = new Conexion().conectarBaseDatos();
            stm = connect.createStatement();
            stm.execute(sql);
            eliminar = true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return eliminar;
    }
    
     //Sirve para traer todos los registros de persona de la base de datos 
    public List<Proveedores> obtenerProveedores() {
        
        String sql = "SELECT * FROM ejercicio.proveedores;";
        List<Proveedores> listaProveedores = new ArrayList<Proveedores>();
        try {
             Connection co = new Conexion().conectarBaseDatos();
            Statement stm = co.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Proveedores c = new Proveedores();
                c.setIdProveedores(rs.getString(1));
                c.setRuc(rs.getString(2));
                c.setRazonSocial(rs.getString(3));
                c.setTipoActividad(rs.getString(4));
                c.setNombreRepresentanteLegal(rs.getString(5));
                c.setApellidoRepresentanteLegal(rs.getString(6));
                c.setTelefonoProveedor(rs.getString(7));
                c.setCorreoProveedor(rs.getString(8));
                
                listaProveedores.add(c);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:"+ e.getMessage());
        }

        return listaProveedores;
    }
    public List<Proveedores> getProveedorRuc (String ruc) {

        List<Proveedores> ProveedorEncontrado = new ArrayList<>();
        String sql = "SELECT * FROM ejercicio.proveedores WHERE ruc LIKE \"%" + ruc + "%\"";
        try {
            
            Connection co = new Conexion().conectarBaseDatos();
            Statement stm = co.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Proveedores c = new Proveedores();
                c.setRuc(rs.getString(1));
                c.setRazonSocial(rs.getString(2));
                c.setTipoActividad(rs.getString(3));
                c.setNombreRepresentanteLegal(rs.getString(4));
                c.setApellidoRepresentanteLegal(rs.getString(5));
                c.setTelefonoProveedor(rs.getString(6));
                c.setCorreoProveedor(rs.getString(7));
                ProveedorEncontrado.add(c);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
        return ProveedorEncontrado;
    }

    public List<Proveedores> getProveedorRazonSocial (String RazonSocial) {

        List<Proveedores> ProveedorEncontrado = new ArrayList<>();
        String sql = "SELECT * FROM ejercicio.proveedores WHERE razon_social LIKE \"%" + RazonSocial + "%\"";
        try {
           Connection co = new Conexion().conectarBaseDatos();
            Statement stm = co.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Proveedores c = new Proveedores();
                c.setRuc(rs.getString(1));
                c.setRazonSocial(rs.getString(2));
                c.setTipoActividad(rs.getString(3));
                c.setNombreRepresentanteLegal(rs.getString(4));
                c.setApellidoRepresentanteLegal(rs.getString(5));
                c.setTelefonoProveedor(rs.getString(6));
                c.setCorreoProveedor(rs.getString(7));
                ProveedorEncontrado.add(c);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
        return ProveedorEncontrado;
    }

    public List<Proveedores> getTipoActividad(String TipoActividad) {

        List<Proveedores> ProveedorEncontrado = new ArrayList<>();
        String sql = "SELECT * FROM ejercicio.proveedores WHERE tipo_actividad LIKE \"%" + TipoActividad + "%\"";
        try {
            Connection co = new Conexion().conectarBaseDatos();
            Statement stm = co.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Proveedores c = new Proveedores();
                c.setRuc(rs.getString(1));
                c.setRazonSocial(rs.getString(2));
                c.setTipoActividad(rs.getString(3));
                c.setNombreRepresentanteLegal(rs.getString(4));
                c.setApellidoRepresentanteLegal(rs.getString(5));
                c.setTelefonoProveedor(rs.getString(6));
                c.setCorreoProveedor(rs.getString(7));
                ProveedorEncontrado.add(c);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
        return ProveedorEncontrado;
    }

    public List<Proveedores> getNombreRepresentante (String NombreRepresentante) {

        List<Proveedores> ProveedorEncontrado = new ArrayList<>();
        String sql = "SELECT * FROM ejercicio.proveedores WHERE nombre_representante_legal LIKE \"%" + NombreRepresentante + "%\"";
        try {
            Connection co = new Conexion().conectarBaseDatos();
            Statement stm = co.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Proveedores c = new Proveedores();
               c.setRuc(rs.getString(1));
                c.setRazonSocial(rs.getString(2));
                c.setTipoActividad(rs.getString(3));
                c.setNombreRepresentanteLegal(rs.getString(4));
                c.setApellidoRepresentanteLegal(rs.getString(5));
                c.setTelefonoProveedor(rs.getString(6));
                c.setCorreoProveedor(rs.getString(7));
                ProveedorEncontrado.add(c);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
        return ProveedorEncontrado;
    }

    public List<Proveedores> getApellidoRepresentante(String ApellidoRepresentante) {
        
        List<Proveedores> ProveedorEncontrado = new ArrayList<>();
        String sql = "SELECT * FROM ejercicio.proveedores WHERE apellido_representante_legal LIKE \"%" + ApellidoRepresentante + "%\"";
        try {
            Connection co = new Conexion().conectarBaseDatos();
            Statement stm = co.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Proveedores c = new Proveedores();
                c.setRuc(rs.getString(1));
                c.setRazonSocial(rs.getString(2));
                c.setTipoActividad(rs.getString(3));
                c.setNombreRepresentanteLegal(rs.getString(4));
                c.setApellidoRepresentanteLegal(rs.getString(5));
                c.setTelefonoProveedor(rs.getString(6));
                c.setCorreoProveedor(rs.getString(7));
                ProveedorEncontrado.add(c);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
        return ProveedorEncontrado;
    }

    public List<Proveedores> getTelefonoProveedor(String TelefonoProveedor) {
        List<Proveedores> ProveedorEncontrado = new ArrayList<>();
        String sql = "SELECT * FROM ejercicio.proveedores WHERE telefono_proveedor LIKE \"%" + TelefonoProveedor + "%\"";
        try {
            Connection co = new Conexion().conectarBaseDatos();
            Statement stm = co.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Proveedores c = new Proveedores();
                c.setRuc(rs.getString(1));
                c.setRazonSocial(rs.getString(2));
                c.setTipoActividad(rs.getString(3));
                c.setNombreRepresentanteLegal(rs.getString(4));
                c.setApellidoRepresentanteLegal(rs.getString(5));
                c.setTelefonoProveedor(rs.getString(6));
                c.setCorreoProveedor(rs.getString(7));
                ProveedorEncontrado.add(c);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
        return ProveedorEncontrado;
    }
     public List<Proveedores> getCorreoProveedor(String CorreoProveedor) {
        List<Proveedores> ProveedorEncontrado = new ArrayList<>();
        String sql = "SELECT * FROM ejercicio.proveedores WHERE correo_proveedor LIKE \"%" + CorreoProveedor + "%\"";
        try {
            Connection co = new Conexion().conectarBaseDatos();
            Statement stm = co.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Proveedores c = new Proveedores();
                c.setRuc(rs.getString(1));
                c.setRazonSocial(rs.getString(2));
                c.setTipoActividad(rs.getString(3));
                c.setNombreRepresentanteLegal(rs.getString(4));
                c.setApellidoRepresentanteLegal(rs.getString(5));
                c.setTelefonoProveedor(rs.getString(6));
                c.setCorreoProveedor(rs.getString(7));
                ProveedorEncontrado.add(c);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
        return ProveedorEncontrado;
    }
}
