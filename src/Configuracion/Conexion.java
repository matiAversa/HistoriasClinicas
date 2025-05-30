/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Configuracion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matia
 */
public class Conexion {

    private Connection conexion;
    private String cadenaConexion, usuarioDB, passwordDB;

    public Conexion(String cadenaConexion, String usuarioDB, String passwordDB) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.cadenaConexion = cadenaConexion;
            this.usuarioDB = usuarioDB;
            this.passwordDB = passwordDB;
            this.conexion = DriverManager.getConnection(this.cadenaConexion, this.usuarioDB, this.passwordDB);
            System.out.println("Conexion Establecida correctamente con la DB...");
            
        }catch(ClassNotFoundException | SQLException ex){
            
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    public void StartConexion(){
        try{
            this.conexion = DriverManager.getConnection(this.cadenaConexion, this.usuarioDB, this.passwordDB);
            System.out.println("Conectado a la DB...");
        }catch(SQLException ex){
            
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    public void CloseConexion (){
        try{
            this.conexion.close();
            System.out.println("Desconectado de la DB...");
            
        }catch(SQLException ex){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getConexion (){
        return this.conexion;
    }

}
