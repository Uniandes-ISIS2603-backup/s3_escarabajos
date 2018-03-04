/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.entities;

import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Andres
 */
@Entity
public class AccesorioEntity extends ItemEntity implements Serializable{
    
    /**
     * Variable que representa el tipo de accesorio (Casco, Silla,...).
     */
    private String tipoAcessorio;
    
    /**
     * Descripcion del accesorio.
     */
    private String descripcion;
     

    /**
     * @return the tipoAcessorio
     */
    public String getTipoAcessorio() {
        return tipoAcessorio;
    }

    /**
     * @param tipoAcessorio the tipoAcessorio to set
     */
    public void setTipoAcessorio(String tipoAcessorio) {
        this.tipoAcessorio = tipoAcessorio;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
