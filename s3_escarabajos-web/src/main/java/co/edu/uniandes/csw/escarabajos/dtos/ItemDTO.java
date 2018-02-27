/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;

import co.edu.uniandes.csw.escarabajos.entities.BicicletaEntity;
import co.edu.uniandes.csw.escarabajos.entities.FotoEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * ItemDTO Objeto de transferencia de datos de Accesorios. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente item: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "precio": number,
 *      "album": {"url":string,"url":String}
 * 
 *   }
 * </pre>
 * Por ejemplo un item se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "id": 123456,
 *      "precio": "30,000",
 *      "album": {"url":"url1","url":"url2"}
 *   }
 *
 * </pre>
 * @author Andres
 */
public abstract class ItemDTO {
 
    private Long id;
    private double precio;
    
    private Long modeloId;
    
    private List<FotoDTO> album;
    /**
     * Constructor por defecto
     */
    public ItemDTO() {
    }
    
     /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param entity: Es la entidad que se va a convertir a DTO
     */
    public ItemDTO(ItemEntity entity) {
       this.id = entity.getId();
       this.precio = entity.getPrecio();
       this.modeloId = entity.getModeloId();
       if (entity.getAlbum()!= null) {
            album = new ArrayList<>();
            for (FotoEntity entityFoto : entity.getAlbum()) {
               album.add(new FotoDTO(entityFoto));
            }
        }
    }
    public abstract ItemEntity toEntity();
      /**
     * Convertir DTO a Entity
     *
     * @param entity Es la entidad que se le van a asignarlos valores del DTO
     * @return Un Entity con los valores del DTO
     */
    public ItemEntity toEntity(ItemEntity entity) {
       entity.setId(this.getId());
       entity.setPrecio(this.getPrecio());
       entity.setModeloId(this.getModeloId());
        if (getAlbum()!= null) {
            List<FotoEntity> fotoEntity = new ArrayList<>();
            for (FotoDTO dtoFoto : getAlbum()) {
                fotoEntity.add(dtoFoto.toEntity());
            }
            entity.setAlbum(fotoEntity);
        }
        return entity;
    }
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }


    /**
     * @return the album
     */
    public List<FotoDTO> getAlbum() {
        return album;
    }

    /**
     * @param album the album to set
     */
    public void setAlbum(List<FotoDTO> album) {
        this.album = album;
    }

    /**
     * @return the modeloId
     */
    public long getModeloId() {
        return modeloId;
    }

    /**
     * @param modeloId the modeloId to set
     */
    public void setModeloId(long modeloId) {
        this.modeloId = modeloId;
    }

}