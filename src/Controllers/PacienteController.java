/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Configuracion.*;
import Models.Paciente;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;

/**
 *
 * @author matia
 */
public class PacienteController {

    private ConexionLocal conexion = new ConexionLocal();

    public boolean registrarNuevo(Paciente p) {

        try {
            this.conexion.conectar();
            String consulta = "insert into paciente (nombre, apellido, FechaNacimiento, idObraSocial, numeroAfiliado, documento, telefono, medicacion, pesoMaximo, pesoMinimo, pesoActual, actividadFisica) values (?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement st = this.conexion.getConexion().prepareStatement(consulta);
            st.setString(1, p.getNombre());
            st.setString(2, p.getApellido());
            st.setString(3, p.getDia() + "/" + p.getMes() + "/" + p.getAño());
            st.setInt(4, p.getIdOS());
            st.setString(5, p.getAfiliado());
            st.setString(6, p.getDocumento());
            st.setString(7, p.getTelefono());
            st.setString(8, p.getMedicacion());
            double pesomin = 999.999;
            double pesomax = 0.0;
            double pesoact = 0.0;
            BigDecimal pmin = BigDecimal.valueOf(pesomin);
            BigDecimal pmax = BigDecimal.valueOf(pesomax);
            BigDecimal pact = BigDecimal.valueOf(pesoact);
            st.setBigDecimal(9, pmax);
            st.setBigDecimal(10, pmin);
            st.setBigDecimal(11, pact);
            st.setString(12, p.getActFisica());
            st.execute();
            this.conexion.desconectar();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "error al registrar paciente..", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public int getId(String doc) {
        int id = -1;
        try {
            this.conexion.conectar();
            String consulta = "select idpaciente from paciente where documento = ?;";
            PreparedStatement st = this.conexion.getConexion().prepareStatement(consulta);
            st.setString(1, doc);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                id = rs.getInt("idPaciente");
            }
            this.conexion.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "error al tomar id de paciente..", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }

    public String getInformacion(int id) {

        String info = "";
        try {
            this.conexion.conectar();
            String consulta = "select * from paciente where idpaciente = ?;";
            PreparedStatement st = this.conexion.getConexion().prepareStatement(consulta);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                info += "Nombre y Apellido: " + rs.getString("nombre") + " " + rs.getString("apellido") + "\n";
                info += "Fecha de Nacimiento: " + rs.getString("FechaNacimiento") + "\n";
                ObraSocialController osc = new ObraSocialController();
                String obraSocial = osc.getObraSocial(rs.getInt("idObraSocial"));
                info += "Obra Social: " + obraSocial + "\n";
                info += "Nro de Afiliado: " + rs.getString("numeroAfiliado") + "\n";
                info += "Documento: " + rs.getString("documento") + "\n";
                info += "Telefono: " + rs.getString("telefono") + "\n";
                info += "Peso Maximo Alcanzado: " + rs.getBigDecimal("pesoMaximo") + "\n";
                info += "Peso Minimo Alcanzado: " + rs.getBigDecimal("pesoMinimo") + "\n";
                info += "Peso Actual: " + rs.getBigDecimal("pesoActual") + "\n";
                info += "Actividad Fisica: " + rs.getString("ActividadFisica") + "\n";
                info += "Medicacion: " + rs.getString("medicacion") + "\n";
            }
            this.conexion.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error al recopilar la informacion del paciente.", JOptionPane.ERROR_MESSAGE);
        }
        info += recuperarAntecedentes(id);
        return info;
    }

    public String recuperarAntecedentes(int id) {
        AntecedenteController ac = new AntecedenteController();
        String info = "Antecedentes Personales: ";
        info += ac.AntecedentesPersonales(id);
        info += "Antecedentes Familiares (Madre): ";
        info += ac.AntecedentesFamiliares(id, 4);
        info += "Antecedentes Familiares (Padre): ";
        info += ac.AntecedentesFamiliares(id, 5);
        info += "Antecedentes Familiares (Abuelo/a): ";
        info += ac.AntecedentesFamiliares(id, 6);
        return info;
    }

    public boolean existeDocumento(int dni) {
        try {
            this.conexion.conectar();
            String consulta = "select nombre from paciente where documento = ?;";
            PreparedStatement st = this.conexion.getConexion().prepareStatement(consulta);
            st.setString(1, String.valueOf(dni));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "error al verificar existencia de documento", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public void setPesos(int id, BigDecimal peso) {

        try {
            this.conexion.conectar();
            String consulta = "select pesoActual,PesoMinimo,pesoMaximo from paciente where idPaciente=?;";
            PreparedStatement st = this.conexion.getConexion().prepareStatement(consulta);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                
                consulta = "Update paciente set pesoActual = ? where idpaciente = ?;";
                    PreparedStatement st2 = this.conexion.getConexion().prepareStatement(consulta);
                    st2.setBigDecimal(1, peso);
                    st2.setInt(2, id);
                    st2.executeUpdate();
                
                BigDecimal minimo = rs.getBigDecimal("pesoMinimo");
                if (peso.compareTo(minimo) < 0){
                    consulta = "Update paciente set pesoMinimo = ? where idpaciente = ?;";
                    PreparedStatement st3 = this.conexion.getConexion().prepareStatement(consulta);
                    st3.setBigDecimal(1, peso);
                    st3.setInt(2, id);
                    st3.executeUpdate();
                }
                BigDecimal maximo = rs.getBigDecimal("pesoMaximo");
                if (peso.compareTo(maximo) > 0){
                    consulta = "Update paciente set pesoMaximo = ? where idpaciente = ?;";
                    PreparedStatement st4 = this.conexion.getConexion().prepareStatement(consulta);
                    st4.setBigDecimal(1, peso);
                    st4.setInt(2, id);
                    st4.executeUpdate();
                }
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "error al actualizar los pesos", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    public void actualizarActFisicaYMedicacion (int id, String af, String med){
        try{
            
            this.conexion.conectar();
            String consulta = "update paciente set actividadfisica = ? where idPaciente = ?;";
            PreparedStatement st = this.conexion.getConexion().prepareStatement(consulta);
            st.setString(1,af);
            st.setInt(2,id);
            st.executeUpdate();
            
            String consulta2 = "update paciente set medicacion = ? where idPaciente = ?;";
            PreparedStatement st2 = this.conexion.getConexion().prepareStatement(consulta2);
            st2.setString(1,med);
            st2.setInt(2,id);
            st2.executeUpdate();
            
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "error al actualizar act fisica y medicacion", JOptionPane.ERROR_MESSAGE);
        }
    }

}
