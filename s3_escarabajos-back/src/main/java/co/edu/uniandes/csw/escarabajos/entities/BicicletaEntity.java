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
     * Atributo que representa la categoria a la que pertenece esta bicicleta.
     */
    private String categoria;
    /**
     * Atributo que permite diferenciar si la bicicleta es usada o no.
     */
    private Boolean usada;
    /**
     * Devuelve la categoria
     * @return categoria
     */
    public String getCategoria() {
        return categoria;
    }
    /**
     * Modifica la categoria
     * @param categoria nueva categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
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
