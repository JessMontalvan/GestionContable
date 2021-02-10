/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author JSS JIMENEZ
 */
public class Conexion {
    private Connection conexion;
    //declaramos los dadtos de conexion
    private static final String user = "root";
    private static final String pass = "jssnoe02";
    private static final String url = "jdbc:mysql://localhost:3306/ejerciciobd? useUnicode=true&use"
            + " JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public Connection conectarBaseDatos() {
        //reseteamos null a la conexion de la BD
        conexion = null;
        try {
            //conexion a la BD
            conexion = (Connection) DriverManager.getConnection(url, user, pass);
            //comprobacion
            if (conexion != null) {
                System.out.println("Conexion exitosa");
            }
            //mensaje de error
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return conexion;
    }
}
