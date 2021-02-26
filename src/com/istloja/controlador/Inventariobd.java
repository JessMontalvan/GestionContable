/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.controlador;

import com.istloja.conexion.Conexion;
import com.istloja.modelo.Inventario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author jssmontalvan
 */
public class Inventariobd {
     PreparedStatement ps = null;
    Connection con = null;
    Statement stm = null;
    ResultSet rs = null;
    
    public boolean crearProducto(Inventario inventario) {
        boolean registrar = false;

        String sql = "INSERT INTO inventario ( codigo_pro, descripcion, precio_compra, precio_venta, can_productos) VALUES('" + String.valueOf(inventario.getIdInventario()) + "', '" + inventario.getCodigoProducto() + "', '" + inventario.getDescripcion() + "', '" + inventario.getPrecioCompra() + "', '" + inventario.getPrecioVenta() + "', '" + inventario.getCantidadProducto() + "')";
        try {
            Conexion conexion = new Conexion();
            con = conexion.conectarBaseDatos();
            stm = con.createStatement();
            stm.execute(sql);
            registrar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
            e.printStackTrace();
        }
        return registrar;
    }

    //Método para actualizar, modificar o editar un producto
    public boolean actualizarProducto(Inventario inventario) {
        boolean registrar = false;

        String sql = "UPDATE inventario SET codigo_pro = '" + inventario.getCodigoProducto() + "', descripcion = '" + inventario.getDescripcion() + "',precio_compra = '" + inventario.getPrecioCompra() + "', precio_venta= '" + inventario.getPrecioVenta() + "', can_productos = '" + inventario.getCantidadProducto() + "'WHERE idinventario =" + String.valueOf(inventario.getIdInventario());
        try {
           Conexion conexion = new Conexion();
            con = conexion.conectarBaseDatos();
            stm = con.createStatement();
            stm.execute(sql);
            registrar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
            e.printStackTrace();
        }
        return registrar;
    }

    //Método para quitar o eliminar un producto
    public boolean eliminarProducto(Inventario inventario) {
        boolean registrar = false;

        String sql = "DELETE FROM inventario WHERE idinventario=" + String.valueOf(inventario.getIdInventario());
        try {
            Conexion conexion = new Conexion();
            con = conexion.conectarBaseDatos();
            stm = con.createStatement();
            stm.execute(sql);
            registrar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
            e.printStackTrace();
        }
        return registrar;
    }

    //Método traer a todos los productos
    
    public List<Inventario> obtenerProductos() {
        
        String sql = "SELECT * FROM ejercicio.inventario;";
        List<Inventario> listaInventario = new ArrayList<Inventario>();
        try {
             Connection con = new Conexion().conectarBaseDatos();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                  Inventario p = new Inventario();
                p.setIdInventario(rs.getString(1));
                p.setCodigoProducto(rs.getString(2));
                p.setDescripcion(rs.getString(3));
                p.setPrecioCompra(rs.getString(4));
                p.setPrecioVenta(rs.getString(5));
                p.setCantidadProducto(rs.getString(6));
                listaInventario.add(p);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error:"+ e.getMessage());
        }

        return listaInventario;
    }
}
