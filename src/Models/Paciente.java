/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author matia
 */
public class Paciente {
    
    private String nombre, apellido, telefono,documento, afiliado, actFisica, medicacion, dia,mes,año;
    private int id, idOS;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Paciente(String nombre, String apellido, String afiliado, String telefono, String  dia, String  mes, String  año, int  idOS) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.afiliado = afiliado;
        this.dia = dia;
        this.mes = mes;
        this.año = año;
        this.idOS = idOS;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getMedicacion() {
        return medicacion;
    }

    public void setMedicacion(String medicacion) {
        this.medicacion = medicacion;
    }

    public String getActFisica() {
        return actFisica;
    }

    public void setActFisica(String actFisica) {
        this.actFisica = actFisica;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(String afiliado) {
        this.afiliado = afiliado;
    }

    public String  getDia() {
        return dia;
    }

    public void setDia(String  dia) {
        this.dia = dia;
    }

    public String  getMes() {
        return mes;
    }

    public void setMes(String  mes) {
        this.mes = mes;
    }

    public String  getAño() {
        return año;
    }

    public void setAño(String  año) {
        this.año = año;
    }

    public int  getIdOS() {
        return idOS;
    }

    public void setIdOS(int  idOS) {
        this.idOS = idOS;
    }
    
    
    
}
