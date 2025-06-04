/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Configuracion.*;
import Models.Consulta;
import Models.PruebaLaboratorio;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matia
 */
public class ConsultaController {

    private ConexionLocal conexion = new ConexionLocal();

    public int agregarDatosConsulta(Consulta c, int id, String examenF) {

        try {
            this.conexion.conectar();
            String consulta = "insert into datosconsulta (Circunferencia,altura,grasaCorporal"
                    + ",indicemasaCorporal,examenfisico,pesoConsulta,datosConsulta) values (?,?,?,?,?,?,?);";
            PreparedStatement st = this.conexion.getConexion().prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            st.setBigDecimal(1, c.getCircunferencia());
            st.setBigDecimal(2, c.getAltura());
            st.setBigDecimal(3, c.getGrasaCorporal());
            st.setBigDecimal(4, c.getMasaCorporal());
            st.setString(5, examenF);
            st.setBigDecimal(6, c.getPeso());
            st.setString(7, c.getDatosDeConsulta());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            int idDatos = -999;
            if (rs.next()) {
                idDatos = rs.getInt(1);
            }
            this.conexion.desconectar();
            PacienteController pc = new PacienteController();
            pc.setPesos(id, c.getPeso());
            return idDatos;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "error al Crear los datos de la Consulta.", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public void crearPruebaLab(ArrayList<PruebaLaboratorio> lista, int idConsulta) {
        if (!lista.isEmpty()) {
            try {
                this.conexion.conectar();
                ResultSet rs;
                for (PruebaLaboratorio dato : lista) {
                    String consulta1 = "select iddatoLaboratorio from datoLaboratorio where nombreDato = ?;";
                    PreparedStatement st = this.conexion.getConexion().prepareStatement(consulta1);
                    st.setString(1, dato.getNombre());
                    rs = st.executeQuery();
                    if (rs.next()) {
                        int idDato = rs.getInt("iddatoLaboratorio");
                        String consulta = "insert into consultadatolab (idConsulta, idDatoLab, Valor) values (?, ?, ?);";
                        st = this.conexion.getConexion().prepareStatement(consulta);
                        st.setInt(1, idConsulta);
                        st.setInt(2, idDato);
                        st.setBigDecimal(3, new BigDecimal(dato.getValor()));
                        st.execute();
                    }
                }
                this.conexion.desconectar();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex, "error al Crear un dato de laboratorio.", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public int crearConsulta(boolean lab, int id, int idDatos) {

        try {
            this.conexion.conectar();
            String consulta = "insert into consulta (fechaconsulta, idpaciente, idDatosConsulta) values (?,?,?);";
            PreparedStatement st = this.conexion.getConexion().prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            LocalDate hoy = LocalDate.now();
            String fecha = hoy.getDayOfMonth() + "/" + hoy.getMonthValue() + "/" + hoy.getYear();
            st.setString(1, fecha);
            st.setInt(2, id);
            st.setInt(3, idDatos);
            st.execute();

            ResultSet rs = st.getGeneratedKeys();
            int idGenerado = -999;
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }
            this.conexion.desconectar();

            return idGenerado;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "error al Crear una consulta.", JOptionPane.ERROR_MESSAGE);
            return -999;
        }

    }

    public List<Consulta> getConsultas(int id) {

        List<Consulta> listaconsultas = new ArrayList<Consulta>();
        try {
            this.conexion.conectar();
            String consulta = "select * from Consulta as c inner join DatosConsulta as dc on (c.idDatosConsulta = dc.idDatosConsulta)"
                    + " where c.idPaciente = ?;";
            PreparedStatement st = this.conexion.getConexion().prepareStatement(consulta);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int idc = rs.getInt("idConsulta");
                BigDecimal circunferencia = rs.getBigDecimal("circunferencia") == null ? BigDecimal.ZERO : rs.getBigDecimal("circunferencia");
                BigDecimal altura = rs.getBigDecimal("altura") == null ? BigDecimal.ZERO : rs.getBigDecimal("altura");
                BigDecimal grasaCorporal = rs.getBigDecimal("grasaCorporal") == null ? BigDecimal.ZERO : rs.getBigDecimal("grasaCorporal");
                BigDecimal IndiceMasaCorporal = rs.getBigDecimal("indiceMasaCorporal") == null ? BigDecimal.ZERO : rs.getBigDecimal("indiceMasaCorporal");
                BigDecimal pesoConsulta = rs.getBigDecimal("pesoConsulta") == null ? BigDecimal.ZERO : rs.getBigDecimal("pesoConsulta");
                String examenFisico = rs.getString("examenFisico");
                String datosConsulta = rs.getString("datosConsulta");
                String fecha = rs.getString("fechaConsulta");

                Consulta c = new Consulta(pesoConsulta, circunferencia, IndiceMasaCorporal, grasaCorporal, altura, datosConsulta);
                c.setFecha(fecha);
                c.setExamenFisico(examenFisico);
                c.setId(idc);

                listaconsultas.add(c);
            }
            this.conexion.desconectar();
            return listaconsultas;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "error al recuperar los datos de todas las consulta.", JOptionPane.ERROR_MESSAGE);
            return null;

        }

    }

    public List<PruebaLaboratorio> getPruebasLab(int id) {

        List<PruebaLaboratorio> lista = new ArrayList();
        try {

            this.conexion.conectar();
            String consulta = "select d.nombreDato, cd.Valor "
                            + "from consultadatolab as cd "
                                + "inner join DatoLaboratorio as d on (cd.idDatoLab=d.idDatoLaboratorio) "
                            + "where idConsulta = ?;";
            PreparedStatement st = this.conexion.getConexion().prepareStatement(consulta);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lista.add(new PruebaLaboratorio(rs.getString("nombreDato"), Double.valueOf(String.valueOf(rs.getBigDecimal("valor")))));
            }
            this.conexion.desconectar();
            return lista;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "error al recuperar las pruebas de laboratorio de la consulta.", JOptionPane.ERROR_MESSAGE);
            return null;

        }

    }

}
