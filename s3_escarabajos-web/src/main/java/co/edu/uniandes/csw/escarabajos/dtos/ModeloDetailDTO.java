/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.escarabajos.dtos;
//import co.edu.uniandes.csw.escarabajos.entities.CalificacionEntity;
import co.edu.uniandes.csw.escarabajos.entities.ItemEntity;
import co.edu.uniandes.csw.escarabajos.entities.ModeloEntity;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase que extiende de {@link ModeloDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. 
 * @author Andres
 */
public class ModeloDetailDTO extends ModeloDTO
{
    /**
     * Lista que representa todos los items de un modelo
     */
    private List<ItemDTO> items;
    
    /**
     * Lista que representa todas las calficaciones de un modelo
     */
    private List<CalificacionDTO> calificaciones;
    
    /**
     * Constructor por defecto
     */
    public ModeloDetailDTO()
    {
    }
        /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de modelo a partir de la cual se construye el objeto
     */
    public ModeloDetailDTO(ModeloEntity entity) {
        super(entity);
        if (entity.getItems() != null) {
            //items = new ArrayList<>();
            for (ItemEntity entityItem : entity.getItems()) {
              //  items.add(new ItemDTO(entityItem));
            }
        }
        /*if (entity.getCalificaciones()!= null) {
            calificaciones = new ArrayList<>();
            for (CalificacionEntity entityCalificacion : entity.getCalificaciones()) {
                calificaciones.add(new CalificacionDTO(entityCalificacion));
            }
        }*/
    }
        /**
     * Transformar un DTO a un Entity
     *
     * @return  La entidad construida a partir del DTO.
     */
    public ModeloEntity toEntity()
    {
        ModeloEntity modelo = super.toEntity();
        if (getItems() != null) {
            List<ItemEntity> itemsEntity = new ArrayList<>();
            for (ItemDTO dtoItem : getItems()) {
              //  itemsEntity.add(dtoItem.toEntity());
            }
           // modelo.setItems(itemsEntity);
        }
       /* if (getCalificaciones() != null) {
            List<CalificacionEntity> calificacionesEntity = new ArrayList<>();
            for (CalificacionDTO dtoCalificacion : getCalificaciones()) {
                calificacionesEntity.add(dtoCalificacion.toEntity());
            }
            modelo.setCalificaciones(calificacionesEntity);
        }*/
        
        return modelo;
    }

    /**
     * @return the items
     */
    public List<ItemDTO> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    /**
     * @return the calificaciones
     */
    public List<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    /**
     * @param calificaciones the calificaciones to set
     */
    public void setCalificaciones(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }
    
}
