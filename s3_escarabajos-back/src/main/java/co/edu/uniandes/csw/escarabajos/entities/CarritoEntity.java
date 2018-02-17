/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Mateo
 */
@Entity
public class CarritoEntity implements Serializable{
    
    //----------------------------------------------------
    // Atributos
    //----------------------------------------------------
    
    @Id
    private Long id;
    
    private Double precioTotal;
    
    
    //----------------------------------------------------
    // Getters y Setters
    //----------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }
    
    
    
}
