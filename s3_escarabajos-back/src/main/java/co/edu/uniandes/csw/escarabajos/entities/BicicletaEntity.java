/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
/**
 *
 * @author c.santacruza
 */
@Entity
public class BicicletaEntity extends ItemEntity implements Serializable  {
    /**
     * Atributo que permite diferenciar si la bicicleta es usada o no.
     */
    private Boolean usada;
    
    /**
     * Devuelve usada
     * @return usada
     */
    public Boolean getUsada() {
        return usada;
    }
    /**
     * Modifica usada
     * @param usada  nuevo usada
     */
    public void setUsada(Boolean usada) {
        this.usada = usada;
    }  
}
