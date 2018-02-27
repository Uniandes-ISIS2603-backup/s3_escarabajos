/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.FotoEntity;

/**
 *
 * @author af.varon
 */
public class FotoDTO {
    
    private long id;
    private String descripcion;

    FotoDTO(){
        
    }
    FotoDTO(FotoEntity entityFoto) {
        this.id = entityFoto.getId();
        this.descripcion = entityFoto.getDescripcion();
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
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
    
     /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public FotoEntity toEntity() {
       FotoEntity entity = new FotoEntity();
       entity.setId(this.getId());
       entity.setDescripcion(this.getDescripcion());
        return entity;
    }
    
}
