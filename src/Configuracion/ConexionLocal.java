/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matia
 */
public class ConexionLocal{

    private String url = "jdbc:mysql://localhost/database-HistoriasClinicas";
    private String usuario = "root";
    private String clave = "passsqlnote";
    private Connection conexion;

    public void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conexion = DriverManager.getConnection(this.url, this.usuario, this.clave);
            System.out.println("Conectado a la base de datos local...");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConexionLocal.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void desconectar() {
        try {
            this.conexion.close();
            System.out.println("Desconectado de la base de datos local...");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionLocal.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    public Connection getConexion() {
        /*Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(this.url, this.usuario, this.clave);
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }*/
        return conexion;
    }


    public boolean testearConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                System.out.println("Conexion abierta...");
                return true;
            } else {
                return false;
            }
        }
    catch (SQLException ex) {
        Logger.getLogger(ConexionLocal.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    
    }
    
    }
}
