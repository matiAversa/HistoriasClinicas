/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author matia
 */
public class PruebaLaboratorio {
    private String nombre;
    private double valor;

    public PruebaLaboratorio(String nombre, double valor) {
        this.nombre = nombre;
        this.valor = valor;
        
        
    }

    @Override
    public String toString() {
        return nombre + ", valor=" + valor ;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
    
}
