/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author matia
 */
public class Consulta {

    
    private int id;
    private BigDecimal peso;
    private BigDecimal circunferencia;
    private BigDecimal MasaCorporal;
    private BigDecimal grasaCorporal;
    private BigDecimal altura;
    private String DatosDeConsulta;
    private String ExamenFisico;
    private List<PruebaLaboratorio> listaPruebasLab;
    private String fecha;

    public Consulta(BigDecimal peso, BigDecimal circunferencia, BigDecimal MasaCorporal, BigDecimal grasaCorporal, BigDecimal altura, String DatosDeConsulta) {
        this.peso = peso;
        this.circunferencia = circunferencia;
        this.MasaCorporal = MasaCorporal;
        this.grasaCorporal = grasaCorporal;
        this.altura = altura;
        this.DatosDeConsulta = DatosDeConsulta;
    }

    public String devuelveInfo() {
        String info = "";
        info += "Peso de la Consulta: " + this.peso + "\n";
        info += "Circunferencia: " + this.circunferencia + "\n";
        info += "Indice de Masa Corporal: " + this.MasaCorporal + "\n";
        info += "Grasa Corporal: " + this.grasaCorporal + "\n";
        info += "Altura: " + this.altura + "\n";
        info += "Examen Fisico: "+ this.ExamenFisico + "\n";
        info += "Datos de la Consulta: " + this.DatosDeConsulta + "\n";
        if (this.listaPruebasLab != null) {
            info += "Pruebas de laboratorio: "+"\n";
            for (PruebaLaboratorio p : this.listaPruebasLab) {
                info += p.getNombre() + " - "+ p.getValor() +". \n";
            }
        }else{
                    info+="sin Pruebas de Laboratorio.";
            }
        return info;
    }
    
    @Override
    public String toString (){
        return this.fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setListaPruebasLab (List <PruebaLaboratorio> l){
        this.listaPruebasLab = l;
    }
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getExamenFisico() {
        return ExamenFisico;
    }

    public void setExamenFisico(String ExamenFisico) {
        this.ExamenFisico = ExamenFisico;
    }

    
    
    public void addPrueba(PruebaLaboratorio p) {

        this.listaPruebasLab.add(p);

    }

    public List<PruebaLaboratorio> getListaPruebasLab() {
        return this.listaPruebasLab;
    }

    public String getDatosDeConsulta() {
        return DatosDeConsulta;
    }

    public void setDatosDeConsulta(String DatosDeConsulta) {
        this.DatosDeConsulta = DatosDeConsulta;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getCircunferencia() {
        return circunferencia;
    }

    public void setCircunferencia(BigDecimal circunferencia) {
        this.circunferencia = circunferencia;
    }

    public BigDecimal getMasaCorporal() {
        return MasaCorporal;
    }

    public void setMasaCorporal(BigDecimal MasaCorporal) {
        this.MasaCorporal = MasaCorporal;
    }

    public BigDecimal getGrasaCorporal() {
        return grasaCorporal;
    }

    public void setGrasaCorporal(BigDecimal grasaCorporal) {
        this.grasaCorporal = grasaCorporal;
    }

    public BigDecimal getAltura() {
        return altura;
    }

    public void setAltura(BigDecimal altura) {
        this.altura = altura;
    }

}
