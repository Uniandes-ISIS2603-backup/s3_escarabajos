/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link ItemDTO} para manejar las relaciones entre
 * los Item JSON y otros DTOs. Para conocer el
 * contenido de un item vaya a la documentacion de {@link ItemDTO}
 * @author Andres
 */
public class ItemDetailDTO extends ItemDTO {
    
    /*
    * Relación a un modelo
     */
    private ModeloDTO modelo;
    
     public ItemDetailDTO() {
        super();
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de la cual se construye el DTO
     */
    public ItemDetailDTO(ItemEntity entity) {
        super(entity);
        if (entity.getModelo()!= null) {
            this.modelo = new ModeloDTO(entity.getModelo());
        } else {
            entity.setModelo(null);
        }
    }
    
    /**
     * Transformar el DTO a una entidad
     * @return La entidad que representa el libro.
     */
    @Override
    public ItemEntity toEntity() {
        ItemEntity itemE = super.toEntity();
        if (this.getModelo() != null) {
            itemE.setModelo(this.getModelo().toEntity());
        }
        return itemE;
    }

    /**
     * @return the modelo
     */
    public ModeloDTO getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(ModeloDTO modelo) {
        this.modelo = modelo;
    }
}
