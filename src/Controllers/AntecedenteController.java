/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;
import Configuracion.*;
import Models.Antecedente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author matia
 */
public class AntecedenteController {
    
    private ConexionLocal conexion = new ConexionLocal();
    
    public List<Antecedente> getAntecedentes (){
        List <Antecedente> lista = new ArrayList();
        
        try {
            this.conexion.conectar();
            String consulta = "select * from antecedente;";
            PreparedStatement st = this.conexion.getConexion().prepareStatement(consulta);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()){
                int id = rs.getInt("idantecedente");
                boolean personal = rs.getBoolean("espersonal");
                String nombre = rs.getString("nombreAntecedente");
                lista.add(new Antecedente(id, nombre, personal));
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex, "error al conseguir los antecedentes", JOptionPane.ERROR_MESSAGE);
        }
        
        return lista;
    }
    
    public void AgregarAntecedentesPersonales (List<Integer> lista, int id){
        
        try{
            this.conexion.conectar();
            String consulta = "insert into antecedentePersonal (idAntecedente, idPaciente) values (?, ?);";
            PreparedStatement st = this.conexion.getConexion().prepareStatement(consulta);
            for (Integer idA : lista){
                st.setInt(1,idA);
                st.setInt(2, id);
                st.execute();
            }
            this.conexion.desconectar();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex, "error al registrar los antecedentesPersonales", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void AgregarAntecedentesFamiliares (List<Integer> lista, int idF, int id){
        
        try{
            this.conexion.conectar();
            String consulta = "insert into antecedenteFamiliar (idAntecedente, idPaciente, idFamiliar) values (?, ?, ?);";
            PreparedStatement st = this.conexion.getConexion().prepareStatement(consulta);
            for (Integer idA : lista){
                st.setInt(1,idA);
                st.setInt(2, id);
                st.setInt(3, idF);
                st.execute();
            }
            this.conexion.desconectar();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex, "error al registrar los antecedentes Familiares", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public String AntecedentesPersonales (int id){
        String info="";
        try{
            this.conexion.conectar();
            String consulta = "select a.nombreAntecedente "
                    + "from antecedente as a inner join antecedentePersonal as ap on (a.idAntecedente=ap.idAntecedente) "
                    + "where ap.idPaciente = ?;";
            PreparedStatement st = this.conexion.getConexion().prepareStatement(consulta);
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()){
                info+=rs.getString("nombreAntecedente")+", ";
            }
            info+="\n";
            this.conexion.desconectar();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, ex, "Error al recuperar los antecedentes Personales del paciente.", JOptionPane.ERROR_MESSAGE);
        }
        return info;
    }
    
    public String AntecedentesFamiliares (int id, int idf){
        String info="";
        try{
            this.conexion.conectar();
            String consulta = "select a.nombreAntecedente "
                    + "from antecedente as a inner join antecedenteFamiliar as af on (a.idAntecedente=af.idAntecedente) "
                    + "where af.idPaciente = ? and af.idFamiliar = ?;";
            PreparedStatement st = this.conexion.getConexion().prepareStatement(consulta);
            st.setInt(1,id);
            st.setInt(2,idf);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()){
                info+=rs.getString("nombreAntecedente")+", ";
            }
            info+="\n";
            this.conexion.desconectar();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, ex, "Error al recuperar los antecedentes Familiares del paciente.", JOptionPane.ERROR_MESSAGE);
        }
        return info;
    }
    
}
