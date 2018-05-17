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
 * @author Mateo
 */
@Entity
public class AccesorioEntity extends ItemEntity implements Serializable {

    /**
     * Descripcion del accesorio.
     */
    private String descripcion;

    /**
     * Devuelve la descripcion del accesorio.
     *
     * @return Descripcion del accesorio
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Modifica la descripcion del accesorio.
     *
     * @param descripcion Descripcion del accesorio
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
