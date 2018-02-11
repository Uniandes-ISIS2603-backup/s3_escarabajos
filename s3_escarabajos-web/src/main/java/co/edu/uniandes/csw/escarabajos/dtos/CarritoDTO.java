/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

/**
 *
 * @author Mateo
 */
public class CarritoDTO {
    
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    
    /**
     * modela el precio de todos los itemps a comprar
     */
    private double precioTotal;
    
    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------
    
    public CarritoDTO(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    //-----------------------------------------------------------
    // Getters and Setters
    //-----------------------------------------------------------
    
    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
    
    
}
