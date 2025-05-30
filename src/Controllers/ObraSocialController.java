/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Configuracion.*;
import Models.ObraSocial;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.SQLException;

/**
 *
 * @author matia
 */
public class ObraSocialController {

    private ConexionLocal conexion = new ConexionLocal();

    public ArrayList<ObraSocial> getTodas() {
        ArrayList<ObraSocial> listaOS = new ArrayList();
        try {
            this.conexion.conectar();
            String consulta = "select o.idObraSocial, o.nombreObraSocial from obrasocial as o;";
            PreparedStatement st = this.conexion.getConexion().prepareStatement(consulta);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombreObraSocial");
                int id = rs.getInt("idobraSocial");
                listaOS.add(new ObraSocial(id, nombre));

            }
            this.conexion.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "error al tomar las ObrasSociales (getTodas)", JOptionPane.ERROR_MESSAGE);

        }
        return listaOS;
    }

    public int nuevaObraSocial(String nombre) {

        try {
            this.conexion.conectar();
            String consulta = "insert into obrasocial (nombreObraSocial) values (?);";
            PreparedStatement st = this.conexion.getConexion().prepareStatement(consulta, java.sql.Statement.RETURN_GENERATED_KEYS);
            st.setString(1, nombre);
            st.execute();

            ResultSet rs = st.getGeneratedKeys();
            int aux = -1;
            if (rs.next()) {
                aux = rs.getInt("idObraSocial");
            }
            this.conexion.desconectar();
            return aux;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "error al crear una nueva ObraSocial", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public String getObraSocial(int id) {
        try {
            this.conexion.conectar();
            String consulta = "select obraSocial.nombreObraSocial from obraSocial where obrasocial.idObraSocial = ?;";
            PreparedStatement st = this.conexion.getConexion().prepareStatement(consulta);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            String nombre="";
            if (rs.next()) {
                nombre = rs.getString("nombreObrasocial");
            }
            this.conexion.desconectar();
            return nombre;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error al recuperar la obra social del paciente.", JOptionPane.ERROR_MESSAGE);
            return "";
        }
    }

}
